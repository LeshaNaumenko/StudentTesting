package filter;

import utility.Language;
import utility.LanguageManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;


@WebFilter(filterName = "langFilter")
public class LanguageFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        LanguageManager langManager = (LanguageManager) req.getSession().getAttribute("appLocale");
        String lang = req.getParameter("lang");
        if (lang != null) {
            langManager.setLocale(Language.valueOf(lang).getLocale());
        }
        req.getSession().setAttribute("appLocale", langManager);
        filterChain.doFilter(req, response);
    }
}
