package app.db;

import java.sql.*;
import java.util.concurrent.TimeUnit;

public class InitializeDatabase {
    private static void createSchema() throws SQLException {
        Connection conn = DriverManager.getConnection(DBConstants.DB_URL);
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("drop table goals");
        stmt.executeUpdate("create table goals (" +
                "id integer not null generated always as identity(start with 1, increment by 1)," +
                "dim_path varchar(1024) not null," +
                "description varchar(1024) not null," +
                "type integer not null," +
                "creation_date TIMESTAMP default CURRENT_TIMESTAMP," +
                "deadline TIMESTAMP," +
                "completion_time TIMESTAMP," +
                "status integer not null," +
                "constraint goals_pk primary key (id)," +
                "constraint goals_uk unique (description)" +
                ")");
        conn.close();
    }


    public static void main(String[] args) throws SQLException {
//        createSchema();
        insertSomeData();
        fetchData();
    }

    private static void fetchData() throws SQLException {
        Connection conn = DriverManager.getConnection(DBConstants.DB_URL);
        ResultSet rs = conn.createStatement().executeQuery("select * from goals where dim_path like 'root.A%'");
        while (rs.next()) {
            int id = rs.getInt("id");
            System.out.println(id);
            String dimPath = rs.getString("dim_path");
            String desc = rs.getString("description");
            Timestamp cr_date = rs.getTimestamp("creation_date");
            System.out.println(dimPath);
            System.out.println(desc);
            System.out.println(cr_date);
        }
        conn.close();
    }

    private static void insertSomeData() throws SQLException {
        Connection conn = DriverManager.getConnection(DBConstants.DB_URL);
        PreparedStatement stmt = conn.prepareStatement("insert into goals (dim_path,description,type,creation_date,deadline,completion_time,status)" +
                "values (?,?,?,?,?,?,?)");
        stmt.setString(1, "root.HHH");
        stmt.setString(2, "Do this  sdfdf something");
        stmt.setInt(3, 1);
        stmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
        stmt.setTimestamp(5, new Timestamp(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(7)));
        stmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
        stmt.setInt(7, 1);
        boolean vv = stmt.execute();
        System.out.println(vv);
        stmt.close();
        conn.close();
    }
}
