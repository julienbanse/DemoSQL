package sample.jbanse.demosql.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import sample.jbanse.demosql.data.controller.Repository
import sample.jbanse.demosql.data.controller.model.NewsListItem
import sample.jbanse.demosql.data.tools.AppSchedulers
import timber.log.Timber
import java.util.Date
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by julien on 08/11/2017.
 */
class MainViewModel
@Inject
constructor(private val repository: Repository, private val appSchedulers: AppSchedulers) : ViewModel() {

    var sortByDate = true
        set(value) {
            if (field != value) {
                field = value
                loadData()
            }
        }

    private val disposables = CompositeDisposable()

    private val newsList = MutableLiveData<List<NewsListItem>>()

    init {
        loadData()
    }

    private fun loadData() {
        if (sortByDate) loadNewsOrderByDate() else loadNewsOrderByPosition()
    }

    fun addItem(count: Int) {
        disposables.add(addItemOperation(count)
                .subscribeOn(appSchedulers.database)
                .observeOn(appSchedulers.ui)
                .subscribe({ Timber.i("item added") },
                        { Timber.e(it, "item add error") }))
    }

    private fun addItemOperation(count: Int): Single<Long> {
        return repository.insertNews("title $count", Date(), count)
    }

    fun addLater(delay: Long, count: Int) {
        disposables.add(
                Single.just(count)
                        .subscribeOn(appSchedulers.disk)
                        .delay(delay, TimeUnit.SECONDS)
                        .flatMap { addItemOperation(count) }
                        .observeOn(appSchedulers.ui)
                        .subscribe({ Timber.i("item added") },
                                { Timber.e(it, "item add error") })
        )
    }

    fun newsListLiveData(): LiveData<List<NewsListItem>> = newsList

    private fun loadNewsOrderByDate() {
        disposables.clear()
        disposables.add(repository.selectNewsOrderByDate()
                .subscribe({ newsList.postValue(it) },
                        { Timber.d(it, "news oder by date") })
        )
    }

    private fun loadNewsOrderByPosition() {
        disposables.clear()
        disposables.add(repository.selectNewsOrderByPosition()
                .subscribe({ newsList.postValue(it) },
                        { Timber.d(it, "news oder by date") }))
    }

    override fun onCleared() {
        Timber.d("onCleared")
        super.onCleared()
        disposables.clear()
    }
}