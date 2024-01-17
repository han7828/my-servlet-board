package com.kitri.myservletboard.data;

import java.time.LocalDateTime;

public class Comment {
    private Long id;
    private Long board_id;
    private Long member_id;
    private String commenter;
    private String content;
    private LocalDateTime created_at;

    public Comment(Long id, Long board_id, Long member_id, String commenter, String content, LocalDateTime created_at) {
        this.id = id;
        this.board_id = board_id;
        this.member_id = member_id;
        this.commenter = commenter;
        this.content = content;
        this.created_at = created_at;
    }

    public Comment(Long board_id, Long member_id, String content, LocalDateTime created_at) {
        this.board_id = board_id;
        this.member_id = member_id;
        this.content = content;
        this.created_at = created_at;
    }

    public void setId(Long id) { this.id = id; }
    public void setBoard_id(Long board_id) { this.board_id = board_id; }
    public void setMember_id(Long member_id) { this.member_id = member_id; }
    public void setCommenter(String commenter) { this.commenter = commenter; }
    public void setContent(String content) { this.content = content; }
    public void setCreated_at(LocalDateTime created_at) { this.created_at = created_at; }

    public Long getId() { return id; }
    public Long getBoard_id() { return board_id; }
    public Long getMember_id() { return member_id; }
    public String getCommenter() { return commenter; }
    public String getContent() { return content; }
    public LocalDateTime getCreated_at() { return created_at;}
}
