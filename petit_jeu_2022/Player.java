import java.util.Stack;
import java.util.HashMap;

/**
 * Classe qui s'occupe de créer un objet Player qui représente les caractéristiques du personnage incarné par le joueur.
 *
 * @author BRAUX Owen
 * @version 2022
 */
public class Player
{
    private String aPlayerName;
    private Room aCurrentRoom;
    private Stack<Room> aPreviousRooms ;
    private double aMaxWeight;
    private double aWeight;
    private ItemList aPlayerInventory;
    
    /**
     * Constructeur d'objets de classe Player
     */
    public Player(final String pPlayerName)
    {
        this.aPlayerName = pPlayerName;
        this.aPreviousRooms = new Stack();
        this.aPlayerInventory = new ItemList();
        this.aMaxWeight = 20;
        this.aWeight = 0;
        
    }//Player()
    
    
    /**
     * Permet de supprimer toutes les pièces précédentes
     */
    public void clearPreviousRooms(){
        this.aPreviousRooms.clear();
    }//clearPreviousRoom()
    
    /**
     * Permet d'obtenir la pièce actuelle 
     */
    public Room getCurrentRoom(){
        return this.aCurrentRoom;
    }//getCurrentRoom()
    
    /**
     * Permet de "check" les items 
     */
    public String checkItems(){
        return this.aCurrentRoom.getItemsString();
    }//checkItems()
    
    /**
     * Permet d'initialiser la pièce actuelle 
     */
    public void setCurrentRoom(final Room pCurrentRoom){
        this.aCurrentRoom = pCurrentRoom;
    }//setCurrentRoom()
    
    
    /**
     * Permet d'obtenir la pièce précédente
     */
    public Room getPreviousRoom(){
        return this.aPreviousRooms.lastElement();
    }//getPreviousRoom()
    
    /**
     * Permet d'ajouter la pièce précédente
     */
    public void addPreviousRoom(final Room pPreviousRoom){
         this.aPreviousRooms.push(pPreviousRoom);
    }//addPreviousRoom()
    
    /**
     * Permet de savoir s'il existe une pièce précédente
     */
    public boolean isPreviousRoomEmpty(){
        return this.aPreviousRooms.isEmpty();
    }//isPreviousRoomEmpty()
    
    /**
     * procédure s'occupant de déplacer le joueur
     */  
  public void movePlayer(final Room pNextRoom){
      
          this.aPreviousRooms.push(this.aCurrentRoom);
          this.setCurrentRoom(pNextRoom);
      }//movePlayer()
      
      /**
     * procédure s'occupant de déplacer le joueur en arrière
     */  
  public void movePlayerBack(){
      
          this.aCurrentRoom = this.aPreviousRooms.lastElement();
          this.aPreviousRooms.pop();

      }//movePlayerBack()
    
      /**
     * procédure s'occupant de ranger un objet dans l'inventaire
     */  
  public void playerTake(final Item pItem){
          
          this.aPlayerInventory.addInventoryItem(pItem);

      }//playerTake()   
  
      /**
     * procédure s'occupant d'enlever un objet de l'inventaire
     */  
  public void playerDrop(final Item pItem){
      
          this.aPlayerInventory.removeInventoryItem(pItem);

      }//playerDrop()
      
      /**
     * cherche si l'item existe dans l'inventaire du joueur.
     * 
     */
   public Item searchInventory(final String pItem)
   {

       return this.aPlayerInventory.searchItem(pItem);
       
   }//searchIventory()
   
   /**
     * Renvoie sous forme de String les items de l'inventaire
     * 
     */
   public String getPlayerInventory()
   {
       if(aPlayerInventory == null){ // ne marche plus à cause du hashmap
           return "Je ne trouve pas d'objet dans votre inventaire";
       }else{
           String vAllItems = "objets présents votre inventaire :" + "\n" + "";
           vAllItems = vAllItems + this.aPlayerInventory.getStringInventory();
           return vAllItems;
       }
   }//getInventory()
   
   /**
     * Accède à l'inventaire
     * 
     */
   public ItemList getItemList()
   {
       return this.aPlayerInventory;
   }//ItemList()
   
   /**
     * Obtient le poids actuel du joueur
     */
   public double getPlayerWeight()
   {
       return this.aWeight;
   }//getPlayerWeight()
   
   /**
     * Obtient le poids maximum transporatable par le joueur
     */
   public double getMaxWeight()
   {
       return this.aMaxWeight;
   }//getMaxWeight()
   
   /**
     * ajoute du poid à l'inventaire
     */
   public void addWeight(final double pPoid)
   {
        this.aWeight += pPoid;
   }//addWeight()
   
   /**
     * enlève du poid à l'inventaire
     */
   public void removeWeight(final double pPoid)
   {
        this.aWeight += - pPoid;
   }//removeWeight()
   
   /**
     * change le poids maximum du joueur
     */
   public void changeMaxWeight(final double pPoid)
   {
        this.aMaxWeight = pPoid;
   }//changeMaxWeight()
   
   /**
     * vérifie que le poids maximum du joueur n'est pas dépassé.
     */
   public boolean isWeightGood()
   {
        return (this.aWeight <= this.aMaxWeight );
   }//isWeightGood()
}
