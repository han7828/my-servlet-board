package com.kitri.myservletboard.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Board {
    private Long id;
    private String title;
    private String content;
    private Long memberId;
    private String writer;
    private LocalDateTime createdAt;
    private int viewCount;
    private int commentCount;
    public Board() {
    }
    public Board(Long id, String title, String content, Long memberId, String writer, LocalDateTime createdAt, int viewCount, int commentCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.memberId = memberId;
        this.writer = writer;
        this.createdAt = createdAt;
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
    public Long getMemberId() { return memberId; }
    public String getWriter() {
        return writer;
    }
    public LocalDateTime getCreatedAt() { return createdAt;}
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
    public void setMemberId(Long memberId) { this.memberId = memberId; }
    public void setWriter(String writer) {
        this.writer = writer;
    }
    public void setCreatedAt(LocalDateTime createAt) {
        this.createdAt = createAt;
    }
    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }
    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}
