package filter;

import utility.Language;
import utility.LanguageManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

/**
 * Class {@code LanguageFilter} is a filter for changing the language.
 *
 * @author Alex Naumenko
 *
 * @see Filter
 */
@WebFilter(filterName = "langFilter")
public class LanguageFilter implements Filter {

    /**
     * This method sets the locale.
     *
     * @param request     {@link ServletRequest}.
     * @param response    {@link ServletResponse}.
     * @param filterChain {@link FilterChain}.
     *
     * @throws IOException      if I/O error occurs.
     * @throws ServletException if any inner exception in servlet occurs.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        LanguageManager langManager = (LanguageManager) req.getSession().getAttribute("appLocale");
        String lang = req.getParameter("lang");
        if (lang != null) {
            langManager.setLocale(lang);
            req.getSession().setAttribute("appLocale", langManager);
        }
        filterChain.doFilter(req, response);
    }
}
