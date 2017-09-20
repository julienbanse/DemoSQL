package sample.jbanse.demosql.data.controller.model

import java.util.Date

/**
 * Created by julien on 20/09/2017.
 */
interface NewsListItem {

    fun id(): Long

    fun title(): String?

    fun publicationDate(): Date
}