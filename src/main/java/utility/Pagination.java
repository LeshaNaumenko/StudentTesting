package utility;

public class Pagination {
    private int recordsPerPage;
    private int currentPage;

    public Pagination(int recordsPerPage, int currentPage) {
        this.recordsPerPage = recordsPerPage;
        this.currentPage = currentPage;
    }

    public int calculateStart(){
        return currentPage * recordsPerPage - recordsPerPage;
    }

    public int calculateNumOfPages(int rows){
        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }
        return nOfPages;
    }

    public int getRecordsPerPage() {
        return recordsPerPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }
}
