package dao;

import com.kitri.myservletboard.Board;
import com.kitri.myservletboard.Pagination;

import java.util.ArrayList;

public interface BoardDao {
    public ArrayList<Board> getAll(Pagination pagination);
    ArrayList<Board> getAll();
    public Board getById(Long id);
    public void save(Board board);
    public void update(Board board);
    public void Delete(Board board);

}
