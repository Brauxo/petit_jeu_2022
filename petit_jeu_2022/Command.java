 

/**
 * The test class Command.
 *
 * @author  BRAUX Owen
 * @version 2022
 */
public class Command
{
  
   private String aCommandWord = null;
   private String aSecondWord = null; 
   
   /**
     * Constructeur de la classe qui initialise les des deux mots écrits par l'utilisateur
     */ 
   public Command(final String pCommandWord,final String pSecondWord)
  {

      this.aCommandWord = pCommandWord;
      this.aSecondWord = pSecondWord;
      
  } //Command()
  
  /**
     * Permet d'obtenir le premier mot écrit par l'utilisateur
     */  
   public String getCommandWord()
  {
      
      return this.aCommandWord;
      
  } //getCommandWord() 
  
  /**
     * Permet d'obtenir le second mot écrit par l'utilisateur
     */  
   public String getSecondWord()
  {
      
      return this.aSecondWord;
      
  } //getSecondWord() 
  
  /**
     * Vérifie si le mot après commande mot existe
     */ 
   public  boolean hasSecondWord()
   {
       
         return !(this.aSecondWord == null);
         
   }//hasSecondWord()
   
   /**
     * Vérifie si le mot de commande existe
     */ 
   public  boolean isUnknown()
   {
       
       return (this.aCommandWord == null);
       
   } //isUnknown()
    
   
} // Command
