package sample.jbanse.demosql.data.controller

import android.database.sqlite.SQLiteOpenHelper
import sample.jbanse.demosql.data.dao.News

/**
 * Created by julien on 18/09/2017.
 */
class DbRepository(private val db: SQLiteOpenHelper) {

    fun insertNews(title: String): Long {
        TODO()
    }

    private val newsMapper = News.FACTORY.selectNewsItemMapper()

    fun selectNewsOrderByDate(): List<News> {
        TODO()
    }

    fun selectNews(newsId: Long): News? {
        TODO()
    }
}