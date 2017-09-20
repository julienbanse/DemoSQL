package sample.jbanse.demosql

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Created by julien on 16/09/2017.
 */
class SampleApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerSampleComponent.builder().create(this)
    }
}