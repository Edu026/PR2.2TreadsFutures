package com.project;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.application.Platform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;

public class Controller0 {

    @FXML
    private Button button0, button1, button2, button3;
    @FXML
    private AnchorPane container;
    @FXML
    private Label percentatge0, percentatge1, percentatge2;
    @FXML
    private ProgressBar progressBar0, progressBar1, progressBar2;

    private boolean isTaskRunning0 = false , 
                    isTaskRunning1 = false, 
                    isTaskRunning2 = false;
    Random rand = new Random();
    
                    

    
    private ExecutorService executor = Executors.newFixedThreadPool(3); // Creem una pool de dos fils

    @FXML
    private void animateToView1(ActionEvent event) {
        UtilsViews.setViewAnimating("View1");
    }

    @FXML
    private void runTask() {
    
        if (isTaskRunning0) {
            isTaskRunning0 = false;
            button1.setText("Iniciar");
            return;
        }
        isTaskRunning0 = true;
    
        backgroundTask(0);
    }

    @FXML
    private void runTask1() {
        if (isTaskRunning1) {
            isTaskRunning1 = false;
            button2.setText("Iniciar");
            return;
        }
        isTaskRunning1 = true;

        backgroundTask(1);
    }

    @FXML
    private void runTask2() {
        if (isTaskRunning2) {
            isTaskRunning2 = false;
            button3.setText("Iniciar");
            return;
        }
        isTaskRunning2 = true;

        backgroundTask(2);
    }

    private void backgroundTask(int index) {
        // Executar la tasca
        executor.submit(() -> {        
            try {
                
                for (int i = 0; i <= 100; i++) {
                    final int currentValue = i;
                    if (isTaskRunning0){
                        if (index == 0) {
                            isTaskRunning0 = true;

                            if (currentValue == 100){
                                isTaskRunning0 = false;
                            }
                            // Actualitzar el Label en el fil d'aplicació de l'UI
                            Platform.runLater(() -> {
                                button1.setText("Aturar");
                                percentatge0.setText(String.valueOf(currentValue) + "%");
                                progressBar0.setProgress((double)currentValue/100);     
                            });
                            Thread.sleep(1000);
                            


                        }
                    }
                    if (isTaskRunning1) {
                        if (index == 1) {  

                            isTaskRunning1 = true;
                            if (currentValue == 100){
                                isTaskRunning1 = false;
                            }
                            // Actualitzar el Label en el fil d'aplicació de l'UI
                            Platform.runLater(() -> {
                                button2.setText("Aturar");
                                Double value1 = (double)currentValue + rand.nextInt(2,4);
                                if (value1 > 100){
                                    percentatge1.setText(String.valueOf(100) + "%");
                                    progressBar1.setProgress(1);
                                }
                                else {
                                    percentatge1.setText(String.valueOf(value1) + "%");
                                    progressBar1.setProgress(value1 / 100);
                                }
                                
                            });
                            Thread.sleep(rand.nextInt(3000, 5000));
                        } 
                    }
                    if (isTaskRunning2){
                        if (index == 2) {

                            isTaskRunning2 = true;
                            if (currentValue == 100){
                                isTaskRunning2 = false;
                            }
                            // Actualitzar el Label en el fil d'aplicació de l'UI
                            Platform.runLater(() -> {
                                button3.setText("Aturar");
                                Double value2 = (double)currentValue + rand.nextInt(4,6);
                                if (value2 > 100){
                                    percentatge2.setText(String.valueOf(100) + "%");
                                    progressBar2.setProgress(1);
                                }else{
                                    percentatge2.setText(String.valueOf(value2) + "%");
                                    progressBar2.setProgress(value2 / 100);
                                }
                            });
                            Thread.sleep(rand.nextInt(3000,8000));
                        } 
                    }

                    System.out.println("Updating label: " + index + ", Value: " + currentValue);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
    
    // Aquesta funció la cridaries quan vulguis tancar l'executor (per exemple, quan tanquis la teva aplicació)
    public void stopExecutor() {
        executor.shutdown();
    }

}