package sample.jbanse.demosql.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import sample.jbanse.demosql.data.controller.Repository
import sample.jbanse.demosql.data.controller.model.NewsListItem
import sample.jbanse.demosql.data.tools.AppSchedulers
import java.util.Date
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by julien on 08/11/2017.
 */
class MainViewModel
@Inject
constructor(private val repository: Repository, private val appSchedulers: AppSchedulers) : ViewModel() {

    companion object {
        private const val TAG = "MainViewModel"
    }

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
                .subscribe({ Log.i(TAG, "item added") },
                        { Log.e(TAG, "item add error", it) }))
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
                        .subscribe({ Log.i(TAG, "item added") },
                                { Log.e(TAG, "item add error", it) })
        )
    }

    fun newsListLiveData(): LiveData<List<NewsListItem>> = newsList

    private fun loadNewsOrderByDate() {
        disposables.clear()
        disposables.add(repository.selectNewsOrderByDate()
                .subscribe({ newsList.postValue(it) },
                        { Log.d(TAG, "news oder by date", it) })
        )
    }

    private fun loadNewsOrderByPosition() {
        disposables.clear()
        disposables.add(repository.selectNewsOrderByPosition()
                .subscribe({ newsList.postValue(it) },
                        { Log.d(TAG, "news oder by date", it) }))
    }

    override fun onCleared() {
        Log.d(TAG, "onCleared")
        super.onCleared()
        disposables.clear()
    }
}