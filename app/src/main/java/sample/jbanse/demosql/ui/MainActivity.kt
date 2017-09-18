package sample.jbanse.demosql.ui

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import sample.jbanse.demosql.R


class MainActivity : DaggerAppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    //@Inject lateinit var repository: Repository

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
            else -> super.onOptionsItemSelected(item)
        }
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
//        compositeDisposable.add(
//                repository.selectNewsOrderByDate()
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe({ adapter.updateData(it) },
//                                { Log.e("MAIN", "observeList", it) }))
    }

    private fun addItem() {
//        compositeDisposable.add(
//                repository.insertNews("title ${adapter.itemCount}")
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe({ Log.i("MAIN", "item added") },
//                                { Log.e("MAIN", "item add error", it) })
//        )
    }

}
