import java.awt.geom.Point2D;

import java.util.Collection;
import java.util.LinkedList;

public class UnitGroup implements Cloneable {


    //_______________ATTRIBUTS_________________//

    protected LinkedList<Unit> group;
    protected double compactdim;
    protected Player owner;

    //_______________CONSTRUCTEURS______________//

    /**
     * Constructeur.
     */
    public UnitGroup() {
        group = new LinkedList<Unit>();
    }

    /**
     * Constructeur pour une seule unite.
     * @param u unite
     * @param iaToSet Intelligence artificielle du groupe
     */
    public UnitGroup(Unit u) {
        if (u != null) {
            group = new LinkedList<Unit>();
            group.add(u);
            owner = u.owner;
            compactdim = Finals.Group_compactDim;
        }
    }

    /**
     * Constructeur pour un groupe d’unites.
     * @param units groupe d’unites
     * @param iaToSet Intelligence artificielle du groupe
     */
    public UnitGroup(Collection units) {
        group = new LinkedList<Unit>(units);
        owner = null;
        compactdim = Finals.Group_compactDim;
    }

    /**
     * Constructeur pour un tableau d'unites.
     * @param u tableau d'unites
     */
    public UnitGroup(Unit[] u) {
        this(u[0]);
        for (int i = 1; i < u.length; i++) {
            if (u[i].owner == owner)
                add(u[i]);
        }
    }

    /**
     * Constructeur pour un groupe d’unites ayant un possesseur attitre.
     * @param units groupe d’unites
     * @param iaToSet Intelligence artificielle du groupe
     * @param ownerToSet possesseur du groupe
     */
    public UnitGroup(Collection units, Player ownerToSet) {
        this(units);

        owner = ownerToSet;
        for (Unit i : group)
            if (i.owner != owner)
                group.remove(i);

    }

    //_______________MÉTHODES______________//


    /**
     * @return taille du groupe
     */
    public int numberUnit() {
        return group.size();
    }

    /**
     * @return toutes les unités du groupe
     */
    public LinkedList<Unit> getGroup() {
        return group;
    }

    /**
     * @return postion du Centre des masse du groupe
     */
    // travailler les Update pour ameliorer la vitesse
    public Point2D getPosition() {
        double X = 0;
        double Y = 0;
        for (Unit i : group) {
            X += i.getCenter().getX();
            Y += i.getCenter().getY();
        }
        X = X / this.group.size();
        Y = Y / this.group.size();
        return new Point2D.Double(X, Y);
    }

    /**
     * @return postion du Centre des masse du groupe
     */
    public static Point2D getPosition(Unit[] u) {
        double X = 0;
        double Y = 0;
        for (Unit i : u) {
            X += i.getCenter().getX();
            Y += i.getCenter().getY();
        }
        X = X / u.length;
        Y = Y / u.length;
        return new Point2D.Double(X, Y);
    }


    /**
     * @param p position sur la carte
     * @return distance à une position
     */
    public double distanceTo(Point2D p) {
        return p.distance(getPosition());
    }

    /**
     * @param i objet sur la carte
     * @return distance à un objet
     */
    public double distanceTo(Item i) {
        return i.getCenter().distance(getPosition());
    }

    /**
     * @param ug groupe d’unités
     * @return distance à un autre groupe d’unités
     */
    public double distanceTo(UnitGroup ug) {
        return ug.getPosition().distance(getPosition());
    }

    /**
     * @return true si detruit
     */
    public boolean isDestructed() {
        boolean removed = false;
        for (Unit u : group)
            removed = u.isDestructed();
        return removed;
    }

    /**
     * @return possesseur
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * @param targetToSet
     */
    public void setTarget(Point2D targetToSet) {
        for (Unit i : group)
            i.setTarget(targetToSet);
    }

    /**
     * @param targetToSet
     */
    public void setTarget(Item targetToSet) {
        for (Unit i : group)
            i.setTarget(targetToSet);
    }

    /**
     * Pour voir les unites tuees d'un tour a l'autre de l'execution de l'IA
     * @return nombre d'unite du groupe qui sont mortes � present
     */
    public int areDeadNow() {
        int mortes = 0;
        for (int i = 0; i < this.getGroup().size(); i++) {
            if (this.getGroup().get(i).isDead()) {
                mortes++;
            }
        }
        return mortes;
    }

    /**
     * @return la quantite de vie cumulee de toutes les unites du UnitGroup
     */
    public double getQuantityOfLife() {
        double quantity = 0.0;
        for (Unit i : getGroup()) {
            quantity += i.life;
        }
        return quantity;
    }

    /**
     * @return la quantite de dommages cumulee de toutes les unites du UnitGroup
     */
    public double getQuantityOfDamages() {
        return 0.0;
    }

    /**
     * @param u objet a suprimer de group
     */
    public void remove(Unit u) {
        group.remove(u);

    }

    /**
     * @return clone de l'obj
     */
    public Object clone() {
        Object o = null;
        try {
            // On recupere l'instance a renvoyer par l'appel de la methode super.clone()
            o = super.clone();
        } catch (CloneNotSupportedException cnse) {
            // Ne devrait jamais arriver car nous implementons l'interface Cloneable
            cnse.printStackTrace(System.err);
        }
        // on renvoie le clone
        return o;
    }

    public void add(Unit u) {
        group.add(u);
    }

    public void setSelected(Boolean b) {
        for (Unit u : group)
            u.setSelected(b);
    }

    void clear() {
        group.clear();
    }

    int size() {
        return group.size();
    }


    boolean isSimpleUnitGroup() {
        for (Unit u : group) {
            if (u.getClass().getName() != "SimpleUnit")
                return false;
        }
        return true;
    }

    boolean hasDead() {
        for (Unit u : group) {
            if (u.isDead())
                return true;
        }
        return false;
    }
}
