package by.hustlestar.dao.iface;

import by.hustlestar.bean.entity.Actor;
import by.hustlestar.dao.exception.DAOException;

import java.util.List;

/**
 * Created by dell on 05.12.2016.
 */
public interface ActorDAO {

    List<Actor> getActorsForMovie(int normId) throws DAOException;

    Actor getDirectorForMovie(int normId) throws DAOException;

    Actor getActor(int normId) throws DAOException;

    void addActor(String nameRu, String nameEn) throws DAOException;

    void updateActor(int actorID, String nameRu, String nameEn) throws DAOException;

    void addActorForMovie(int actorID,int movieID) throws DAOException;

    void deleteActorForMovie(int actorID,int movieID) throws DAOException;

    void addDirectorForMovie(int actorID,int movieID) throws DAOException;

    void deleteDirectorForMovie(int actorID,int movieID) throws DAOException;

}
