package sample.jbanse.demosql

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import sample.jbanse.demosql.data.module.AppModule
import sample.jbanse.demosql.data.module.BDDModule
import sample.jbanse.demosql.ui.MainBuilder
import javax.inject.Singleton

/**
 * Created by julien on 16/09/2017.
 */
@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        AppModule::class,
        BDDModule::class,
        MainBuilder::class))
interface SampleComponent : AndroidInjector<SampleApplication>{

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<SampleApplication>()

}