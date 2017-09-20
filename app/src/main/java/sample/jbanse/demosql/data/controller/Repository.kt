package sample.jbanse.demosql.data.controller

import com.squareup.sqlbrite2.BriteDatabase
import io.reactivex.Observable
import io.reactivex.Single
import sample.jbanse.demosql.data.controller.model.NewsListItem
import sample.jbanse.demosql.data.dao.News
import sample.jbanse.demosql.data.dao.NewsDetail
import sample.jbanse.demosql.data.dao.NewsItem
import sample.jbanse.demosql.data.dao.NewsModel
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by julien on 16/09/2017.
 */
@Singleton
class Repository
@Inject
constructor(private val db: BriteDatabase) {

    fun insertNews(title: String, publicationDate: Date, position: Int): Single<Long> {
        return Single.fromCallable {
            val select = NewsModel.InsertItem(db.readableDatabase, News.FACTORY).apply {
                bind(title, publicationDate, position)
            }
            db.executeInsert(select.table, select.program)
        }
    }

    fun selectNewsOrderByDate(): Observable<List<NewsListItem>> {
        News.FACTORY.newsListItemOrderByDate().let { statement ->
            return db.createQuery(statement.tables, statement.statement, *statement.args)
                    .mapToList { NewsItem.NEWS_ITEM_MAPPER.map(it) }
        }
    }

    fun selectNewsOrderByPosition(): Observable<List<NewsListItem>> {
        News.FACTORY.newsListItemOrderByPosition().let { statement ->
            return db.createQuery(statement.tables, statement.statement, *statement.args)
                    .mapToList { NewsItem.NEWS_ITEM_MAPPER.map(it) }
        }
    }

    fun selectNewsDetail(newsId: Long): Observable<NewsDetail> {
        News.FACTORY.selectNewsDetail(newsId).let { select ->
            return db.createQuery(select.tables, select.statement, *select.args)
                    .mapToOne { NewsDetail.MAPPER.map(it) }
        }
    }
}