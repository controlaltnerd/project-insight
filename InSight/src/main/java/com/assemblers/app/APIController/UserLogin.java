//this class is for login @para (username, password) return User
package com.assemblers.app.APIController;
import com.assemblers.app.Models.User;
import com.assemblers.app.DatabaseAccess.UserLoginDAO;

public class UserLogin {
    public static User login(String username, String password) {
        return (UserLoginDAO.login(username, password));
    }
}
