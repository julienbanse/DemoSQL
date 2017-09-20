package sample.jbanse.demosql.data.module

import android.content.Context
import dagger.Module
import dagger.Provides
import sample.jbanse.demosql.SampleApplication

/**
 * Created by julien on 16/09/2017.
 */
@Module
class AppModule {

    @Provides
    fun provideContext(application: SampleApplication): Context {
        return application.applicationContext
    }
}