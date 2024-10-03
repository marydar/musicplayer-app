package org.example.mplayer.model.user;

import java.util.Date;

public class FreeListener extends Listener {
    private static final int playListLimit=3;
    private static final int addSongToPLaylistLimit=10;
    public FreeListener(String password,String username, String name, String emailAddress, String phoneNumber,
            Date birthDate, double accountCredit) {
        super(password,username, name, emailAddress, phoneNumber, birthDate, accountCredit);
    }
    public static int getPlayListLimit() {
        return playListLimit;
    }
    public static int getAddSongToPLaylistLimit() {
        return addSongToPLaylistLimit;
    }
       
}
