package com.template;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
//Herda do JavaFX
public class Main extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("main.fxml"));
        Scene scene = new Scene(loader.load(),600,400);

        stage.setTitle("Cadastre seu Jogador");
        stage.setScene(scene);
        stage.show();
    }
    //Executa
    public static void main(String[] args)
    {
        launch();
    }
}