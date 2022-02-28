package controller;

import dto.User;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import model.UserModel;

/**
 *
 * @author Alejandra
 */
@Named("userController")
@RequestScoped
public class UserController {

    @Inject
    UserModel userModel;
    @Inject
    User user;

    private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

    // Guardar Registros
    public String saveUser() {

        System.out.println("controller.UserController.saveUser()");
        userModel.save(user);
        return "index.xhtml?faces-redirect=true";

    }

    // Listar Registros
    public List<User> userList() {

        System.out.println("controller.UserController.userList()");
        return userModel.usersList();

    }

    // Elimanar Registros    
    public String deleteUser(int id){
        
        System.out.println("controller.UserController.deleteUser()");
        userModel.delete(id);
        return "index.xhtml?faces-redirect=true";
        
    }

    // Cargar Registros    
    public String loadUser(int id){
        System.out.println("controller.UserController.loadUser()");
        User userEdit = userModel.edit(id);
        sessionMap.put("editUser", userEdit);
        return "/edit.xhtml?faces-redirect=true";
    }
 
    // Actualizar Registros
    public String updateUser(User userEdit) {

        System.out.println("controller.UserController.updateUser()");
        userModel.update(userEdit);
        return "index.xhtml?faces-redirect=true";

    }

}
