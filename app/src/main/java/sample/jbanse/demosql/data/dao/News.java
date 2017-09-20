package sample.jbanse.demosql.data.dao;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

import java.util.Date;

import sample.jbanse.demosql.data.db.DateAdapter;

/**
 * Created by julien on 20/09/2017.
 */
@AutoValue
public abstract class News implements NewsModel {

    public static final NewsModel.Factory<News> FACTORY = new NewsModel.Factory<News>(new NewsModel.Creator<News>() {

        @Override
        public News create(long id, @Nullable String title, Date publicationDate, int topRatedPosition, @Nullable Long authorId) {
            return new AutoValue_News(id, title, publicationDate, topRatedPosition, authorId);
        }
    }, new DateAdapter());
}
