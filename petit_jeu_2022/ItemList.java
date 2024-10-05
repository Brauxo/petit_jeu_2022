import java.util.HashMap;
import java.util.Set;

/**
 * Classe qui s'occupe des inventaires
 *
 * @author Braux Owen
 * @version 2022
 */
public class ItemList
{
    private HashMap<Item, ItemList> aInventory;

    /**
     * Constructeur d'objets de classe ItemsList
     */
    public ItemList()
    {
        this.aInventory = new HashMap<>();
    }

    /**
     * Méthode qui retourne sous forme de String tout les objets présents dans l'inventaire.
     *
     */
    public String getStringInventory()
    {
        String vAllItems = "";
        for (Item vItem : aInventory.keySet()) {
           vAllItems += vItem.getLongItemDescription() + "\n"  +"";
       }
           return vAllItems;
    }
        
    /**
     * procédure s'occupant de ranger un objet dans l'inventaire
     */  
    public void addInventoryItem(final Item pItem)
    {
          
          this.aInventory.put(pItem,this);

    }//addItem()
      
    /**
     * procédure s'occupant d'enlever un objet de l'inventaire
     */  
    public void removeInventoryItem(final Item pItem)
    {
      
          this.aInventory.remove(pItem);

    }//removeItem()
      
    /**
     * cherche si l'item existe dans l'inventaire du joueur.
     * 
     */
    public Item searchItem(final String pItem)
    {
       for (Item vItem : aInventory.keySet()) {
           if(vItem.getItemDescription().equals(pItem)){
               return vItem;
           }
       }
       return null;
    }//searchItem()

}

