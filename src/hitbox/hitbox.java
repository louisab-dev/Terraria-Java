package hitbox;

import java.awt.event.*;

public abstract class hitbox {

    protected ActionListener onOverlap;
    protected ActionListener onHit;
    
    public void onOverlap(ActionListener ac){
        this.onOverlap = ac;
    }

    public void onHit(ActionListener ac){
        this.onHit = ac;
    }
    
}
