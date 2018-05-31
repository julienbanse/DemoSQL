package sample.jbanse.demosql.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import dagger.android.support.DaggerAppCompatActivity
import sample.jbanse.demosql.R
import sample.jbanse.demosql.data.controller.model.NewsListItem
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    private val recycler: RecyclerView by lazy { findViewById<RecyclerView>(R.id.mainRv) }

    private val adapter: NewsListAdapter by lazy { NewsListAdapter(this) }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler.adapter = adapter
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.addItem -> {
                viewModel.addLater(1, adapter.itemCount)
                true
            }
            R.id.modifySort -> {
                viewModel.sortByDate = !viewModel.sortByDate
                observeList()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putBoolean(SAVE_STATE_SORT_BY_DATE, viewModel.sortByDate)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        viewModel.sortByDate = savedInstanceState?.getBoolean(SAVE_STATE_SORT_BY_DATE, true) ?: true
    }

    override fun onStart() {
        super.onStart()
        observeList()
    }

    private fun observeList() {
        viewModel.newsListLiveData().observe(this,
                Observer<List<NewsListItem>> { t -> adapter.updateData(t ?: emptyList()) })
    }

    companion object {

        private const val SAVE_STATE_SORT_BY_DATE = "IS_SORT_BY_DATE"

    }
}
