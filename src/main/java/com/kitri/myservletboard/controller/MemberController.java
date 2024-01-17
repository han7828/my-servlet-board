package com.kitri.myservletboard.controller;
import com.kitri.myservletboard.data.Member;
import com.kitri.myservletboard.data.Organize;
import com.kitri.myservletboard.data.Pagination;
import com.kitri.myservletboard.data.SearchData;
import com.kitri.myservletboard.service.BoardService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {

    BoardService boardService = BoardService.getInstance();

    static HashMap<String, String> members = new HashMap<>();
    static {
        members.put("abc123", "12345");
        members.put("captain91", "12345");
        members.put("ilovecoding", "12345");
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<h1>요청을 잘 응답받았습니다</h1>");

        out.println(request.getRequestURL());

        request.setCharacterEncoding("UTF-8");
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = requestURI.substring(contextPath.length());

        out.println("command = " + command);

        String view = ("/view/member/");

        if (command.contains("/member/join")){
            String memberId = request.getParameter("id");
            String pw = request.getParameter("pw");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            Member member = new Member(null, memberId, pw, name, email);

            boolean joinSuccess = true;
            if ( boardService.searchId(memberId) ) { // 중복되는 id가 존재함
                joinSuccess = false;
            }
            if ( !joinSuccess ) { // 회원가입 실패
                request.setAttribute("joinSuccess",joinSuccess);
                view += "join.jsp";
            } else { // 회원가입 성공
                boardService.joinMember(member);
                view = "/board/list";
            }
        }

        else if (command.contains("/member/login")){
            String id = request.getParameter("id");
            String pw = request.getParameter("pw");
            boolean loingSuccess = true;
            if ( id.isEmpty() || pw.isEmpty()) {
                loingSuccess = false;
            }
            if ( !boardService.searchId(id) ) { // id가 존재하지 않음
                loingSuccess = false;
            }
            else {
                if ( !boardService.searchPW(id, pw)) { // pw가 존재하지 않음
                    loingSuccess = false;
                }
            }
            String[] members = boardService.memberData(id, pw);

            if ( !loingSuccess ) { // 로그인 실패
                request.setAttribute("loingSuccess",loingSuccess);
                view = "login.jsp";
            } else { // 로그인 성공
                HttpSession session= request.getSession();
                session.setAttribute("members", members);
                view = "/board/list";
            }
        }

        else if (command.contains("/member/logout")){
            HttpSession session = request.getSession();
            session.invalidate();

            view = "/board/list";
        }

        else if (command.contains("/member/registration")){
            Long id = Long.valueOf(request.getParameter("id"));
            String memberId = request.getParameter("memberid");
            String pw = request.getParameter("pw");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            Member member = new Member(id, memberId, pw, name, email);

            boardService.registration(member);

            view = "/board/list";
        }
        else if (command.contains("/member/jForm")){
            view += "join.jsp";
        }
        else if (command.contains("/member/lForm")){
            view += "login.jsp";
        }
        else if (command.contains("/member/rForm")){
            view += "registration.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);

    }
}