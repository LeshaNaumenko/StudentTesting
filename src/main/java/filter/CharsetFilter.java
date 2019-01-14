package filter;

import javax.servlet.*;

import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Class {@code CharsetFilter} is a filter that allows you to change the encoding.
 *
 * @author Alex Naumenko
 *
 * @see Filter
 */

@WebFilter(filterName = "charsetFilter")
public class CharsetFilter implements Filter {
    private String encoding;

    /**
     * Set the encoding for FilterConfig
     *
     * @param filterConfig {@link FilterConfig}
     */

    @Override
    public void init(FilterConfig filterConfig) {
        encoding = filterConfig.getInitParameter("requestEncoding");
        if (encoding == null) encoding = "UTF-8";
    }

    /**
     * Request coding.
     *
     * @param servletRequest  {@link ServletRequest}.
     * @param servletResponse {@link ServletResponse}.
     * @param filterChain     {@link FilterChain}.
     *
     * @throws IOException      if I/O error occurs.
     * @throws ServletException if any inner exception in servlet occurs.
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (null == servletRequest.getCharacterEncoding()) {
            servletRequest.setCharacterEncoding(encoding);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
