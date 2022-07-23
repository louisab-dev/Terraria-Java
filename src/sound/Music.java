package sound;

import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Music {

    public static void loop() {
        Clip clip;
        try {
            InputStream musiqueFile = Music.class.getResourceAsStream("/sound/Musique.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(musiqueFile);
            clip = AudioSystem.getClip();
            clip.open(ais);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch(Exception e) {
            System.out.println("Erreur de chargement de la musique : " + e.getMessage());
        }
    }
}
    