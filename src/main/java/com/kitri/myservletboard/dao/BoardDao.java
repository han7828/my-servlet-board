package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.data.*;

import java.util.ArrayList;

public interface BoardDao {
    public ArrayList<Board> getAll(Pagination pagination, Organize organize);
    ArrayList<Board> getAll();
    public Board getById(Long id);
    public void save(Board board);
    public void update(Board board);
    public void Delete(Board board);
    public ArrayList<Board> search(SearchData searchData, Pagination pagination, Organize organize);
    public void viewCount(Long id);
    public void join(Member member);
    public boolean searchId(String id);
    public boolean searchPW(String id, String pw);
    public String[] memberData(String id, String pw);
    public void registration(Member member);
    public ArrayList<Comment> getComment(Long id);
    public void createComment(Comment comment);
    public void deleteComment(Long id);
}
