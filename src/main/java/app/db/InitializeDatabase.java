package app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InitializeDatabase {
    private static void createSchema() throws SQLException {
        Connection conn = DriverManager.getConnection(DBConstants.DB_URL);
        Statement stmt = conn.createStatement();
//        stmt.executeUpdate("drop table goals");
        stmt.executeUpdate("create table goals (" +
                "id integer not null generated always as identity(start with 1, increment by 1)," +
                "dim_path varchar(1024) not null," +
                "description varchar(1024) not null," +
                "type integer not null," +
                "creation_date TIMESTAMP default CURRENT_TIMESTAMP," +
                "deadline TIMESTAMP," +
                "completion_time TIMESTAMP," +
                "status integer not null," +
                "constraint goals_pk primary key (id)" +
                ")");
    }

    public static void main(String[] args) throws SQLException {
        createSchema();
    }
}
