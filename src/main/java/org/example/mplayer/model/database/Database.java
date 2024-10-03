package org.example.mplayer.model.database;

import java.util.ArrayList;

import org.example.mplayer.model.audio.Audio;
import org.example.mplayer.model.user.Report;
import org.example.mplayer.model.user.User;

public class Database {
    private ArrayList<User> allUsers;
    private ArrayList<Audio> allAudios;
    private ArrayList<Report> allReports;

    private static Database database;

    private Database() {
        allAudios = new ArrayList<>();
        allReports = new ArrayList<>();
        allUsers = new ArrayList<>();
    }

    public static Database getDatabase() {//semi singleton
        if (database == null)
            database = new Database();
        return database;
    }

    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public ArrayList<Audio> getAllAudio() {
        return allAudios;
    }

    public ArrayList<Report> getAllReports() {
        return allReports;
    }

    public void addToAllUsers(User user) {
        this.allUsers.add(user);
    }

    public void addToAllAudio(Audio audio) {
        this.allAudios.add(audio);
    }

    public void addToAllReports(Report report) {
        this.allReports.add(report);
    }
    public void setAllUsers(ArrayList<User> allUsers) {
        this.allUsers = allUsers;
    }

    public void setAllAudio(ArrayList<Audio> allAudio) {
        this.allAudios = allAudio;
    }

    public void setAllReports(ArrayList<Report> allReports) {
        this.allReports = allReports;
    }

    
}
