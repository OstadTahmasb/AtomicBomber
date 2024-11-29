package atomic.bomber.controller.menus;

import atomic.bomber.controller.ControllerResponse;
import atomic.bomber.model.User;

public class RegisterController {
    public static ControllerResponse register(String username, String password, String confirmPassword) {
        boolean isFail = true;
        String error = "";

        if (username.isEmpty()) {
            error = "Username is empty";
        } else if (password.isEmpty()) {
            error = "Password is empty";
        } else if (confirmPassword.isEmpty()) {
            error = "Confirm Password is empty";
        } else if (!password.equals(confirmPassword)) {
            error = "Passwords not equal";
        } else {
            User user = User.getUserByUsername(username);
            if (user != null) {
                error = "Username taken";
            }
        }

        User user = new User(username, password);
        user.save();

        if (error.isEmpty()) {
            error = "Register successful";
            isFail = false;
        }

        return new ControllerResponse(error, isFail);
    }
}
