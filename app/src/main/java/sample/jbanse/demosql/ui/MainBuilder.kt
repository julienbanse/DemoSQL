package sample.jbanse.demosql.ui

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import sample.jbanse.demosql.inject.ViewModelKey

/**
 * Created by julien on 17/09/2017.
 */
@Module
internal abstract class MainBuilder {

    @ContributesAndroidInjector
    abstract fun bindActivity(): MainActivity


    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainActivityViewModel(viewModel: MainViewModel): ViewModel

}