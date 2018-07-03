package sample.jbanse.demosql.data.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.db.SupportSQLiteOpenHelper
import com.squareup.sqldelight.android.create
import sample.jbanse.demosql.QueryWrapper

/**
 * Created by julien on 16/09/2017.
 */
class DbHelper : SupportSQLiteOpenHelper.Callback(QueryWrapper.version) {
    override fun onCreate(db: SupportSQLiteDatabase?) {
        db?.let { QueryWrapper.onCreate(QueryWrapper.create(it).getConnection()) }
    }

    override fun onUpgrade(db: SupportSQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.let { updateDb(it, oldVersion, newVersion) }
    }

    companion object {

        const val NAME = "demo.db"

    }

    fun updateDb(db: SupportSQLiteDatabase, oldVersion: Int, newVersion: Int) {
        QueryWrapper.onMigrate(QueryWrapper.create(db).getConnection(), oldVersion, newVersion)
    }

    fun downgrade(db: SupportSQLiteDatabase) {

    }

    private fun deleteDb(db: SupportSQLiteDatabase) {

    }
}