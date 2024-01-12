package dao;

import com.kitri.myservletboard.Board;
import com.kitri.myservletboard.Pagination;
import com.kitri.myservletboard.SearchData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class BoardJdbcDao implements BoardDao{
    private static final BoardJdbcDao instance = new BoardJdbcDao();
    public static BoardJdbcDao getInstance() { return instance; }
    public Connection connectDB() {
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/my-servlet-board";
            String user = "root";
            String pwd = "1234";
            conn = DriverManager.getConnection(url, user, pwd);
        } catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    @Override
    public ArrayList<Board> getAll() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Board> boards = new ArrayList<>();

        try {
            connection = connectDB();
            String sql = "SELECT * FROM board";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery(sql);

            while (rs.next()) {
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String writer = rs.getString("writer");
                LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
                int viewCount = rs.getInt("view_count");
                int commentCount = rs.getInt("comment_count");

                boards.add(new Board(id, title, content, writer, createdAt, viewCount, commentCount));
            }
        } catch (Exception e) {

        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return boards;
    }
    @Override
    public ArrayList<Board> getAll(Pagination pagination) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Board> boards = new ArrayList<>();

        try {
            connection = connectDB();
            String sql = "SELECT * FROM board LIMIT ?, ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, ( pagination.getPage() - 1 ) * pagination.getRecordsPerPage() );
            ps.setInt(2, pagination.getRecordsPerPage());
            rs = ps.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String writer = rs.getString("writer");
                LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
                int viewCount = rs.getInt("view_count");
                int commentCount = rs.getInt("comment_count");

                boards.add(new Board(id, title, content, writer, createdAt, viewCount, commentCount));
            }
        } catch (Exception e) {

        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return boards;
    }

    @Override
    public Board getById(Long id) {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Board board = null;

        try {
            connection = connectDB();
            String sql = "SELECT * FROM board where id=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            while (rs.next()){
                Long id_ = rs.getLong("id");
                String title_ = rs.getString("title");
                String content_ = rs.getString("content");
                String writer_ = rs.getString("writer");
                LocalDateTime createdAt_ = rs.getTimestamp("created_at").toLocalDateTime();
                int viewCount_ = rs.getInt("view_Count");
                int commentCount_ = rs.getInt("comment_count");

                board = new Board(id_, title_, content_, writer_, createdAt_, viewCount_, commentCount_);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return board;
    }
    @Override
    public void save(Board board) {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = connectDB();
            String sql = "INSERT INTO board (title, content, writer) VALUES ( ?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1,board.getTitle());
            ps.setString(2,board.getContent());
            ps.setString(3,board.getWriter());
            ps.executeUpdate();
        } catch (Exception e) {

        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void update(Board board) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = connectDB();
            String sql = "UPDATE board SET title = ?, content = ? WHERE id = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1,board.getTitle());
            ps.setString(2,board.getContent());
            ps.setLong(3,board.getId());
            ps.executeUpdate();
        } catch (Exception e) {

        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void Delete(Board board) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = connectDB();
            String sql = "DELETE FROM board WHERE id=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1,board.getId());
            ps.executeUpdate();
        } catch (Exception e) {

        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public int count() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int count = 0;

        try {
            connection = connectDB();
            String sql = "SELECT count(*) FROM board";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            count = rs.getInt("count(*)");
        }
        catch (Exception e) {
        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count;
    }
    public ArrayList<Board> search(SearchData searchData, Pagination pagination) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Board> boards = new ArrayList<>();
        String searchField = searchData.getSearchField();

        try {
            connection = connectDB();
            String sql = null;
            if (searchField.equals("title")) {
                sql = "SELECT * FROM board WHERE title LIKE '%" + searchData.getSearchText() + "%' LIMIT ?, ?";
                if ( !searchData.getSearchTime().equals("allday") ) {
                    sql = "SELECT * FROM board WHERE title LIKE '%" + searchData.getSearchText() + "%' " +
                            "AND created_at BETWEEN DATE_SUB(NOW(), INTERVAL " + searchData.getTimeIndex() +" DAY) AND DATE(NOW() + INTERVAL 1 DAY) LIMIT ?, ?";
                }
            } else if (searchField.equals("writer")) {
                sql = "SELECT * FROM board where writer LIKE '%" + searchData.getSearchText() + "%' LIMIT ?, ?";
                if ( !searchData.getSearchTime().equals("allday") ) {
                    sql = "SELECT * FROM board WHERE writer LIKE '%" + searchData.getSearchText() + "%' " +
                            "AND created_at BETWEEN DATE_SUB(NOW(), INTERVAL " + searchData.getTimeIndex() +" DAY) AND DATE(NOW() + INTERVAL 1 DAY) LIMIT ?, ?";
                }
            }
            ps = connection.prepareStatement(sql);
            ps.setInt(1, ( pagination.getPage() - 1 ) * pagination.getRecordsPerPage() );
            ps.setInt(2, pagination.getRecordsPerPage());
            rs = ps.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String writer = rs.getString("writer");
                LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
                int viewCount = rs.getInt("view_Count");
                int commentCount = rs.getInt("comment_count");

                boards.add(new Board(id, title, content, writer, createdAt, viewCount, commentCount));
            }
        } catch (Exception e) {
        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return boards;
    }
    public int searchCount(SearchData searchData) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int count = 0;
        String searchField = searchData.getSearchField();

        try {
            connection = connectDB();
            String sql = null;
//            if (searchField.equals("title")) {
//                sql = "SELECT count(*) FROM board where title LIKE '%" + searchData.getSearchText() + "%'";
//            } else if (searchField.equals("writer")) {
//                sql = "SELECT count(*) FROM board where writer LIKE '%" + searchData.getSearchText() + "%'";
//            }
            if (searchField.equals("title")) {
                sql = "SELECT count(*) FROM board WHERE title LIKE '%" + searchData.getSearchText() + "%' ";
                if ( !searchData.getSearchTime().equals("allday") ) {
                    sql = "SELECT count(*) FROM board WHERE title LIKE '%" + searchData.getSearchText() + "%' " +
                            "AND created_at BETWEEN DATE_SUB(NOW(), INTERVAL " + searchData.getTimeIndex() +" DAY) AND DATE(NOW() + INTERVAL 1 DAY) ";
                }
            } else if (searchField.equals("writer")) {
                sql = "SELECT count(*) FROM board where writer LIKE '%" + searchData.getSearchText() + "%' ";
                if ( !searchData.getSearchTime().equals("allday") ) {
                    sql = "SELECT count(*) FROM board WHERE writer LIKE '%" + searchData.getSearchText() + "%' " +
                            "AND created_at BETWEEN DATE_SUB(NOW(), INTERVAL " + searchData.getTimeIndex() +" DAY) AND DATE(NOW() + INTERVAL 1 DAY) ";
                }
            }
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            count = rs.getInt("count(*)");
        }
        catch (Exception e) {
        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count;
    }
}
