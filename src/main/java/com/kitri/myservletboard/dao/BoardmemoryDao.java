package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.data.*;

import java.util.ArrayList;

public class BoardmemoryDao implements BoardDao{
    private static final BoardmemoryDao instance = new BoardmemoryDao();
    public static BoardmemoryDao getInstance() {
        return instance;
    }

    ArrayList<Board> memoryBoardDB = new ArrayList<>();
    private BoardmemoryDao() {
    }
    @Override
    public ArrayList<Board> getAll() { return memoryBoardDB; }
    @Override
    public ArrayList<Board> getAll(Pagination pagination, Organize organize) { return null; }
    @Override
    public Board getById(Long id) {
        return memoryBoardDB.stream().filter(board -> {
            return board.getId() == id;
        }).findFirst().get();
    }
    @Override
    public void save(Board board) {
        Long id = 0L;
        boolean fleg = false;

        while (!fleg) {
            fleg = true;
            id++;
            for ( Board board_ : memoryBoardDB) {
                if ( id == board_.getId()) {
                    fleg = false;
                    break;
                }
            }
        }
        board.setId(id);
        memoryBoardDB.add(board);
    }

    @Override
    public void update(Board board) {
        Board board_ = getById(board.getId());
        board_.setTitle(board.getTitle());
        board_.setContent(board.getContent());
    }

    @Override
    public void Delete(Board board) {
        memoryBoardDB.remove(board);
    }

    @Override
    public ArrayList<Board> search(SearchData searchData, Pagination pagination, Organize organize) {
        memoryBoardDB.contains(searchData);
        return null;
    }
    @Override
    public void viewCount(Long id) { }
    @Override
    public void join(Member member) { }
    @Override
    public boolean searchId(String id) { return false; }
    @Override
    public boolean searchPW(String id, String pw) { return false; }
    @Override
    public String[] memberData(String id, String pw) { return new String[0]; }
    @Override
    public void registration(Member member) { }
    @Override
    public ArrayList<Comment> getComment(Long id) { return null; }
    @Override
    public void createComment(Comment comment) { }
    @Override
    public void deleteComment(Long id) { }
}
