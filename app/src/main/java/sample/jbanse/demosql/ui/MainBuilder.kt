package sample.jbanse.demosql.ui

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by julien on 17/09/2017.
 */
@Module
internal abstract class MainBuilder {

    @ContributesAndroidInjector
    abstract fun bindActivity(): MainActivity
}