package jdb;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Lasanga Madushan on 3/11/2017.
 */
public class Main {
    public static void main(String[] args) throws Exception{
        creatTable();
        post();
        get();

    }

    public static ArrayList<String> get() throws Exception{
        try {
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT first, last FROM mytable");

            ResultSet result = statement.executeQuery();

            ArrayList<String> array = new ArrayList<String>();
            while (result.next()) {
                System.out.print(result.getString("first"));
                System.out.print(" ");
                System.out.println(result.getString("last"));

                array.add(result.getString("last"));
            }
            System.out.println("All recorded have been selected!");
            return array;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public static void post() throws Exception{
        final String var1 = "Lasanga";
        final String var2 = "Madushan";
        try {
            Connection con = getConnection();
            PreparedStatement post = con.prepareStatement("INSERT INTO mytable (first, last) VALUES ('"+var1+"', '"+var2+"')");
            post.executeUpdate();

        }catch (Exception e){
            System.out.println(e);
        }
        finally {
            System.out.println("Insert Completed.");
        }
    }

    public static void creatTable() throws Exception{
        try {
            Connection con = getConnection();
            PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS mytable(id int NOT NULL AUTO_INCREMENT, first varchar(255), last varchar(255),PRIMARY KEY(id))");
            create.executeUpdate();

        }catch (Exception e){System.out.println(e);}
        finally {
            System.out.println("Functon complete.");
        }
    }

    public static Connection getConnection() throws Exception{
        try {
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/workshop";
            String username ="user";
            String password = "welcome";
            Class.forName(driver);

            Connection conn = DriverManager.getConnection(url,username,password);
            System.out.println("Connected");
            return conn;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
}
