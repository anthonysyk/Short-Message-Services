package com.supsms.web.servlet.conversation;

import com.supsms.entity.ContactEntity;
import com.supsms.entity.MessageEntity;
import com.supsms.service.ContactService;
import com.supsms.service.ConversationService;
import com.supsms.service.UserService;
import com.supsms.web.form.ConversationFormValidator;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.jms.JMSException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet d'envoi d'un nouveau message
 */
@WebServlet(name = "NewMessageServlet", urlPatterns = {"/private/messages/new"})
public class NewMessageServlet extends HttpServlet {
    @EJB
    private ContactService contactService;
    @EJB
    private UserService userService;
    @EJB
    private ConversationService conversationService;
    
    private static final String _viewNewMessage = "/WEB-INF/view/conversation/newMessage.jsp";
    private static final String _routeConversationOut = "/private/conversations/details?message=";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        if(req.getParameter("contact") != null){
            Integer contactId = Integer.parseInt(req.getParameter("contact"));
            ContactEntity contact = contactService.getContactById(contactId, 
                    Long.parseLong(req.getSession().getAttribute("user_id").toString()));
            req.setAttribute("contact_phone", contact.getPhone());
        }
        req.getRequestDispatcher(_viewNewMessage).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        // Validation du formulaire
        ConversationFormValidator validator = new ConversationFormValidator(
                req, ConversationFormValidator.ConversationFormsNames.NEW_MSG_PHONE);
        MessageEntity message = validator.getMessage();
        
        if(validator.getError().size() > 0){
            // Le formulaire est invalide, retour au formulaire
            req.setAttribute("contact_phone", message.getPhone());
            req.setAttribute("form_message", message);
            req.setAttribute("form_error", validator.getError());
            this.doGet(req, resp);
        } else {
            // Le formulaire est valide, envoi du message et direction la conversation
            try {
                message = conversationService.sendMessage(message, Long.parseLong(req.getSession().getAttribute("user_id").toString()));
            } catch (JMSException ex) {
                Logger.getLogger(NewMessageServlet.class.getName()).log(Level.SEVERE, "erreur JMS", ex);
            }
            resp.sendRedirect(req.getServletContext().getContextPath() + _routeConversationOut + message.getId());
        }
    }  
}
