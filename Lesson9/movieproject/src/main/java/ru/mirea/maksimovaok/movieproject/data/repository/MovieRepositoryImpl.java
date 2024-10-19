package ru.mirea.maksimovaok.movieproject.data.repository;

import android.content.Context;
import android.content.SharedPreferences;

import ru.mirea.maksimovaok.movieproject.domain.models.Movie;
import ru.mirea.maksimovaok.movieproject.domain.repository.MovieRepository;

public class MovieRepositoryImpl implements MovieRepository {

    private static final String PREFS_NAME = "favorite_movie_prefs";
    private static final String KEY_MOVIE_NAME = "movie_name";
    private static final String KEY_MOVIE_ID = "movie_id";
    private final SharedPreferences sharedPreferences;

    public MovieRepositoryImpl(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public boolean saveMovie(Movie movie) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_MOVIE_ID, movie.getId());
        editor.putString(KEY_MOVIE_NAME, movie.getName());
        return editor.commit();
    }

    @Override
    public Movie getMovie() {
        int id = sharedPreferences.getInt(KEY_MOVIE_ID, -1);
        String name = sharedPreferences.getString(KEY_MOVIE_NAME, null);

        if (id == -1 || name == null) {
            return null;
        }

        return new Movie(id, name);
    }
}
