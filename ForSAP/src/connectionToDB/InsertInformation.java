package connectionToDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertInformation {
    public static void main(String[] args) throws SQLException {
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration" , "root" ,"OliaVladova2303");
            //String query = "delete from users where id = %s";
            String query = "insert into clients(name, password, email, role_id) values(?,?,?,?)";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString (1, "Stefan Georgiev");
            preparedStatement.setString (2, "ravenclaw15");
            preparedStatement.setString (3, "sGeorgiev20@gmail.com");
            preparedStatement.setString (4, "2");
            preparedStatement.execute();
            preparedStatement.close();
        }catch (Exception exception){
            System.out.println("SQLException: " + exception.getMessage());
        }


    }
}
