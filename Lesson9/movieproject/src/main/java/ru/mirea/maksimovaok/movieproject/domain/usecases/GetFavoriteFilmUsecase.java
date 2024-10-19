package ru.mirea.maksimovaok.movieproject.domain.usecases;

import ru.mirea.maksimovaok.movieproject.domain.models.Movie;
import ru.mirea.maksimovaok.movieproject.domain.repository.MovieRepository;

public class GetFavoriteFilmUsecase {
    private MovieRepository movieRepository;
    public GetFavoriteFilmUsecase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    public Movie execute(){
        return movieRepository.getMovie();
    }
}

