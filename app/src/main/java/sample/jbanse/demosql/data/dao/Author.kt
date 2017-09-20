package sample.jbanse.demosql.data.dao

import com.google.auto.value.AutoValue

/**
 * Created by julien on 18/09/2017.
 */
@AutoValue
abstract class Author : AuthorModel {

    companion object {
        @JvmField
        val FACTORY: AuthorModel.Factory<Author> = AuthorModel.Factory<Author>(AuthorModel.Creator<Author>(::AutoValue_Author))

    }
}