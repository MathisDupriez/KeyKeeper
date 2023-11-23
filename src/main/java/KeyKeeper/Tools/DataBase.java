package KeyKeeper.Tools;

import KeyKeeper.Modele.Groupe;
import KeyKeeper.Modele.Password;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private static final String URL = "jdbc:postgresql://localhost:5432/passwd";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1913141627";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insertGroup(String name) {
        String sql = "INSERT INTO public.\"groupe\"(name) VALUES(?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public List<String> fetchGroupData() {
        List<String> groups = new ArrayList<>();
        String sql = "SELECT id, name FROM groupe";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Groupe group = new Groupe(id, name);
                groups.add(group.toString());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return groups;
    }
    public void updateGroup(int id, String newName) {
        String sql = "UPDATE group SET name = ? WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newName);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void deleteGroup(int id) {
        String sql = "DELETE FROM groupe WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void insertPassword(Password password, Integer groupId) {
        String sql = "INSERT INTO public.\"password\"(Username, Password, Description, \"group\") VALUES(?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, password.getName());
            pstmt.setString(2, password.getPassword());
            pstmt.setString(3, password.getDescription());
            if (groupId != null) {
                pstmt.setInt(4, groupId);
            } else {
                pstmt.setNull(4, java.sql.Types.INTEGER);
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public List<Password> fetchPasswordData() {
        List<Password> passwords = new ArrayList<>();
        String sql = "SELECT id, Username, Password, Description, \"group\" FROM password";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Password pwd = new Password(
                        rs.getString("Username"),
                        rs.getString("Description"),
                        rs.getString("Password")
                );
                passwords.add(pwd);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return passwords;
    }
    public void updatePassword(int id, Password newPassword, Integer newGroupId) {
        String sql = "UPDATE password SET Username = ?, Password = ?, Description = ?, \"group\" = ? WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newPassword.getName());
            pstmt.setString(2, newPassword.getPassword());
            pstmt.setString(3, newPassword.getDescription());
            if (newGroupId != null) {
                pstmt.setInt(4, newGroupId);
            } else {
                pstmt.setNull(4, java.sql.Types.INTEGER);
            }
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void deletePassword(int id) {
        String sql = "DELETE FROM password WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
