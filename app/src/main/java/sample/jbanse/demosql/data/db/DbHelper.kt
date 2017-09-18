package sample.jbanse.demosql.data.db

import android.database.sqlite.SQLiteDatabase
import sample.jbanse.demosql.data.dao.AuthorModel
import sample.jbanse.demosql.data.dao.NewsModel

/**
 * Created by julien on 16/09/2017.
 */
object DbHelper {

    const val VERSION = 1

    const val NAME = "demo.db"

    fun createDb(db: SQLiteDatabase) {
        db.execSQL(NewsModel.CREATE_TABLE)
        db.execSQL(AuthorModel.CREATE_TABLE)
    }

    fun updateDb(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    fun downgrade(db: SQLiteDatabase) {
        deleteDb(db)
        createDb(db)
    }

    private fun deleteDb(db: SQLiteDatabase) {
        db.execSQL("DROP TABLE IF EXISTS ${NewsModel.TABLE_NAME}")
        db.execSQL("DROP TABLE IF EXISTS ${AuthorModel.TABLE_NAME}")
    }
}