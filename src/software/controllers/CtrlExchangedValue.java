package software.controllers;

import java.util.List;

import entities.values.Content;
import resources.databases.dao.api.ExchangedValueDaoInterface;
import resources.databases.impl.relacional.ExchangedValueDaoReacional;
import resources.databases.impl.relacional.javadb.ConexaoDerbyDefault;

public class CtrlExchangedValue implements ExchangedValueDaoInterface {
	public String makeSymLink(long contractId){
		return (String) null;
	}

	@Override
	public List<Content> listContent() {
		ExchangedValueDaoInterface exv_dao = new ExchangedValueDaoReacional(ConexaoDerbyDefault.defaultConection());
		
		return exv_dao.listContent();
	}

	@Override
	public Content findByContentId(long id) {
		ExchangedValueDaoInterface exv_dao = new ExchangedValueDaoReacional(ConexaoDerbyDefault.defaultConection());
		
		return exv_dao.findByContentId(id);
	}
}
