package org.example.mplayer.controller;

public class Help {

    public static String help(){
        String txt = "[Help]\n\n";
        txt+="[ when you are not logged in ]\n\n"+
        "<-->Signup-L|S|P-[username]-[password]-[name]-[email]-[phone number]"+
        "-[birth date]-[bio ]\n"+
        "(the date shoud be in dd/mm/yyyy format like 1/1/2000)\n"+
        "(L for Listener, S for Singer, P for Podcaster)\n\n"+
        "<-->FavouriteGenres-[favourite genres separated with comma(,)]\n"+
        "(make sure you set your favoriteGenres after your Sign up, at most four genre)\n\n"+
        "<-->Login -[username] -[password]\n\n"+
        "<-->Logout\n\n\n"+
        "[when you are logged in]\n\n"+
        "<-->AccountInfo\n\n\n"+
        "[when you are logged in as Listener]\n\n"+
        "<-->GetSuggestions-n\n"+
        "(number of Audios you want to get suggestion)\n\n"+
        "<-->Artists\n\n"+
        "<-->Artist-[username]\n\n"+
        "<-->Follow-[username]\n\n"+
        "<-->Search-[artist name OR audio’s title]\n\n"+
        "<-->Sort-L|P \n"+
        "(L:based on Likes / P:based on Plays)\n\n"+
        "<-->Filter-A|G|D-[filter by] \n"+
        "(Filter by Artist username, Genre or releaseDate)\n\n"+
        "<-->Filter-D-[first date]-[second date] \n"+
        "(Filter between first date & second date)\n\n"+
        "<-->Add-[playlist’s name] -[audio’s ID]\n\n"+
        "<-->ShowPlaylists\n\n"+
        "<-->SelectPlaylist-[playlist’s name]\n\n"+
        "<-->Play-[audio’s ID]\n\n"+
        "<-->Like-[audio’s ID]\n\n"+
        "<-->Lyric-[audio’s ID]\n\n"+
        "<-->NewPlaylist -[playlist’s name]\n\n"+
        "<-->Followings\n\n"+
        "<-->Report-[artist’s username]-[explanation]\n\n"+
        "<-->IncreaseCredit-[value]\n\n"+
        "<-->GetPremium-[package]\n"+
        "(30 days(5$) enter package=30\n"+
        "60 days(9$) enter package=60\n"+
        "180 days(14$)enter package=180)\n\n"+
        "<-->Logout\n\n\n"+
        "[when you are logged in as Artist]\n\n"+
        "<-->Followers\n\n"+
        "<-->ViewsStatistics\n\n"+
        "<-->CalculateEarnings\n\n"+
        "<-->NewAlbum-[album name]\n\n"+
        "<-->Publish-M|P-[title]-[genre]-[lyric|caption]-[link]-[cover]-[album ID]\n"+
        "(M for Music / P for Podcast/albumId if you are publishing a Music)\n\n"+
        "<-->Logout\n\n\n"+
        "[when you are logged in as Admin]\n"+
        "<-->Statistics\n\n"+
        "<-->Audios-[page number]\n\n"+
        "<-->Audio-[audio’s ID]\n\n"+
        "<-->Artists\n\n"+
        "<-->Artist-[username]\n\n"+
        "<-->Reports\n\n"+
        "<-->Logout\n\n";
        return txt;
    

    }
}
