package resources.databases.impl.relacional.javadb;

import resources.databases.impl.relacional.ConexaoInterface;

public class ConexaoDerbyDefault {
    /**
     * 
     * @return conexao ConexaoInterface instanceof ConexaoJavaDbDerby
     */
	public static ConexaoInterface defaultConection(){
		ConexaoInterface conexao = new ConexaoJavaDbDerby("app", "app", "localhost", 1527, "tccpingado");
//		ConexaoInterface conexao = new ConexaoJavaDbDerby("sys", "sys", "localhost", 1527, "tccpingado");
		return conexao;
	}
}
