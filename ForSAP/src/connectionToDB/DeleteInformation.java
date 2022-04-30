package connectionToDB;

import java.sql.*;

public class DeleteInformation {

    public static void main(String[] args) throws SQLException {

        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
            String query = "delete from clients where clients.name = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, "Cvetan Sokolov");
            preparedStatement.execute();
            preparedStatement.close();
        } catch (Exception exception) {
            System.out.println("SQLException: " + exception.getMessage());
        }


    }
}
