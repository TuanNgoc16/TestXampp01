package test.jdbc;

import com.mysql.jdbc.Driver;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jdk.internal.net.http.common.Utils.close;

public class StudentDbUtil {
    private DataSource dataSource;

    public StudentDbUtil(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    public List<Student> getStudent() throws Exception {
        List<Student> students = new ArrayList<>();
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            String url = "jdbc:mysql://localhost:3306/web_student_tracker";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            myConn = DriverManager.getConnection(url, username, password);

            String sql = "select * from student order by last_name";

            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery(sql);

            while (myRs.next()) {
                int id = myRs.getInt("id");
                String firstName = myRs.getString("firstName");
                String lastName = myRs.getString("lastName");
                String email = myRs.getString("email");


                Student tempStudent = new Student(id, firstName, lastName, email);

                students.add(tempStudent);
            }
            return students;
        } finally {
            close(myConn, myStmt, myRs);
        }

    }

    private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
        try {
            if (myRs != null) {
                myRs.close();
            }
            if (myStmt != null) {
                myStmt.close();
            }
            if (myConn != null) {
                myConn.close();
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void addStudent(Student theStudent) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;
        try {
            String url = "jdbc:mysql://localhost:3306/web_student_tracker";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            myConn = DriverManager.getConnection(url, username, password);

            String sql = "insert into student"
                    + "(first_name, last_name, email)"
                    + " values (?, ?, ?)";
            myStmt = myConn.prepareStatement(sql);

            myStmt.setString(1, theStudent.getFirstName());
            myStmt.setString(2, theStudent.getLastName());
            myStmt.setString(3, theStudent.getEmail());
            myStmt.execute();


        }
        finally {
            close(myConn, myStmt, null);
        }
    }

    public Student getStudent(String theStudentId) throws Exception {
        Student theStudent = null;
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        int studentId = 0;
        try {
            String url = "jdbc:mysql://localhost:3306/web_student_tracker";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            myConn = DriverManager.getConnection(url, username, password);

            String sql = "select * from student where id=?";

            myStmt = myConn.prepareStatement(sql);

            myStmt.setInt(1, studentId);

            myRs = myStmt.executeQuery();

            if (myRs.next()) {
                String firstName = myRs.getString("firstName");
                String lastName = myRs.getString("lastName");
                String email = myRs.getString("email");
                theStudent = new Student( studentId, firstName,lastName, email);
            } else {
                throw new Exception("Cloud not find student id: " + studentId);
            }
            return theStudent;
        } finally {
            close(myConn, myStmt, myRs);
        }
    }

    public void updateStudent(Student theStudent) throws Exception{
        Connection myConn = null;
        PreparedStatement myStmt = null;
        try {
            String sql = "update student"
                    + "set first_name=?, last_name=?, email=?"
                    + "where id=?";

            myStmt = myConn.prepareStatement(sql);

            myStmt.setString(1, theStudent.getFirstName());
            myStmt.setString(2, theStudent.getLastName());
            myStmt.setString(3, theStudent.getEmail());
            myStmt.setInt(4, theStudent.getId());

            myStmt.execute();

        }finally {
            close(myConn, myStmt, null);
        }
    }
    public void deleteStudent(String theStudentId) throws Exception{
        Connection myConn = null;
        PreparedStatement myStmt = null;
        int studentId = 0;

        try{
            String url = "jdbc:mysql://localhost:3306/web_student_tracker";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            myConn = DriverManager.getConnection(url, username, password);

            String sql ="delete from student where id=?";

            myStmt = myConn.prepareStatement(sql);

            myStmt.setInt(1, studentId);
            myStmt.execute();
        }finally {
            close(myConn,myStmt, null);
        }
    }
}

