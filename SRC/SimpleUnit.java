import java.awt.Graphics;
import java.awt.geom.Point2D;

public class SimpleUnit extends Unit {

    // _____________CONSTRUCTEURS______________//
    /**
     * @param owner
     * @param locationToSet
     */
    public SimpleUnit(Player owner, Point2D topLeftCorner, Point2D targetToSet){
        super(owner, topLeftCorner, 1, targetToSet);
    }
    
    public SimpleUnit(Player owner, Point2D topLeftCorner){
        super(owner, topLeftCorner, 1, null);
    }

    //________________MÉTHODES_______________//
    
    public void createSoldier(){
        // TODO implémenter cette méthode
    }
}
