package com.supsms.web.servlet.conversation;

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
 * Servlet d'affichage d'une conversation
 */
@WebServlet(name = "detailsConversationServlet", urlPatterns = {"/private/conversations/details"})
public class detailsConversationServlet extends HttpServlet {
    @EJB
    private ContactService contactService;
    @EJB
    private ConversationService conversationService;
    
    private static final String _viewDetailsConversation = "/WEB-INF/view/conversation/detailsConversation.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ConversationEntity conversation;
        
        if(req.getParameter("message") != null)
            // La conversation est à afficher à partir d'un ID de message
            conversation = conversationService.getOneMessageById(
                Long.parseLong(req.getSession().getAttribute("user_id").toString()), 
                Long.parseLong(req.getParameter("message"))).getConversation();
        else
            // La conversation est à afficher à partir d'un ID de conversation
            conversation = conversationService.getOneConversationById(
                Long.parseLong(req.getSession().getAttribute("user_id").toString()), 
                Long.parseLong(req.getParameter("id")));
        
        if(conversation == null)
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "conversation inconnue");
        else {
            ContactEntity contact = contactService.getOneContactByPhone(
                    conversation.getPhone(), 
                    Long.parseLong(req.getSession().getAttribute("user_id").toString()));
            
            req.setAttribute("contact", contact);
            req.setAttribute("conversation", conversation);
            req.getRequestDispatcher(_viewDetailsConversation).forward(req, resp);
        }
    }
}
