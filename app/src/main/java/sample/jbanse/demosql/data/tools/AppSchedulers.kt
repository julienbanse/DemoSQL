package sample.jbanse.demosql.data.tools

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by jbanse on 28/08/2017.
 */
class AppSchedulers(val database: Scheduler = Schedulers.single(),
                    val ui: Scheduler = AndroidSchedulers.mainThread(),
                    val disk: Scheduler = Schedulers.io(),
                    val network: Scheduler = Schedulers.io())
