package com.supsms.web.servlet.monetisation;

import com.supsms.entity.InvoiceEntity;
import com.supsms.service.InvoiceService;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet de detail d'une facture
 */
@WebServlet(urlPatterns = {"/private/invoices/details", "/private/admin/invoices/details"})
public class InvoiceServlet extends HttpServlet{    
    @EJB
    private InvoiceService invoiceService;
    
    private static final String _viewInvoice = "/WEB-INF/view/monetisation/invoice.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InvoiceEntity invoice = invoiceService.getOneById(
                Long.parseLong(req.getParameter("id")),
                Long.parseLong(req.getSession().getAttribute("user_id").toString()));
        
        if(invoice == null)
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "facture inconnue");
        else {
            req.setAttribute("invoice", invoice);
            req.getRequestDispatcher(_viewInvoice).forward(req, resp);
        }
    }
}
