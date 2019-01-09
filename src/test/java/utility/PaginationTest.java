package utility;

import exceptions.PaginationException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PaginationTest {

    private static int RECORDS_PER_PAGE_ZERO = 0;
    private static int CURRENT_PAGE_ZERO = 0;
    private static int ROWS_FROM_DB = 100;
    private static int CURRENT_PAGE = 2;
    private static int RECORDS_PER_PAGE = 5;
    private static int EXPECTED_START = 5;
    private static int EXPECTED_NUMBER_OF_PAGES = 20;

    private Pagination pagination;

    @Before
    public void setUp() {
        pagination = new Pagination(RECORDS_PER_PAGE, CURRENT_PAGE);
    }

    @Test
    public void calculateStart() throws PaginationException {
        int start = pagination.calculateStart();
        assertEquals(EXPECTED_START, start);

    }

    @Test
    public void calculateNumOfPages() throws PaginationException {
        int numOfPages = pagination.calculateNumOfPages(ROWS_FROM_DB);
        assertEquals(EXPECTED_NUMBER_OF_PAGES, numOfPages);
    }

    @Test(expected = PaginationException.class)
    public void shouldThrowPaginationExceptionCalculateNumOfPagesTest() throws PaginationException {
        pagination = new Pagination(RECORDS_PER_PAGE_ZERO, CURRENT_PAGE);
        pagination.calculateNumOfPages(ROWS_FROM_DB);
    }

    @Test(expected = PaginationException.class)
    public void shouldThrowPaginationExceptionCalculateStartTest() throws PaginationException {
        pagination = new Pagination(RECORDS_PER_PAGE, CURRENT_PAGE_ZERO);
        pagination.calculateStart();
    }

}