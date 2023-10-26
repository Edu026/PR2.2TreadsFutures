package com.project;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Controller1 implements initialize {

    @FXML
    private Button button0, button1;
    @FXML
    private ImageView img;
    @FXML
    private AnchorPane container;
    @FXML
    private Label loading;

    @FXML
    public void initialize() {
        loading.setVisible(false);
    }

    
    private static final String[] IMAGES = {
            "assets/image1.png",
            "assets/image2.png",
            "assets/image3.png",
            "assets/image4.png",
            "assets/image5.png",
            "assets/image6.png",
            "assets/image7.png",
            "assets/image8.png",
            "assets/image9.png",
            "assets/image10.png",
            "assets/image11.png",
            "assets/image12.png",
            "assets/image13.png",
            "assets/image14.png",
            "assets/image15.png",
            "assets/image16.png",
            "assets/image17.png",
            "assets/image18.png",
            "assets/image19.png",
            "assets/image20.png",
            "assets/image21.png",
            "assets/image22.png",
            "assets/image23.png",
            "assets/image24.png"
    };

    @FXML
    private void animateToView0(ActionEvent event) {
        UtilsViews.setViewAnimating("View0");
    }

    @FXML
    private void loadImage() {
        System.out.println("Loading image...");
        loading.setVisible(true);
        img.setImage(null);
        loadImageBackground((image) -> {
            System.out.println("Image loaded");
            img.setImage(image);
            loading.setVisible(false);
        });
    }

    public void loadImageBackground(Consumer<Image> callBack) {
        // Use a thread to avoid blocking the UI
        CompletableFuture<Image> futureImage = CompletableFuture.supplyAsync(() -> {
            try {
                // Wait a second to simulate a long loading time
                Thread.sleep(1000);

                // Load the data from the assets file
                Image image = new Image(getClass().getResource("/assets/image.png").toString());
                return image;

            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        })
        .exceptionally(ex -> {
            ex.printStackTrace();
            return null;
        });

        futureImage.thenAcceptAsync(result -> {
            callBack.accept(result);
        }, Platform::runLater);
    }
}