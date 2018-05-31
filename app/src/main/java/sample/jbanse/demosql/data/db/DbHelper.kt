package sample.jbanse.demosql.data.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.db.SupportSQLiteOpenHelper
import sample.jbanse.demosql.data.dao.AuthorModel
import sample.jbanse.demosql.data.dao.NewsModel

/**
 * Created by julien on 16/09/2017.
 */
class DbHelper : SupportSQLiteOpenHelper.Callback(VERSION) {
    override fun onCreate(db: SupportSQLiteDatabase?) {
        db?.let { createDb(it) }
    }

    override fun onUpgrade(db: SupportSQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.let { updateDb(it, oldVersion, newVersion) }
    }

    companion object {

        const val VERSION = 1

        const val NAME = "demo.db"

    }

    fun createDb(db: SupportSQLiteDatabase) {
        db.execSQL(NewsModel.CREATE_TABLE)
        db.execSQL(AuthorModel.CREATE_TABLE)
    }

    fun updateDb(db: SupportSQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    fun downgrade(db: SupportSQLiteDatabase) {
        deleteDb(db)
        createDb(db)
    }

    private fun deleteDb(db: SupportSQLiteDatabase) {
        db.execSQL("DROP TABLE IF EXISTS ${NewsModel.TABLE_NAME}")
        db.execSQL("DROP TABLE IF EXISTS ${AuthorModel.TABLE_NAME}")
    }
}