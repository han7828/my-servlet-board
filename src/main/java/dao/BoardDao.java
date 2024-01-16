package dao;

import com.kitri.myservletboard.Board;
import data.Organize;
import data.Pagination;
import data.SearchData;

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
}
