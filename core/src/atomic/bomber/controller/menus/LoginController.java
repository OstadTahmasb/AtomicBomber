package atomic.bomber.controller.menus;

import atomic.bomber.controller.ControllerResponse;
import atomic.bomber.model.User;

public class LoginController {
    public static User currentUser;

    public static ControllerResponse login(String username, String password) {
        User user = User.getUserByUsername(username);

        String err = "";
        boolean isFail = true;
        if (user == null) {
            err = "User not found";
        } else if (!user.getPassword().equals(password)) {
            err = "Wrong password";
        } else {
            isFail = false;
            LoginController.currentUser =  user;
        }

        return new ControllerResponse(err, isFail);
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }
}
