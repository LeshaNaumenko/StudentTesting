package utility;

import exceptions.PaginationException;

public class Pagination {
    private int recordsPerPage;
    private int currentPage;

    public Pagination(int recordsPerPage, int currentPage) {
        this.recordsPerPage = recordsPerPage;
        this.currentPage = currentPage;
    }

    public int calculateStart() throws PaginationException {
        if(currentPage == 0){
            throw new PaginationException("Argument 'currentPage' is 0");
        }
        return currentPage * recordsPerPage - recordsPerPage;
    }

    public int calculateNumOfPages(int rows) throws PaginationException {
        if (recordsPerPage==0)
            throw new PaginationException("Argument 'recordsPerPage' is 0");
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

