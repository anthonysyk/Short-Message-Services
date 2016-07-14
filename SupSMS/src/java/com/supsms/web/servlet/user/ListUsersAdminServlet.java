package com.supsms.web.servlet.user;

import com.supsms.entity.UserEntity;
import com.supsms.service.UserService;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet de listing des utilisateurs pour l'administrateur
 */
@WebServlet(name = "ListUsersAdminServlet", urlPatterns = {"/private/admin/users"})
public class ListUsersAdminServlet extends HttpServlet {
    @EJB
    private UserService userService;
    
    private static final String _viewListUsers = "/WEB-INF/view/user/adminListUsers.jsp";
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserEntity> users = userService.getAllUsers();
        req.setAttribute("list_users", users);
        
        req.getRequestDispatcher(_viewListUsers).forward(req, resp);
    }
    
}
