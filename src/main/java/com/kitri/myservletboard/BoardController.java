package com.kitri.myservletboard;

import data.Organize;
import data.Pagination;
import data.SearchData;
import service.BoardService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {

    BoardService boardService = BoardService.getInstance();

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

        String view = ("/view/board/");

        if (command.equals("/board/list")) {
            String page = request.getParameter("page");
            if (page == null) { page = "1"; }
            String organize = request.getParameter("organize");
            if (organize == null) { organize = "newest"; }
            String recordsPerPage = request.getParameter("recordsPerPage");
            if (recordsPerPage == null) { recordsPerPage = "10"; }

            String searchTime = request.getParameter("searchTime");
            String searchField = request.getParameter("searchField");
            String searchText = request.getParameter("searchText");

            Organize organizes = new Organize(organize);
            SearchData searchData = new SearchData(searchField, searchText, searchTime);
            Pagination pagination = new Pagination(Integer.parseInt(page));
            pagination.setRecordsPerPage(Integer.parseInt(recordsPerPage));
            ArrayList<Board> boards = null;

            if (searchText != null ) {
                boards = boardService.searchBoard(searchData, pagination, organizes);
            } else {
                boards = boardService.getBoards(pagination, organizes);
            }

            request.setAttribute("searchData",searchData);
            request.setAttribute("pagination",pagination);
            request.setAttribute("organize",organizes);
            request.setAttribute("boards", boards);
            view += "list.jsp";
        }
        else if (command.equals("/board/createForm")){
            view += "createForm.jsp";
        }
        else if (command.equals("/board/create")){
            String title = request.getParameter("title");
            String writer = request.getParameter("writer");
            String content = request.getParameter("content");

            Board boards = new Board(null, title, content, writer, LocalDateTime.now(), 0, 0);
            boardService.addBoard(boards);

            response.sendRedirect("/board/list");
            return;
        }
        else if (command.equals("/board/updateForm")){
            Long id = Long.parseLong(request.getParameter("id"));
            Board board = boardService.getBoard(id);
            request.setAttribute("board", board);

            view += "updateForm.jsp";
        }
        else if (command.equals("/board/update")){
            Long id = Long.parseLong(request.getParameter("id"));
            String title = request.getParameter("title");
            String content = request.getParameter("content");

            Board boards = new Board(id, title, content, null, null, 0, 0);
            boardService.updateBoard(boards);

            response.sendRedirect("/board/list");
            return;
        }
        else if (command.equals("/board/delete")){

            Board board = boardService.getBoard(Long.parseLong(request.getParameter("id")));
            boardService.DeleteBoard(board);

            response.sendRedirect("/board/list");
            return;
        }
        else if (command.contains("/board/detail")){
            Long id = Long.parseLong(request.getParameter("id"));
            Board board = boardService.getBoard(id);
            request.setAttribute("board", board);

            view += "detail.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);

    }
}