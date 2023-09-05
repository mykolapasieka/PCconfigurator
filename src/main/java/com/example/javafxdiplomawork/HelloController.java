package com.example.javafxdiplomawork;

import com.example.javafxdiplomawork.database.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloController {

    @FXML
    private ImageView PCImage;

    @FXML
    private Button closeButton;

    @FXML
    private Label labelIsEmpty;

    @FXML
    private Button logInButton;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    private Label userNotFoundLabel;
    @FXML
    void initialize() {
        passwordField.setFocusTraversable(false);
        loginField.setFocusTraversable(false);
        closeButton.setOnAction(event -> closeButton.getScene().getWindow().hide());

        //LOGIN
        logInButton.setOnAction(event -> {
            labelIsEmpty.setVisible(false);
            userNotFoundLabel.setVisible(false);
            String loginText = loginField.getText().trim();
            String passwordText = passwordField.getText().trim();
            if(!loginText.equals("") && !passwordText.equals("")) {
                try {
                    loginUser(loginText,passwordText);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else{
                labelIsEmpty.setVisible(true);
            }
        });


        //OPEN REGISTRATION FORM
       registerButton.setOnAction(actionEvent -> {
           registerButton.getScene().getWindow().hide();
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(getClass().getResource("register.fxml"));
           try {
               loader.load();
           } catch (IOException e) {
               throw new RuntimeException(e);
           }


           Parent root = loader.getRoot();
           Stage stage = new Stage();
           stage.initStyle(StageStyle.UNDECORATED);
           stage.setScene(new Scene(root));
           stage.showAndWait();
       });
    }

    private void loginUser(String loginText, String passwordText) throws SQLException, ClassNotFoundException, IOException {
       DatabaseHandler dbHandler = new DatabaseHandler();
       User.setLogin(loginText);
        User.setPassword(passwordText);
        ResultSet result = dbHandler.getInfoUser();
        int counter =0;
        while(result.next()){
            counter++;
            User.setId(result.getString("id"));
            User.setCountry(result.getString("country"));
            User.setPhone(result.getString("phone"));
            User.setGender(result.getString("gender"));
       }
       if(counter>=1){
           System.out.println("id= "+User.getId()+" country= "+User.getCountry()+" phone= "+User.getPhone()+" gender= "+User.getGender());
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("main.fxml"));
            loader.load();  registerButton.getScene().getWindow().hide();
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.showAndWait();
            System.out.println("Success");
        }else{
            userNotFoundLabel.setVisible(true);
        }
    }

}
