package com.example.javanotepad;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HelloApplication extends Application {
    private TextArea textArea = new TextArea();
    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = new BorderPane();
        root.setTop(createMenuBar());

        textArea.setWrapText(true);
        root.setCenter(textArea);

        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Notepad");
        stage.setScene(scene);
        stage.show();
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");
        MenuItem newItem = new MenuItem("New");
        newItem.setOnAction(e -> textArea.clear());

        MenuItem openItem = new MenuItem("Open");
        openItem.setOnAction(e -> openFile());

        MenuItem saveItem = new MenuItem("Save");
        saveItem.setOnAction(e -> saveFile());

        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(e -> System.exit(0));

        fileMenu.getItems().addAll(newItem, openItem, saveItem, exitItem);
        menuBar.getMenus().add(fileMenu);

        return menuBar;
    }

    private void openFile() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try {
                byte[] fileContent = Files.readAllBytes(file.toPath());
                textArea.setText(new String(fileContent));
            } catch (IOException ex) {
                ex.printStackTrace(); // Handle the exception properly
            }
        }
    }

    private void saveFile() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            try {
                Files.write(Paths.get(file.toURI()), textArea.getText().getBytes());
            } catch (IOException ex) {
                ex.printStackTrace(); // Handle the exception properly
            }
        }
    }



    public static void main(String[] args) {

        launch();
    }
}