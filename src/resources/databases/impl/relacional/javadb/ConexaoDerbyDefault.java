package resources.databases.impl.relacional.javadb;

import resources.databases.impl.relacional.ConexaoInterface;

public class ConexaoDerbyDefault {
	public static ConexaoInterface defaultConection(){
		ConexaoInterface conexao = new ConexaoJavaDbDerby("app", "app", "127.0.0.1", 1527, "tccpingado");
		return conexao;
	}
}
