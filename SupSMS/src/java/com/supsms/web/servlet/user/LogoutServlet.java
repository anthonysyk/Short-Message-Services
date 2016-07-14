package com.supsms.web.servlet.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet de déconnexion des utilisateurs
 */
@WebServlet(name = "LogoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {
    private static final String _routeLogin = "/login";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Déstruction de la session utilisateur puis direction la connexion
        request.getSession().invalidate();
        request.getSession().setAttribute("flash_success", "vous ếtes à présent déconnecté.");
        response.sendRedirect(request.getServletContext().getContextPath() + _routeLogin);
    }
}