package sample.jbanse.demosql.data.controller

import com.squareup.sqldelight.runtime.rx.asObservable
import com.squareup.sqldelight.runtime.rx.mapToList
import com.squareup.sqldelight.runtime.rx.mapToOne
import io.reactivex.Observable
import io.reactivex.Single
import sample.jbanse.demosql.QueryWrapper
import sample.jbanse.demosql.data.controller.model.NewsListItem
import sample.jbanse.demosql.data.dao.NewsDetail
import sample.jbanse.demosql.data.tools.AppSchedulers
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by julien on 16/09/2017.
 */
@Singleton
class Repository
@Inject
constructor(private val db: QueryWrapper, private val schedulers: AppSchedulers) {

    fun insertNews(title: String, publicationDate: Date, position: Int): Single<Long> {
        return Single.fromCallable {
            db.newsQueries.insertItem(title, publicationDate, position)
        }
    }

    fun selectNewsOrderByDate(): Observable<List<NewsListItem>> {
        return db.newsQueries.newsListItemOrderByDate<NewsListItem>(mapper = NewsListItem::Impl)
                .asObservable(schedulers.database)
                .mapToList()
    }

    fun selectNewsOrderByPosition(): Observable<List<NewsListItem>> {
        return db.newsQueries.newsListItemOrderByPosition<NewsListItem>(mapper = NewsListItem::Impl)
                .asObservable(schedulers.database)
                .mapToList()
    }

    fun selectNewsDetail(newsId: Long): Observable<NewsDetail> {
        return db.newsQueries.newsDetail(newsId)
                .asObservable(schedulers.database)
                .mapToOne()
    }
}