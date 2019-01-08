package controller.commands;

import exceptions.ServiceException;
import model.entity.TestDTO;
import model.entity.User;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import service.TestService;
import service.UserService;
import utility.LanguageManager;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class GetStudentResultCommandTest {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private UserService userService;
    private TestService testService;
    private LanguageManager languageManager;

    @Parameterized.Parameter()
    public String expectedPage;
    @Parameterized.Parameter(1)
    public List<TestDTO> testDTOArg;
    @Parameterized.Parameter(2)
    public int studentId;
    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        userService = mock(UserService.class);
        testService = mock(TestService.class);
        languageManager = LanguageManager.INSTANCE;
    }
    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExTest() throws ServiceException, ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("appLocale")).thenReturn(languageManager);

        when(request.getSession().getAttribute("student")).thenReturn(new User.Builder().setId(1).build());

        when(request.getSession().getAttribute("currentPage")).thenReturn(1);

        when(testService.getResultsById(anyInt(),anyInt(), anyInt())).thenThrow(ServiceException.class);
        GetStudentResultCommand command = new GetStudentResultCommand(userService, testService);
        command.execute(request, response);
    }
    @Test
    public void shouldReturnCorrectRedirect() throws ServiceException, ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("appLocale")).thenReturn(languageManager);

        when(request.getSession().getAttribute("student")).thenReturn(new User.Builder().setId(1).build());

        when(request.getSession().getAttribute("currentPage")).thenReturn(1);

        when(testService.getResultsById(anyInt(),anyInt(), anyInt())).thenReturn(testDTOArg);

        GetStudentResultCommand command = new GetStudentResultCommand(userService, testService);
        CommandResult execute = command.execute(request, response);
        assertEquals(execute.getPage(),expectedPage);
//        verify(request, atLeast(1)).setAttribute(eq("testDTOList"), anyCollection());
//        verify(request,atLeast(1)).setAttribute(eq("recordsPerPage"), anyInt());

    }

    @Parameterized.Parameters(name = " url: {0},  test : {1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {CommandPages.ERROR_PAGE, null, 1},
                {CommandPages.RESULTS_PAGE, Arrays.asList(new TestDTO.TestBuilder()
                        .setCourseName("test").build()), 2 }
        });
    }
}