import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * classe s'occupant de générer aléatoirement une pièce du jeu.
 *
 * @author BRAUX Owen
 * @version 2022
 */
public class RoomRandomizer
{
    
    private List<Room> aRooms;
    private Random aRandom;
    
    /**
     * Constructeur d'objets de classe RoomRandomizer
     */
    public RoomRandomizer()
    {
        this.aRooms = new ArrayList();
        this.aRandom = new Random();
    }

    /**
     * retrourne une pièce aléatoire.
     * 
     */
    public Room getRandomRoom()
    {
        return aRooms.get(aRandom.nextInt(aRooms.size()));
    }
    
    
    /**
     * ajoute une pièce.
     * 
     */
    public void addRoom(final Room pRoom)
    {
        this.aRooms.add(pRoom);
    }
}
