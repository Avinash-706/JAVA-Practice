import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Storage {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/scott?useSSL=false&allowPublicKeyRetrieval=true";
        String user = "root";
        String password = "Asansol@0341";

        try {
            
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to MySQL successfully");

            Statement st = con.createStatement();

            System.out.println("-- Showing Tables --");
            ResultSet rs = st.executeQuery("SHOW TABLES;");
            while(rs.next())    System.out.println(rs.getString(1));
            
            
            System.out.println("-- Print All Data From EMP Table --");
            rs = st.executeQuery("SELECT * FROM EMP;");
            while(rs.next())    {
                System.out.println(rs.getString(1) + 
                rs.getString(2) + rs.getString(3) + rs.getString(4));
            }
            
            

            con.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}