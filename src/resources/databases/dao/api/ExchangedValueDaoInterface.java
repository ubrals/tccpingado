package resources.databases.dao.api;

import java.util.Collection;
import java.util.List;
import entities.values.*;

public interface ExchangedValueDaoInterface {
	Collection<Content> listContent();
	Content findByContentId(long id);
}
