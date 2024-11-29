package atomic.bomber.controller.menus;

import atomic.bomber.controller.ControllerResponse;
import atomic.bomber.model.User;

public class ProfileController {
    public static void deleteAccount() {
        User user = LoginController.getCurrentUser();
        User.removeUser(user);
        LoginController.setCurrentUser(null);
    }

    public static ControllerResponse changePassword(String oldPassword, String newPassword, String confirmPassword) {
        User user = LoginController.getCurrentUser();

        String err = "";
        boolean isFail = true;
        if (!user.getPassword().equals(oldPassword)) err = "Old Password Incorrect";
        else if (newPassword.isEmpty()) err = "New Password Empty";
        else if (newPassword.equals(oldPassword)) err = "Same Password";
        else if (!newPassword.equals(confirmPassword)) err = "Confirm Password Doesn't Match";
        else {
            isFail = false;
            user.setPassword(newPassword);
            user.save();
            err = "Password Changed Successfully";
        }

        return new ControllerResponse(err, isFail);
    }

    public static ControllerResponse changeUsername(String username) {
        User user = LoginController.getCurrentUser();

        String err = "";
        boolean isFail = true;
        if (user.getUsername().equals(username)) err = "Same Username";
        else if (username.isEmpty()) err = "Empty Username";
        else {
            user.setUsername(username);
            user.save();
            err = "Changed Successfully";
            isFail = false;
        }

        return new ControllerResponse(err, isFail);
    }
}
