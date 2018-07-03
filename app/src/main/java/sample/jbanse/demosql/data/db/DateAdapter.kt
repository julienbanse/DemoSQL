package sample.jbanse.demosql.data.db

import com.squareup.sqldelight.ColumnAdapter
import java.util.Date

/**
 * Created by julien on 17/09/2017.
 */
class DateAdapter : ColumnAdapter<Date, Long> {
    override fun decode(databaseValue: Long): Date {
        return Date(databaseValue)
    }

    override fun encode(value: Date): Long {
        return value.time
    }
}