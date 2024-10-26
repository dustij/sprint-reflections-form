package org.dustijohnson;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * JavaFX App
 */
public class App extends Application
{
    private static final Logger logger = Logger.getLogger(App.class.getName());

    @Override
    public void start(Stage primaryStage)
    {
        setupLogger();

        primaryStage.setTitle("Sprint Feedback Form");

        // Create labels and input fields
        Label projectNameLabel = new Label("Project Name:");
        projectNameLabel.setWrapText(true);
        TextField projectNameField = new TextField();

        Label sprintNumberLabel = new Label("Sprint Number:");
        sprintNumberLabel.setWrapText(true);
        TextField sprintNumberField = new TextField();

        Label wentWellLabel = new Label("What went well?");
        wentWellLabel.setWrapText(true);
        TextArea wentWellArea = new TextArea();
        wentWellArea.setWrapText(true);

        Label challengesLabel = new Label("What challenges did you face?");
        challengesLabel.setWrapText(true);
        TextArea challengesArea = new TextArea();
        challengesArea.setWrapText(true);

        Label improvementsLabel = new Label("What can be improved for the next sprint?");
        improvementsLabel.setWrapText(true);
        TextArea improvementsArea = new TextArea();
        improvementsArea.setWrapText(true);

        // Create save button
        Button saveButton = new Button("Save Feedback");
        saveButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Destination to Save File");
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "/Dropbox/Portfolio Projects/Documentation/Sprint Reflections"));

            String date = LocalDate.now().toString();
            String projectName = projectNameField.getText();
            String sprintNumber = sprintNumberField.getText();
            int sprintNumberInt = 0;
            try {
                sprintNumberInt = Integer.parseInt(sprintNumber);
            } catch (NumberFormatException ex) {
                logger.log(Level.WARNING, "Could not parse sprint number to int");
            }
            String fileName = String.format("%s_sprint_%02d_%s.txt", projectName, sprintNumberInt, date);
            fileChooser.setInitialFileName(fileName);

            // Show save dialog
            File file = fileChooser.showSaveDialog(primaryStage);
            if (file != null) {
                saveFeedback(
                        file,
                        fileName,
                        projectName,
                        sprintNumber,
                        wentWellArea.getText(),
                        challengesArea.getText(),
                        improvementsArea.getText());
                primaryStage.close();
            }
        });

        // Arrange components in a grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(10);
        grid.setHgap(10);

        grid.add(projectNameLabel, 0, 0);
        grid.add(projectNameField, 1, 0);
        grid.add(sprintNumberLabel, 0, 1);
        grid.add(sprintNumberField, 1, 1);
        grid.add(wentWellLabel, 0, 2);
        grid.add(wentWellArea, 1, 2);
        grid.add(challengesLabel, 0, 3);
        grid.add(challengesArea, 1, 3);
        grid.add(improvementsLabel, 0, 4);
        grid.add(improvementsArea, 1, 4);
        grid.add(saveButton, 1, 5);

        // Set scene and show stage
        Scene scene = new Scene(grid, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Set tab traversal policy
        scene.getRoot().setFocusTraversable(true);
        wentWellArea.addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == javafx.scene.input.KeyCode.TAB) {
                event.consume();
                if (event.isShiftDown()) {
                    sprintNumberField.requestFocus();
                } else {
                    challengesArea.requestFocus();
                }
            }
        });
        challengesArea.addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == javafx.scene.input.KeyCode.TAB) {
                event.consume();
                if (event.isShiftDown()) {
                    wentWellArea.requestFocus();
                } else {
                    improvementsArea.requestFocus();
                }
            }
        });
        improvementsArea.addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == javafx.scene.input.KeyCode.TAB) {
                event.consume();
                if (event.isShiftDown()) {
                    challengesArea.requestFocus();
                } else {
                    saveButton.requestFocus();
                }
            }
        });
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    private static void saveFeedback(File file, String fileName, String projectName, String sprintNumber, String wentWell, String challenges, String improvements)
    {
        String date = LocalDate.now().toString();
        File saveFile = new File(file.getParentFile(), fileName);

        try (FileWriter writer = new FileWriter(saveFile)) {
            writer.write("Project Name: " + projectName + "\n");
            writer.write("Sprint Number: " + sprintNumber + "\n");
            writer.write("Date: " + date + "\n\n");
            writer.write("What went well?\n" + wentWell + "\n\n");
            writer.write("What challenges did you face?\n" + challenges + "\n\n");
            writer.write("What can be improved for the next sprint?\n" + improvements + "\n");
        } catch (IOException e) {
            logger.log(Level.WARNING, "Could not save file ", e);
        }
    }

    private static void setupLogger()
    {
        try (InputStream configFile = App.class.getResourceAsStream("logging")) {
            if (configFile != null) {
                LogManager.getLogManager().readConfiguration(configFile);
            } else {
                logger.log(Level.WARNING, "Could not load logging configuration");
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error setting up logger", e);
            throw new RuntimeException(e);
        }
    }

}