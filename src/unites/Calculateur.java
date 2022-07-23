package unites;
import utils.*;

import blocks.*;
import hitbox.*;

public class Calculateur {


    /**
     * Vérifie si la hitbox du joueur entre en collision avec un bloc, et si c'est le cas, il
     * renvoie un vecteur qui corrigera la position du joueur afin qu'il n'entre plus en collision
     * 
     * @param h la hitbox de l'objet que vous souhaitez déplacer
     * @param deplacement le vecteur du mouvement
     * @param blocks l'objet BlocksManager qui contient tous les blocs
     * @return Le vecteur de la collision.
     */
    public static Vecteur calculCollision(rectanglehitbox h, Vecteur deplacement, BlocksManager blocks){
        double eps = 1.001;
        Vecteur vcorr = new Vecteur(0, 0);
        double nmax = 0;
        rectanglehitbox hb;
        //System.out.println(deplacement);
        //Vecteur vectsigne1 = new Vecteur(h.getPosition(), hb.getPosition());
        h.translater(deplacement.getX(), deplacement.getY());
        //rectanglehitbox nh = h.HBtranslatee(deplacement.getX(), deplacement.getY());
        rectanglehitbox nh = h;
        Point pos = nh.getPosition();
        //System.out.println("position :" + pos);
        //System.out.println("x :"+ pos.getX()+ "y :"+ pos.getY());
        int posx = (int) Math.floor(pos.getX());
        int posy = (int) Math.floor(pos.getY());
        //System.out.println("largeur : "+nh.getLargeur() + " hauteur : "+nh.getHauteur());
        int bornex = (int) Math.ceil(deplacement.getX() + nh.getLargeur());
        int borney = (int) Math.ceil(deplacement.getY() + nh.getHauteur());
        //System.out.println("posx: "+posx+" posy: "+posy+ " bornex: "+bornex+" borney: "+borney ); 
        for(int i = posx - bornex -1; i < posx + bornex + 1; i++){
            for (int j = posy - borney - 1; j < posy + borney + 1; j++){
                //System.out.println("i : "+i+" ,j : "+j + " ,posx : "+posx+" ,posy : "+posy+ " ,bornex: "+bornex+" ,borney : "+borney ); 
                Block tb = blocks.getBlock(i,j);
                if (tb != null){
                    if (tb.collide){
                        //tb.debug = true;
                        //System.out.println("collision");
                        //System.out.println("x : " + tb.getX() + " y :" + tb.getY());
                        hb = tb.getHitbox();
                        //System.out.println(nh.getPoints()[0].getY());
                        //System.out.println(nh.getCollisionVector(hb).vOppo());
                        Vecteur vc = nh.getCollisionVector(hb).vOppo();
                        //Vecteur vectsigne2 = new Vecteur(nh.getPosition(), hb.getPosition());
                        //double s = Math.signum(vectsigne2.pDot(vc));
                        //double sx = Math.signum(vectsigne2.getX());
                        //double sy = Math.signum(vectsigne2.getY());
                        //System.out.println(s);
                        //System.out.println((new Vecteur(h.getPosition(), hb.getPosition())).getY());
                        if(Math.abs(vc.getX()) >= Math.abs(vcorr.getX())){
                                vcorr.setX(vc.getX());
                        }
                        if(Math.abs(vc.getY()) >= Math.abs(vcorr.getY())){

                                vcorr.setY(vc.getY()+ 0.001);

                        }
                        //vcorr.addv(nh.getCollisionVector(hb).vOppo());
                    }
                }
            }
        }
        
        h.translater(-deplacement.getX(), -deplacement.getY());
        //vcorr.setX(Math.signum(deplacement.getX())*( Math.floor(vcorr.getX()*1000000))/1000000.0);
        //vcorr.setY(Math.signum(deplacement.getY())*( Math.floor(vcorr.getY()*1000000))/1000000.0);
        vcorr.setX(( (vcorr.getX())));
        vcorr.setY(( (vcorr.getY())));
        //Math.signum((new Vecteur(h.getPosition(), hb.getPosition()).pDot(vcorr);
        //System.out.println(vcorr);
        return vcorr;
    }
    
}
