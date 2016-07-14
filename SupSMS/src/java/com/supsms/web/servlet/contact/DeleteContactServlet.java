package com.supsms.web.servlet.contact;

import com.supsms.entity.ContactEntity;
import com.supsms.entity.ConversationEntity;
import com.supsms.service.ContactService;
import com.supsms.service.ConversationService;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Suppression d'un contact
 */
@WebServlet(name = "DeleteContactServlet", urlPatterns = {"/private/contacts/remove"})
public class DeleteContactServlet extends HttpServlet{
    @EJB
    private ConversationService conversationService;
    @EJB
    private ContactService contactService;
    
    private static final String _viewDeleteUser = "/WEB-INF/view/contact/deleteContact.jsp";
    private static final String _routeListContacts = "/private/contacts";
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        ContactEntity contact = null;
        
        try{
            contact = contactService.getContactById(Integer.parseInt(req.getParameter("id")), 
                    Long.parseLong(req.getSession().getAttribute("user_id").toString()));
        } catch (NumberFormatException e){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "identifiant du contact incorrect");
        }
        
        if(contact == null)
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "contact inconnu");
        else {
            req.setAttribute("contact", contact);
            req.getRequestDispatcher(_viewDeleteUser).forward(req, resp);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
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
            // Suppression des messages liés au contact
            ConversationEntity conversation =  conversationService.getOneConversationByPhone(
                    Long.parseLong(req.getSession().getAttribute("user_id").toString()), 
                    contact.getPhone());
            if(conversation != null)
                conversationService.removeConversation(conversation);
            
            // Suppression du contact
            contactService.removeContact(contact);
            
            req.getSession().setAttribute("flash_success", "contact supprimé.");
            resp.sendRedirect(req.getServletContext().getContextPath() + _routeListContacts);
        }
    }
    
}
