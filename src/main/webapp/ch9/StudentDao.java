package ch9;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    Connection conn = null;
    PreparedStatement pstmt;

    final String JDBC_DRIVER = "org.h2.Driver";
    final String JDBC_URL = "jdbc:h2:tcp://localhost/~/test";

    public void open() throws ClassNotFoundException {
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(JDBC_URL,"hanju","poca9090!!");
        } catch (Exception e) {
            e.printStackTrace();;
        }
    }

    public void close(){
        try{
            pstmt.close();
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();;
        }
    }

    public void insert(Student s) throws ClassNotFoundException {
        open();
        String sql = "INSERT INTO student(username, univ, birth, email) values(?,?,?,?)";

        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, s.getUsername());
            pstmt.setString(2, s.getUniv());
            pstmt.setDate(3, s.getBirth());
            pstmt.setString(4,s.getEmail());

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            close();
        }
    }

    public List<Student> getAll() throws ClassNotFoundException {
        open();
        List<Student> students = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement("select * from student");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setUsername(rs.getString("username"));
                s.setUniv(rs.getString("univ"));
                s.setBirth(rs.getDate("birth"));
                s.setEmail(rs.getString("email"));

                students.add(s);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close();
        }
        return students;
    }
}

