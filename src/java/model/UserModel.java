package model;

import dto.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Alejandra
 */
@Named("userModel")
@RequestScoped
public class UserModel {
    

    Connection connection;

    public Connection getConnection() {
        System.out.println("model.UserModel.getConnection()");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mbank", "root", "");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return connection;
    }

    // Guardar Registros
    public boolean save(User user) {
        System.out.println("model.UserModel.save()");

        int result = 0;
        try {
            connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement("insert into bank_request(identification_user,full_name_user,address_user,flag_datacredito) values(?,?,?,?)");
            stmt.setInt(1, user.getIdentification());
            stmt.setString(2, user.getFullName());
            stmt.setString(3, user.getAddress());
            stmt.setBoolean(4, false);
            result = stmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    // Lista de Registros
    public List<User> usersList() {
        System.out.println("model.UserModel.usersList()");
        List<User> usersList = new ArrayList();
        try {

            connection = getConnection();
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("select * from bank_request");
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setIdentification(rs.getInt("identification_user"));
                user.setFullName(rs.getString("full_name_user"));
                user.setAddress(rs.getString("address_user"));
                user.setFlagDatacredito(rs.getBoolean("flag_datacredito"));
                usersList.add(user);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return usersList;
    }

    // Eliminar Registros
    public boolean delete(int id) {
        System.out.println("model.UserModel.delete()");
        try {
            connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement("delete from bank_request where id = " + id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    // Editar de Registros
    public User edit(int id) {
        System.out.println("model.UserModel.edit()");
        User user = new User();
        System.out.println(id);
        try {
            connection = getConnection();
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("select * from bank_request where id = " + (id));
            rs.next();
            user.setId(rs.getInt("id"));
            user.setIdentification(rs.getInt("identification_user"));
            user.setFullName(rs.getString("full_name_user"));
            user.setAddress(rs.getString("address_user"));
            user.setFlagDatacredito(rs.getBoolean("flag_datacredito"));
            connection.close();
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return user;
    }

    // Actualizar Registros
    public boolean update(User user) {
        
        System.out.println("model.UserModel.update()");
        //int result = 0;
        try {
            connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement("update bank_request set identification_user=?,full_name_user=?,address_user=?,flag_datacredito=? where id=?");
            stmt.setInt(5, user.getId());
            stmt.setInt(1, user.getIdentification());
            stmt.setString(2, user.getFullName());
            stmt.setString(3, user.getAddress());
            stmt.setBoolean(4, user.getFlagDatacredito());
            stmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

}
