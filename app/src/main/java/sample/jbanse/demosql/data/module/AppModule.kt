package sample.jbanse.demosql.data.module

import android.content.Context
import com.squareup.leakcanary.LeakCanary
import dagger.Module
import dagger.Provides
import sample.jbanse.demosql.SampleApplication
import sample.jbanse.demosql.data.tools.AppSchedulers
import timber.log.Timber
import javax.inject.Singleton

/**
 * Created by julien on 16/09/2017.
 */
@Module
class AppModule(application: SampleApplication) {

    init {
        Timber.plant(Timber.DebugTree())
        if (!LeakCanary.isInAnalyzerProcess(application))
            LeakCanary.install(application)
    }

    @Provides
    fun provideContext(application: SampleApplication): Context = application.applicationContext

    @Singleton
    @Provides
    fun appScheduler() = AppSchedulers()
}