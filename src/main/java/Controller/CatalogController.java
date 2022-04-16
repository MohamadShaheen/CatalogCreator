package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Model.Flower;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.example.App;
import org.example.MyListener;

public class CatalogController implements Initializable {

    @FXML
    private VBox chosenItem;

    @FXML
    private Button chosenItemChangePriceButton;

    @FXML
    private TextField chosenItemChangePriceTB;

    @FXML
    private Label chosenItemDetails;

    @FXML
    private ImageView chosenItemImage;

    @FXML
    private Label chosenItemName;

    @FXML
    private Label chosenItemPrice;

    @FXML
    private Label chosenItemSize;

    @FXML
    private ImageView currentPageIcon;

    @FXML
    private Label currentPageName;

    @FXML
    private GridPane gridPane;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button searchItemButton;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField searchItemTB;

    private List<Flower> flowerList = new ArrayList<>();

    private Flower flower;

    private MyListener myListener;

    private List<Flower> getData() {

        flower = new Flower("/Image/All_Day_Love.png", "All Day Love",
                65.99, "Mixed roses in a glass bowl and a teddy\nbear",
                "Approximately 11\" W x 12\" H", "ffd000");
        flowerList.add(flower);

        flower = new Flower("/Image/Basket_To_Love_You.png", "Basket To Love You",
                59.99, "Arrangement of roses in a basket and a\nteddy bear", "Approximately 12\" W x 12\" H", "f76da9");
        flowerList.add(flower);

        flower = new Flower("/Image/Beautiful_You.png", "Beautiful You",
                69.99, "Arrangement of roses in a wicker basket",
                "Approximately 12\" W x 12\" H", "f72323");
        flowerList.add(flower);

        flower = new Flower("/Image/Charming_Day.png", "Charming Day",
                88.99, "Premium long stem roses arranged in a\nglass vase and a box of chocolates", "Approximately 20\" W x 24\" H", "ff0000");
        flowerList.add(flower);

        flower = new Flower("/Image/Charming_Roses.png", "Charming Roses",
                85.99, "Arrangement of long-stemmed roses in\na vase",
                "Approximately 27\" W. x 31\" H", "750000");
        flowerList.add(flower);

        flower = new Flower("/Image/Lavender_Roses.png", "Lavender Roses",
                57.99, "Arrangement of 12 or 18 lavender roses\nin a glass vase", "Approximately 14\" W. x 16\" H", "db7fd1");
        flowerList.add(flower);

        flower = new Flower("/Image/Love_Arrangement.png", "Love Arrangement",
                64.99, "Arrangement of pink carnations, monte\ncasino, lisianthus and others in a glass\nvase",
                "Approximately 9\" W x 11\" H", "e0a2da");
        flowerList.add(flower);

        flower = new Flower("/Image/Multicoloured_Aroma.png", "Multicoloured Aroma",
                55.99, "Arrangement of gerbera, daisies, roses\nand seasonal flowers in a glass vase", "Approximately 10\" W x 11\" H", "a200ff");
        flowerList.add(flower);

        flower = new Flower("/Image/Night_Wish_Roses.png", "Night Wish Roses",
                39.99, "Arrangement of orange roses and green\nfoliage in a glass vase",
                "Approximately 16\" W. x 18\" H", "ff8c00");
        flowerList.add(flower);

        flower = new Flower("/Image/Purple_Heaven.png", "Purple Heaven",
                69.99, "Arrangement of lavender roses, pink\nwaxflower, fuchsia miniature carnations,\npurple stock, trachelium and alstroemeria\nin a glass vase.", "Approximately 10\" W x 10\" H", "9d00ff");
        flowerList.add(flower);

        flower = new Flower("/Image/Sweet_Tender.png", "Sweet Tender",
                59.99, "Arrangement of mixed pink, purple and\nlavender roses in a vase",
                "Approximately 12\" W. x 16\" H", "ff0055");
        flowerList.add(flower);

        flower = new Flower("/Image/The_Best_Day.png", "The Best Day",
                59.99, "Arrangement of roses, lilies and\nalstroemeria in a glass vase", "Approximately 10.5\" W x 11\" H", "c9c9c9");
        flowerList.add(flower);

        return flowerList;
    }

    private void setChosenItem(Flower flower) {
        chosenItemName.setText(flower.getName());
        chosenItemPrice.setText(App.CURRENCY + flower.getPrice());
        chosenItemDetails.setText(flower.getDetails());
        chosenItemSize.setText(flower.getSize());
        Image image = new Image(getClass().getResourceAsStream(flower.getImgSource()));
        chosenItemImage.setImage(image);
        chosenItem.setStyle("-fx-background-color: #" + flower.getColor() + ";\n" +
                "    -fx-background-radius: 30;");
    }

    @FXML
    void sendRequestButton(ActionEvent event) {
        String text = chosenItemChangePriceTB.getText();
        try {
            double price = Double.parseDouble(text);
            errorLabel.setTextFill(Color.web("#43bd13"));
            errorLabel.setText("The request was sent successfully");
        } catch (Exception e) {
            errorLabel.setTextFill(Color.web("#ff0000"));
            errorLabel.setText("The price must be numeric");
            e.printStackTrace();
        }
    }

    @FXML
    void textChanged(KeyEvent event) {
        gridPane.getChildren().clear();
        String text = searchItemTB.getText();
        if (text == "") {
            flowerList.clear();
            getData();
            loadGridPane();
            return;
        }
        flowerList.clear();
        getData();
        myListener = new MyListener() {
            @Override
            public void onClickListener(Flower flower) {
                setChosenItem(flower);
            }
        };
        int column = 0;
        int row = 1;
        for (int i = 0; i < flowerList.size(); i++) {
            try {
                if (flowerList.get(i).getName().toUpperCase().contains(text.toUpperCase())) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/org/example/Item.fxml"));

                    AnchorPane anchorPane = fxmlLoader.load();

                    ItemController itemController = fxmlLoader.getController();
                    itemController.setData(flowerList.get(i), myListener);

                    if (column == 3) {
                        column = 0;
                        row++;
                    }

                    gridPane.add(anchorPane, column++, row);
                    gridPane.setMinWidth(Region.USE_COMPUTED_SIZE);
                    gridPane.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    gridPane.setMaxWidth(Region.USE_PREF_SIZE);

                    gridPane.setMinHeight(Region.USE_COMPUTED_SIZE);
                    gridPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    gridPane.setMaxHeight(Region.USE_PREF_SIZE);

                    GridPane.setMargin(anchorPane, new Insets(10));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadGridPane() {
        myListener = new MyListener() {
            @Override
            public void onClickListener(Flower flower) {
                setChosenItem(flower);
            }
        };
        int column = 0;
        int row = 1;


        for (int i = 0; i < flowerList.size(); i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/org/example/Item.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(flowerList.get(i), myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                gridPane.add(anchorPane, column++, row);
                gridPane.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridPane.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridPane.setMaxWidth(Region.USE_PREF_SIZE);

                gridPane.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridPane.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        errorLabel.setText("");
        getData();
        if (flowerList.size() > 0) setChosenItem(flowerList.get(0));
        loadGridPane();
    }
}
