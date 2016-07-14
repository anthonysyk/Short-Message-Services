package com.supsms.web.servlet.user;

import com.supsms.entity.UserEntity;
import com.supsms.service.UserService;
import com.supsms.web.form.UserFormValidator;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet d'inscription des utilisateurs
 */
@WebServlet(name = "SigninServlet", urlPatterns = {"/signin"})
public class SigninServlet extends HttpServlet {
    @EJB
    private UserService userService;
    
    private static final String _viewLocationSignin = "/WEB-INF/view/user/signin.jsp";
    private static final String _routeLogin = "/login";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.getRequestDispatcher(_viewLocationSignin).forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Validation du formulaire
        UserFormValidator validator = new UserFormValidator(request, UserFormValidator.UserFormsNames.SIGNIN);
        UserEntity user = validator.getUser();
        
        if(validator.getError().size() > 0){
            // Le formulaire est invalide, retour au formulaire
            user.setPassword(null);
            
            request.setAttribute("form_user", user);
            request.setAttribute("form_error", validator.getError());
            
            this.doGet(request, response);
        } else {
            // Le formulaire est valide, enregistrement de l'utilisateur puis connexion            
            userService.add(user);
            request.getSession().setAttribute("flash_success", "vous ếtes à présent inscrit.");
            response.sendRedirect(request.getServletContext().getContextPath() + _routeLogin);
        }
    }
}

