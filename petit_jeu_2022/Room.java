import java.util.HashMap;

/**
 * The test class Room.
 *
 * @author  BRAUX Owen
 * @version 2022
 */
public class Room
{
  
  private String aDescription;
  private HashMap<String, Room> aExits;
  private String aImageName;
  private ItemList aRoomInventory;
  
  /**
     * Constructeur de la classe qui applique la descritption du lieu.
     */
  public Room(final String pDescription,final String pImage)
  {

      this.aDescription = pDescription ;
      aExits = new HashMap<String, Room>();
      this.aRoomInventory = new ItemList();
      this.aImageName = pImage;
      
  } //Room()
  
  /**
     * Cette méthode permet d'obtenir la description d'un objet de classe room.
     */
  public String getDescription()
  {
      
      return this.aDescription;
      
  } //getDescription() 
  
  /**
     * Cette méthode créer une description complète de la pièce.
     */
  public String getLongDescription()
  {
      
      return "Pièce actuel : " +this.aDescription + "\n" + getExitString() ; 
      
  } //getLongDescription() 
  
    
  /**
  * procédure qui s'occupe d'une sortie d'un objet de classe room.
  */
  public void setExit(final String pDirection, final Room pRoom){
        
    aExits.put(pDirection, pRoom);
        
  }//setExit()
    
  /**
  * procédure qui s'occupe de savoir si la sortie vers l'ancienne pièce existe.
  */
  public boolean isExit(final Room pPreviousRoom){
    for (Room vRoom : aExits.values()){
        if(pPreviousRoom.equals(vRoom)){
        return true; 
        }
    }
    return false;
  }//isExit()
  
  /**
   * Cette méthode permet de connaitre la valeur des directions.
   */
   public Room getExit(final String pDirection)
   {
    return aExits.get(pDirection);
    
    
   }//getExit()
    
      /**
     * Cette méthode ajoute un item dans la pièce.
     */
    public void addItem(final Item pItem)
  {
      aRoomInventory.addInventoryItem(pItem);
  }//addItem()
  
  /**
     * Cette méthode retire un Item de la pièce.
     */
    public void removeItem(final Item pItem)
  {
      aRoomInventory.removeInventoryItem(pItem);
  }//removeItem()
  
  /**
     * cherche si l'item existe dans la pièce
     * 
     */
   public Item searchRoomItem(final String pItem)
   {
       
       return aRoomInventory.searchItem(pItem);
       
   }//searchRoomItem()
  
  /**
     * Renvoie sous forme de String les items de la pièces
     * 
     */
   public String getItemsString()
   {
       if(this.aRoomInventory == null){ // ne marche plus à cause du hashmap
           return "Je ne trouve pas d'objet dans cette pièce";
       }else{
           String vAllItems = "objets présents dans cette pièce :" + "\n" + "";
           vAllItems = vAllItems + this.aRoomInventory.getStringInventory();
           return vAllItems;
       }
   }//getItemsString()
   
   /**
   * Cette méthode retourne à l'utilisateur les sorties qui existent ou non.
   */
   public String getExitString()
   {
       String vString = "Sorties : ";
       for (String vDir: aExits.keySet()) {
           vString += " " + vDir;
       }
           return vString;      
              
   }//getExitString()
   
   /**
     * Renvoie le nom de l'image de la pièce
     * 
     */
   public String getImageName()
   {
       return this.aImageName;
   }//getImageName()
   
   
   /**
     * Accède à l'inventaire
     * 
     */
   public ItemList getItemList()
   {
       return this.aRoomInventory;
   }//ItemList()
   
   
} // Room
