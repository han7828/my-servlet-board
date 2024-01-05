package com.kitri.myservletboard;


import java.time.LocalDateTime;

public class Board {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createAt;
    private int viewCount;
    private int commentCount;

    public Board() {
    }
    public Board(Long id, String title, String content, String writer, LocalDateTime createAt, int viewCount, int commentCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createAt = createAt;
        this.viewCount = viewCount;
        this.commentCount = commentCount;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getWriter() {
        return writer;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public int getViewCount() {
        return viewCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}
