package software.infrastructure._deprec_;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.ResultSet; 
import java.sql.Statement; 
import java.util.*; 
import java.io.*; 
import java.sql.SQLException; 


public class _deprec_Main {
    public static final boolean EMBEDDED = true; 
    public static final String URL = "jdbc:derby://localhost:1527/"; 
    public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver"; 
    public static final String DEFAULT_DB = "tccpingado"; 
    private static final String USER = "app";  // substitute your own! 
    private static final String PASSWORD = "app";  // substitute your own! 
    private Connection con = null;
    private String databaseName = DEFAULT_DB;
    private String startDir = System.getProperty("user.home");
    
    public static void main(String[] args) { 
        // TODO code application logic here 
//        Main myMain = new Main(); 
    } 

    public _deprec_Main() { 
        if (makeDB() != 0) { 
            System.exit(-1); 
        } 
    } 
    
    public int makeDB() { 
        String sql; 

        // make database connection 
        try { 
            Class.forName(DRIVER).newInstance(); 
        } catch (Exception e) { 
            System.err.println("Exception creating DriverManager: " + e); 
            return -1; 
        } 
        System.out.println("Success in creating DriverManager instance"); 

        // create a new database and connect to it 
        Properties props = new Properties(); 
        props.put("user", USER); 
        props.put("password", PASSWORD); 
        String full_url = URL + DEFAULT_DB + ";create=true"; 
        try { 
            con = DriverManager.getConnection(full_url, props); 
        } catch (Exception e2) { 
            System.err.println("Exception creating new database '" 
                    + full_url + "': " + e2); 
            return -1; 
        } 
        System.out.println("Success in creating database '" 
                + full_url + "'"); 

        try { 
            sendSQL("drop table CRYPTOPERSON"); 
            sendSQL("drop table ECONTRACT"); 
            sendSQL("drop table EXCHANGEDVALUE"); 
        } catch (SQLException ex) { 
            // allow to fail silently 
        } 

        // build the table anew 
        try { 
            sql = "create table CRYPTOPERSON ("
            	+ "ID int primary key,"
            	+ "NAME varchar(512),"
            	+ "WALLET varchar(512),"
            	+ "ROLE varchar(8)"
                + ")";
            sendSQL(sql); 
        } catch (Exception ex) { 
            System.err.println("Exception creating table 'CRYPTOPERSON': " + ex); 
            ex.printStackTrace();
            return -1; 
        } 
        System.out.println("Success in creating table 'CRYPTOPERSON'"); 
        // build the table anew 
        try { 
            sql = "create table ECONTRACT ("
            	+ "ID int primary key,"
            	+ "CONTENTID int,"
            	+ "PARTYID1 int,"
            	+ "PARTYID2 int,"
            	+ "MICROFRACTION int,"
            	+ "JITTIMETOSTART varchar(14),"
            	+ "ENACTMENTVALID int,"
            	+ "MANAGEMENTSTATUS int"
            	+ ")";
            sendSQL(sql); 
        } catch (Exception ex) { 
            System.err.println("Exception creating table 'ECONTRACT': " + ex); 
            ex.printStackTrace();
            return -1; 
        } 
        System.out.println("Success in creating table 'ECONTRACT'"); 
        // build the table anew 
        try { 
            sql = "create table EXCHANGEDVALUE ("
            	+ "ID int primary key,"
            	+ "TYPE varchar(128),"
            	+ "SUBTYPE varchar(128),"
            	+ "VALUE varchar(128),"
            	+ "ECONTRACTID int,"
            	+ "TITLE varchar(512),"
            	+ "SIZE int,"
            	+ "PRODUCERID int,"
            	+ "ISPID int,"
            	+ "LOCATION varchar(512),"
            	+ "FILENAME varchar(512)"
            	+ ")";
            sendSQL(sql); 
        } catch (Exception ex) { 
            System.err.println("Exception creating table 'EXCHANGEDVALUE': " + ex); 
            ex.printStackTrace();
            return -1; 
        } 
        System.out.println("Success in creating table 'EXCHANGEDVALUE'"); 

        // add some data to the table 
        sql = "insert into \"econtract\" values (102938, 918273, 18374, 10000, 60, '201801022345', 1, 1)"
                + "('row1-col1', 'row1-col2')"; 
//        sendSQL(sql); 

        // ...

        // show table contents 
        // ...
        
        // dump the table data to a file 
        // ...
        
        // clear out the table 
        // ...
        return 0;
    }
    
    public ResultSet sendSQL(String sql) throws SQLException { 
        ResultSet r = null; 
        Statement statement = con.createStatement(); 
        if (statement.execute(sql)) { 
            r = statement.getResultSet(); 
        } 
        return r; 
    } 
    
    // print all the records in the specified table 
    public int printTable(String tname) { 
        System.out.println("Contents of '" + tname + "':"); 
        System.out.println("------------------------------------"); 
        String sql; 
        sql = "SELECT * FROM " + tname; 
        try { 
            ResultSet r = sendSQL(sql); 
            if (r == null) { 
                System.err.println("Null result from query"); 
                return -1; 
            } 

            while (r.next()) { 
                int id = r.getInt("ID"); 
                String name1 = r.getString("NAME1"); 
                String name2 = r.getString("NAME2"); 
                System.out.println("" + id + ": '" + name1 + "'\t'" 
                        + name2 + "'"); 
            } 
            System.out.println("------------------------------------"); 
        } catch (Exception ex) { 
            System.err.println("Exception querying table '" 
                    + tname + "': " + ex); 
            System.err.println("sql was '" + sql + "'"); 
            return -1; 
        } 
        return 0; 
    } 
    
    // return the database name 
    public String getDatabaseName() { 
        return databaseName; 
    } 

}
