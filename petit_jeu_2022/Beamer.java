
/**
 * Classe héritant de Item et permettant de s'occuper du fonctionnement de l'objet Beamer.
 *
 * @author Braux Owen
 * @version 2022
 */
public class Beamer extends Item
{
    private Room aRoomToTeleport;
    private boolean aCharge;
    
    
    /**
     * Constructeur d'objets de classe Beamer
     */
    public Beamer(final String pDescription,final double pWeight)
    {
       super(pDescription,pWeight);
       this.aCharge = false;
    }//Beamer()

    /**
     * setter de la Room où l'on doit se teleporter grâce au beamer
     */
    public void setRoomToTeleport(final Room pRoomToTeleport)
    {
       this.aRoomToTeleport = pRoomToTeleport;
       
    }//setRoomToTeleport()
    
    /**
     * retourne la Room où l'on doit se teleporter grâce au beamer
     */
    public Room getRoomToTeleport()
    {
       return this.aRoomToTeleport;
       
    }//getRoomToTeleport()
    
    
    /**
     * setter de la boolean charge
     */
    public void setCharge(final Boolean pVF)
    {
       this.aCharge = pVF;
       
    }//setCharge()
    
    /**
     * retourne la boolean charge
     */
    public boolean getCharge()
    {
       return this.aCharge;
       
    }//getCharge()
}
