package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class {@code LoginFilter} is a filter that does the duty of checking requests
 * before allowing accessing the protected pages.
 *
 * @author Alex Naumenko
 *
 * @see Filter
 */
@WebFilter(filterName = "loginFilter")
public class LoginFilter implements Filter {

    /**
     * This method checks requests before allowing accessing the protected pages.
     *
     * @param req     {@link ServletRequest}.
     * @param res    {@link ServletResponse}.
     * @param chain {@link FilterChain}.
     *
     * @throws IOException      if I/O error occurs.
     * @throws ServletException if any inner exception in servlet occurs.
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        String loginURI = request.getContextPath() + "/";
        boolean loginRequest = request.getRequestURI().equals(loginURI);
        if (session != null && session.getAttribute("user") != null || loginRequest || (req.getParameter("command") != null)) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(loginURI);
        }
    }
}
