package com.revature.repositories;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepo implements CrudRepository<User> {

    ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    //CREATE
    @Override
    public User add(User a) {

        try (Connection conn = cu.getConnection()) {

            System.out.println(" inserting into users");
            String sql = "insert into users values (default, ?, ?, ?, ?, ?) returning *";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1, a.getIsFan());
            ps.setString(2, a.getFirstName());
            ps.setString(3, a.getLastName());
            ps.setString(4, a.getUsername());
            ps.setString(5, a.getPassword());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                a.setId(rs.getInt("id"));
            }

            if(a != null) {
                System.out.println("New user added");
                return a;
            }
            else
                System.out.println("New user added");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //READ
    @Override
    public User getById(Integer id) {

        /* try with resources -> a way to initialize a resource that will
        then be closed when we're done with it */

        try (Connection conn = cu.getConnection()) {
            String sql = "select * from users where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql); //helps prevent SQL Injection attacks
            ps.setInt(1, id); // parameter indexes start from 1 not 0

            ResultSet rs = ps.executeQuery();

            if (rs.next()){

                User a = new User();

                a.setId(rs.getInt("id"));
                a.setIsFan(rs.getBoolean("is_fan"));
                a.setFirstName(rs.getString("first_name"));
                a.setLastName(rs.getString("last_name"));

                return a;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    public User getByUsername(String username) {
        // parentheses around Connection conn = cu.getConnection means *"with resources"
        try (Connection conn = cu.getConnection()) {

            String sql = "select * from users where username = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User u = new User(
                        rs.getInt("id"),
                        rs.getBoolean("is_fan"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("username"),
                        rs.getString("password")
                );
                return u;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        // try *"with resources" automatically closes resources after execution
        // finally {
        //      conn.close();
        //  }

        return null;
    }

    public List<User> getAllMusicians() {

        List<User> users = new ArrayList<>();

        try (Connection conn = cu.getConnection()) {

            String sql ="select * from users where is_fan = false order by last_name;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                User a = new User(
                        rs.getInt("Id"),
                        rs.getBoolean("is_fan"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("username"),
                        rs.getString("password")
                );

                users.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public List<User> getAll() {

        List<User> users = new ArrayList<>();

        try (Connection conn = cu.getConnection()) {

            String sql ="select * from users";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                User a = new User(
                        rs.getInt("Id"),
                        rs.getBoolean("is_fan"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("username"),
                        rs.getString("password")
                );

                users.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    // UPDATE - this will eventually become a PUT Http Request
    @Override
    public void update(User a) {
        try (Connection conn = cu.getConnection()) {

            String sql = "update users set first_name = ?, last_name = ? where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, a.getFirstName());
            ps.setString(2, a.getLastName());
            ps.setInt(3, a.getId());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //DELETE
    @Override
    public boolean delete(Integer id) {
        try (Connection conn = cu.getConnection()) {

            String sql = "delete from users where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}