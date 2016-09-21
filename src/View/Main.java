package View;

import RBTree.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

public class Main extends Application {



    private Tree<Integer,Integer> T;





    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();

        Ellipse ellipse = new Ellipse();
        ellipse.setCenterX(180.0f);
        ellipse.setCenterY(150.0f);

        // Radius X
        ellipse.setRadiusX(120.0f);

        // Radius Y
        ellipse.setRadiusY(80.0f);

        // Fill color.
        ellipse.setFill(Color.CORNFLOWERBLUE);

        // Rotate 30 degrees
        ellipse.setRotate(30);

        root.getChildren().add(ellipse);

        Scene scene = new Scene(root, 400, 250);

        primaryStage.setTitle("JavaFX Ellipse (o7planning.org)");

        primaryStage.setScene(scene);
        primaryStage.show();

    }



    public static void main(String[] args) {
        launch(args);
    }
}
