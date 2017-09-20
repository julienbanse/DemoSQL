package sample.jbanse.demosql.data.db

import android.content.Context
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by julien on 12/09/2017.
 */
class DemoSQLOpenHelper : SQLiteOpenHelper {

    constructor(context: Context) : super(context, DbHelper.NAME, null, DbHelper.VERSION)

    constructor(context: Context, factory: SQLiteDatabase.CursorFactory, errorHandler: DatabaseErrorHandler) : super(context, DbHelper.NAME, factory, DbHelper.VERSION, errorHandler)

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.let { DbHelper.createDb(it) }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        p0?.let { DbHelper.updateDb(it, oldVersion, newVersion) }
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
        db?.let { DbHelper.downgrade(it) }
    }
}