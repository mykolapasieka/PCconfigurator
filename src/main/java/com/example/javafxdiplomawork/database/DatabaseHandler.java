package com.example.javafxdiplomawork.database;

import com.example.javafxdiplomawork.User;
import com.example.javafxdiplomawork.configurationPC.ConfigurationPC;

import java.sql.*;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDBConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?serverTimezone=UTC";

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public boolean RegistrationErrorLogin(String login) throws SQLException, ClassNotFoundException {
        boolean loginIsTaken = true;
        ResultSet resSet;
        String select_login = "SELECT * FROM " + Constant.USER_TABLE + " WHERE " + Constant.USER_LOGIN + " =?";
        PreparedStatement preparedStatement = getDBConnection().prepareStatement(select_login);
        preparedStatement.setString(1, login);

        resSet = preparedStatement.executeQuery();
        int counter = 0;
        while (resSet.next()) {
            counter++;
        }
        if (counter >= 1) {
            System.out.println("login not ok");
        } else if (counter == 0) {
            loginIsTaken = false;
            System.out.println("login ok");
        }
        return loginIsTaken;
    }


    public boolean RegistrationErrorPhone(String phone) throws SQLException, ClassNotFoundException {
        boolean phoneIsTaken = true;
        ResultSet resSet;
        String select_phone = "SELECT * FROM " + Constant.USER_TABLE + " WHERE " + Constant.USER_PHONE + " =?";
        PreparedStatement preparedStatement = getDBConnection().prepareStatement(select_phone);
        preparedStatement.setString(1, phone);
        resSet = preparedStatement.executeQuery();
        int counter = 0;
        while (resSet.next()) {
            counter++;
        }
        if (counter >= 1) {
            System.out.println("phone not ok");
        } else if (counter == 0) {
            phoneIsTaken = false;
            System.out.println("phone ok");
        }
        return phoneIsTaken;
    }

    public ResultSet getInfoUser() throws SQLException, ClassNotFoundException {
        Statement st =  getDBConnection().createStatement();
        String select = "SELECT * FROM user WHERE login = '"+ User.getLogin()+"' and password = '"+ User.getPassword()+"'";
        return st.executeQuery(select);
    }

    public ResultSet getOrderList() throws SQLException, ClassNotFoundException {
        Statement st =  getDBConnection().createStatement();
        String select = "SELECT * FROM `order` where id_user = '"+User.getId()+"' ORDER BY id_order DESC limit 3";
        return st.executeQuery(select);
    }

    public void createOrder(String id, ConfigurationPC configurationPC,int totalPrice) throws SQLException, ClassNotFoundException {
        Statement st = getDBConnection().createStatement();
        String insert = "INSERT INTO `order` (id_user, id_cpu, id_gpu, id_motherboard, id_cooling_cpu, id_power_supply, id_case, id_memory, id_ram, total_price) VALUES("+id+", "+configurationPC.getCpu().getId()+", "+configurationPC.getGpu().getId()+","+configurationPC.getMotherboard().getId()+", "+configurationPC.getCooling().getId()+", "+configurationPC.getPowerSupply().getId()+", "+configurationPC.getCasePC().getId()+", "+configurationPC.getMemory().getId()+", "+configurationPC.getRam().getId()+","+totalPrice+")";
        st.executeUpdate(insert);
    }

    public void registerUser(User User) {
        String insert = "INSERT INTO " + Constant.USER_TABLE + " (" + Constant.USER_NAME + "," + Constant.USER_SURNAME + "," +
                Constant.USER_LOGIN + "," + Constant.USER_PASSWORD + "," + Constant.USER_COUNTRY + "," +
                Constant.USER_GENDER + "," + Constant.USER_PHONE + ")" + "VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDBConnection().prepareStatement(insert);
            preparedStatement.setString(1, User.getName());
            preparedStatement.setString(2, User.getSurname());
            preparedStatement.setString(3, User.getLogin());
            preparedStatement.setString(4, User.getPassword());
            preparedStatement.setString(5, User.getCountry());
            preparedStatement.setString(6, User.getGender());
            preparedStatement.setString(7, User.getPhone());

            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getInfo(String request) throws SQLException, ClassNotFoundException {
        Statement st = null;
        st =  getDBConnection().createStatement();
        String select = request;
        ResultSet resSet = st.executeQuery(select);
        return resSet;
    }
}
