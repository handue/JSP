package ch9;

import java.sql.Connection;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;

import static java.util.Collections.list;

@WebServlet("/studentControl")
public class StudentController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    StudentDao dao;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dao = new StudentDao();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String action = request.getParameter("action");
        String view = "";
        if(request.getParameter("action") == null){
            getServletContext().getRequestDispatcher("/studentControl?action=list").forward(request, response);
        } else{
            switch (action){
                case "list": view = list(request,response); break;
                case "insert": view = insert(request,response); break;
            }
            getServletContext().getRequestDispatcher("/ch9/" +view).forward(request,response);
        }
    }
    public String list(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("students",dao.getAll());
            return "studentInfo.jsp";
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public String insert(HttpServletRequest request, HttpServletResponse response) {
        Student s = new Student();
        try {
            BeanUtils.populate(s, request.getParameterMap());
        }catch(Exception e){
            e.printStackTrace();
        }
        try {
            dao.insert(s);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return list(request,response);
    }

}
