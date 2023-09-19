package Master.HistoricalCandles.ModelPKG.PostGreSQL;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostGreSQLJDBC {
    public static void main(String[] args) {

        Connection PostGre = null;

        try {
            Class.forName("org.postgresql.Driver");
            PostGre = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/testdb", "postgres", "123");
        }

        catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        System.out.println("Opened database successfully");

    }

}