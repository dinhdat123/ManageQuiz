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
public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {    
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
            
        User user = (User) session.getAttribute("user");
        String url = req.getRequestURI();

        if (url.contains("make") || url.contains("manage") || url.contains("delete")) {
            //user access to make-quiz or manage-quiz
            if (user.isIsTeacher()) {
                //user has role is teacher is allowed to access
                chain.doFilter(request, response);
            } else {
                //user has role is teacher is not allowed to access
                req.setAttribute("mess", "You don't have permission to access!");
                req.getRequestDispatcher("/view/home.jsp").forward(request, response);
            }            
        } else {
            //user access to log-out or other url
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }

}
