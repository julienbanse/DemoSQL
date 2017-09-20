package sample.jbanse.demosql.data.dao;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

import java.util.Date;

import sample.jbanse.demosql.data.controller.model.NewsListItem;

/**
 * Created by julien on 20/09/2017.
 */
@AutoValue
public abstract class NewsItem implements NewsModel.NewsListItemOrderByDateModel, NewsModel.NewsListItemOrderByPositionModel, NewsListItem {

    //étant donné qu'une colonne nécessite un adapteur, il faut fournir la factory pour qu'elle puisse fournir le column adapter au mapper.
    public static final NewsModel.NewsListItemOrderByDateMapper<? extends NewsListItem, News> NEWS_ITEM_MAPPER = new NewsModel.NewsListItemOrderByDateMapper<>(new NewsModel.NewsListItemOrderByDateCreator<NewsItem>() {

        @Override
        public NewsItem create(long id, @Nullable String title, @NonNull Date publicationDate) {
            return new AutoValue_NewsItem(id, title, publicationDate);
        }
    }, News.FACTORY);
}
