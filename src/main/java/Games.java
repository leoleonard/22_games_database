//create table games_table(
//        id int auto_increment primary key,
//        name varchar(50) not null,
//        producent varchar(50),
//        type varchar(200),
//        rating int(2)
//        );
//
//        SELECT * FROM games_table;
//
//        insert into games_table(name, producent, type, rating) values
//        ("Spyro", "Pixel", "Adventure", 10),
//        ("Crash Bandicoot", "Disney", "Adventure", 9),
//        ("Need for Speed", "Fast and Furious", "Race", 7),
//        ("The Sims", "SimsCompany", "Social planning", 5),
//        ("Zoo Tycon", "Hungry cheetach", "Educational", 6);
//
//        SELECT * FROM games_table;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Games {


    private String url = "jdbc:mysql://localhost:3306/games?useSSL=false";
    private String username = "root";
    private String password = "#######";
    Connection connection;
    Statement statement;

    public Games(String url, String username, String password) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.url = url;
        this.username = username;
        this.password = password;
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();

        String query = "select * from games_table";
        ResultSet resultSet = statement.executeQuery(query);
        System.out.println("These are all games: ");
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString("Name");
            System.out.println(name);
        }
        connection.close();
    }

    public void GetRecordsByRanking() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter expecting rating of the game (1-10)");
        String userInput = scanner.next();
        System.out.println("These are games fullfiling the requirements: ");
        Statement statement = connection.createStatement();
        String query = "select * from games_table where rating =\"" + userInput + "\"";
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString("Name");
            String rating = resultSet.getString(5);
            System.out.println(name);
        }
        connection.close();
    }

    public void GetRecordsByCategory() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter desired game type: Adventure/Race/Educational/Social planning");
        String userInput = scanner.next();
        System.out.println("These are games fullfiling the requirements: ");
        Statement statement = connection.createStatement();
        String query = "select * from games_table where type =\"" + userInput + "\"";
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString("Name");
            String rating = resultSet.getString(5);
            String type = resultSet.getString(4);
            System.out.println(name + " " + type);
        }
        connection.close();
    }


    public void saveToFile(String fileName) throws IOException, SQLException {
        String title, producent, type;
        int rating;

        String query = "select * from games_table";
        ResultSet resultSet = statement.executeQuery(query);
        System.out.println(resultSet);
        ResultSet resultSet1 = statement.executeQuery(query);

        FileWriter fileWriter = new FileWriter((fileName + ".csv"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        while (resultSet.next()) {
            title = resultSet.getString(2);
            producent = resultSet.getString(3);
            type = resultSet.getString(4);
            rating = resultSet.getInt(5);

            bufferedWriter.write(title + ";" + producent + ";" + type + ";" + rating + ";");
            bufferedWriter.newLine();
        }
        bufferedWriter.close();

    }


}
