package ex.login;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/ex/login")
public class Login extends HttpServlet {
    static HashMap<String, String> members = new HashMap<>();
    static {
        members.put("abc123", "12345");
        members.put("captain91", "12345");
        members.put("ilovecoding", "12345");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/ex/login/login.jsp");
        dispatcher.forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");

        boolean isLoginFeiled = false;
        if ( id.isEmpty() || pw.isEmpty()) {
            isLoginFeiled = true;
        }
        if ( members.get(id) == null ) {
            isLoginFeiled = true;
        }
        else {
            if ( !members.get(id).equals(pw)) {
                isLoginFeiled = true;
            }
        }

        if ( isLoginFeiled ) {
            request.setAttribute("LoginFailed",isLoginFeiled);
        } else {
            HttpSession session= request.getSession();
            session.setAttribute("id", id);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/ex/login/login.jsp");
        dispatcher.forward(request,response);
    }
}
