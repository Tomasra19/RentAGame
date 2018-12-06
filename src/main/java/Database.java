import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
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

//    public void select() {
//        String query = "SELECT * FROM games";
//        try(Connection connection = dataSource.getConnection();
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(query)) {
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String name = resultSet.getString("name");
//                String platform = resultSet.getString("platform");
//                int price = resultSet.getInt("price");
//                String imgURL = resultSet.getString("imgURL");
//                System.out.println(id + name +platform +price + imgURL);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
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
        return game;
    }
    public void insertOrder (String name,String platform, String startDate, String returnDate) {
        String query = "INSERT INTO rentorders (name, platform, startDate, returnDate) VALUES (?, ?, ?, ?)";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1,name);
            statement.setString(2,platform);
            statement.setString(3,startDate);
            statement.setString(4,returnDate);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<CalendarObject> getOrders() {
        String query = "SELECT * FROM rentorders";
        ArrayList<CalendarObject> orders = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                String platform = resultSet.getString("platform");
                String name = resultSet.getString("name");
                if (name.contains("'")) {
                    name = name.replace("'","&#39;");
                }
                if (platform.equals("PS4")) {
                    CalendarObject order = new CalendarObject(
                            resultSet.getInt("id"),
                            name,
                            resultSet.getString("startDate"),
                            resultSet.getString("returnDate"),
                            "#368ce7");
                    orders.add(order);
                } else {
                    CalendarObject order = new CalendarObject(
                            resultSet.getInt("id"),
                            name,
                            resultSet.getString("startDate"),
                            resultSet.getString("returnDate"),
                            "green");
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
