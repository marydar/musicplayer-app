package org.example.mplayer.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.example.mplayer.exceptions.InvalidFormatException;
import org.example.mplayer.exceptions.UserNotFoundException;
import org.example.mplayer.exceptions.WrongPaswordException;
import org.example.mplayer.model.database.Database;
import org.example.mplayer.model.user.*;

public class UserController {
    private static UserController userController;

    private UserController() {

    }

    public static UserController getUserController() {
        if (userController == null) {
            userController = new UserController();
        }
        return userController;
    }

    public boolean emailPassRegex(String email) {
        Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

//    public boolean DatePassRegex(String birthDate) {
//        Pattern pattern = Pattern.compile("(^[1-9]|[12][0-9]|3[01])/([1-9]|1[0-2])/(19[0-9]{2}|2[0-9]{3}$)");
//        Matcher matcher = pattern.matcher(birthDate);
//        return matcher.matches();
//    }

    //comment
    public boolean phoneNumPassRegex(String phoneNumber) {
        Pattern pattern = Pattern.compile("^09[0-9]{9}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public boolean passwordPassRegex(String password) {
        Pattern pattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public boolean usernameIsUnic(String username) {
        for (User user : Database.getDatabase().getAllUsers()) {
            if (user.getUsername().equals(username))
                return false;
        }
        return true;
    }

    public String signupNewUser(String type, String username, String password, String name, String emailAddress,
            String phoneNumber,
            String birthDate, String bio) throws ParseException, InvalidFormatException {
        if (!(emailPassRegex(emailAddress))) {
            throw new InvalidFormatException("Please enter a valid email address");
        }
        if (!(phoneNumPassRegex(phoneNumber))) {
            throw new InvalidFormatException("Please enter a valid phone Number");
        }
        if (!(passwordPassRegex(password))) {
            throw new InvalidFormatException("Not a strong password!!\nYour password must be at least 8 characters long, \ncontain at least one number and\nhave a mixture of uppercase and lowercase letters.");
        }
        if (!(usernameIsUnic(username))) {
            throw new InvalidFormatException("This username is alredy taken ! Please enter another one");
        }
//        if (!(DatePassRegex(birthDate))) {
//            return "This Date is not valid !please enter a valid one";
//        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy", Locale.ENGLISH);
        Date dateOfbirth = formatter.parse(birthDate);
        System.out.println("Date of birth: " + dateOfbirth);
        switch (type) {
            case "L":
                FreeListener listener = new FreeListener(password, username, name, emailAddress, phoneNumber,
                        dateOfbirth, 50);
                Database.getDatabase().addToAllUsers(listener);
                ListenerController.getListenerController().setListener(listener);

                break;

            case "S":
                Singer singer = new Singer(password, username, name, emailAddress, phoneNumber, dateOfbirth, bio);
                Database.getDatabase().addToAllUsers(singer);
                break;

            case "P":
                Podcaster podcaster = new Podcaster(password, username, name, emailAddress, phoneNumber, dateOfbirth,
                        bio);
                Database.getDatabase().addToAllUsers(podcaster);
                break;

            default:
                break;
        }
        return "Thanks for signing up. Your account has been created";
    }

    public User findUser(String username, String password) throws WrongPaswordException, UserNotFoundException {
        for (User user : Database.getDatabase().getAllUsers()) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {
                    return user;
                }
                else{
                    throw new WrongPaswordException("the password is not correct , try again");
                }
            }
        }
        throw  new UserNotFoundException("User not found . not a correct username, try again");
    }

    public String loginUser(String username, String password) throws UserNotFoundException, WrongPaswordException {
        User user = findUser(username, password);
        if (user instanceof Listener) {
            ListenerController.getListenerController().loginListener((Listener) user);
            return "L";
        }
        if (user instanceof Artist) {
            ArtistController.getArtistController().loginArtist((Artist) user);
            return "A";
        }
        if (user instanceof Admin) {
            return "Admin";
        }
        return "null";
    }

    public User findUser(String username) {
        for (User user : Database.getDatabase().getAllUsers()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;

    }
}