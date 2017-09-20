package sample.jbanse.demosql.ui

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import sample.jbanse.demosql.R
import sample.jbanse.demosql.data.controller.Repository
import sample.jbanse.demosql.data.controller.model.NewsListItem
import java.util.Date
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity(), Observer<List<NewsListItem>> {

    private val SAVE_STATE_SORT_BY_DATE = "IS_SORT_BY_DATE"

    private var sortByDate = true

    private val compositeDisposable = CompositeDisposable()

    private var currentDisposable: Disposable? = null

    @Inject lateinit var repository: Repository

    private val recycler: RecyclerView by lazy { findViewById<RecyclerView>(R.id.mainRv) }

    private val adapter: NewsListAdapter by lazy { NewsListAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.addItem -> {
                addItem()
                true
            }
            R.id.modifySort -> {
                sortByDate = !sortByDate
                observeList()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putBoolean(SAVE_STATE_SORT_BY_DATE, sortByDate)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        sortByDate = savedInstanceState?.getBoolean(SAVE_STATE_SORT_BY_DATE, true) ?: true
    }

    override fun onStart() {
        super.onStart()
        observeList()
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    private fun observeList() {
        //remove previous disposable to listen only the current request.
        currentDisposable?.let { compositeDisposable.delete(it) }
        if (sortByDate) {
            repository.selectNewsOrderByDate()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this)
        } else {
            repository.selectNewsOrderByPosition()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this)
        }
    }

    private fun addItem() {
        compositeDisposable.add(
                repository.insertNews("title ${adapter.itemCount}", Date(), adapter.itemCount)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ Log.i("MAIN", "item added") },
                                { Log.e("MAIN", "item add error", it) })
        )
    }

    override fun onNext(t: List<NewsListItem>) {
        adapter.updateData(t)
    }

    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable) {
        compositeDisposable.add(d)
        currentDisposable = d
    }

    override fun onError(e: Throwable) {
        Log.e("MAIN", "observeList", e)
        Toast.makeText(this, "Error: " + e.message, Toast.LENGTH_LONG).show()
    }
}
