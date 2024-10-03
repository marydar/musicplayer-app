package org.example.mplayer.view;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import org.example.mplayer.controller.ListenerController;
import org.example.mplayer.model.audio.Audio;
import org.example.mplayer.model.audio.PlayList;
public class PlayListChoose implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    private Set<String> selected = new HashSet<>();
    private Set<Integer> selectedIndex = new HashSet<>();
    @FXML
    private VBox vbox;

    @FXML
    private JFXButton done;

    public void set(Audio audio){
        selected.clear();
        vbox.getChildren().clear();
        int count = 0;
        System.out.println("sett");
        for(PlayList a : ListenerController.getListenerController().getListener().getListOfPlayLists()){
            System.out.println(ListenerController.getListenerController().getListener().getListOfPlayLists().size());
            System.out.println(a.getPlayListName());
            if(a.getAudioList().contains(audio)){
                AddNames(a.getPlayListName(),true,count);
            }
            else{
                AddNames(a.getPlayListName(),false,count);
            }
            count++;
        }
    }

    public void AddNames(String name, boolean fire,int index){
        CheckBox cb = new CheckBox();
        System.out.println(name);
        cb.setText(name);
        cb.setStyle("-fx-text-fill: white;");
        vbox.getChildren().add(cb);
        if(fire) {
            cb.fire();
        }
        VBox.setMargin(cb, new Insets(10, 10, 10, 30));
        cb.setOnAction(e -> {
            System.out.println(cb.getText());
            if(!(selected.add(cb.getText()))){
                selected.remove(cb.getText());
            }
            if(!(selectedIndex.add(index))){
                selectedIndex.remove(index);
            }
        });
    }

    @FXML
    void doneClick(ActionEvent event) {
        System.out.println(selected);
        System.out.println(selectedIndex);
        Cpanes.getInstance().getBorder().getNewStage().close();
        Cpanes.getInstance().getBorder().setPlayLists(selected);
    }
}
