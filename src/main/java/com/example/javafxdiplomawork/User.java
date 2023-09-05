package com.example.javafxdiplomawork;

public class User {
    private static String id;
    private static String name;
    private static String surname;
    private static String login;
    private static String password;
    private static String country;
    private static String phone;
    private static String gender;

    public User() {
    }

    public User(String name, String surname, String login, String password, String country, String phone, String gender) {

        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.country = country;
        this.phone = phone;
        this.gender = gender;
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        User.id = id;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        User.name = name;
    }

    public static String getSurname() {
        return surname;
    }

    public static void setSurname(String surname) {
        User.surname = surname;
    }

    public static String getLogin() {
        return login;
    }

    public static void setLogin(String login) {
        User.login = login;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        User.password = password;
    }

    public static String getCountry() {
        return country;
    }

    public static void setCountry(String country) {
        User.country = country;
    }

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        User.phone = phone;
    }

    public static String getGender() {
        return gender;
    }

    public static void setGender(String gender) {
        User.gender = gender;
    }
}
