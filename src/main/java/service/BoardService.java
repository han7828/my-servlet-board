package service;

import com.kitri.myservletboard.Board;
import dao.BoardDao;
import dao.BoardmemoryDao;

import javax.servlet.http.HttpServlet;
import java.util.ArrayList;

public class BoardService extends HttpServlet {
    BoardDao boardDao = BoardmemoryDao.getInstance();
    private BoardService() {};
    public Board getBoard(Long id) { return boardDao.getById(id);}
    private static final BoardService instance = new BoardService();
    public static BoardService getInstance() {
        return instance;
    }
    public ArrayList<Board> getBoards() {
        return boardDao.getAll();
    }
    public void addBoard(Board board) {
        boardDao.save(board);
    }
    public void updateBoard(Board board) {
        boardDao.update(board);
    }
    public void DeleteBoard(Board board) {
        boardDao.Delete(board);
    }
}