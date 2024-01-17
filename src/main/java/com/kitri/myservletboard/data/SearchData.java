package com.kitri.myservletboard.data;

import java.time.LocalDate;

public class SearchData {
    private String searchField;
    private String searchText;
    private String searchTime;
    private int timeIndex;

    public SearchData(String searchField, String searchText, String searchTime) {
        this.searchField = searchField;
        this.searchText = searchText;
        this.searchTime = searchTime;
    }
    public String getSearchField() {
        if( searchField == null ) { return searchField = "title"; }
        return searchField;
    }
    public String getSearchText() {
        if( searchText == null ) { return ""; }
        return searchText;
    }
    public String getSearchTime() {
        if( searchTime == null ) { return searchTime = "allday"; }
        return searchTime;
    }
    public void setSearchField(String searchField) { this.searchField = searchField; }
    public void setSearchText(String searchText) { this.searchText = searchText; }
    public void setSearchTime(String searchTime) { this.searchTime = searchTime; }

    public void setTimeIndex(String setSearchTime) {
        if (setSearchTime.equals("1day")) { this.timeIndex = 1; }
        if (setSearchTime.equals("1week")) { this.timeIndex = 7; }
        if (setSearchTime.equals("1month")) { this.timeIndex = 30; }
        if (setSearchTime.equals("6month")) { this.timeIndex = 180; }
        if (setSearchTime.equals("1year")) { this.timeIndex = 365; }
    }
    public int getTimeIndex() {
        return timeIndex;
    }
}
