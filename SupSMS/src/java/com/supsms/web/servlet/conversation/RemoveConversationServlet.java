package com.supsms.web.servlet.conversation;

import com.supsms.entity.ConversationEntity;
import com.supsms.service.ConversationService;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet de suppression d'une conversation
 */
@WebServlet(name = "RemoveConversationServlet", urlPatterns = {"/private/conversations/remove"})
public class RemoveConversationServlet extends HttpServlet {
    @EJB
    private ConversationService conversationService;
    
    private static final String _viewRemoveConversation = "/WEB-INF/view/conversation/removeConversation.jsp";
    private static final String _routeListConversations = "/home";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ConversationEntity conversation = conversationService.getOneConversationById(
                Long.parseLong(req.getSession().getAttribute("user_id").toString()), 
                Long.parseLong(req.getParameter("id").toString()));
        
        if(conversation == null)
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "conversation inconnue");
        else {
            req.setAttribute("conversation", conversation);
            req.getRequestDispatcher(_viewRemoveConversation).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ConversationEntity conversation = conversationService.getOneConversationById(
                Long.parseLong(req.getSession().getAttribute("user_id").toString()), 
                Long.parseLong(req.getParameter("id").toString()));
        
        if(conversation == null)
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "conversation inconnue");
        else {
            conversationService.removeConversation(conversation);
            req.getSession().setAttribute("flash_success", "conversation supprimée.");
            resp.sendRedirect(req.getServletContext().getContextPath() + _routeListConversations);
        }
    }
}
