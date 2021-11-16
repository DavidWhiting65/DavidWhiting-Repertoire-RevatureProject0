package com.revature.repositories;

import com.revature.models.Song;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/* This class will be used to define methods that we can use to interact with our database
The methods in this class will be CRUD methods

C - Create ( add (or INSERT INTO) a new record into our database)
R - Read (SELECT - retrieve data from the database)
U - Update (UPDATE - update data in our database)
D - Delete (DELETE FROM - remove data from our database)
 */

public class SongRepo implements CrudRepository<Song> {
    private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    // Create
    @Override
    public Song add(Song a) {

        try (Connection conn = cu.getConnection()) {

            String sql = "insert into songs values (default, ?, ?) returning *";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, a.getTitle());
            ps.setString(2, a.getAuthor());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                a.setId(rs.getInt("id"));
                return a;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Read
    @Override
    public Song getById(Integer id) {

        /* try with resources -> a way to initialize a resource that will
        then be closed when we're done with it */

        try (Connection conn = cu.getConnection()) {
            String sql = "select * from songs where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql); //helps prevent SQL Injection attacks
            ps.setInt(1, id); // parameter indexes start from 1 not 0

            ResultSet rs = ps.executeQuery();

            if (rs.next()){

                Song a = new Song();

                a.setId(rs.getInt("id"));
                a.setTitle(rs.getString("title"));
                a.setAuthor(rs.getString("author"));

                return a;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }
    @Override
    public List<Song> getAll() {

        List<Song> songs = new ArrayList<>();

        try (Connection conn = cu.getConnection()) {

            String sql ="select * from songs order by title";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Song a = new Song(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author")
                );

                songs.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return songs;
    }

    // Update - this will eventually become a PUT Http Request
   @Override
    public void update(Song a) {
        try (Connection conn = cu.getConnection()) {

            String sql = "update songs set title = ?, author = ? where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, a.getTitle());
            ps.setString(2, a.getAuthor());
            ps.setInt(3, a.getId());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete
    @Override
    public boolean delete(Integer id) {
        try (Connection conn = cu.getConnection()) {

            String sql = "delete from songs where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
