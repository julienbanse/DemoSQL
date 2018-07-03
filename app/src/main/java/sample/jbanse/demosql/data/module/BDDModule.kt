package sample.jbanse.demosql.data.module

import android.content.Context
import com.squareup.sqldelight.android.create
import com.squareup.sqldelight.db.SqlDatabase
import dagger.Module
import dagger.Provides
import sample.jbanse.demosql.QueryWrapper
import sample.jbanse.demosql.data.dao.News
import sample.jbanse.demosql.data.db.DateAdapter
import sample.jbanse.demosql.data.db.DbHelper
import javax.inject.Singleton

/**
 * Created by julien on 16/09/2017.
 */
@Module
class BDDModule {

    @Provides
    fun sqldelight(context: Context): SqlDatabase {
        return QueryWrapper.create(context, DbHelper.NAME, DbHelper())
    }

    @Singleton
    @Provides
    fun queryWrapper(sqlDatabase: SqlDatabase): QueryWrapper {
        return QueryWrapper(sqlDatabase, News.Adapter(DateAdapter()))
    }
}