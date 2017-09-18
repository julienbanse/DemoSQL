package sample.jbanse.demosql.data.controller

import android.database.Cursor
import com.squareup.sqlbrite2.BriteDatabase
import io.reactivex.Completable
import io.reactivex.Observable
import sample.jbanse.demosql.data.dao.News
import sample.jbanse.demosql.data.dao.NewsModel
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by julien on 16/09/2017.
 */
@Singleton
class Repository
@Inject
constructor(private val db: BriteDatabase) {

    fun insertNews(title: String): Completable {
        return Completable.fromCallable {
            val insertItem = NewsModel.InsertItem(db.readableDatabase)
                    .apply { bind(title) }
            db.executeInsert(insertItem.table, insertItem.program)
        }
    }

    private val newsMapper = News.FACTORY.selectNewsItemMapper()

    fun selectNewsOrderByDate(): Observable<List<News>> {
        News.FACTORY.selectNewsOrderByDate().let { request ->
            return db.createQuery(request.tables, request.statement, *request.args)
                    .mapToList { cursor -> newsMapper.map(cursor) }
        }
    }

    fun selectNews(newsId: Long): Observable<Cursor> {
        News.FACTORY.selectNewsItem(newsId).let { statement ->
            return db.createQuery(statement.tables, statement.statement, *statement.args)
                    .map { query -> query.run() }
        }
    }
}