package affichage;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import main.Game;

public class SpriteSheet {
    
    public static final int SPRITE_SEPARATION = 1;
    private BufferedImage blocks;
    private BufferedImage image;

    public SpriteSheet() {
        String texture = Game.texture;

        System.out.println("Chargement de la texture " + texture);
        try {
            blocks = loadImage("affichage/" + texture + ".png");
            // blocks = new SpriteSheet("affichage/" + texture + ".png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SpriteSheet(String path) {
        loadImage(path);
    }

    private BufferedImage loadImage(String path) {
        try {
            image = ImageIO.read(new File(path));
            if (image == null) {
                System.out.println("Erreur chargement image");
                return null;
            }
            else {
                System.out.println("Image chargée");
                return image;
            }
        } catch (Exception e) {
            System.out.println("Error loading spriteSheet : il faut surement rajouter /src au lieu de src dans SpriteSheet.java : " + path);
            return null;
        }
    }

    public BufferedImage getImage() {
        return image;
    }

}
