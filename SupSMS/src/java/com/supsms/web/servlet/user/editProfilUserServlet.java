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
 * Edition du profil d'un utilisateur
 * @author remy
 */
@WebServlet(name = "editProfilUser", urlPatterns = {"/private/profil/update"})
public class editProfilUserServlet extends HttpServlet {
    @EJB
    private UserService userService;
    
    private static final String _viewEditProfil = "/WEB-INF/view/user/editProfilUser.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserEntity user = userService.getUserById(Integer.parseInt(req.getSession().getAttribute("user_id").toString()));
        
        req.setAttribute("user", user);
        req.getRequestDispatcher(_viewEditProfil).forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserEntity user = userService.getUserById(Integer.parseInt(req.getSession().getAttribute("user_id").toString()));
        
        if(req.getParameter("part").equals("password")){
            // Modification du mot de passe
            UserFormValidator validator = new UserFormValidator(req, UserFormValidator.UserFormsNames.PROFIL_PASSWORD, user);
            
            if(validator.getError().size() > 0)
                // Il y a des erreurs, retour au formulaire
                req.setAttribute("form_error_password", validator.getError());
            else {
                // Enregistrement des modifications
                userService.updateUser(user, true);
                req.setAttribute("flash_success", "modifications enregistrées.");
            }
        } else {
            // Modification des informations
            UserFormValidator validator = new UserFormValidator(req, UserFormValidator.UserFormsNames.PROFIL_INFO, user);
            
            if(validator.getError().size() > 0)
                req.setAttribute("form_error_informations", validator.getError());
            else {
                // Enregistrement des modifications
                userService.updateUser(user, false);
                req.getSession().setAttribute("flash_success", "modifications enregistrées.");
            }
        }
        
        this.doGet(req, resp);
    }    
}
