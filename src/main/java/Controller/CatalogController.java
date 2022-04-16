package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import Model.Flower;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.example.App;
import org.example.MyListener;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class CatalogController implements Initializable {

    private static Session session;

    @FXML
    private VBox chosenItem;

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
    private GridPane gridPane;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField searchItemTB;

    private final List<Flower> flowerList = new ArrayList<>();

    private MyListener myListener;

    private static SessionFactory getSessionFactory() throws HibernateException {
        Configuration configuration =new Configuration();
        configuration.addAnnotatedClass(Flower.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    private void getData() {

        Flower flower = new Flower("/Image/All_Day_Love.png", "All Day Love",
                65.99, "Mixed roses in a glass bowl and a teddy bear",
                "Approximately 11\" W x 12\" H", "ffd000");
        flowerList.add(flower);
        session.save(flower);

        flower = new Flower("/Image/Basket_To_Love_You.png", "Basket To Love You",
                59.99, "Arrangement of roses in a basket and a teddy bear", "Approximately 12\" W x 12\" H", "f76da9");
        flowerList.add(flower);
        session.save(flower);

        flower = new Flower("/Image/Beautiful_You.png", "Beautiful You",
                69.99, "Arrangement of roses in a wicker basket",
                "Approximately 12\" W x 12\" H", "f72323");
        flowerList.add(flower);
        session.save(flower);

        flower = new Flower("/Image/Charming_Day.png", "Charming Day",
                88.99, "Premium long stem roses arranged in a glass vase and a box of chocolates", "Approximately 20\" W x 24\" H", "ff0000");
        flowerList.add(flower);
        session.save(flower);

        flower = new Flower("/Image/Charming_Roses.png", "Charming Roses",
                85.99, "Arrangement of long-stemmed roses in a vase",
                "Approximately 27\" W. x 31\" H", "750000");
        flowerList.add(flower);
        session.save(flower);

        flower = new Flower("/Image/Lavender_Roses.png", "Lavender Roses",
                57.99, "Arrangement of 12 or 18 lavender roses in a glass vase", "Approximately 14\" W. x 16\" H", "db7fd1");
        flowerList.add(flower);
        session.save(flower);

        flower = new Flower("/Image/Love_Arrangement.png", "Love Arrangement",
                64.99, "Arrangement of pink carnations, monte casino, lisianthus and others in a glass vase",
                "Approximately 9\" W x 11\" H", "e0a2da");
        flowerList.add(flower);
        session.save(flower);

        flower = new Flower("/Image/Multicoloured_Aroma.png", "Multicoloured Aroma",
                55.99, "Arrangement of gerbera, daisies, roses and seasonal flowers in a glass vase", "Approximately 10\" W x 11\" H", "a200ff");
        flowerList.add(flower);
        session.save(flower);

        flower = new Flower("/Image/Night_Wish_Roses.png", "Night Wish Roses",
                39.99, "Arrangement of orange roses and green foliage in a glass vase",
                "Approximately 16\" W. x 18\" H", "ff8c00");
        flowerList.add(flower);
        session.save(flower);

        flower = new Flower("/Image/Pop_Israel_Flowers.png", "Pop Israel Flowers",
                56.99, "Arrangement of daisies, peruvian lilies, gerberas and chrysanthemums along with a vase", "Approximately 9\" H x 8\" W", "196ef7");
        flowerList.add(flower);
        session.save(flower);

        flower = new Flower("/Image/Sweet_Tender.png", "Sweet Tender",
                59.99, "Arrangement of mixed pink, purple and lavender roses in a vase",
                "Approximately 12\" W. x 16\" H", "ff0055");
        flowerList.add(flower);
        session.save(flower);

        flower = new Flower("/Image/The_Best_Day.png", "The Best Day",
                59.99, "Arrangement of roses, lilies and alstroemeria in a glass vase", "Approximately 10.5\" W x 11\" H", "c9c9c9");
        flowerList.add(flower);
        session.save(flower);
    }

    @FXML
    void priceTB() {
        sendRequestButton();
    }

    private void setChosenItem(Flower flower) {
        chosenItemName.setText(flower.getName());
        chosenItemPrice.setText("Price: " + App.CURRENCY + flower.getPrice());
        chosenItemDetails.setText(flower.getDetails());
        chosenItemSize.setText(flower.getSize());
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(flower.getImgSource())));
        chosenItemImage.setImage(image);
        chosenItem.setStyle("-fx-background-color: #" + flower.getColor() + ";\n" +
                "    -fx-background-radius: 30;");
    }

    @FXML
    void sendRequestButton() {
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
    void textChanged() {
        pressSearch();
    }

    @FXML
    void pressSearch() {
        gridPane.getChildren().clear();
        String text = searchItemTB.getText();
        if (text.equals("")) {
            flowerList.clear();
            getData();
            loadGridPane();
            return;
        }
        flowerList.clear();
        getData();
        myListener = this::setChosenItem;
        int column = 0;
        int row = 1;
        for (Flower flower : flowerList) {
            try {
                if (flower.getName().toUpperCase().contains(text.toUpperCase())) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/org/example/Item.fxml"));

                    AnchorPane anchorPane = fxmlLoader.load();

                    ItemController itemController = fxmlLoader.getController();
                    itemController.setData(flower, myListener);

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
        myListener = this::setChosenItem;
        int column = 0;
        int row = 1;


        for (Flower flower : flowerList) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/org/example/Item.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(flower, myListener);

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
