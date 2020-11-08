package com.example.mymoviesmvc.common;

public class Constants
{
    private Constants()
    {
    }

    public static final String BASE_URL = "https://api.themoviedb.org/";
    public static final int LOADER_ID = 17;

    public static final String KEY_RESULTS = "results";


    // NetworkUtils
    public static final String BASE_URL_MOVIE = "https://api.themoviedb.org/3/discover/movie";
    public static final String BASE_URL_VIDEOS = "https://api.themoviedb.org/3/movie/%s/videos";
    public static final String BASE_URL_REVIEWS = "https://api.themoviedb.org/3/movie/%s/reviews";


    public static final String PARAMS_API_KEY = "api_key";
    public static final String PARAMS_LANGUAGE = "language";
    public static final String PARAMS_SORT_BY = "sort_by";
    public static final String PARAMS_PAGE = "page";
    public static final String PARAMS_MIN_VOTE_COUNT = "vote_count.gte";

    public static final String API_KEY = "754fa9d8fd80b794d6d14ae8911b68a8";

    public static final String SORT_BY_POPULARITY = "popularity.desc";
    public static final String SORT_BY_TOP_RATED = "vote_average.desc";
    public static final String MIN_VOTE_COUNT_VALUE = "1000";

    public static final int POPULARITY = 0;
    public static final int TOP_RATED = 1;


    // JSONUtils
    // Для отзывов
    public static final String KEY_AUTHOR = "author";
    public static final String KEY_CONTENT = "content";


    // Для видео
    public static final String KEY_KEY_OF_VIDEO = "key";
    public static final String KEY_NAME = "name";
    public static final String BASE_YOUTUBE_URL = "https://www.youtube.com/watch?v=";


    // Вся информация о фильме
    public static final int KEY_VOTE_COUNT = Integer.parseInt("vote_count");
    public static final String KEY_ID = "id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_ORIGINAL_TITLE = "original_title";
    public static final String KEY_OVERVIEW = "overview";
    public static final String KEY_POSTER_PATH = "poster_path";
    public static final String KEY_BACKDROP_PATH = "backdrop_path";
    public static final String KEY_VOTE_AVERAGE = "vote_average";
    public static final String KEY_RELEASE_DATE = "release_date";

    public static final String BASE_POSTER_URL = "https://image.tmdb.org/t/p/";
    public static final String SMALL_POSTER_SIZE = "w185";
    public static final String BIG_POSTER_SIZE = "w780";
}
