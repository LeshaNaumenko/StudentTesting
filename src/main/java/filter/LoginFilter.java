package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@WebFilter(filterName = "loginFilter")
public class LoginFilter implements Filter {
    public static AtomicInteger integer = new AtomicInteger(0);

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        String loginURI = request.getContextPath() + "/";
        boolean loginRequest = request.getRequestURI().equals(loginURI);
        boolean testRequest = request.getRequestURI().equals("/test");
        System.out.println(testRequest);
        if (session != null && session.getAttribute("user") != null || loginRequest || (req.getParameter("command") != null)) {
            System.out.println("sm1");
            chain.doFilter(request, response);
        } else {
            System.out.println("sm2");
            response.sendRedirect(loginURI);
        }
       /* HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        //******************************Encoding*****************************************

        if(null == request.getCharacterEncoding())
            request.setCharacterEncoding(encoding);
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        //********************************************************************************
        HttpSession session = request.getSession(false);
        String loginURI = request.getContextPath() + "/";
        String regURI = request.getContextPath() + "/registration";
        boolean loginRequest = request.getRequestURI().equals(loginURI);
        boolean regRequest = request.getRequestURI().equals(regURI);
        printSmth(req, request, session, loginURI, loginRequest);
        if (session != null && session.getAttribute("user") != null||loginRequest) {
            if(request.getAttribute("error")!=null) {
                request.removeAttribute("error");
            }
            System.out.println(">>>I  am True:" + integer.getAndIncrement());
            chain.doFilter(request, response);
        } else {
            System.out.println(">>>I  am False:" + integer.getAndIncrement());
            if(request.getSession().getAttribute("error")!=null) {
                request.removeAttribute("error");
            }
            response.sendRedirect(loginURI);
        }*/
    }

    public void printSmth(ServletRequest req, HttpServletRequest request, HttpSession session, String loginURI, boolean loginRequest) {
        if (session != null) {
            System.out.println("{  \n" +
                    "    Session is true");
            if (session.getAttribute("user") != null) {
                System.out.println("    session.getAttribute(\"user\") != null");
            } else
                System.out.println("    session.getAttribute(\"user\") IS null");
        } else
            System.out.println("{  \n" +
                    "    Session is false");
        if (loginRequest) {
            System.out.println("    req is true");
        } else
            System.out.println("    req is false");

        System.out.println("        Method: " + request.getMethod());
        System.out.println("        Request URI: " + request.getRequestURI());
        System.out.println("        Protocol: " + request.getProtocol());
        System.out.println("        PathInfo: " + request.getPathInfo());
        System.out.println("        Remote Address: " + req.getRemoteAddr());
//        System.out.println("session != null                            "+session != null);
        System.out.println("    **" + request.getRequestURI() + "**" + (loginURI) + "**\n" +
                "}");
        System.out.println(((HttpServletRequest) req).getSession().getId());
    }

    @Override
    public void destroy() {

    }
}
