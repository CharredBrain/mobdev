package ru.mirea.maksimovaok.movieproject.domain.usecases;

import ru.mirea.maksimovaok.movieproject.domain.models.Movie;
import ru.mirea.maksimovaok.movieproject.domain.repository.MovieRepository;

public class SaveMovieToFavoriteUsecase {
    private MovieRepository movieRepository;
    public SaveMovieToFavoriteUsecase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    public boolean execute(Movie movie){
        return movieRepository.saveMovie(movie);
    }
}
