package by.hustlestar.dao.impl;

import by.hustlestar.bean.entity.Actor;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.iface.ActorDAO;
import by.hustlestar.dao.impl.pool.ConnectionPoolException;
import by.hustlestar.dao.impl.pool.ConnectionPoolSQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 05.12.2016.
 */
public class ActorSQLDAO implements ActorDAO {
    private static final String ACTORS_FOR_MOVIE =
            "SELECT a_id, a_name_ru, a_name_en FROM actors\n" +
                    "LEFT JOIN actor_starred\n" +
                    "ON actors.a_id=actor_starred.actors_a_id\n" +
                    "WHERE movies_m_id=?;";
    private final static String DIRECTOR_FOR_MOVIE =
            "SELECT a_id, a_name_ru, a_name_en FROM actors\n" +
                    "LEFT JOIN director\n" +
                    "ON actors.a_id=director.actors_a_id\n" +
                    "WHERE movies_m_id=?;";
    private static final String ACTOR_BY_ID =
            "SELECT a_name_ru, a_name_en FROM actors WHERE a_id=?;";
    private final static String ADD_ACTOR =
            "INSERT INTO actors (a_name_ru, a_name_en) VALUES (?, ?)";
    private final static String UPDATE_ACTOR =
            "UPDATE actors SET a_name_ru = ?, a_name_en = ? WHERE a_id = ?;\n";
    private final static String ADD_ACTOR_FOR_MOVIE =
            "INSERT INTO actor_starred (actors_a_id, movies_m_id) VALUES (?, ?)";
    private static final String DELETE_ACTOR_FOR_MOVIE =
            "DELETE FROM actor_starred\n" +
                    "WHERE movies_m_id=? AND actors_a_id=?;";
    private final static String ADD_DIRECTOR_FOR_MOVIE =
            "INSERT INTO director (actors_a_id, movies_m_id) VALUES (?, ?)";
    private static final String DELETE_DIRECTOR_FOR_MOVIE =
            "DELETE FROM director\n" +
                    "WHERE movies_m_id=? AND actors_a_id=?;";
    private static final String ACTORS_FOR_NEWS =
            "SELECT a_id, a_name_ru, a_name_en FROM actors\n" +
                    "LEFT JOIN news_about_actors\n" +
                    "ON actors.a_id=news_about_actors.actors_a_id\n" +
                    "WHERE news_n_id=?;";
    private static final String ALL_ACTORS =
            "SELECT * FROM actors;";

    private static final String A_ID = "a_id";
    private static final String A_NAME_RU = "a_name_ru";
    private static final String A_NAME_EN = "a_name_en";


    @Override
    public List<Actor> getActorsForMovie(int normId) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(ACTORS_FOR_MOVIE);
            st.setInt(1, normId);

            rs = st.executeQuery();

