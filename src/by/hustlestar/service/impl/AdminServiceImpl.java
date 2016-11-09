package by.hustlestar.service.impl;

import by.hustlestar.bean.entity.User;
import by.hustlestar.dao.DAOFactory;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.iface.UserDAO;
import by.hustlestar.service.AdminService;
import by.hustlestar.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Hustler on 07.11.2016.
 */
public class AdminServiceImpl implements AdminService {

    @Override
    public void banUser(String userNickname) throws ServiceException {

    }

    @Override
    public void unbanUser(String userNickname) throws ServiceException {

    }

    @Override
    public List<User> showAllUsers() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO dao = daoFactory.getUserDAO();
        List<User> users;
        try {
            users = dao.viewAllUsers();
            if (users == null || users.size() == 0) {
                throw new ServiceException("No users matching your query");
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return users;
    }
}
