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
 * Servlet de listing des factures pour les administrateurs
 */
@WebServlet(urlPatterns = {"/private/admin/invoices"})
public class ListInvoicesAdminServlet extends HttpServlet{    
    @EJB
    private InvoiceService invoiceService;
    
    private static final String _viewListInvoices = "/WEB-INF/view/monetisation/adminInvoicesList.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("list_invoices", invoiceService.getAllInvoices());
        req.getRequestDispatcher(_viewListInvoices).forward(req, resp);
    } 
}
