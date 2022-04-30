package connectionToDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateInformation {
    public static void main(String[] args) throws SQLException {

        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
            String query = "update suppliers set name = ? where name = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, "TVKeeper");
            preparedStatement.setString(2, "Boreks");
            preparedStatement.execute();
            preparedStatement.close();
        } catch (Exception exception) {
            System.out.println("SQLException: " + exception.getMessage());
        }


    }
}
