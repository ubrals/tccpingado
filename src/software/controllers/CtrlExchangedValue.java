package software.controllers;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import entities.values.Content;
import resources.databases.dao.api.ExchangedValueDaoInterface;
import resources.databases.impl.relacional.ExchangedValueDaoReacional;
import resources.databases.impl.relacional.javadb.ConexaoDerbyDefault;

public class CtrlExchangedValue implements ExchangedValueDaoInterface {
	
    /*
     * (non-Javadoc)
     * @see resources.databases.dao.api.ExchangedValueDaoInterface#listContents()
     */
    @Override
	public Collection<Content> listContents() {
		ExchangedValueDaoInterface exv_dao = new ExchangedValueDaoReacional(ConexaoDerbyDefault.defaultConection());
		
		return exv_dao.listContents();
	}
	
	
	
	/*
	 * (non-Javadoc)
	 * @see resources.databases.dao.api.ExchangedValueDaoInterface#findByContentId(long)
	 */
	@Override
	public Content findByContentId(long id) {
		ExchangedValueDaoInterface exv_dao = new ExchangedValueDaoReacional(ConexaoDerbyDefault.defaultConection());
		return exv_dao.findByContentId(id);
	}
	
	
	
	/**
	 * @return String
	 * @param contractId
	 * @param location
	 * @param filename
	 */
    public String makeSymLink(long contractId, String location, String filename){
        String file[] = filename.split("\\.");
        String extension = file[file.length-1];
        String old_file = location + "/" + filename;
        String new_file = location + "/" + contractId + "." + extension;
        String command[] = {
                "sudo",
                "/bin/ln",
                "-s",
                old_file, 
                new_file
        };
        try {
            Runtime.getRuntime().exec(command);
//            for(String cmd : command)
//                System.err.print(cmd + " ");
//            System.err.println("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    	return new_file;
    }
    
    
    
    /**
     * 
     * @param server
     * @param server_path
     * @param contract_file
     * @return String
     */
    public String getURL(String server, String server_path, String contract_file){
        String uri[] = contract_file.split("/");
        String file = uri[uri.length-1];
        return server + server_path + file;
    }
}
