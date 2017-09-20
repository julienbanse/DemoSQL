package sample.jbanse.demosql.data.module

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.squareup.sqlbrite2.BriteDatabase
import com.squareup.sqlbrite2.SqlBrite
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import sample.jbanse.demosql.BuildConfig
import sample.jbanse.demosql.data.db.DemoSQLOpenHelper

/**
 * Created by julien on 16/09/2017.
 */
@Module
class BDDModule {

    @Provides
    fun createSqlBrite(): SqlBrite {
        return SqlBrite.Builder()
                .logger { message -> Log.d("Database", message) }
                .build()
    }

    @Provides
    fun openHelper(context: Context): SQLiteOpenHelper = DemoSQLOpenHelper(context)

    @Provides
    fun briteDatabase(sqlBrite: SqlBrite, sqLiteOpenHelper: SQLiteOpenHelper): BriteDatabase {
        val briteDb = sqlBrite.wrapDatabaseHelper(sqLiteOpenHelper, Schedulers.single())
        briteDb.setLoggingEnabled(BuildConfig.DEBUG)
        return briteDb
    }
}