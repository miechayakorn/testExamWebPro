/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.servlet;

import bank.jpa.Account;
import bank.jpa.History;
import bank.jpa.controller.AccountJpaController;
import bank.jpa.controller.HistoryJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

/**
 *
 * @author Acer_E5
 */
@WebServlet(name = "WithdrawServlet", urlPatterns = {"/Withdraw"})
public class WithdrawServlet extends HttpServlet {

    @PersistenceUnit(unitName = "testExamWebProPU")
    EntityManagerFactory emf;

    @Resource
    UserTransaction utx;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String withdraw = request.getParameter("withdraw");
        HttpSession session = request.getSession(false);
        //Filter
        if (session == null) {
            response.sendRedirect("Login");
            return;
        }
        if (session.getAttribute("acc") == null) {
            response.sendRedirect("Login");
            return;
        }

        if (withdraw != null && withdraw.trim().length() > 0) {

            AccountJpaController accJpa = new AccountJpaController(utx, emf);
            Account acc = accJpa.findAccount(((Account) session.getAttribute("acc")).getAccountid());
            int balance = acc.getBalance();
            int withdrawInt = Integer.parseInt(withdraw);

            if (withdrawInt > 0 && balance - withdrawInt >= 0) {
                balance = balance - withdrawInt;
                try {
                    acc.setBalance(balance);
                    accJpa.edit(acc);
                } catch (Exception ex) {
                    Logger.getLogger(DepositServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                HistoryJpaController historyJpa = new HistoryJpaController(utx, emf);
                int historyId = historyJpa.getHistoryCount() + 1;

                History history = new History();
                history.setHistoryid(historyId);
                history.setAccountid(acc);
                history.setMethod("withdraw");
                history.setAmount(withdrawInt);
                history.setTime(new Date());
                history.setBalance(balance);

                try {
                    historyJpa.create(history);
                } catch (Exception ex) {
                    Logger.getLogger(DepositServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                session.setAttribute("acc", acc);
                request.setAttribute("message", "Withdraw Complete");
                getServletContext().getRequestDispatcher("/MyAccount.jsp").forward(request, response);
                return;
            } else {
                request.setAttribute("message", "Error");
            }

        }
        getServletContext().getRequestDispatcher("/Withdraw.jsp").forward(request, response);
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
        processRequest(request, response);
    }

    /**
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
