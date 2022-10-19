/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.QuestionDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Question;

/**
 *
 * @author DINHDAT
 */
public class ManageQuizServlet extends HttpServlet {

    private static int PAGE_SIZE = 5;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            QuestionDao qDao = new QuestionDao();       
            int totalPage = qDao.getTotalPage(PAGE_SIZE);
            //get total page with page size 
            int pageIndex = 1;
            //standard page index is 1
            if (request.getParameter("page") != null) {
                //the page index is assigned when the parameter is requested
                pageIndex = Integer.parseInt(request.getParameter("page"));
                if (totalPage < pageIndex) {
                    //when the page index is over total page then return to client 404 page
                    request.getRequestDispatcher("view/error404.jsp").forward(request, response);
                    return;
                }
            }
            request.setAttribute("pageIndex", pageIndex);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("number", qDao.countQuestion());
            ArrayList<Question> list = qDao.getQuestions(pageIndex, PAGE_SIZE);
            request.setAttribute("questions", list);
            request.getRequestDispatcher("view/manageQuiz.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("error", e);
            request.getRequestDispatcher("view/error.jsp").forward(request, response);
        }

    }

    /**
     * s
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
