package tests;

import org.testng.annotations.Test;
import utils.DBUtil;

import java.sql.*;

public class MySQLCrudTest {

    @Test
    public void testInsertAndRead() throws SQLException {
        System.out.println("[TEST] Starting testInsertAndRead...");

        Connection conn = DBUtil.getConnection();
        Statement stmt = conn.createStatement();

        System.out.println("[DEBUG] Creating table if it doesn't exist...");
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50))");

        System.out.println("[DEBUG] Inserting user: 'Captain Fantastic'...");
        stmt.executeUpdate("INSERT INTO users (name) VALUES ('Captain Fantastic')");

        System.out.println("[DEBUG] Querying for user: 'Captain Fantastic'...");
        ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE name = 'Captain Fantastic'");

        boolean userFound = rs.next();
        System.out.println("[RESULT] User found: " + userFound);

        assert userFound : "User was not inserted properly";

        stmt.close();
        conn.close();
        System.out.println("[TEST] testInsertAndRead completed.\n");
    }

    @Test
    public void testUpdateAndDelete() throws SQLException {
        System.out.println("[TEST] Starting testUpdateAndDelete...");

        Connection conn = DBUtil.getConnection();
        Statement stmt = conn.createStatement();

        System.out.println("[DEBUG] Updating user name from 'Captain Fantastic' to 'Sweet Child'...");
        int rowsUpdated = stmt.executeUpdate("UPDATE users SET name = 'Sweet Child' WHERE name = 'Captain Fantastic'");
        System.out.println("[RESULT] Rows updated: " + rowsUpdated);

        System.out.println("[DEBUG] Deleting user: 'Sweet Child'...");
        int rowsDeleted = stmt.executeUpdate("DELETE FROM users WHERE name = 'Sweet Child'");
        System.out.println("[RESULT] Rows deleted: " + rowsDeleted);

        System.out.println("[DEBUG] Verifying user deletion...");
        ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE name = 'Sweet Child'");
        boolean userStillExists = rs.next();

        System.out.println("[RESULT] User still exists: " + userStillExists);
        assert !userStillExists : "User was not deleted properly";

        stmt.close();
        conn.close();
        System.out.println("[TEST] testUpdateAndDelete completed.\n");
    }
}
