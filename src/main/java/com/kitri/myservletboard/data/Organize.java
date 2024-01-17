package com.kitri.myservletboard.data;

public class Organize {
    private String organize;
    private String orderBy;
    public void setOrganize(String organize) { this.organize = organize; }
    public void setOrderBy(String orderBy) { this.orderBy = orderBy; }
    public Organize(String organize) { this.organize = organize; }
    public String getOrganize() { return organize; }
    public String getOrderBy() {
        if (organize.equals("newest")) {
            this.orderBy = "ORDER BY created_at desc";
            return orderBy;
        }
        if (organize.equals("oldest")) {
            this.orderBy = "ORDER BY created_at asc";
            return orderBy;
        }
        if (organize.equals("views")) {
            this.orderBy = "ORDER BY view_count desc";
            return orderBy;
        }
        if (organize.equals("accuracy")) {
            this.orderBy = "ORDER BY created_at desc";
            return orderBy;
        }
        return "";
    }
}
