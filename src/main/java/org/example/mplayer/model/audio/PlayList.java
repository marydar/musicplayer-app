package org.example.mplayer.model.audio;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class PlayList implements Iterable<Audio> {
    private final String id;
    private String playListName;
    private String createrName;
    private ArrayList<Audio> audioList;
    private static long playlistCounter=0;
    private int playlistIndex=0;
    public PlayList(String playListName, String createrName) {
        playlistCounter++;
        this.playListName = playListName;
        this.createrName = createrName;
        audioList = new ArrayList<>();
        this.id = String.valueOf(playlistCounter)+"/"+playListName;
    }
    public String getId() {
        return id;
    }
    public String getPlayListName() {
        return playListName;
    }
    public String getCreaterName() {
        return createrName;
    }
    public ArrayList<Audio> getAudioList() {
        return audioList;
    }
    public static long getPlaylistCounter() {
        return playlistCounter;
    }
    public void setPlayListName(String playListName) {
        this.playListName = playListName;
    }
    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }
    public static void setPlaylistCounter(long playlistCounter) {
        PlayList.playlistCounter = playlistCounter;
    }
    public void setAudioList(ArrayList<Audio> audioList) {
        this.audioList = audioList;
    }
    public void addToAudioList(Audio audio) {
        this.audioList.add(audio);
    }
    @Override
    public String toString(){
        String txt="playlist "+playListName+" By "+createrName+"\n";
        for(Audio audio : audioList){
            txt+="-"+audio.getAudioName()+"\n";
        }
        return txt;
    }


    @Override
    public Iterator<Audio> iterator() {
        return new Iterator<Audio>() {

            @Override
            public boolean hasNext() {
                if(playlistIndex <audioList.size()){
                    return true;
                }
                playlistIndex=0;
                return false;
            }

            @Override
            public Audio next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return audioList.get(playlistIndex++);
            }

        };
    }
}
