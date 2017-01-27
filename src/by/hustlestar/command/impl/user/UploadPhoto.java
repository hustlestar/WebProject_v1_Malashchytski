package by.hustlestar.command.impl.user;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import by.hustlestar.bean.entity.User;
import by.hustlestar.command.exception.CommandException;
import by.hustlestar.command.util.CommandsUtil;
import by.hustlestar.service.ServiceFactory;
import by.hustlestar.service.exception.ServiceException;
import by.hustlestar.service.iface.AdminService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * UploadPhoto class is used to handle client request to
 * upload some photo for news, movie, actor or profile.
 */
public class UploadPhoto implements by.hustlestar.command.Command {

    private static final Logger logger = LogManager.getLogger(UploadPhoto.class);

    private String filename;
    private String directory;
    private String entity;

    private static final String MOVIE = "movies";
    private static final String ACTOR = "actors";
    private static final String NEWS = "news";
    private static final String USERS = "users";

    private static final String USER = "user";
    private static final String MODER = "moder";
    private static final String ADMIN = "admin";

    private static final String FILENAME = "filename";
    private static final String TEMP_DIRECTORY = "javax.servlet.context.tempdir";

    private static final String MOVIES_DIRECTORY = "E:\\Apache Software Foundation\\Tomcat 7.0\\webapps\\FinalProject\\out\\artifacts\\FinalProject_war_exploded\\images\\movies\\";
    private static final String USERS_DIRECTORY = "E:\\Apache Software Foundation\\Tomcat 7.0\\webapps\\FinalProject\\out\\artifacts\\FinalProject_war_exploded\\images\\users\\";
    private static final String NEWS_DIRECTORY = "E:\\Apache Software Foundation\\Tomcat 7.0\\webapps\\FinalProject\\out\\artifacts\\FinalProject_war_exploded\\images\\news\\";
    private static final String ACTORS_DIRECTORY = "E:\\Apache Software Foundation\\Tomcat 7.0\\webapps\\FinalProject\\out\\artifacts\\FinalProject_war_exploded\\images\\actors\\";
    private static final String FILE_EXTENSION = ".jpg";

    private static final String COMMAND = "command";
    private static final String UPLOAD_PHOTO_FOR_MOVIE = "upload-photo-for-movie";
    private static final String UPLOAD_PHOTO_FOR_NEWS = "upload-photo-for-news";
    private static final String UPLOAD_PHOTO_FOR_ACTOR = "upload-photo-for-actor";
    private static final String UPLOAD_PHOTO_FOR_AVATAR = "upload-photo-for-avatar";

    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR = "Failed to add picture";
    private static final String MESSAGE_OF_ERROR_2 = "Failed to create file";
    private static final String MESSAGE_OF_ERROR_3 = "Failed to write file";
    private static final String MESSAGE_OF_ERROR_4 = "Wrong file extension .jpg only allowed";
    private static final String MESSAGE_OF_ERROR_5 = "Cannot write file";

    private static final char QUERY_AMPERSAND = '&';
    private static final char QUERY_EQUALS = '=';

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userType = null;
        Object object = request.getSession(false).getAttribute(USER);
        if (object.getClass().equals(User.class)) {
            User user = (User) object;
            userType = user.getType();
        }
        String previousQuery = CommandsUtil.getPreviousQuery(request);
        //System.out.println(previousQuery);
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Maximal buffer size
        factory.setSizeThreshold(1024 * 1024);

        // temporary directory
        File tempDir = (File) request.getSession().getServletContext().getAttribute(TEMP_DIRECTORY);
        factory.setRepository(tempDir);

        // File uploader
        ServletFileUpload upload = new ServletFileUpload(factory);

        // setting maximum upload size
        upload.setSizeMax(1024 * 1024);

        try {
            List<FileItem> items = upload.parseRequest(request);
            Iterator<FileItem> iter = items.iterator();

            while (iter.hasNext()) {
                FileItem item = iter.next();
                if (item.isFormField()) {
                    String fieldName = item.getFieldName();
                    String value = item.getString();
                    if (fieldName.equals(COMMAND) && value.equals(UPLOAD_PHOTO_FOR_AVATAR)
                            && (userType.equals(ADMIN) || userType.equals(MODER) || userType.equals(USER))) {
                        directory = USERS_DIRECTORY;
                        entity = USERS;
                    } else if (fieldName.equals(COMMAND) && (userType.equals(ADMIN) || userType.equals(MODER))) {
                        switch (value) {
                            case UPLOAD_PHOTO_FOR_MOVIE:
                                directory = MOVIES_DIRECTORY;
                                entity = MOVIE;
                                break;
                            case UPLOAD_PHOTO_FOR_NEWS:
                                directory = NEWS_DIRECTORY;
                                entity = NEWS;
                                break;
                            case UPLOAD_PHOTO_FOR_ACTOR:
                                directory = ACTORS_DIRECTORY;
                                entity = ACTOR;
                                break;
                        }
                    } else if (fieldName.equals(FILENAME)) {
                        filename = value;
                    }
                } else {
                    processUploadedFile(item, directory);
                }
            }
            response.sendRedirect(previousQuery);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage(), e);
            response.sendRedirect(previousQuery + QUERY_AMPERSAND + ERROR + QUERY_EQUALS + MESSAGE_OF_ERROR);
        } catch (IOException e) {
            logger.log(Level.ERROR, e.getMessage(), e);
            response.sendRedirect(previousQuery + QUERY_AMPERSAND + ERROR + QUERY_EQUALS + MESSAGE_OF_ERROR_2);
        } catch (FileUploadException e) {
            logger.log(Level.ERROR, e.getMessage(), e);
            response.sendRedirect(previousQuery + QUERY_AMPERSAND + ERROR + QUERY_EQUALS + MESSAGE_OF_ERROR_3);
        } catch (CommandException e) {
            logger.log(Level.ERROR, e.getMessage(), e);
            response.sendRedirect(previousQuery + QUERY_AMPERSAND + ERROR + QUERY_EQUALS + MESSAGE_OF_ERROR_4);
        } catch (Exception e) {
            logger.log(Level.ERROR, e.getMessage(), e);
            response.sendRedirect(previousQuery + QUERY_AMPERSAND + ERROR + QUERY_EQUALS + MESSAGE_OF_ERROR_5);
        }
    }

    /**
     * Saves file on server in given directory.
     *
     * @param item file
     * @param directory path
     * @throws Exception when error occurred processing file
     */
    private void processUploadedFile(FileItem item, String directory) throws Exception {
        File uploadedFile = null;
        do {
            if (item.getName().endsWith(FILE_EXTENSION)) {
                String path = directory + filename + FILE_EXTENSION;
                File file = new File(path);
                if (file.exists()) {
                    if (file.delete()) {
                        uploadedFile = new File(path);
                    }
                } else {
                    uploadedFile = new File(path);
                }
            } else {
                throw new CommandException("Wrong file extension only .jpg is allowed");
            }
        } while (uploadedFile.exists());

        // creating file
        uploadedFile.createNewFile();
        // writing data into it
        item.write(uploadedFile);
        AdminService adminService = ServiceFactory.getInstance().getAdminService();
        adminService.updateImage(entity, filename, filename + FILE_EXTENSION);
    }
}
