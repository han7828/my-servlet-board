package service;

import com.kitri.myservletboard.Board;
import data.Organize;
import data.Pagination;
import data.SearchData;
import dao.BoardDao;
import dao.BoardJdbcDao;

import javax.servlet.http.HttpServlet;
import java.util.ArrayList;

public class BoardService extends HttpServlet {
//    BoardDao boardDao = BoardmemoryDao.getInstance();
    BoardDao boardDao = BoardJdbcDao.getInstance();
    private BoardService() {};
    public Board getBoard(Long id) {
        boardDao.viewCount(id);
        return boardDao.getById(id);}
    private static final BoardService instance = new BoardService();
    public static BoardService getInstance() {
        return instance;
    }

    public ArrayList<Board> getBoards() { return boardDao.getAll(); }
    public ArrayList<Board> getBoards(Pagination pagination, Organize organize) {
        pagination.setTotalRecords(((BoardJdbcDao)boardDao).count());
        pagination.calcPagination();
        return boardDao.getAll(pagination, organize);
    }
    public ArrayList<Board> searchBoard(SearchData searchData, Pagination pagination, Organize organize) {
        searchData.setTimeIndex(searchData.getSearchTime());
        pagination.setTotalRecords(((BoardJdbcDao)boardDao).searchCount(searchData));
        pagination.calcPagination();
        return boardDao.search(searchData, pagination, organize);
    }

    public void addBoard(Board board) {
        boardDao.save(board);
    }
    public void updateBoard(Board board) { boardDao.update(board); }
    public void DeleteBoard(Board board) {
        boardDao.Delete(board);
    }
}