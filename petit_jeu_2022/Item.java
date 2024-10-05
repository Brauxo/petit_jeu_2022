
/**
 * Classe qui s'occuupe de la création d'un item
 *
 * @author BRAUX Owen
 * @version 2022
 */
public class Item
{
  
  private String aDescription;
  private double aWeight;
  
  /**
  * Constructeur d'objets de classe Item
  */
  public Item(final String pDescription,final double pWeight )
  {
      
      this.aDescription = pDescription;
      this.aWeight = pWeight;
  }
  
  /**
  * retourne la description d'un objet
  */
  public String getItemDescription(){
      
      return this.aDescription;
      
  }//getItemDescription()
  
  /**
  * retourne le poid d'un objet sous forme de String
  */
  public String getStringWeight(){
      
      return "" + this.aWeight + "kilos";
      
  }//getStringWeight()
  
  /**
  * retourne le poid d'un objet 
  */
  public double getItemWeight(){
      
      return this.aWeight;
      
  }//getItemWeight()
  
  /**
     * Cette méthode envoie une description complète de l'objet.
     */
  public String getLongItemDescription()
  {
      
      return this.aDescription + "  " + " le poids de l'objet est de " + this.aWeight + " kilos"; 
      
  } //getLongItemDescription()
  
}
