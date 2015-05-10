/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package optimus.core.conn;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class TConnectionPostgreSql extends TAbstractConnection {

    static String driver = "org.postgresql.Driver";
    static String url = "jdbc:postgresql://170.0.0.161:5432/Optimus_producao";
    //static String url = "jdbc:postgresql://170.0.0.161:5432/Optimus";
    static String user = "postgres";
    static String pass = "postgres";
    private static Connection isCon;

    protected void getConnection() throws SQLException{
        try{
        Class.forName(TConnectionPostgreSql.driver);

        isCon = (Connection) DriverManager.getConnection(TConnectionPostgreSql.url,TConnectionPostgreSql.user, TConnectionPostgreSql.pass);
        isCon.setTransactionIsolation(isCon.TRANSACTION_READ_COMMITTED);

        }catch (ClassNotFoundException e){
            throw new SQLException (e.getMessage());
        }
    }
    
    public Connection getSingleConnection() throws SQLException{
        /*   if (TConnectionPostgreSql.isCon==null){
          this.getConnection();
        }
        
        return isCon;*/
        this.getConnection();
        
        return isCon;

    }
    
}
    
