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

    /**
     * 
     * @param usuario String
     * @param senha String
     * @param hostname String
     * @param porta int
     * @param nomeBancoDados String
     */
    public ConexaoJavaDbDerby(String usuario, String senha, String hostname, int porta, String nomeBancoDados) {
        this.usuario = usuario;
        this.senha = senha;
        this.hostname = hostname;
        this.porta = porta;
        this.nomeBancoDados = nomeBancoDados;
    }
    
    /*
     * (non-Javadoc)
     * @see resources.databases.impl.relacional.ConexaoInterface#getConnection()
     */
    @Override
    public Connection getConnection() {
        if(conexao == null)
            try {
//                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                
                String url = "jdbc:derby://" + hostname + ":" + porta + "/" + nomeBancoDados;
//                System.out.println(url + " :: " + usuario + " :: " + senha + " :: " + nomeBancoDados);
                conexao = DriverManager.getConnection(url, usuario, senha);
//                System.out.println(" >>" + conexao.getMetaData().getURL() + 
//                				   "." + conexao.getMetaData().getUserName() + 
//                				   "." + conexao.getSchema()
//                				   );
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        return conexao;
    }

    /*
     * (non-Javadoc)
     * @see resources.databases.impl.relacional.ConexaoInterface#close()
     */
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
