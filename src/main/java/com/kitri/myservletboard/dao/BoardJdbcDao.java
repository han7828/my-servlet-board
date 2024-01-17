package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.data.*;

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
                Long memberId = rs.getLong("member_Id");
                String writer = rs.getString("writer");
                LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
                int viewCount = rs.getInt("view_count");
                int commentCount = rs.getInt("comment_count");

                boards.add(new Board(id, title, content, memberId, writer, createdAt, viewCount, commentCount));
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
    public ArrayList<Board> getAll(Pagination pagination, Organize organize) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Board> boards = new ArrayList<>();
        String orderBy = organize.getOrderBy();

        try {
            connection = connectDB();
            String sql = "SELECT * FROM board " + orderBy + " LIMIT ?, ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, ( pagination.getPage() - 1 ) * pagination.getRecordsPerPage() );
            ps.setInt(2, pagination.getRecordsPerPage());
            rs = ps.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                Long memberId = rs.getLong("member_Id");
                String writer = rs.getString("writer");
                LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
                int viewCount = rs.getInt("view_count");
                int commentCount = rs.getInt("comment_count");

                boards.add(new Board(id, title, content, memberId, writer, createdAt, viewCount, commentCount));
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
                Long boardId = rs.getLong("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                Long memberId = rs.getLong("member_Id");
                String writer = rs.getString("writer");
                LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
                int viewCount = rs.getInt("view_count");
                int commentCount = rs.getInt("comment_count");

                board = new Board(boardId, title, content, memberId, writer, createdAt, viewCount, commentCount);
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
            String sql = "INSERT INTO board (title, content, member_id, writer) VALUES ( ?, ?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1,board.getTitle());
            ps.setString(2,board.getContent());
            ps.setLong(3,board.getMemberId());
            ps.setString(4,board.getWriter());
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
            String sql = "UPDATE board SET title = ?, content = ? WHERE id = ? AND member_id = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1,board.getTitle());
            ps.setString(2,board.getContent());
            ps.setLong(3,board.getId());
            ps.setLong(4,board.getMemberId());
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
    public ArrayList<Board> search(SearchData searchData, Pagination pagination, Organize organize) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Board> boards = new ArrayList<>();
        String searchField = searchData.getSearchField();
        String orderBy = organize.getOrderBy();

        try {
            connection = connectDB();
            String sql = null;
            if (searchField.equals("title")) {
                sql = "SELECT * FROM board WHERE title LIKE '%" + searchData.getSearchText() + "%' " + orderBy + " LIMIT ?, ?";
                if ( !searchData.getSearchTime().equals("allday") ) {
                    sql = "SELECT * FROM board WHERE title LIKE '%" + searchData.getSearchText() + "%' " +
                            "AND created_at BETWEEN DATE_SUB(NOW(), INTERVAL " + searchData.getTimeIndex() +" DAY) AND DATE(NOW() + INTERVAL 1 DAY) " + orderBy + " LIMIT ?, ?";
                }
            } else if (searchField.equals("writer")) {
                sql = "SELECT * FROM board where writer LIKE '%" + searchData.getSearchText() + "%' " + orderBy + " LIMIT ?, ?";
                if ( !searchData.getSearchTime().equals("allday") ) {
                    sql = "SELECT * FROM board WHERE writer LIKE '%" + searchData.getSearchText() + "%' " +
                            "AND created_at BETWEEN DATE_SUB(NOW(), INTERVAL " + searchData.getTimeIndex() +" DAY) AND DATE(NOW() + INTERVAL 1 DAY) " + orderBy + " LIMIT ?, ?";
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
                Long memberId = rs.getLong("member_Id");
                String writer = rs.getString("writer");
                LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
                int viewCount = rs.getInt("view_count");
                int commentCount = rs.getInt("comment_count");

                boards.add(new Board(id, title, content, memberId, writer, createdAt, viewCount, commentCount));
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

    public void viewCount(Long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = connectDB();
            String sql = "UPDATE board SET view_count = view_count + 1 WHERE id = ?;";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
        }
        catch (Exception e) { }
        finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void join(Member member) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = null;
            connection = connectDB();
            if ( member.getEmail() == null) {
                sql = "INSERT INTO member (`login_id`, `password`, `name`) VALUES ('"+ member.getMemberId() +"', '" + member.getPw() + "', '" + member.getName() + "')";
            } else {
                sql = "INSERT INTO member (`login_id`, `password`, `name`, `email`) VALUES ('" + member.getMemberId() + "', '" + member.getPw() + "', '" + member.getName() + "', '" + member.getEmail() + "')";
            }
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
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
    }
    @Override
    public boolean searchId(String id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = connectDB();
            String sql = "SELECT * FROM member WHERE login_id = '" + id + "';";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if ( rs.next() ) {
                return true;
            } else {
                return false;
            }
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
        return false;
    }

    public boolean searchPW(String id, String pw) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = connectDB();
            String sql = "SELECT * FROM member WHERE login_id = '" + id + "' AND password = '" + pw + "';";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if ( rs.next() ) {
                return true;
            } else {
                return false;
            }
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
        return false;
    }

    @Override
    public String[] memberData(String id, String pw) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String[] member = new String[5];

        try {
            connection = connectDB();
            String sql = "SELECT * FROM member WHERE login_id = '" + id + "' AND password = '" + pw + "';";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                member[0] = String.valueOf(rs.getLong("id"));
                member[1] = rs.getString("login_id");
                member[2] = rs.getString("password");
                member[3] = rs.getString("name");
                member[4] = rs.getString("email");
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

        return member;
    }

    @Override
    public void registration(Member member) {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = connectDB();
            String sql = null;
            if ( member.getEmail() == null ) {
                sql = "UPDATE member SET `password` = '" + member.getPw()+ "', `name` = '" + member.getName() + "' WHERE (`id` = '" + member.getId() + "') AND (`login_id` = '" + member.getMemberId() + "');";
            } else {
                sql = "UPDATE member SET `password` = '" + member.getPw()+ "', `name` = '" + member.getName() + "', `email` = '" + member.getEmail() + "' WHERE (`id` = '" + member.getId() + "') AND (`login_id` = '" + member.getMemberId() + "');";
            }
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
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
    }

    @Override
    public ArrayList<Comment> getComment(Long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Comment> comment = new ArrayList<>();

        try {
            connection = connectDB();
            String sql = "SELECT * FROM comment c, member m  WHERE c.member_id = m.id AND board_id = ? ;";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                Long comment_id = rs.getLong("id");
                Long board_id = rs.getLong("board_id");
                Long member_id = rs.getLong("member_id");
                String commenter = rs.getString("name");
                String content = rs.getString("content");
                LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();

                comment.add(new Comment(comment_id, board_id, member_id, commenter, content, createdAt));
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
        return comment;
    }

    @Override
    public void createComment(Comment comment) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = connectDB();
            String sql = "INSERT INTO comment ( `board_id`, `member_id`, `content`) VALUES ( ?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setLong(1,comment.getBoard_id());
            ps.setLong(2,comment.getMember_id());
            ps.setString(3,comment.getContent());
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
    public void deleteComment(Long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = connectDB();
            String sql = "DELETE FROM comment WHERE id = ?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
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
}

