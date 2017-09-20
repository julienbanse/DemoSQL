package sample.jbanse.demosql.data.dao

import com.google.auto.value.AutoValue

/**
 * Created by julien on 20/09/2017.
 */
@AutoValue
abstract class NewsDetail : NewsModel.SelectNewsDetailModel<News, Author> {
    companion object {
        @JvmField
        val MAPPER: NewsModel.SelectNewsDetailMapper<News, Author, NewsDetail> = News.FACTORY.selectNewsDetailMapper({ news, author -> AutoValue_NewsDetail(news, author) }, Author.FACTORY)
    }
}
