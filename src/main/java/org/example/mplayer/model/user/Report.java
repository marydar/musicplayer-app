package org.example.mplayer.model.user;

public class Report {
    private User reportingUser;
    private Artist reportedArtist;
    private String Description;
    public Report(User reportingUser, Artist reportedArtist, String description) {
        this.reportingUser = reportingUser;
        this.reportedArtist = reportedArtist;
        Description = description;
    }
    public User getReportingUser() {
        return reportingUser;
    }
    public Artist getReportedArtist() {
        return reportedArtist;
    }
    public String getDescription() {
        return Description;
    }
    public void setReportingUser(User reportingUser) {
        this.reportingUser = reportingUser;
    }
    public void setReportedArtist(Artist reportedArtist) {
        this.reportedArtist = reportedArtist;
    }
    public void setDescription(String description) {
        Description = description;
    }
    @Override
    public String toString(){
        return "User : "+reportingUser.getUsername()+" / Artist : "+reportedArtist.getUsername()+"\n";
    }
}
