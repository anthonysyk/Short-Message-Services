package com.supsms.web.servlet.contact;

import com.supsms.entity.ContactEntity;
import com.supsms.service.ContactService;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet de listing des contacts
 */
@WebServlet(name = "ListContactsServlet", urlPatterns = {"/private/contacts"})
public class ListContactsServlet extends HttpServlet {
    @EJB
    private ContactService contactService;
    
    private static final String _viewListContacts = "/WEB-INF/view/contact/contacts.jsp";
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ContactEntity> contacts = contactService.getAllContactsByUserId(Long.parseLong(req.getSession().getAttribute("user_id").toString()));
        req.setAttribute("list_contacts", contacts);
        
        req.getRequestDispatcher(_viewListContacts).forward(req,resp);
    }
}
