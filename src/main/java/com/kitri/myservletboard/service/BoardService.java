package com.kitri.myservletboard.service;

import com.kitri.myservletboard.data.*;
import com.kitri.myservletboard.dao.BoardDao;
import com.kitri.myservletboard.dao.BoardJdbcDao;

import javax.servlet.http.HttpServlet;
import java.time.LocalDateTime;
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
    public void joinMember(Member member) {
         boardDao.join(member);
    }
    public boolean searchId(String id) { return boardDao.searchId(id); }
    public boolean searchPW(String id, String pw) { return boardDao.searchPW(id, pw); }
    public String[] memberData(String id, String pw) { return boardDao.memberData(id, pw); }
    public void registration(Member member) { boardDao.registration(member); }
    public ArrayList<Comment> getComment(Long id) { return boardDao.getComment(id); }
    public void createComment(Comment comment) { boardDao.createComment(comment); }
    public void deleteComment(Long id) { boardDao.deleteComment(id); }
}