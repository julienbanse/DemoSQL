package sample.jbanse.demosql.data.dao

import com.google.auto.value.AutoValue

/**
 * Created by julien on 12/09/2017.
 */
@AutoValue
abstract class News : NewsModel {

    companion object {

        val FACTORY: NewsModel.Factory<News> = NewsModel.Factory<News>(NewsModel.Creator<News>(::AutoValue_News))

        //val NEWS_DETAIL_MAPPER :RowMapper<NewsDetail> = FACTORY.selectNewsDetailMapper(NewsModel.SelectNewsDetailCreator<News,Author, NewsDetail>(::AutoValue_NewsDetail), Author.FACTORY)
    }
}