package sample.jbanse.demosql.data.module

import android.app.Application
import android.content.Context
import com.squareup.leakcanary.LeakCanary
import dagger.Module
import dagger.Provides
import sample.jbanse.demosql.SampleApplication
import timber.log.Timber

/**
 * Created by julien on 16/09/2017.
 */
@Module
class AppModule(application: Application) {

    init {
        Timber.plant(Timber.DebugTree())
        if (!LeakCanary.isInAnalyzerProcess(application))
            LeakCanary.install(application)
    }

    @Provides
    fun provideContext(application: SampleApplication): Context {
        return application.applicationContext
    }
}