package by.hustlestar.dao.iface;

import by.hustlestar.bean.entity.Movie;
import by.hustlestar.dao.exception.DAOException;

import java.util.List;

/**
 * MovieDAO interface describes methods for interaction with Movie beans mainly.
 */
public interface MovieDAO {
    /**
     * This method is used to get list of movies from data source.
     *
     * @param offset      number to begin with
     * @param noOfRecords number of records to return
     * @return list of filled movie beans
     * @throws DAOException if some error occurred while processing data.
     */
    List<Movie> getFullMovieList(int offset, int noOfRecords) throws DAOException;

    /**
     * This method is used to get movie list belonging to a particular country from data source.
     *
     * @param country        of movie
     * @param offset         first entry offset
     * @param recordsPerPage number of records to return
     * @return list of filled movie beans
     * @throws DAOException if some error occurred while processing data.
     */
    List<Movie> getMoviesByCountry(String country, int offset, int recordsPerPage) throws DAOException;

    /**
     * This method is used to get movies of a particular genre from data source.
     *
     * @param genre          of movie
     * @param offset         first entry offset
     * @param recordsPerPage number of records to return
     * @return list of filled movie beans
     * @throws DAOException if some error occurred while processing data.
     */
    List<Movie> getMoviesByGenre(String genre, int offset, int recordsPerPage) throws DAOException;

    /**
     * This method is used to search movies into data source.
     *
     * @param title of movie
     * @return list of filled movie beans
     * @throws DAOException if some error occurred while processing data.
     */
    List<Movie> findMovieByTitle(String title) throws DAOException;

    /**
     * This method is used to get movies by a particular decade.
     *
     * @param years of decade
     * @return list of filled movie beans
     * @throws DAOException if some error occurred while processing data.
     */
    List<Movie> getMoviesOfTenYearsPeriod(int years) throws DAOException;

    /**
     * This method is used to get movies of a particular year.
     *
     * @param year of movie
     * @return list of filled movie beans
     * @throws DAOException if some error occurred while processing data.
     */
    List<Movie> getMoviesOfYear(int year) throws DAOException;

    /**
     * This method is used to get detailed information about a particular
     * movie from data source.
     *
     * @param id of movie
     * @return filled movie bean
     * @throws DAOException if some error occurred while processing data.
     */
    Movie getMovieByID(int id) throws DAOException;

    /**
     * This method is used to insert new entry into data source about some movie
     *
     * @param titleRu russian title
     * @param titleEn english title
     * @param year    of movie
     * @param budget  of movie
     * @param gross   of movie
     * @throws DAOException if some error occurred while processing data.
     */
    void addMovie(String titleRu, String titleEn, int year, long budget, long gross) throws DAOException;

    /**
     * This method is used to updated information about some movie in data source.
     *
     * @param id      of movie
     * @param titleRu russian title
     * @param titleEn english title
     * @param year    of movie
     * @param budget  of movie
     * @param gross   of movie
     * @throws DAOException if some error occurred while processing data.
     */
    void updateMovie(int id, String titleRu, String titleEn, int year, long budget, long gross) throws DAOException;

    /**
     * This method is used to get movies for a particular actor from data source
     *
     * @param actorID actor id
     * @return list of filled movie beans
     * @throws DAOException if some error occurred while processing data.
     */
    List<Movie> getMoviesForActor(int actorID) throws DAOException;

    /**
     * This method is to get movies for a particular news from data source.
     *
     * @param id of movie
     * @return list of filled movie beans
     * @throws DAOException if some error occurred while processing data.
     */
    List<Movie> getMoviesForNews(int id) throws DAOException;

    /**
     * This method is used to get movies with the latest reviews from data source.
     *
     * @param lang of reviews
     * @return list of filled movie beans
     * @throws DAOException if some error occurred while processing data.
     */
    List<Movie> getMoviesWithLatestReviews(String lang) throws DAOException;

    /**
     * This method is used to get the newest movies from data source.
     *
     * @return list of filled movie beans
     * @throws DAOException if some error occurred while processing data.
     */
    List<Movie> getLatestMovies() throws DAOException;

    /**
     * This method is used to count number of movies  available from data source.
     *
     * @return number of movies
     * @throws DAOException if some error occurred while processing data.
     */
    int countAllMoviesAmount() throws DAOException;

    /**
     * This method counts number of movie for a particular country available from data source.
     *
     * @param country of movie
     * @return number of movies
     * @throws DAOException if some error occurred while processing data.
     */
    int countMoviesByCountry(String country) throws DAOException;

    /**
     * This method is used to count movies of a particular genre available from data source.
     *
     * @param genre of movie
     * @return number of movies
     * @throws DAOException if some error occurred while processing data.
     */
    int countMoviesByGenre(String genre) throws DAOException;

    /**
     * This method is used to remove movie entry from data source and used only for tests!
     *
     * @param id of movie
     * @throws DAOException if some error occurred while processing data.
     */
    void deleteMovie(int id) throws DAOException;

    /**
     * This method is used to get a last inserted movie from data source.
     *
     * @return filled movie bean
     * @throws DAOException if some error occurred while processing data.
     */
    Movie getLastInsertedMovie() throws DAOException;

    /**
     * This method is used to update image path for a particular movie in data source.
     *
     * @param id   of movie
     * @param path to image
     * @throws DAOException if some error occurred while processing data.
     */
    void updateImage(int id, String path) throws DAOException;
}
