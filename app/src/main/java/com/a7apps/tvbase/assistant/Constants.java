package com.a7apps.tvbase.assistant;

public class Constants {
    private static final String API_KEY = "8c192694ea899ac35ead1ae82c4d2cda";
    private static final String BASE_MOVIE_URL = "https://api.themoviedb.org/3/movie/popular?api_key="+API_KEY+"&language=pt-BR&page=";
    private static final String BASE_TV_URL = "https://api.themoviedb.org/3/tv/popular?api_key="+API_KEY+"&language=pt-BR&page=";
    private static final String IMAGE_URL = "https://image.tmdb.org/t/p/w500";

    public static String getBaseMovieUrl() {
        return BASE_MOVIE_URL;
    }

    public static String getBaseTvUrl() {
        return BASE_TV_URL;
    }

    public static String getIMAGE_URL() {
        return IMAGE_URL;
    }
}
