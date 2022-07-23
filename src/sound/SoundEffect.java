package sound;

import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundEffect {
    public static void play(String fileName) {
        Clip clip;
        try {
            InputStream musiqueFile = Music.class.getResourceAsStream("/sound/" + fileName + ".wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(musiqueFile);
            clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
        }
        catch(Exception e) {
            System.out.println("Erreur de chargement de la musique : " + e.getMessage());
        }
    }
}
