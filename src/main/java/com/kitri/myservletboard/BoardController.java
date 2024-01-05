package com.kitri.myservletboard;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<h1>요청을 잘 응답받았습니다</h1>");

        out.println(request.getRequestURL());

        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = requestURI.substring(contextPath.length());

        out.println("command = " + command);

        String view = ("/view/board/");

        if (command.equals("/board/list")) {
            view += "list.jsp";

        } else if (command.equals("/board/createForm")){
            view += "createForm.jsp";

        } else if (command.equals("/board/create")){

        } else if (command.equals("/board/updateForm")){
            view += "updateForm.jsp";

        } else if (command.equals("/board/update")){

        } else if (command.equals("/board/delete")){

        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);

    }
}