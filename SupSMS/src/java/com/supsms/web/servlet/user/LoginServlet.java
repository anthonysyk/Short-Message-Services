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
 * Servlet de connexion des utilisateurs
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    @EJB
    private UserService userService;
    
    private static final String _viewLocationLogin = "/WEB-INF/view/user/login.jsp";
    private static final String _routeHome = "/home";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.getRequestDispatcher(_viewLocationLogin).forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Récupération de l'utilisateur si login valide
        UserEntity user = this.userService.getUserByLogin(
                request.getParameter("login"), 
                request.getParameter("password"));

        
        if(user != null){
            // Le login est valide alors connexion puis direction l'accueil
            request.getSession().setAttribute("user_id", user.getId());
            request.getSession().setAttribute("user_fname", user.getFname());
            request.getSession().setAttribute("role_user", true);
            request.getSession().setAttribute("role_admin", (user.isAdmin()) ? true : null);
            request.getSession().setAttribute("user_prenium", (user.isPrenium()) ? true : null);
            request.getSession().setAttribute("flash_success", "vous ếtes à présent connecté.");
            response.sendRedirect(request.getServletContext().getContextPath() + _routeHome);
        }else{
            // Le login est invalide, direction le formulaire
            request.getSession().setAttribute("flash_error", "identifiants incorrects.");
            this.doGet(request, response);
        }
    }
}
