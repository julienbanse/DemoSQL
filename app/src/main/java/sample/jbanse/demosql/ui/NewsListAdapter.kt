package sample.jbanse.demosql.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import sample.jbanse.demosql.data.controller.model.NewsListItem
import java.text.DateFormat

/**
 * Created by julien on 17/09/2017.
 */
class NewsListAdapter(context: Context) : RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>() {

    private val data = mutableListOf<NewsListItem>()

    private val layoutInflater = LayoutInflater.from(context)

    private val dateFormatter = DateFormat.getDateTimeInstance()

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return data[position].id()
    }

    fun updateData(list: List<NewsListItem>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NewsViewHolder {
        return NewsViewHolder(layoutInflater, parent)
    }

    override fun onBindViewHolder(holder: NewsViewHolder?, position: Int) {
        holder?.bindNews(data[position], dateFormatter)
    }

    inner class NewsViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup?) : RecyclerView.ViewHolder(layoutInflater.inflate(android.R.layout.simple_list_item_2, parent, false)) {
        private val title: TextView by lazy { itemView.findViewById<TextView>(android.R.id.text1) }
        private val subTitle: TextView by lazy { itemView.findViewById<TextView>(android.R.id.text2) }

        fun bindNews(item: NewsListItem, dateFormat: DateFormat) {
            title.text = item.title()
            subTitle.text = dateFormat.format(item.publicationDate())
        }
    }
}