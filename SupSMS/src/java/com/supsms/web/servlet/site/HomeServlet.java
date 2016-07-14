package com.supsms.web.servlet.site;

import com.supsms.entity.ContactEntity;
import com.supsms.entity.ConversationEntity;
import com.supsms.service.ContactService;
import com.supsms.service.ConversationService;
import com.supsms.service.UserService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet de l'accueil du site
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"", "/home"})
public class HomeServlet extends HttpServlet {
    @EJB
    private ConversationService conversationService1;
    @EJB
    private ContactService contactService;
    @EJB
    private ConversationService conversationService;
    @EJB
    private UserService userService;
    
    private static final String _viewLocationHomeAnonymous = "/WEB-INF/view/site/homeAnonymous.jsp";
    private static final String _viewLocationHomeUsers = "/WEB-INF/view/site/homeUsers.jsp";
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        if(req.getSession().getAttribute("user_id") != null){
            Long userId = Long.parseLong(req.getSession().getAttribute("user_id").toString());
            
            // Accueil des utilisateurs (conversations)
            List<ConversationEntity> conversations = conversationService.getAllConversations(userId);
            List<ContactEntity> contactsList = contactService.getAllContactsByUserId(userId);
            Map<String, ContactEntity> contactsMap = contactsList.stream()
                            .collect(Collectors.toMap(ContactEntity::getPhone,
                                                      item -> item));
            
            req.setAttribute("contacts_list", contactsMap);
            req.setAttribute("list_conversations", conversations);
            req.getRequestDispatcher(_viewLocationHomeUsers).forward(req, resp);
        } else {
            // Accueil des visiteur anonymes (statistiques)
            req.setAttribute("users_count", userService.countAllUsers());
            req.setAttribute("messages_count", conversationService.countAllMessages());
            req.getRequestDispatcher(_viewLocationHomeAnonymous).forward(req, resp);
        }
    }
}