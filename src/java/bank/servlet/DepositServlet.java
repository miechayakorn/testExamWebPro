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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

/**
 *
 * @author Acer_E5
 */
public class DepositServlet extends HttpServlet {

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
        String deposit = request.getParameter("deposit");

        if (deposit != null && deposit.trim().length() > 0) {
            HttpSession session = request.getSession(false);
            AccountJpaController accJpa = new AccountJpaController(utx, emf);
            
            //find AccountDB from session
            Account acc = accJpa.findAccount(((Account) session.getAttribute("acc")).getAccountid());
            int balance = acc.getBalance();
            int depositInt = Integer.parseInt(deposit);

            if (depositInt > 0) {
                balance = balance + depositInt;
                try {
                    //Edit Balance
                    acc.setBalance(balance);
                    //Edit AccountDB
                    accJpa.edit(acc);
                } catch (Exception ex) {
                    Logger.getLogger(DepositServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                
                HistoryJpaController historyJpa = new HistoryJpaController(utx, emf);
                History history = historyJpa.findHistory(acc.getAccountid());
                history.setHistoryid(history.getHistoryid() + 1);
                history.setAccountid(acc);
                history.setMethod("deposit");
                history.setAmount(depositInt);
                history.setTime(new Date());
                history.setBalance(balance);

                try {
                    //Create row historyDB
                    historyJpa.create(history);
                } catch (Exception ex) {
                    Logger.getLogger(DepositServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                /*//HistoryList set Session ของ Accountid
                List<History> historyList = historyJpa.findHistoryEntities();
                List<History> historyAdd = new ArrayList<>();
                for (History history1 : historyList) {
                    if (Objects.equals(history1.getAccountid().getAccountid(), acc.getAccountid())) {
                        historyAdd.add(history1);
                    }
                }
                acc.setHistoryList(historyAdd);*/
                session.setAttribute("acc", acc);
                
                response.sendRedirect("MyAccount.jsp");
                return;
            }else{
                request.setAttribute("message", "Error");
            }

        }
        getServletContext().getRequestDispatcher("/Deposit.jsp").forward(request, response);
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
