package com.supsms.web.servlet.monetisation;

import com.supsms.service.InvoiceService;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet de linsting des factures d'un utilisateur
 */
@WebServlet(urlPatterns = {"/private/invoices"})
public class ListInvoicesServlet extends HttpServlet{
    @EJB
    private InvoiceService invoiceService;
    
    private static final String _viewListInvoices = "/WEB-INF/view/monetisation/userInvoicesList.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("list_invoices", invoiceService.getAllInvoices(Long.parseLong(req.getSession().getAttribute("user_id").toString())));
        req.getRequestDispatcher(_viewListInvoices).forward(req, resp);
    }   
}