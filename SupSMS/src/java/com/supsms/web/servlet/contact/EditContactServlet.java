package com.supsms.web.servlet.contact;

import com.supsms.entity.ContactEntity;
import com.supsms.service.ContactService;
import com.supsms.web.form.ContactFormValidator;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet de modification d'un contact
 */

@WebServlet(name = "EditContactServlet", urlPatterns = {"/private/contacts/edit"})
public class EditContactServlet extends HttpServlet{
    @EJB
    private ContactService contactService;
    
    private static final String _viewEditContact = "/WEB-INF/view/contact/editContact.jsp";
    private static final String _routeListContacts = "/private/contacts";
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        ContactEntity contact = null;
        
        try {
            contact = contactService.getContactById(
                    Integer.parseInt(req.getParameter("id")),
                    Long.parseLong(req.getSession().getAttribute("user_id").toString()));
        } catch (NumberFormatException e){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "identifiant du contact incorrect");
        }
        
        if(contact == null)
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "contact inconnu");
        else {
            req.setAttribute("contact", contact);
            req.getRequestDispatcher(_viewEditContact).forward(req, resp);
         }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        ContactEntity contact = contactService.getContactById(
                Integer.parseInt(req.getParameter("id")),
                Long.parseLong(req.getSession().getAttribute("user_id").toString()));
        
        // Validation du formulaire
        ContactFormValidator validator = new ContactFormValidator(req, ContactFormValidator.ContactFormsNames.CONTACT, contact);
        
        if(validator.getError().size() > 0){
            req.setAttribute("form_contact", contact);
            req.setAttribute("form_error", validator.getError());
            
            this.doGet(req, resp);
        } else {
            // Enregistrement des modifications
            contactService.updateContact(contact);
            req.getSession().setAttribute("flash_success", "modifications enregistrées.");
            resp.sendRedirect(req.getServletContext().getContextPath() + _routeListContacts);
        }
    }
    
}
