package sample.jbanse.demosql.data.module

import android.arch.persistence.db.SupportSQLiteOpenHelper
import android.arch.persistence.db.framework.FrameworkSQLiteOpenHelperFactory
import android.content.Context
import com.squareup.sqlbrite3.BriteDatabase
import com.squareup.sqlbrite3.SqlBrite
import dagger.Module
import dagger.Provides
import sample.jbanse.demosql.data.db.DbHelper
import sample.jbanse.demosql.data.tools.AppSchedulers
import timber.log.Timber
import javax.inject.Singleton

/**
 * Created by julien on 16/09/2017.
 */
@Module
class BDDModule {

    @Provides
    fun createSqlBrite(): SqlBrite {
        return SqlBrite.Builder().logger { message -> Timber.tag("BDD").d(message) }
                .build()
    }

    @Provides
    fun openHelper(context: Context): SupportSQLiteOpenHelper {
        val configuration = SupportSQLiteOpenHelper.Configuration.builder(context)
                .name(DbHelper.NAME)
                .callback(DbHelper())
                .build()
        return FrameworkSQLiteOpenHelperFactory().create(configuration)
    }

    @Singleton
    @Provides
    fun briteDatabase(sqlBrite: SqlBrite, sqLiteOpenHelper: SupportSQLiteOpenHelper, schedulers: AppSchedulers): BriteDatabase {
        val briteDatabase = sqlBrite.wrapDatabaseHelper(sqLiteOpenHelper, schedulers.database)
        briteDatabase.setLoggingEnabled(true)
        return briteDatabase
    }
}