package sample.jbanse.demosql.data.dao;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

/**
 * Created by julien on 20/09/2017.
 */
@AutoValue
public abstract class NewsDetail implements NewsModel.SelectNewsDetailModel<News, Author> {

    public static final NewsModel.SelectNewsDetailMapper<News, Author, NewsDetail> MAPPER = News.FACTORY.selectNewsDetailMapper(new NewsModel.SelectNewsDetailCreator<News, Author, NewsDetail>() {
        @Override
        public NewsDetail create(@NonNull News news, @Nullable Author author) {
            return new AutoValue_NewsDetail(news, author);
        }
    }, Author.FACTORY);
}
