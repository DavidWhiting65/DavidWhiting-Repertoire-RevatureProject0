package com.revature.repositories;

import com.revature.models.Repertoire;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepertoireRepo implements CrudRepository<Repertoire> {
    private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    // Create
    @Override
    public Repertoire add(Repertoire a) {

        try (Connection conn = cu.getConnection()) {

            String sql = "insert into repertoires values (?, ?, 0, default) returning *";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, a.getMusicianId());
            ps.setInt(2, a.getSongId());

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
    public Repertoire getById(Integer id) {

        /* try with resources -> a way to initialize a resource that will
        then be closed when we're done with it */

        try (Connection conn = cu.getConnection()) {
            String sql = "select * from repertoires where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql); //helps prevent SQL Injection attacks
            ps.setInt(1, id); // parameter indexes start from 1 not 0

            ResultSet rs = ps.executeQuery();

            if (rs.next()){

                Repertoire a = new Repertoire();

                a.setLikes(rs.getInt("likes"));
                a.setMusicianId(rs.getInt("musicians_id"));
                a.setSongId(rs.getInt("songs_id"));
                a.setId(rs.getInt("Id"));

                return a;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    public List<Repertoire> getByMusicianId(int musiciansId) {

        List<Repertoire> repertoires = new ArrayList<>();

        try (Connection conn = cu.getConnection()) {

            String sql ="select repertoires.id, songs.title, songs.author from repertoires inner join users on repertoires.musicians_id=users.id inner join songs on repertoires.songs_id=songs.id where \n" +
                    "repertoires.musicians_id = ? order by songs.title;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, musiciansId);

            ResultSet rs = ps.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            while (rs.next()) {
                for (int i =1; i <= columnsNumber; i++) {
                    if (i >1) System.out.print(", ");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue);
                    //System.out.print(rsmd.getColumnName(i) + ": " + columnValue);
                }
                System.out.println("");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return repertoires;
    }

@Override
    public List<Repertoire> getAll() {

        List<Repertoire> repertoires = new ArrayList<>();

        try (Connection conn = cu.getConnection()) {

            String sql ="select repertoires.musicians_id, users.first_name, users.last_name, repertoires.songs_id, songs.title, songs.author from repertoires inner join users on repertoires.musicians_id=users.id inner join songs on repertoires.songs_id=songs.id;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            while (rs.next()) {
                for (int i =1; i <= columnsNumber; i++) {
                    if (i >1) System.out.print(", ");
                    String columnValue = rs.getString(i);
                    System.out.print(rsmd.getColumnName(i) + ": " + columnValue);
                }
                System.out.println("");

//                Repertoire a = new Repertoire(
//                        rs.getInt("likes"),
//                        rs.getInt("musicians_id"),
//                        rs.getInt("songs_id")
//                );
//
//                repertoires.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return repertoires;
    }

    // Update - this will eventually become a PUT Http Request
    @Override
    public void update(Repertoire a) {
        try (Connection conn = cu.getConnection()) {

            String sql = "update repertoires set likes = ? where musicians_id = ? and songs_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, a.getMusicianId());
            ps.setInt(2, a.getSongId());
            ps.setInt(3, a.getLikes());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete
    @Override
    public boolean delete(Integer id) {
        try (Connection conn = cu.getConnection()) {

            String sql = "delete from repertoires where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            boolean result = ps.execute();

            // needs legit if statement
            if (result)
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
