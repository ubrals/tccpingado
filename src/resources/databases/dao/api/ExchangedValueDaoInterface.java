package resources.databases.dao.api;

import java.util.List;
import entities.values.*;

public interface ExchangedValueDaoInterface {
	List<Content> listContent();
	Content findByContentId(long id);
}
