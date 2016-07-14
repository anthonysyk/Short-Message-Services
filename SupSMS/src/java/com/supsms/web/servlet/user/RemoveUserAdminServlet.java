package com.supsms.web.servlet.user;

import com.supsms.entity.UserEntity;
import com.supsms.service.UserService;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Suppression d'un utilisateur par l'administrateur
 */
@WebServlet(name = "RemoveUserAdminServlet", urlPatterns = {"/private/admin/users/remove"})
public class RemoveUserAdminServlet extends HttpServlet {
    @EJB
    private UserService userService;
    
    private static final String _viewRemoveUser = "/WEB-INF/view/user/adminRemoveUser.jsp";
    private static final String _routeListUsers = "/private/admin/users";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserEntity user = null;
        
        try {
            user = userService.getUserById(Integer.parseInt(req.getParameter("id")));
        } catch (NumberFormatException e){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "identifiant de l'utilisateur incorrect");
        }
        
        if(user == null)
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "utilisateur inconnu");
        else {
            req.setAttribute("user", user);
            req.getRequestDispatcher(_viewRemoveUser).forward(req, resp);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserEntity user = null;
        
        try {
            user = userService.getUserById(Integer.parseInt(req.getParameter("id")));
        } catch (NumberFormatException e){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "identifiant de l'utilisateur incorrect");
        }
        
        if(user == null)
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "utilisateur inconnu");
        else {
            userService.removeUser(user);
            req.getSession().setAttribute("flash_success", "utilisateur supprimé.");
            resp.sendRedirect(req.getServletContext().getContextPath() + _routeListUsers);
        }
    }
}
