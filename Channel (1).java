package unit04;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


/**Channel class acts as helper class that will be used to represent the TV set, defining methods that are used to control visuals and audio
 * Group 6
 * @author Mohammad Fahmi
 * @author Sufyan 
 * @author Rashid
 * @author Shahmeer
 * Homework 2
 * semester: SPRING2023
 * section: 600 
*/




public class Channel {

    private String description;
    private Image image;
    private Media media;
    private MediaPlayer mediaPlayer;
    // Rashed
    private static double volume = 0.5;
    // Rashed
    private String imagePath;


    /**
     * this is a parameterized constructor
     * @param description 
     * description of the channel
     * @param imagepath
     * imagepath, dictates what is displayed on each channel
     * @param audiopath
     * dictates waht audio is played alongside the image that is displayed
     */
    public Channel (String description, String imagepath, String audiopath ){
        this.description=description;
        this.image = new Image(imagepath);
        //Rashed
        this.imagePath = imagepath;
        this.media = new Media(audiopath);
        this.mediaPlayer= new MediaPlayer(media);
        this.mediaPlayer.setVolume(volume);

    }

    //getters

     /**
     * getter for channel description
     * @return String channel description
     */
    public String getDescription(){
        return description;}

    /**
     * getter for channel's image / imagepath
     * @return ImageView object of channel
     */
    public Image getImage(){
        return image;}

    public String getImagePath(){
        return imagePath;}

     /**
     * getter for channel media / mediaplayer
     * @return media object of channel
     */
    public Media getMedia(){
        return media;}

    public MediaPlayer getMediaPlayer(){
        return mediaPlayer;}

     /**
     * getter for channel's volume
     * @return double channels volume
     */
    public static double getVolume(){
        return volume;}

    //setters

    /**
     * setter for channel description
     * @param description
     */
    public void setDescription(String description) {this.description = description;}

    /**
     * setter for channel image
     * @param image
     */
    public void setImage(Image image){this.image = image;}

    /**
     * setter for channel media
     * @param media
     */
    public void setMedia(Media media){this.media = media;}

    /**
     * setter for channel mediaplayer
     * @param mediaPlayer
     */
    public void setMediaPlayer(MediaPlayer mediaPlayer){this.mediaPlayer = mediaPlayer;}

    /**
     * setter for channel volume
     * @param volume
     * limits max and min volume, if volume goes below 0.0 then it is 0.0, and vice versa for going above 1.0
     */
    public void setVolume(double volume){
        if(volume<0.0){volume = 0.0;}
        else if(volume>1.0){volume = 1.0;}

        this.volume = volume;
        mediaPlayer.setVolume(volume);
    }
    /**
     * allows the user to increment the volume up or down in 0.1 increments
     */
    public void increaseVolume(){
        setVolume(volume+0.1);
    }
    public void decreaseVolume(){
        setVolume(volume-0.1);
    }


    /**
     * to methods used to stop and start any media in the media player
     */
    public void play(){
        mediaPlayer.play();
    }
    public void stop(){
        mediaPlayer.stop();
    }
}