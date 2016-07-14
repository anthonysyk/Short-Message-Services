package com.supsms.web.servlet.contact;

import com.supsms.entity.ContactEntity;
import com.supsms.service.ContactService;
import com.supsms.service.UserService;
import com.supsms.web.form.ContactFormValidator;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet d'ajout de contact
 */
@WebServlet(name = "AddContactServlet", urlPatterns = {"/private/contacts/new"})
public class AddContactServlet extends HttpServlet {
    @EJB
    private ContactService contactService;
    @EJB
    private UserService userService;
            
    private static final String _viewLocationAddContact = "/WEB-INF/view/contact/addContact.jsp";
    private static final String _routeContacts = "/private/contacts";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.getRequestDispatcher(_viewLocationAddContact).forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
        
        // Validation du formulaire
        ContactFormValidator validator = new ContactFormValidator(request, ContactFormValidator.ContactFormsNames.CONTACT);
        ContactEntity contact = validator.getContact();
        
        
        if(validator.getError().size() > 0){
            // Le formulaire est invalide, retour au formulaire
            
            request.setAttribute("form_contact", contact);
            request.setAttribute("form_error", validator.getError());
            
            this.doGet(request, response);
        } else {
            // Le formulaire est valide, enregistrerment du contact puis retour à la liste de contacts
            contact.setUser(userService.getUserById(Integer.parseInt(request.getSession().getAttribute("user_id").toString())));
            contactService.add(contact);
            request.getSession().setAttribute("flash_success", "contact ajouté.");
            response.sendRedirect(request.getServletContext().getContextPath() + _routeContacts);
        }
        
    }
    
}
