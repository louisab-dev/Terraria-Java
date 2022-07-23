package affichage;

import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.lang.Math;

import blocks.Block;

public class Sprite {
    
    private BufferedImage[] images;
    private static BufferedImage blocks = new SpriteSheet().getImage();

    public static final Sprite grass = new Sprite(blocks, 0, 0);
    public static final Sprite dirt = new Sprite(blocks, 2, 0);
    public static final Sprite stone = new Sprite(blocks, 1, 0);
    public static final Sprite iron = new Sprite(blocks,3,0);
    public static final Sprite coal = new Sprite(blocks,4,0);
    public static final Sprite snow = new Sprite(blocks,1,1);
    public static final Sprite diamond = new Sprite(blocks,0,1);
    public static final Sprite bucket = new Sprite(blocks,1,1);
    public static final Sprite air = new Sprite(blocks,4,0);
    public static final Sprite wood = new Sprite(blocks,2 , 1);
    public static final Sprite leaf = new Sprite(blocks,3 , 1);
    public static final Sprite pickaxe = new Sprite(blocks,4,1);
    public static final Sprite sword = new Sprite(blocks,0,2);
    public static final Sprite bedrock = new Sprite(blocks,1,2);
    public static final Sprite apple = new Sprite(blocks,2,2);


    public Sprite(BufferedImage image, int x, int y, int width, int height, int count) {
        if (image == null) {
            System.out.println("Erreur image null");
        }
        images = new BufferedImage[count];
        for (int i = 0; i < count; i++) {
            int w = width + SpriteSheet.SPRITE_SEPARATION;
            int h = height + SpriteSheet.SPRITE_SEPARATION;
            images[i] = image.getSubimage((x + i) * w, y * h, width, height);
        }
    }

    public Sprite(BufferedImage image, int x, int y) {
        this(image, x, y, Block.TAILLE, Block.TAILLE, 1);
    }

    public Sprite(BufferedImage[] images) {
        this.images = images;
    }

    public BufferedImage getImage() {
        return images[0];
    }

    public static BufferedImage scaleImage(BufferedImage image, float scale) {
        int width = (int) Math.ceil(image.getWidth() * scale);
        int height = (int) Math.ceil(image.getHeight() * scale);
        BufferedImage scaled = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        AffineTransform scaleInstance = AffineTransform.getScaleInstance(scale, scale);
        AffineTransformOp scaleOp = new AffineTransformOp(scaleInstance, AffineTransformOp.TYPE_BILINEAR);
    
        scaleOp.filter(image, scaled);
        return scaled;
    }

}
