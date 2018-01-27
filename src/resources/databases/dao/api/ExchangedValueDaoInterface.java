package resources.databases.dao.api;

import java.util.Collection;
import java.util.List;
import entities.values.*;

public interface ExchangedValueDaoInterface {
    /**
     * 
     * @return {@link Collection} of {@link Content}
     */
	Collection<Content> listContents();
	/**
	 * 
	 * @param id long as Content id
	 * @return {@link Content}
	 */
	Content findContentById(long id);
}
