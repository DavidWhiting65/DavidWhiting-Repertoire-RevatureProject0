package com.revature.repositories;

import com.revature.models.Fangroup;
import com.revature.models.Repertoire;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FangroupRepo implements CrudRepository<Fangroup> {
    private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    // Create
    @Override
    public Fangroup add(Fangroup a) {

        try (Connection conn = cu.getConnection()) {

            String sql = "insert into fangroups values (?, ?, default) returning *";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, a.getFanId());
            ps.setInt(2, a.getMusicianId());

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

    public List<Fangroup> getByFanId(int fansId) {

        List<Fangroup> fangroups = new ArrayList<>();

        try (Connection conn = cu.getConnection()) {

            String sql = "select fangroups.fans_id, users.first_name, users.last_name, fangroups.musicians_id from fangroups inner join users on fangroups.musicians_id=users.id where \n" +
                    "fangroups.fans_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, fansId);

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
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fangroups;
    }

    public List<Fangroup> getByMusicianId(int musiciansId) {

        List<Fangroup> fangroups = new ArrayList<>();

        try (Connection conn = cu.getConnection()) {

            String sql = "select fangroups.fans_id, users.first_name, users.last_name, fangroups.musicians_id from fangroups inner join users on fangroups.fans_id=users.id where \n" +
                    "fangroups.musicians_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, musiciansId);

            ResultSet rs = ps.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            while (rs.next()) {
                for (int i =1; i <= columnsNumber; i++) {
                    if (i >1) System.out.print(", ");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println("");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fangroups;
    }


    // Read
    @Override
    public Fangroup getById(Integer id) {

        /* try with resources -> a way to initialize a resource that will
        then be closed when we're done with it */

        try (Connection conn = cu.getConnection()) {
            String sql = "select * from fangroups where fans_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql); //helps prevent SQL Injection attacks
            ps.setInt(1, id); // parameter indexes start from 1 not 0

            ResultSet rs = ps.executeQuery();

            if (rs.next()){

                Fangroup a = new Fangroup();

                a.setMusicianId(rs.getInt("musicians_id"));
                a.setFanId(rs.getInt("fans_id"));

                return a;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public List<Fangroup> getAll() {

        List<Fangroup> fangroups = new ArrayList<>();

        try (Connection conn = cu.getConnection()) {

            String sql ="select * from fangroups";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Fangroup a = new Fangroup(
                        rs.getInt("musicians_id"),
                        rs.getInt("fans_id")
                );

                fangroups.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Update - this will eventually become a PUT Http Request
    @Override
    public void update(Fangroup a) {
        try (Connection conn = cu.getConnection()) {

            String sql = "update fangroups set musicians_id = ? where fans_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, a.getMusicianId());
            ps.setInt(2, a.getFanId());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete
    @Override
    public boolean delete(Integer id) {
        try (Connection conn = cu.getConnection()) {

            String sql = "delete from fangroups where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
