/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author DINHDAT
 */
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String url = req.getRequestURI();

        if (url.contains("quiz") || url.contains("delete")) {
            //if user access to take-quiz or make-quiz or manage-quiz
            User user = (User) session.getAttribute("user");
            if (user == null) {
                //user is not login
                  req.setAttribute("mess", "You have to login firstly");
                  req.getRequestDispatcher("/view/login.jsp").forward(request, response);
            } else {
                //user has logged in then direct to other filter
                chain.doFilter(request, response);
            }
        } else {
            //user access to home
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {
    }

}
