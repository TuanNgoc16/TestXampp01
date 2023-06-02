package test.jdbc;
import com.oracle.wls.shaded.org.apache.xml.utils.res.XResources_sv;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;
@WebServlet("/StudentControllerServlet")

public class StudentControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDbUtil studentDbUtil;


     private DataSource dataSource;
    @Override
    public void init() throws ServletException{
        super.init();
        try{
          studentDbUtil = new StudentDbUtil(dataSource);
        }
        catch(Exception exc){
            throw new ServletException(exc);
        }
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            String theCommand = request.getParameter("command");
            if (theCommand == null)
                theCommand = "list";

            switch (theCommand){
                case "ADD":
                    addStudent(request, response);
                    break;
                case "LOAD":
                    loadStudent(response, request);
                    break;
                case "UPDATE":
                    updateStudent(request, response);
                    break;
                case "DELETE":
                    deleteStudent(request, response);
                    break;
                default:
                    listStudents(response, request);

            }
        }
        catch (Exception exc)
        {
            throw new ServletException(exc);
        }
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        String theStudentId = request.getParameter("studentId");

        studentDbUtil.deleteStudent(theStudentId);

        listStudents(response, request);

    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        int id = Integer.parseInt(request.getParameter("studentId"));
        String firstName = request.getParameter("firstName");
        String lastName  = request.getParameter("lastName");
        String email = request.getParameter("email");

        Student theStudent = new Student(id, firstName, lastName, email);

        studentDbUtil.updateStudent(theStudent);
        listStudents(response, request);
    }

    private void loadStudent(HttpServletResponse response, HttpServletRequest request)
            throws Exception{
        String theStudentId = request.getParameter("studentId");
        Student theStudent = studentDbUtil.getStudent(theStudentId);
        request.setAttribute("THE_STUDENT", theStudent);
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/update-student-form.jsp");
        dispatcher.forward(request, response);


    }
    private void addStudent(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        Student theStudent = new Student(firstName,lastName,email);
        studentDbUtil.addStudent(theStudent);
        listStudents(response, request);
    }
    private void listStudents(HttpServletResponse response, HttpServletRequest request)
            throws Exception{
        List<Student> students = studentDbUtil.getStudent();
        request.setAttribute("STUDENT_LIST", students);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
        dispatcher.forward(request, response);
    }
}