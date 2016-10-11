package View;

import RBTree.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    private TextField TextKey;
    private Group TreePanel;
    private Group StagePanel;
    private Scene scene;
    private Tree<Integer, Integer> T;
    private double SY = 15;
    private double SX = 15;


    private void refreshTree(Node<Integer, Integer> t) {

        TreePanel.getChildren().addAll(t.getShape());
        if (t.TryLeft())
            refreshTree(t.getLeft());
        if (t.TryRight())
            refreshTree(t.getRight());
    }

    private void refreshTree() {
        T.refreshXY();
        TreePanel.getChildren().clear();
        if (T.getRoot() != null)
            refreshTree(T.getRoot());
    }

    private void TNew() {
        System.out.println("New Tree");
        T = new Tree<>();
        T.setSX(SX);
        T.setSY(SY);
        refreshTree();
    }

    private void InsertElem() {
        System.out.println("+ " + TextKey.getText());
        T.Insert(Integer.parseInt(TextKey.getText()), Integer.parseInt(TextKey.getText()));
        refreshTree();
        TextKey.setText("");
    }


    private void DelElem() {
        System.out.println("- " + TextKey.getText());
        ArrayList<Integer> s = T.getRoot().findPath(Integer.parseInt(TextKey.getText()));
        for (Integer integer : s) {
            T.Del(integer);
        }
        T.Del(Integer.parseInt(TextKey.getText()));
        refreshTree();
        TextKey.setText("");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        StagePanel = new Group();

        TreePanel = new Group();
        TreePanel.setLayoutX(0);
        TreePanel.setLayoutY(60);

        TextKey = new TextField();
        TextKey.setMaxWidth(150);
        TextKey.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    InsertElem();
                } else if (event.getCode().equals(KeyCode.DELETE)) {
                    DelElem();
                }
            }
        });

        Button btnInsert = new Button();
        btnInsert.setLayoutX(150);
        btnInsert.setText("Добавить");
        btnInsert.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                InsertElem();
            }
        });
        Button btnDelete = new Button();
        btnDelete.setLayoutX(300);
        btnDelete.setText("Удалить");
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                DelElem();
            }
        });

        Button btnNew = new Button();
        btnNew.setLayoutX(450);
        btnNew.setText("Новое дерево");
        btnNew.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                TNew();
            }
        });


        TNew();


        StagePanel.getChildren().add(TextKey);
        StagePanel.getChildren().add(btnInsert);
        StagePanel.getChildren().add(btnDelete);
        StagePanel.getChildren().add(btnNew);
        StagePanel.getChildren().add(TreePanel);
        scene = new Scene(StagePanel, 600, 300);
        primaryStage.setTitle("RBTree");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
