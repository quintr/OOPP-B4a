package GUI;

import database.Question;
import database.TextAnswer;
import database.TextQuestion;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Admin extends UIScene implements Initializable {

    @FXML
    private ImageView logoImage1, logoImage2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // set image
        Image image = new Image("file:src/images/logo.png");
        logoImage1.setImage(image);
        logoImage2.setImage(image);
    }

    @FXML
    protected void handleMC(ActionEvent e) {
        UI.goToScene("adminAddMC");
    }

    @FXML
    protected void handleIQ(ActionEvent e) {
        UI.goToScene("adminAddImage");
    }

    @FXML
    protected void handleGUI(ActionEvent e) {UI.goToScene("adminAddGUI");}

    @FXML
    protected void handleReturn(ActionEvent e) {
        UI.goToScene("welcome");
    }

    private static Text title;
    private static GridPane centergrid;
    private static Label MCtext;
    private static Button MCbut;
    private static Text subtitle;
    private static Text info;
    private static Button selectbut;
    private static Label selecttext;

/*
    @Override
    public void start(Stage primaryStage) {
        //Set panes
        StackPane rootpane = new StackPane();
        GridPane titlegrid = new GridPane();
        centergrid = new GridPane();
        rootpane.getChildren().addAll(titlegrid, centergrid);

        //Set settings for titlegrid
        primaryStage.setTitle("Stichting Lezen en Schrijven - Admin");
        titlegrid.setAlignment(Pos.TOP_LEFT);
        titlegrid.setHgap(30);
        titlegrid.setVgap(10);
        titlegrid.setPadding(new Insets(25, 25, 25, 25));

        //Add logo
        Image logo = new Image("file:src/images/logo.png");
        ImageView logov = new ImageView();
        logov.setImage(logo);
        logov.setFitWidth(100);
        logov.setPreserveRatio(true);
        logov.setSmooth(true);
        logov.setCache(true);
        titlegrid.add(logov, 0, 0);

        //Add title
        title = new Text("Admin Program");
        title.setId("Title");
        titlegrid.add(title, 1, 0);

        //Set centergrid settings
        centergrid.setAlignment(Pos.CENTER_LEFT);
        centergrid.setHgap(30);
        centergrid.setVgap(10);
        centergrid.setPadding(new Insets(25, 25, 25, 25));

        //Add subtitle and info text
        subtitle = new Text("Here you can add new Questions for the practice program.");
        subtitle.setId("subtitle");
        info = new Text("Please click on the type of question you want to add.");
        info.setId("info");

        //Define scene here so that we can use it in the button action
        Scene scene = new Scene(rootpane, 1920, 1080);

        //Set the button to go to the addMCquestion interface
        MCbut = new Button("1");
        MCbut.setOnAction(e -> {
            title.setText("Adding MC Question");
            AddMCQuestion.AddQuestion(centergrid);
        });
        MCtext = new Label("Multiple Choiche Question");

        //Set the button to go to adding select question
        selectbut = new Button("2");
        selectbut.setOnAction(e -> {
            title.setText("Adding Clickable Image Question");
            AddSelectQuestion.AddQuestion(primaryStage, centergrid);
        });
        selecttext = new Label("Clickable Image Question");

        //Set the button to go to adding gui question
        guibut = new Button("3");
        guibut.setOnAction(e -> {
            title.setText("Adding GUI Question");
            AddGUIQuestion.AddQuestion(centergrid);
        });
        guitext = new Label("GUI Question");

        //Set CSS and show the program
        scene.getStylesheets().add("file:src/stylesheets/admin.css");
        display();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void display() {
        //Empty the centergrid
        centergrid.getChildren().clear();
        //Add Texts and Buttons
        centergrid.add(subtitle, 1, 0);
        centergrid.add(info, 1, 1);
        //Add the buttons and texts
        centergrid.add(MCbut, 0, 2);
        centergrid.add(MCtext, 1, 2);
        centergrid.add(selectbut, 0, 3);
        centergrid.add(selecttext, 1, 3);
        centergrid.add(guibut, 0, 4);
        centergrid.add(guitext, 1, 4);
        //Change back alignment
        centergrid.setAlignment(Pos.CENTER_LEFT);
    }
    */
}
