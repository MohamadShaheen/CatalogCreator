package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.App;

import java.io.IOException;

// In handleSignInButton check what's written

public class SignInController {

    @FXML
    private Label errorMessageLabel;

    @FXML
    void handleContactUs() throws IOException {
        App.setRoot("ContactUsNotSigned");
    }

    @FXML
    void handleExitSignIn() {
        System.exit(0);
    }

    @FXML
    void handleHomeSignIn() throws IOException {
        App.setRoot("MainPage");
    }

    @FXML
    void handleSignInButton() {

        // if the user name is found in the data base and the password is matching with it then
        // App.setRoot("MainPage");
        // else
        // errorMessage.setVisible(true);
    }

    @FXML
    void handleSignUpButton() throws IOException {
        App.setRoot("SignUp");
    }

    @FXML
    void initialize() {

        errorMessageLabel.setVisible(false);
    }
}