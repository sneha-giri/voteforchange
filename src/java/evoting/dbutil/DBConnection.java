package evoting.dbutil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection{
    private static Connection conn=null;
    static{
        try
            {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver successfully loaded");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-QD2J0PH:1521/XE","evoting","evoting");
            System.out.println("Connected successfully to the DB");
            }
            catch(ClassNotFoundException cnf)
            {
               cnf.printStackTrace(); 
            }
            catch(SQLException sqlex){
                sqlex.printStackTrace();
            }
    }
    public static Connection getConnection()
    {
        return conn;
    }
    public static void closeConnection(){
        try{
            if(conn!=null)
                conn.close();
             }
         catch(SQLException sqlex){
                sqlex.printStackTrace();
             }
         }
}



