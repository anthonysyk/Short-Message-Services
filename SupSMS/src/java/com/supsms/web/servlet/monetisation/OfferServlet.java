package com.supsms.web.servlet.monetisation;

import com.supsms.entity.InvoiceEntity;
import com.supsms.entity.UserEntity;
import com.supsms.service.InvoiceService;
import com.supsms.service.UserService;
import java.io.IOException;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet de l'offre professionnelle
 */
@WebServlet(name = "OfferServlet", urlPatterns = {"/product"})
public class OfferServlet extends HttpServlet{
    @EJB
    private InvoiceService invoiceService;
    @EJB
    private UserService userService;
    
    private static final String _viewLocationOfferAnonymous = "/WEB-INF/view/monetisation/offerAnonymous.jsp";
    private static final String _viewLocationOfferUsers = "/WEB-INF/view/monetisation/offerUser.jsp";
    private static final String _routeFacture = "/private/invoices/details?id=";
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException{
        
        if(req.getSession().getAttribute("user_id") != null){
            // Offres des utilisateurs
            req.getRequestDispatcher(_viewLocationOfferUsers).forward(req, resp);
        } else {
            // Offre pour les anonymes
            req.getRequestDispatcher(_viewLocationOfferAnonymous).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Upgrade du compte
        UserEntity user = userService.getUserById(Integer.parseInt(req.getSession().getAttribute("user_id").toString()));
        user.setPrenium(true);
        userService.updateUser(user, false);
        
        // Création de la facture
        InvoiceEntity invoice = new InvoiceEntity();
        invoice.setDate(new Date());
        invoice.setPrice(10);
        invoice.setTaxe(20);
        invoice.setUser(user);
        invoiceService.add(invoice);
        
        req.getSession().setAttribute("user_prenium", (user.isPrenium()) ? true : null);
        req.getSession().setAttribute("flash_success", "vous êtes à présent compte professionnel.");
        
        resp.sendRedirect(req.getServletContext().getContextPath() + _routeFacture + invoice.getId());
    }
    
}
