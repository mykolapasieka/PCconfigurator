package com.example.javafxdiplomawork;

import com.example.javafxdiplomawork.database.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterController {

    @FXML
    private TextField countryField;

    @FXML
    private RadioButton femaleRadioButton;

    @FXML
    private TextField loginField;

    @FXML
    private RadioButton maleRadioButton;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField phoneField;

    @FXML
    private Button registerButton;

    @FXML
    private TextField repeatPasswordField;

    @FXML
    private TextField surnameField;

    @FXML
    private Label labelError;
    @FXML
    private Button returnButton;
    @FXML
    private Button cancelButton;

    @FXML
    void initialize() {

        setFocus();

        returnButton.setOnAction(event -> {
            hideWindow();
        });

        cancelButton.setOnAction(event -> {
            cancelButton.getScene().getWindow().hide();
        });

        registerButton.setOnAction(event -> {
            try {
                signUpNewUser();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void setFocus() {
        loginField.setFocusTraversable(false);
        surnameField.setFocusTraversable(false);
        phoneField.setFocusTraversable(false);
        countryField.setFocusTraversable(false);
        repeatPasswordField.setFocusTraversable(false);
        passwordField.setFocusTraversable(false);
        nameField.setFocusTraversable(false);
    }

    private void hideWindow() {
        returnButton.getScene().getWindow().hide();
        HelloApplication helloApplication = new HelloApplication();
        Stage stage = new Stage();
        try {
            helloApplication.start(stage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void signUpNewUser() throws SQLException, ClassNotFoundException, InterruptedException {

        DatabaseHandler dbHandler = new DatabaseHandler();
        String name = nameField.getText();
        String surname = surnameField.getText();
        String login = loginField.getText();
        String password = passwordField.getText();
        String country = countryField.getText();
        String phone = phoneField.getText();


        if (checkEmptyField()) {
            labelError.setVisible(false);
            if (repeatPasswordField.getText().equals(passwordField.getText())) {
                if (!dbHandler.RegistrationErrorLogin(loginField.getText())) {
                    if (!dbHandler.RegistrationErrorPhone(phoneField.getText())) {
                        String gender_value = gender();
                        User user = new User(name, surname, login, password, country, phone, gender_value);
                        labelError.setVisible(false);
                        dbHandler.registerUser(user);
                        labelError.setStyle("-fx-background-color :  #4d4d4d");
                        labelError.setTextFill(Color.WHITESMOKE);
                        labelError.setText("Register success!");
                        labelError.setVisible(true);
                            hideWindow();

                    } else {
                        phoneField.setStyle("-fx-border-color : red;-fx-background-color :  #4d4d4d");
                        labelError.setText("Phone number is already taken");
                        labelError.setVisible(true);
                    }
                } else {
                    loginField.setStyle("-fx-border-color : red;-fx-background-color :  #4d4d4d");
                    labelError.setText("Username is already taken");
                    labelError.setVisible(true);
                }
            } else {
                passwordField.setStyle("-fx-border-color : red;-fx-background-color :  #4d4d4d");
                repeatPasswordField.setStyle("-fx-border-color : red;-fx-background-color :  #4d4d4d");
                labelError.setText("Password is not equal");
                labelError.setVisible(true);
            }
        }
    }

    @FXML
    private String gender() {
        maleRadioButton.setStyle("-fx-border-color : none;");
        femaleRadioButton.setStyle("-fx-border-color : none;");
        String gender_value = "not selected";
        int k=0;
        if (maleRadioButton.isSelected()) {
            gender_value = "male";
        } else if (femaleRadioButton.isSelected()) {
            gender_value = "female";
        } else {
            maleRadioButton.setStyle("-fx-border-color : red");
            femaleRadioButton.setStyle("-fx-border-color : red");
            k++;
        }
        return gender_value;
    }

    private boolean checkEmptyField() {
        int k = 0;
        if (nameField.getText().equals("")) {
            k++;
            nameField.setStyle("-fx-border-color : red; -fx-background-color :   #4d4d4d");
        } else {
            nameField.setStyle("-fx-border-color : #c5c5c5; -fx-background-color :  #4d4d4d;");
        }
        if (surnameField.getText().equals("")) {
            k++;
            surnameField.setStyle("-fx-border-color : red; -fx-background-color :  #4d4d4d;");
        } else {
            surnameField.setStyle("-fx-border-color : #c5c5c5; -fx-background-color :  #4d4d4d;");
        }
        if (loginField.getText().equals("")) {
            k++;
            loginField.setStyle("-fx-border-color : red; -fx-background-color :  #4d4d4d;");
        } else {
            loginField.setStyle("-fx-border-color : #c5c5c5; -fx-background-color :  #4d4d4d;");
        }
        if (passwordField.getText().equals("")) {
            k++;
            passwordField.setStyle("-fx-border-color : red; -fx-background-color :  #4d4d4d;");
        } else {
            passwordField.setStyle("-fx-border-color : #c5c5c5; -fx-background-color :  #4d4d4d;");
        }
        if (countryField.getText().equals("")) {
            k++;
            countryField.setStyle("-fx-border-color : red; -fx-background-color :  #4d4d4d;");
        } else {
            countryField.setStyle("-fx-border-color : #c5c5c5; -fx-background-color :  #4d4d4d;");
        }
        if (phoneField.getText().equals("")) {
            k++;
            phoneField.setStyle("-fx-border-color : red; -fx-background-color :  #4d4d4d;");
        } else {
            phoneField.setStyle("-fx-border-color : #c5c5c5; -fx-background-color :  #4d4d4d;");
        }
        if (repeatPasswordField.getText().equals("")) {
            k++;
            repeatPasswordField.setStyle("-fx-border-color : red; -fx-background-color :  #4d4d4d;");
        } else {
            repeatPasswordField.setStyle("-fx-border-color : #c5c5c5; -fx-background-color :  #4d4d4d;");
        }

        String genderValue = gender();
        if(genderValue=="not selected")
            k++;
        if (k == 0) {
            return true;
        } else {
            return false;
        }
    }
}
