package by.hustlestar.service.iface;

import by.hustlestar.bean.entity.Actor;
import by.hustlestar.service.exception.ServiceException;

/**
 * ActorService interface is used to interact with Actor beans mainly.
 */
public interface ActorService {

    /**
     * This method shows any actor by its id.
     *
     * @param id id of actor
     * @return Actor bean object with filled in fields.
     * @throws ServiceException if any error occurred while processing method.
     */
    Actor getActor(String id) throws ServiceException;


}
