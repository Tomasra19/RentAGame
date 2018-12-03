import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {

   private BasicDataSource dataSource;
    public Database() {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:mysql://localhost:3306/rentagame");
        dataSource.setMaxIdle(1);
        dataSource.setInitialSize(1);
        dataSource.setValidationQuery("SELECT 1");
    }

    public void select() {
        String query = "SELECT * FROM games";
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String platform = resultSet.getString("platform");
                int price = resultSet.getInt("price");
                String imgURL = resultSet.getString("imgURL");
                System.out.println(id + name +platform +price + imgURL);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//Sudet MYSQL y arraylista
    public ArrayList<Game> getGames() {
        String query = "SELECT * FROM games";
        ArrayList<Game> games = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Game game = new Game(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("platform"),
                        resultSet.getInt("price"),
                        resultSet.getString("imgURL"),
                        resultSet.getString("description"));
                games.add(game);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return games;
    }

    public void insert(String name, String platform, int price, String imgURL) {
        String query = "INSERT INTO games (name, platform, price, imgURL) " +
                "VALUES ('" + name + "', '" + platform + "', '" + price + "', " + imgURL + ")";
        try(Connection c = dataSource.getConnection();
            Statement s = c.createStatement()) {
            int count = s.executeUpdate(query);
            System.out.println("Rows updated: " + count);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Game getGameById(int id) {
        String query = "SELECT * FROM games WHERE id =" + id;
        Game game = new Game();
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                game = new Game(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("platform"),
                        resultSet.getInt("price"),
                        resultSet.getString("imgURL"),
                        resultSet.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(game.getId());
        return game;
    }
}
