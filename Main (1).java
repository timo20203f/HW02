package unit04;
	
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/** the purpose of the main class is to represent a simple TV application with a graphical user interface. 
 * It allows the user to select and watch different TV channels, adjust the volume, and view channel information.
 * 
 * Group 6
 * @author Mohammad Fahmi
 * @author Sufyan 
 * @author Rashid
 * @author Shahmeer
 * Homework 2
 * semester: SPRING2023
 * section: 600 
*/

public class Main extends Application {
	
    private static final String TITLE = "TV Set";
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private List<Chan> channels;
    private int currentChannelIndex;
    public static Label volumeLabel;
    
    private Button volumnUp;
    private Button volumnDown;
    
    private MediaPlayer mediaPlayer;
    private ImageView imageView;
    
    private Label descriptionLabel;

    /**
     * Overridden method of Application class to set stage and application
     * 
     * @param stage main stage of the application
     */

    @Override
    public void start(Stage primaryStage) {
    	//create a new method called createChannels
        createChannels();
        
        // create a new VBox to hold elements vertically
        VBox mainVBox = new VBox();
        
        // create a new HBox to hold elements horizontally
        HBox mainHBox = new HBox();
        
        // Create a new image view and set its width and height
        imageView = new ImageView();
        imageView.setFitWidth(500);
        imageView.setFitHeight(600);
        mainHBox.getChildren().add(imageView);
        
      
        // Create a new VBox called rightsideVBox for holding the channel controls
        VBox rightsideVBox = new VBox();
        
        // Add the channel controls to the rightsideVBox using the createChannelControls method
        rightsideVBox.getChildren().add(createChannelControls());
        
        // Create a new label called descriptionLabel with a font size of 20pt
        descriptionLabel = new Label();
        descriptionLabel.setStyle("-fx-font-size: 20pt");
        
        
        // Create an HBox to hold the volume buttons, and set its alignment and padding
        HBox volumeBox = new HBox();
        volumeBox.setAlignment(Pos.CENTER);
        volumeBox.setPadding(new Insets(20));
       
        // Create a "volume up" button, set its dimensions and font size, and add it to the HBox
        volumnUp = new Button("+");
        volumnUp.setMinWidth(70);
        volumnUp.setMinHeight(70);
        volumnUp.setStyle("-fx-font-size: 20pt");
        volumeBox.getChildren().add(volumnUp);
        
        // Create a "volume down" button, set its dimensions and font size, and add it to the HBox
        volumnDown = new Button("-");
        volumnDown.setMinWidth(70);
        volumnDown.setMinHeight(70);
        volumnDown.setStyle("-fx-font-size: 20pt");
        volumeBox.getChildren().add(volumnDown);
        
       // create an initial value for the volume label
        volumeLabel = new Label("5");
        volumeLabel.setStyle("-fx-font-size: 16pt");
        volumeBox.getChildren().add(volumeLabel);

        //add the volume box to rightsideVbox
        rightsideVBox.getChildren().add(volumeBox);
        // add the right VBox to the mainHbox
        mainHBox.getChildren().add(rightsideVBox);
        // add descrpition label to mainVBox
        mainVBox.getChildren().addAll(descriptionLabel, mainHBox);
     

        /*  Create a new Scene object with the main VBox layout, and set its width and height
        * set the title and scene for the primary stage
        * make the primary stage visble
        * call the playChannel() method to start playing first channel
        */
        Scene scene = new Scene(mainVBox, WIDTH, HEIGHT);
        primaryStage.setTitle(TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
        playChannel(0);
    }
    
    /**
    * created an arraylist named createChannels(), creates 10 channel objetcs using the add method, 
    each object has a Channel Description, image filepath, and audio filepath.

    * @param  createChannels 
    * @return void 
     */

    private void createChannels() {
        channels = new ArrayList<>();       
        channels.add(new Chan("Channel 0 - Horor", "file:media/images/TV/IMG0.jpg", 
        "media/audio/TV/Audio0.mp3"));
        channels.add(new Chan("Channel 1 - Seinfeld", "file:media/images/TV/IMG1.jpg",
        "media/audio/TV/Audio1.mp3"));
        channels.add(new Chan("Channel 2 - Music", "file:media/images/TV/IMG2.jpg",
        "media/audio/TV/Audio2.mp3"));
        channels.add(new Chan("Channel 3 - News", "file:media/images/TV/IMG3.jpg",
        "media/audio/TV/Audio2.mp3"));
        channels.add(new Chan("Channel 4 - Comedy", "file:media/images/TV/IMG4.jpg",
        "media/audio/TV/Audio2.mp3"));
        channels.add(new Chan("Channel 5 - Drama", "file:media/images/TV/IMG5.jpg",
        "media/audio/TV/Audio2.mp3"));
        channels.add(new Chan("Channel 6 - Action", "file:media/images/TV/IMG6.jpg",
        "media/audio/TV/Audio2.mp3"));
        channels.add(new Chan("Channel 7 - Fight", "file:media/images/TV/IMG7.jpg",
        "media/audio/TV/Audio2.mp3"));
        channels.add(new Chan("Channel 8 - Sci", "file:media/images/TV/IMG8.jpg",
        "media/audio/TV/Audio2.mp3"));      
        channels.add(new Chan("Channel 9 - Nature", "file:media/images/TV/IMG9.jpg",
        "media/audio/TV/Audio2.mp3"));
    }

 /**
     * factory method for making volume buttons and changing the channels (image)
     * 
     * @param imageView ImageView object for the image on the button
     * @return Button object representing volume up or down
     */

    private void playChannel(int index) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        Chan channel = channels.get(index);
        imageView.setImage(new Image(channel.getImage()));
        descriptionLabel.setText(channel.getDescription()); 
        mediaPlayer = channel.getMediaPlayer();
        mediaPlayer.setVolume(Chan.getVolume());
        mediaPlayer.play();
        
        volumnUp.setOnAction(e -> {channel.increaseVolume(); volumeLabel.setText(String.valueOf((int)(Chan.getVolume()*10)));});
        volumnDown.setOnAction(e -> {channel.decreaseVolume(); volumeLabel.setText(String.valueOf((int)(Chan.getVolume()*10)));});
    }

     /**
     * private method to set up grid pane for the controls of the tv
     * 
     * @return Grid pane object for channel controls and volume controls
     */

    private VBox createChannelControls() {
    	
        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setPadding(new Insets(20));
        int no = 0; 
        for (int row = 0; row < 3; row++) {
        	for (int col = 0; col < 3; col++) {
            Button button = new Button(String.valueOf(no+1));
            button.setMinWidth(70);
            button.setMinHeight(70);
            int index = no;
            button.setOnAction(e -> {
                currentChannelIndex = index;
                playChannel(index);
            });
            no++;
            
            gp.add(button, col, row);
            }	
        }
        
        VBox vb = new VBox();
        vb.getChildren().add(gp);
        
        /**
        * factory method for making channel buttons
        * 
        * @param text text inside button
        * @return Button object for channel buttons
        */

        Button button0 = new Button("0");
        button0.setMinWidth(300);
        button0.setMinHeight(70);
        button0.setOnAction(event -> {
            currentChannelIndex = 9;
            playChannel(9);
        });
        vb.getChildren().add(button0);
        
        return vb;
    }

    /**
     * main method for launching application
     * 
     * @param args command line arguments
     */

    public static void main(String[] args) {
        launch(args);
    }
}
