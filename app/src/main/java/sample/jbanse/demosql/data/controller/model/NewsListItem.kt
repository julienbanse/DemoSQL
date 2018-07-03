package sample.jbanse.demosql.data.controller.model

import sample.jbanse.demosql.data.dao.NewsListItemOrderByDate
import sample.jbanse.demosql.data.dao.NewsListItemOrderByPosition
import java.util.Date

/**
 * Created by julien on 20/09/2017.
 */
interface NewsListItem : NewsListItemOrderByDate, NewsListItemOrderByPosition {

    class Impl(
            override val id: Long,
            override val title: String?,
            override val publicationDate: Date
    ) : NewsListItem

}