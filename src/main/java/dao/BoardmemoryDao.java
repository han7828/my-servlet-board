package dao;

import com.kitri.myservletboard.Board;
import com.kitri.myservletboard.Pagination;
import com.kitri.myservletboard.SearchData;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class BoardmemoryDao implements BoardDao{
    private static final BoardmemoryDao instance = new BoardmemoryDao();
    public static BoardmemoryDao getInstance() {
        return instance;
    }

    ArrayList<Board> memoryBoardDB = new ArrayList<>();
    private BoardmemoryDao() {
        memoryBoardDB.add(new Board(1L, "첫번째 글입니다!", "반갑습니다.", "박xx", LocalDateTime.now(), 16, 4));
        memoryBoardDB.add(new Board(2L, "두번째 글입니다!", "반갑습니다22.", "이xx", LocalDateTime.now(), 11, 2));
        memoryBoardDB.add(new Board(3L, "세번째 글입니다!", "반갑습니다33.", "우xx", LocalDateTime.now(), 23, 3));
        memoryBoardDB.add(new Board(4L, "네번째 글입니다!", "반갑습니다44.", "하xx", LocalDateTime.now(), 19, 5));
        memoryBoardDB.add(new Board(5L, "다섯번째 글입니다!", "반갑습니다55.", "침xx", LocalDateTime.now(), 26, 1));
        memoryBoardDB.add(new Board(6L, "여섯번째 글입니다!", "반갑습니다66.", "하xx", LocalDateTime.now(), 46, 0));
        memoryBoardDB.add(new Board(7L, "일곱번째 글입니다!", "반갑습니다77.", "오xx", LocalDateTime.now(), 6, 3));
        memoryBoardDB.add(new Board(8L, "여덟번째 글입니다!", "반갑습니다88.", "김xx", LocalDateTime.now(), 11, 2));
        memoryBoardDB.add(new Board(9L, "아홉번째 글입니다!", "반갑습니다99.", "김xx", LocalDateTime.now(), 12, 4));
        memoryBoardDB.add(new Board(10L, "열번째 글입니다!", "반갑습니다1111.", "황xx", LocalDateTime.now(), 24, 6));
    }
    @Override
    public ArrayList<Board> getAll() { return memoryBoardDB; }
    @Override
    public ArrayList<Board> getAll(Pagination pagination) { return null; }
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
    public ArrayList<Board> search(SearchData searchData, Pagination pagination) {
        memoryBoardDB.contains(searchData);
        return null;
    }
}
