package by.hustlestar.service.iface;

import by.hustlestar.bean.entity.User;
import by.hustlestar.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Hustler on 07.11.2016.
 */
public interface AdminService {

    void banUser(String userNickname) throws ServiceException;

    void unbanUser(String userNickname) throws ServiceException;

    List<User> showAllUsers() throws ServiceException;

    List<User> showAllBannedUsers() throws ServiceException;

    void addMovie(String titleRu,String titleEn, String year, String budget, String gross) throws ServiceException;

    void updateMovie(String id,String titleRu, String titleEn, String year, String budget, String gross) throws ServiceException;

    void deleteReview(String movieID, String userNickname) throws ServiceException;

    void addCountryForMovie(String movieID, String nameRu, String nameEn) throws ServiceException;

    void deleteCountryForMovie(String movieID, String nameEn) throws ServiceException;

    void addGenreForMovie(String movieID, String nameRu, String nameEn) throws ServiceException;

    void deleteGenreForMovie(String movieID, String nameEn) throws ServiceException;

    void addActor(String nameRu, String nameEn) throws ServiceException;

    void updateActor(String actorID, String nameRu, String nameEn) throws ServiceException;

    void addActorForMovie(String actorID, String movieID) throws ServiceException;

    void addDirectorForMovie(String actorID, String movieID) throws ServiceException;

    void deleteActorForMovie(String actorID, String movieID) throws ServiceException;

    void deleteDirectorForMovie(String actorID, String movieID) throws ServiceException;

    void addNews(String newsTitleRu, String newsTitleEn, String newsTextRu, String newsTextEn, String actorID, String movieID) throws ServiceException;

    void updateNews(String newsTitleRu, String newsTitleEn, String newsTextRu, String newsTextEn, String actorID, String movieID, String newsID) throws ServiceException;
}
