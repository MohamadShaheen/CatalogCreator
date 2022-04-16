package Controller;

import java.io.IOException;

import Model.Flower;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.example.App;
import org.example.MyListener;

public class ItemController {

    @FXML
    private AnchorPane anItem;

    @FXML
    private ImageView imageLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;

    private Flower flower;

    private MyListener myListener;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(flower);
    }

    public void setData(Flower flower, MyListener myListener) {
        this.flower = flower;
        this.myListener = myListener;
        nameLabel.setText(flower.getName());
        priceLabel.setText(App.CURRENCY + flower.getPrice());
        Image image = new Image(getClass().getResourceAsStream(flower.getImgSource()));
        imageLabel.setImage(image);
        anItem.setStyle("-fx-background-color: #" + flower.getColor() + ";\n" +
                "    -fx-background-radius: 30;" +
                "-fx-effect: dropShadow(three-pass-box,rgba(0,0,0,0.1), 10.0 , 0.0 , 0.0 ,10.0);");
    }
}