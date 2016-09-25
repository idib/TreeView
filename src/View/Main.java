package View;

import RBTree.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Main extends Application {



    private Tree<Integer,Integer> T;


    private void refreshTree(Node<Integer,Integer> t)
    {

        TreePanel.getChildren().addAll(t.getShape());
        if (t.TryLeft())
            refreshTree(t.getLeft());
        if (t.TryRight())
            refreshTree(t.getRight());
    }

    private void refreshTree(){
        T.refreshXY();
        TreePanel.getChildren().clear();
        refreshTree(T.getRoot());
    }

    private TextField TextKey;
    private Group TreePanel;
    private Group StagePanel;
    private Scene scene;
    private Button btn;

    @Override
    public void start(Stage primaryStage) throws Exception{
        T = new Tree<>();
        T.setSX(15);
        T.setSY(15);

        StagePanel = new Group();

        TreePanel = new Group();
        TreePanel.setLayoutX(0);
        TreePanel.setLayoutY(60);

        TextKey = new TextField();
        TextKey.setMaxWidth(150);

        btn = new Button();
        btn.setLayoutX(150);
        btn.setText("Добавить");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                T.Insert(Integer.parseInt(TextKey.getText()),Integer.parseInt(TextKey.getText()));
                refreshTree();
            }
        });

        StagePanel.getChildren().add(TextKey);
        StagePanel.getChildren().add(btn);
        StagePanel.getChildren().add(TreePanel);
        scene = new Scene(StagePanel, 400, 250);
        primaryStage.setTitle("RBTree");
        primaryStage.setScene(scene);
        primaryStage.show();

    }



    public static void main(String[] args) {
        launch(args);
    }
}
