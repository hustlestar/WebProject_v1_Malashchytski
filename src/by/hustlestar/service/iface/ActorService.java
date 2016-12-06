package by.hustlestar.service.iface;

import by.hustlestar.bean.entity.Actor;
import by.hustlestar.service.exception.ServiceException;

/**
 * Created by dell on 05.12.2016.
 */
public interface ActorService {

    Actor viewActor(String id) throws ServiceException;


}
