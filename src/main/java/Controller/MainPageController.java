package Controller;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;
import org.example.App;

public class MainPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonCatalog;

    @FXML
    private Button buttonContactUs;

    @FXML
    private Button buttonSignIn;

    @FXML
    private Button buttonSignUp;

    @FXML
    private Pane pnlCustomer;

    @FXML
    void signUpHandle(ActionEvent event) throws IOException {
        App.setRoot("SignUp");
    }

    @FXML
    void catalogHandle(ActionEvent event) throws IOException {
        App.setRoot("Catalog");
    }

    @FXML
    void contactUsHandle(ActionEvent event) throws IOException {
        App.setRoot("ContactUsNotSigned");
    }

    @FXML
    void handleExitMainPage(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void signInHandle(ActionEvent event) throws IOException {
        App.setRoot("SignIn");
    }

    @FXML
    void initialize() {
        assert buttonCatalog != null : "fx:id=\"buttonCatalog\" was not injected: check your FXML file 'MainPage.fxml'.";
        assert buttonContactUs != null : "fx:id=\"buttonContactUs\" was not injected: check your FXML file 'MainPage.fxml'.";
        assert buttonSignIn != null : "fx:id=\"buttonSignIn\" was not injected: check your FXML file 'MainPage.fxml'.";
        assert buttonSignUp != null : "fx:id=\"buttonSignUp\" was not injected: check your FXML file 'MainPage.fxml'.";
        assert pnlCustomer != null : "fx:id=\"pnlCustomer\" was not injected: check your FXML file 'MainPage.fxml'.";

    }

}