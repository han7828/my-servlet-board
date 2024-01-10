package com.kitri.myservletboard;

public class Pagination {
    private int page;
    private int recordsPerPage = 10;
    private int pagesOnScreen = 5;
    private int startIndex = 0;
    private int totalRecords = 0;
    private boolean hasPrev = false;
    private boolean hasNext = false;
    private int startPageOnScreen = 1;
    private int endPageOnScreen = this.pagesOnScreen;
    public void calcPagination() {
        int totalPages = (int) Math.ceil( (double)this.totalRecords / this.recordsPerPage );
        this.startPageOnScreen = ( (int)Math.ceil( (double)this.page / this.pagesOnScreen ) - 1 ) * this.pagesOnScreen + 1;
        this.endPageOnScreen = this.startPageOnScreen + this.pagesOnScreen - 1 ;
        if ( this.endPageOnScreen > totalPages ) {
            this.endPageOnScreen = totalPages;
        }

        if (this.endPageOnScreen < totalPages) {
            this.hasNext = true;
        }
        if (this.startPageOnScreen > this.pagesOnScreen) {
            this.hasPrev = true;
        }
    }
    public Pagination(int page) { this.page = page; }
    public int getPage() { return page; }
    public int getRecordsPerPage() { return recordsPerPage; }
    public int getPagesOnScreen() { return pagesOnScreen; }
    public int getStartIndex() { return startIndex; }
    public int getTotalRecords() { return totalRecords; }

    public void setPage(int page) { this.page = page; }
    public void setRecordsPerPage(int recordsPerPage) { this.recordsPerPage = recordsPerPage; }
    public void setPagesOnScreen(int pagesOnScreen) { this.pagesOnScreen = pagesOnScreen; }
    public void setStartIndex(int startIndex) { this.startIndex = startIndex; }
    public void setTotalRecords(int totalRecords) { this.totalRecords = totalRecords; }
    public void setStartPageOnScreen(int startPageOnScreen) { this.startPageOnScreen = startPageOnScreen; }
    public void setEndPageOnScreen(int endPageOnScreen) { this.endPageOnScreen = endPageOnScreen; }
    public int getStartPageOnScreen() { return startPageOnScreen; }
    public int getEndPageOnScreen() { return endPageOnScreen; }
    public boolean isHasPrev() { return hasPrev; }
    public boolean isHasNext() { return hasNext; }
}
