package resources.databases.impl.relacional.javadb;

import java.sql.Connection;
import java.sql.DriverManager;

import resources.databases.impl.relacional.ConexaoInterface;

public class ConexaoJavaDbDerby implements ConexaoInterface {
    private String usuario;
    private String senha;
    private String hostname;
    private int porta;
    private String nomeBancoDados;
    private Connection conexao;

    public ConexaoJavaDbDerby(String usuario, String senha, String hostname, int porta, String nomeBancoDados) {
        this.usuario = usuario;
        this.senha = senha;
        this.hostname = hostname;
        this.porta = porta;
        this.nomeBancoDados = nomeBancoDados;
    }
    
    @Override
    public Connection getConnection() {
        if(conexao == null)
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                String url = "jdbc:derby://" + hostname + ":" + porta + "/" + nomeBancoDados;
                System.out.println(url + " :: " + usuario + " :: " + senha);
                conexao = DriverManager.getConnection(url, usuario, senha);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        return conexao;
    }

    @Override
    public void close() {
        try {
            conexao.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        conexao = null;
    }

}
