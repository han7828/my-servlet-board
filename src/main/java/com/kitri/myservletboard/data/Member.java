package com.kitri.myservletboard.data;

public class Member {
    Long id;
    String memberId;
    String pw;
    String name;
    String email;
    public Member(Long id, String memberId, String pw, String name, String email) {
        this.id = id;
        this.memberId = memberId;
        this.pw = pw;
        this.name = name;
        this.email = email;
    }

    public void setId(Long id) { this.id = id; }
    public void setMemberId(String memberId) { this.memberId = memberId;}
    public void setPw(String pw) { this.pw = pw; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public Long getId() { return id; }
    public String getMemberId() { return memberId; }
    public String getPw() { return pw; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}
