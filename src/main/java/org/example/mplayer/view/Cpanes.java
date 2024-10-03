package org.example.mplayer.view;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class Cpanes {

    private static Cpanes cpanes = new Cpanes();
    public static Cpanes getInstance() {
        return cpanes;
    }

    public AnchorPane getPlaylist() {return playlist;}
    public void setPlaylist(AnchorPane playlist) {this.playlist = playlist;}
    private AnchorPane playlist;
    private PlayListChoose playlistChoose;
    public  void setPlaylistChoose(PlayListChoose pl){this.playlistChoose = pl;}
    public  PlayListChoose getPlaylistChoose(){return playlistChoose;}
    //////////
    public AnchorPane getError() {return Error;}
    public void setError(AnchorPane Error) {this.Error = Error;}
    private AnchorPane Error;
    private Error errorC;
    public  void setErrorC(Error error){this.errorC = error;}
    public  Error getErrorC(){return errorC;}
    //////////
    // ////////
    public AnchorPane getInfo() {return info;}
    public void setInfo(AnchorPane info) {this.info = info;}
    private AnchorPane info;
    private InfoC infoC;
    public  void setInfoC(InfoC infoC){this.infoC = infoC;}
    public  InfoC getInfoC(){return infoC;}
    //////////
    public AnchorPane getCreateNewPl() {return createPlaylist;}
    public void setCreatePlaylist(AnchorPane playlist) {this.createPlaylist = playlist;}
    private AnchorPane createPlaylist;
    private NamePlaylist namePlaylist;
    public  void setNamePlaylist(NamePlaylist pl){this.namePlaylist = pl;}
    public  NamePlaylist getNamePlaylist(){return namePlaylist;}
    //////////
    public AnchorPane getListOfPl() {return listOfPlaylist;}
    public void setListOfPl(AnchorPane list) {this.listOfPlaylist = list;}
    private AnchorPane listOfPlaylist;
    private ListOfPlayList listOfPlayListC;
    public  void setListOfPlayListC(ListOfPlayList pl){this.listOfPlayListC = pl;}
    public  ListOfPlayList getListOfPlayListC(){return listOfPlayListC;}
    //////////

    public AnchorPane getBrowse() {return browse;}
    public void setBrowse(AnchorPane browse) {this.browse = browse;}
    private AnchorPane browse;
    private BrowseC browseC;
    public  void setBrowseC(BrowseC browse){this.browseC = browse;}
    public  BrowseC getBrowseC(){return browseC;}

    /////////
    private AnchorPane ArtistInfo;
    public AnchorPane getArtistInfo() {return ArtistInfo;}
    public void setArtistInfo(AnchorPane artistInfo) {ArtistInfo = artistInfo;}
    private ArtistInfoC Af;
    public  void setArF(ArtistInfoC Af){this.Af = Af;}
    public  ArtistInfoC getArF(){return Af;}
    /////////////////

    public void setHome(AnchorPane home) {this.home = home;}
    public AnchorPane getHome() {return home;}
    private AnchorPane home;
    private HomeC homeC;
    public  void setHomeC(HomeC home){this.homeC = home;}
    public  HomeC getHomeC(){return homeC;}
    //////////////
    private Pane LibPane;
    public Pane getLibPane() {
        return LibPane;
    }
    public void setLibPane(Pane libPane) {
        LibPane = libPane;
    }
    private LibraryC libraryC;
    public  void setLibraryC(LibraryC library){this.libraryC = library;}
    public  LibraryC getLibraryC(){return libraryC;}
    /////////////
    private BorderPane thCPane;
    public void setThCPane(BorderPane thCPane) {
        this.thCPane = thCPane;
    }
    public BorderPane getThCPane() {
        return thCPane;
    }
    private BorderC border;
    public  void setBorder(BorderC border){this.border = border;}
    public  BorderC getBorder(){return border;}
    /////////////////
    private AnchorPane signup;
    public void setSignUp(AnchorPane signUp) {
        this.signup = signUp;
    }
    public AnchorPane getSignup() {
        return signup;
    }
    private SignupC signupC;
    public  void setSignupC(SignupC sc){this.signupC = sc;}
    public  SignupC getSignupC(){return signupC;}
    /////////////
    private AnchorPane login;
    public void setLogin(AnchorPane login) {
        this.login = login;
    }
    public AnchorPane getLogin() {
        return login;
    }
    private LoginC loginC;
    public  void setLoginC(LoginC lc){this.loginC = lc;}
    public  LoginC getLoginC(){return loginC;}
}
