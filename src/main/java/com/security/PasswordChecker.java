package com.security;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class PasswordChecker extends Application {

    public static String checkStrength(String password){

        int score = 0;

        if(password.length() >= 8) score++;
        if(password.matches(".*[A-Z].*")) score++;
        if(password.matches(".*[a-z].*")) score++;
        if(password.matches(".*[0-9].*")) score++;
        if(password.matches(".*[@#$%^&*!].*")) score++;

        if(score <= 2) return "Weak";
        else if(score <= 4) return "Medium";
        else return "Strong";
    }

    public static String generatePassword(){

        String chars="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%^&*!";
        Random r=new Random();
        StringBuilder pass=new StringBuilder();

        for(int i=0;i<12;i++)
            pass.append(chars.charAt(r.nextInt(chars.length())));

        return pass.toString();
    }

    @Override
    public void start(Stage stage){

        Label title=new Label("Password Strength Analyzer");

        PasswordField input=new PasswordField();
        input.setPromptText("Enter Password");

        Label result=new Label();

        Button checkBtn=new Button("Check Strength");

        checkBtn.setOnAction(e->{
            result.setText("Strength: "+checkStrength(input.getText()));
        });

        Button generateBtn=new Button("Generate Strong Password");

        generateBtn.setOnAction(e->{
            input.setText(generatePassword());
        });

        VBox root=new VBox(15,title,input,checkBtn,generateBtn,result);
        root.setStyle("-fx-padding:20; -fx-alignment:center; -fx-background-color:#1e1e1e;");

        Scene scene=new Scene(root,400,300);

        stage.setTitle("Cyber Password Tool");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch();
    }
}