            List<Actor> actors = new ArrayList<>();
            Actor actor;
            while (rs.next()) {
                actor = new Actor();
                actor.setId(rs.getInt(A_ID));
                actor.setNameEn(rs.getString(A_NAME_EN));
                actor.setNameRu(rs.getString(A_NAME_RU));
                actors.add(actor);
            }
            return actors;

        } catch (SQLException e) {
            throw new DAOException("Actor sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Actor pool connection error", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing result set", e);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing statement", e);
                }
            }
            try {
                ConnectionPoolSQLDAO.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

    @Override
    public Actor getDirectorForMovie(int normId) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(DIRECTOR_FOR_MOVIE);
            st.setInt(1, normId);
            rs = st.executeQuery();

            Actor actor = null;
            if (rs.next()) {
                actor = new Actor();
                actor.setId(rs.getInt(A_ID));
                actor.setNameEn(rs.getString(A_NAME_EN));
                actor.setNameRu(rs.getString(A_NAME_RU));
            }
            return actor;

        } catch (SQLException e) {
            throw new DAOException("Actor sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Actor pool connection error", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing result set", e);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing statement", e);
                }
            }
            try {
                ConnectionPoolSQLDAO.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

    @Override
    public Actor getActor(int normId) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(ACTOR_BY_ID);
            st.setInt(1, normId);
            rs = st.executeQuery();

            Actor actor = null;
            if (rs.next()) {
                actor = new Actor();
                actor.setId(normId);
                actor.setNameEn(rs.getString(A_NAME_EN));
                actor.setNameRu(rs.getString(A_NAME_RU));
            }
            return actor;

        } catch (SQLException e) {
            throw new DAOException("Actor sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Actor pool connection error", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing result set", e);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing statement", e);
                }
            }
            try {
                ConnectionPoolSQLDAO.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

    @Override
    public void addActor(String nameRu, String nameEn) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();
            st = con.prepareStatement(ADD_ACTOR);
            st.setString(1, nameRu);
            st.setString(2, nameEn);
            int update = st.executeUpdate();
            if (update > 0) {
                System.out.println("Actor dobavlen vse ok" + nameEn);
                return;
            }
            throw new DAOException("Wrong movie data");
        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing statement", e);
                }
            }
            try {
                ConnectionPoolSQLDAO.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

    @Override
    public void updateActor(int actorID, String nameRu, String nameEn) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();
            st = con.prepareStatement(UPDATE_ACTOR);
            st.setString(1, nameRu);
            st.setString(2, nameEn);
            st.setInt(3, actorID);
            int update = st.executeUpdate();
            if (update > 0) {
                System.out.println("Actor dobavlen vse ok" + nameEn);
                return;
            }
            throw new DAOException("Wrong movie data");
        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing statement", e);
                }
            }
            try {
                ConnectionPoolSQLDAO.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

    @Override
    public void addActorForMovie(int actorID, int movieID) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();
            st = con.prepareStatement(ADD_ACTOR_FOR_MOVIE);
            st.setInt(1, actorID);
            st.setInt(2, movieID);
            int update = st.executeUpdate();
            if (update > 0) {
                System.out.println("Actor dobavlen k filmy");
                return;
            }
            throw new DAOException("Wrong movie data");
        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing statement", e);
                }
            }
            try {
                ConnectionPoolSQLDAO.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

    @Override
    public void deleteActorForMovie(int actorID, int movieID) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();
            st = con.prepareStatement(DELETE_ACTOR_FOR_MOVIE);
            st.setInt(1, movieID);
            st.setInt(2, actorID);
            int update = st.executeUpdate();
            if (update > 0) {
                System.out.println("Actor udalen vse ok " + actorID);
                return;
            }
            throw new DAOException("Wrong review data");
        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing statement", e);
                }
            }
            try {
                ConnectionPoolSQLDAO.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

    @Override
    public void addDirectorForMovie(int actorID, int movieID) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();
            st = con.prepareStatement(ADD_DIRECTOR_FOR_MOVIE);
            st.setInt(1, actorID);
            st.setInt(2, movieID);
            int update = st.executeUpdate();
            if (update > 0) {
                System.out.println("Director dobavlen k filmy");
                return;
            }
            throw new DAOException("Wrong actor data");
        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing statement", e);
                }
            }
            try {
                ConnectionPoolSQLDAO.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

    @Override
    public void deleteDirectorForMovie(int actorID, int movieID) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();
            st = con.prepareStatement(DELETE_DIRECTOR_FOR_MOVIE);
            st.setInt(1, movieID);
            st.setInt(2, actorID);
            int update = st.executeUpdate();
            if (update > 0) {
                System.out.println("Director udalen vse ok " + actorID);
                return;
            }
            throw new DAOException("Wrong review data");
        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing statement", e);
                }
            }
            try {
                ConnectionPoolSQLDAO.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

    @Override
    public List<Actor> getActorsForNews(int id) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(ACTORS_FOR_NEWS);
            st.setInt(1, id);

            rs = st.executeQuery();

            List<Actor> actors = new ArrayList<>();
            Actor actor;
            while (rs.next()) {
                actor = new Actor();
                actor.setId(rs.getInt(A_ID));
                actor.setNameEn(rs.getString(A_NAME_EN));
                actor.setNameRu(rs.getString(A_NAME_RU));
                actors.add(actor);
            }
            return actors;

        } catch (SQLException e) {
            throw new DAOException("Actor sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Actor pool connection error", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing result set", e);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing statement", e);
                }
            }
            try {
                ConnectionPoolSQLDAO.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

    @Override
    public List<Actor> getAllActors() throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(ALL_ACTORS);
            //st.setInt(1, normId);

            rs = st.executeQuery();

            List<Actor> actors = new ArrayList<>();
            Actor actor;
            while (rs.next()) {
                actor = new Actor();
                actor.setId(rs.getInt(A_ID));
                actor.setNameEn(rs.getString(A_NAME_EN));
                actor.setNameRu(rs.getString(A_NAME_RU));
                actors.add(actor);
            }
            return actors;

        } catch (SQLException e) {
            throw new DAOException("Actor sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Actor pool connection error", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing result set", e);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing statement", e);
                }
            }
            try {
                ConnectionPoolSQLDAO.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

}
