import java.util.Stack;
import java.util.List;
import java.io.File;
import java.util.Scanner;

/**
 * La classe GameEngine qui s'occupe du fonctionnement du jeu 
 *
 * @author  BRAUX Owen
 * @version 2022
 */
public class GameEngine
{
    private Parser aParser;
    private CommandWords aCommand;
    private UserInterface aGui;
    private Player aPlayer;
    private int aActualTime;
    private int aLimitTime;
    private RoomRandomizer aRandomizer;
    
    /**
     * Constructeur de la classe qui appelle 
     * toutes les méthodes formant le plan du jeu
     */
    public GameEngine()
  {
      this.aPlayer = new Player("Jones");
      this.aRandomizer = new RoomRandomizer();
      this.aParser = new Parser();
      this.aCommand = new CommandWords();
      this.aLimitTime = 50;
      this.createRooms();

      
  } //GameEngine()
  
  
  /**
   *  Initialise l'Interface
   */
  public void setGUI( final UserInterface pUserInterface )
    {
        this.aGui = pUserInterface;
        this.printWelcome();
    }//setGUI()
     
  /**
   *  procédure creant toutes les pièces (Rooms) qui sont 
   *  présentes dans le jeu et leurs caractéristiques.
   *  Elle indique également le lieu de départ
   */
  private void createRooms()
  {
    // On créer ici toutes les pièces du jeu  
    Room vLabo = new Room("Le laboratoire : C’est un laboratoire où des scientifiques travaillaient avant l’évacuation de la station.", "Images/Labo.jpg");
    this.aRandomizer.addRoom(vLabo);
    
    Room vStase = new Room("La chambre de Stase : La salle où je me suis réveillé. C’est une salle créée afin de maintenir en état de stase, idéal pour les voyages spatiaux.", "Images/ChambreStase.jpg");
    this.aRandomizer.addRoom(vStase);
    
    Room vConduit = new Room("Un conduit d'aération : Conduit conçu pour changer l’air des pièces, c'est très étroit.", "Images/Aeration.jpg");
    this.aRandomizer.addRoom(vConduit);
    
    Room vControle = new Room("La salle de Controle : C’est une salle s’occupant de l’entièreté du bloc science.", "Images/Controle.jpg");
    this.aRandomizer.addRoom(vControle);
    
    Room vHall = new Room("Le Hall : Zone sociale de la station mais elle est vide et silencieuse", "Images/Hall.jpg");
    this.aRandomizer.addRoom(vHall);
    
    Room vHangar = new Room("Le Hangar : C’est ici où sont stockés les vaisseaux arrivants, il reste un vaisseau que je pourrait réparer pour sortir d'ici." , "Images/Hangar.jpg");
    this.aRandomizer.addRoom(vHangar);
    
    Room vVaisseau = new Room("Le vaisseau : Je peux enfin sortir d'ici, il me faut juste une clé", "Images/Vaisseau.jpg");
    this.aRandomizer.addRoom(vVaisseau);
    
    Room vHabitations = new Room("La zone des habitations : C’est l’entrée du bloc où vivaient les résidents.", "Images/Habitations.jpg");
    this.aRandomizer.addRoom(vHabitations);
    
    Room vQuartiers = new Room("Les quartiers d'équipages : C’est l’endroit où dormaient les résidents de la station.", "Images/Quartiers.jpg");
    this.aRandomizer.addRoom(vQuartiers);
    
    Room vServeurs = new Room("La salle des serveurs : Salle s’occupant de toute l'infrastructure informatique de la station.", "Images/Serveurs.jpg");
    this.aRandomizer.addRoom(vServeurs);
    
    Room vChambre = new Room("La chambre de Jones : C’est ma chambre, je peux peut être trouver des affaires.", "Images/ChambreJones.jpg");
    this.aRandomizer.addRoom(vChambre);
    
    //pièce spéciale :
    TransporterRoom vSecret = new TransporterRoom("Cette endroit est étrange","Images/secret.jpg");
    vSecret.setExit("sud",vStase);
    
    //ici on indique la position des lieux dans le plan
    vLabo.setExit("est",vStase);
    
    vStase.setExit("ouest",vLabo);
    vStase.setExit("sud",vConduit);
    vStase.setExit("est",vHall);
    vStase.setExit("nord",vSecret);
    
    vConduit.setExit("sud",vControle);
    vConduit.setExit("nord",vStase);
    vConduit.setExit("tomber",vHall);
    
    vControle.setExit("nord",vConduit);
    
    vHall.setExit("ouest",vStase);
    vHall.setExit("nord",vHangar);
    vHall.setExit("est",vHabitations);
    
    vHangar.setExit("nord",vVaisseau);
    vHangar.setExit("sud",vHall);
    
    vVaisseau.setExit("sud",vHangar);
    
    vHabitations.setExit("ouest",vHall);
    vHabitations.setExit("haut",vQuartiers);
    vHabitations.setExit("bas",vServeurs);
    
    vServeurs.setExit("haut",vHabitations);
    
    vQuartiers.setExit("bas",vHabitations);
    vQuartiers.setExit("est",vChambre);
    
    vChambre.setExit("ouest",vQuartiers);
    
    //enfin on initialise le lieu de départ.
    this.aPlayer.setCurrentRoom(vStase);
    
    // on créer les items :
    Item vPiedDeBiche = new Item("pied_de_biche", 10);
    Item vKey1 = new Item("clé_labo", 0.1);
    Item vKey2 = new Item("clé_habitations", 0.2);
    Item vOrdi = new Item("ordinateur", 15);
    Item vKit = new Item("kit_de_réparation", 25);
    Item vKeyVaisseau = new Item("clé_vaisseau", 1);
    Item vCookie = new Item("magic_cookie",1);
    Beamer vBeamer = new Beamer("transporteur_de_poche", 20);
    
    //On atribue les items à une pièce.
    vStase.addItem(vPiedDeBiche);
    vStase.addItem(vKey1);
    vStase.addItem(vCookie);
    vStase.addItem(vBeamer);
    vHall.addItem(vKey2);
    vHabitations.addItem(vKeyVaisseau);
    vControle.addItem(vOrdi);
    vHangar.addItem(vKit);
    
  }//createRooms()
  
  /**
     * procédure s'occupant de la commande "go"
     */  
  private void goRoom(final Command pCommand){
      
      //on commence par vérifié si le mot éxiste
      if( !pCommand.hasSecondWord()){
          this.aGui.println("va où ?");
          return;
      }
      
      String vDirection = pCommand.getSecondWord();
      // on vérifie mainenant la direction 
      Room vNextRoom = this.aPlayer.getCurrentRoom().getExit(vDirection);
      
      if(vNextRoom == null){
          
         this.aGui.println("Il n'y a pas de porte !"); 
        }
      else{
          if(this.CheckTime()){
              this.aGui.println("Le temps est dépassé, relancez le jeu !"); 
          }else
            
              if(vNextRoom instanceof TransporterRoom){
              vNextRoom = this.aRandomizer.getRandomRoom(); 
              this.aPlayer.movePlayer(vNextRoom);
              this.printLocationInfo();
              this.showRoom();
              this.aActualTime += 1;  
          
            }else{
              this.aPlayer.movePlayer(vNextRoom);
              this.printLocationInfo();
              this.showRoom();
              this.aActualTime += 1;
            }
    
       
      }
      
  }//goRoom()
  
  /**
     * S'occupe de vérifier que le temps n'est pas dépassé.
     */  
  private boolean CheckTime()
  {
    if(this.aActualTime >= this.aLimitTime){
        return true;
    }else{
        return false;
    }
  }

  /**
     * C'est le message d'acceuil que va afficher le jeu lors du lancement de celui_ci.
     */  
  private void printWelcome()
  {
      this.aGui.println("Bienvenue dans Wrong place, un jeu basé sur Zuul!");
      this.aGui.println("Vous vous appellez Jones et vous venez tout juste de vous réveiller de votre module de Stase.");
      this.aGui.println("taper 'aide' pour obtnenir de l'aide.");
      this.aGui.println("");
      
      this.printLocationInfo();
      this.showRoom();
      }//printWelcome()
  
      /**
     * C'est le message d'acceuil que va afficher le jeu lors du lancement de celui_ci.
     */  
  private void showRoom()
  {
      if ( this.aPlayer.getCurrentRoom().getImageName() != null )
            this.aGui.showImage( this.aPlayer.getCurrentRoom().getImageName() );
  }//showRoom()
      
    /**
     * C'est le message d'aide qu'affiche le jeu 
     */  
  private void printHelp()
  {
     this.aGui.println("Vous vous demandez comment vous êtes arrivés ici.");
     this.aGui.println("vous cherchez à vous échapper");
     this.aGui.println("");
     this.aGui.println("Les commandes sont:");
     this.ShowCommands();
     this.aGui.println("Utilisez la commande 'chercher Item' pour trouver des objets");
  }//printHelp()
  
    /**
     * c'est une methode verifiant si l'utilisateur souhaite réelement quitter le jeu 
     */  
  private boolean quit(final Command pCommand)
  {                      
    if(pCommand.hasSecondWord()){
        this.aGui.println("Quitter quoi ?");
        return false;
    }else{
        return true;
    }
      
  }//quit()
  
  /**
   *  Methode qui analyse quelle commande correspond ce qu'on a écrit.
   */
  private boolean processCommand(final String pCommand) 
  { 
    this.aGui.println( "> " + pCommand );
        Command vCommand = this.aParser.getCommand( pCommand );
        
      if(vCommand.isUnknown() ){ 
        this.aGui.println("Je ne comprend pas ce que vous voulez dire...");
        return false;
        }
    else if(vCommand.getCommandWord().equals("va")){
        this.goRoom(vCommand);
        
    }else if(vCommand.getCommandWord().equals("aide")){
        this.printHelp();
        
    }else if(vCommand.getCommandWord().equals("quitter") ){
        if ( vCommand.hasSecondWord() )
                this.aGui.println( "quitter quoi?" );
            else
                this.endGame();
    }else if(vCommand.getCommandWord().equals("chercher") ){
        if(vCommand.hasSecondWord()){
        if ( vCommand.getSecondWord().equals("item") ){
            this.lookItem();
            }else{
            this.aGui.println( "examiner quoi?" );
            }
        }else{
                this.look();
            }
    }else if(vCommand.getCommandWord().equals("manger") ){
        this.eat(vCommand);
    }else if(vCommand.getCommandWord().equals("revenir") ){
        if ( vCommand.hasSecondWord() ){
                this.aGui.println( "revenir vers quoi?" );
            }else{
                this.back();
        }
            
    }else if(vCommand.getCommandWord().equals("test") ){
        if(vCommand.hasSecondWord()){
            try {
            String vFileName = vCommand.getSecondWord();
            File vTextFile = new File("Test/" +vFileName +".txt");
            Scanner vRead = new Scanner(vTextFile);
            while(vRead.hasNextLine()){
                String vTestCommand = vRead.nextLine();
                this.processCommand( vTestCommand );
            }
            }catch (java.io.IOException e){
                
            }
        }else{
           this.aGui.println("test quoi?");
        }
    
    }else if(vCommand.getCommandWord().equals("prendre") ){
        
        this.take(vCommand);
        
    }else if(vCommand.getCommandWord().equals("abandonner") ){
        
        this.drop(vCommand);
        
    }else if(vCommand.getCommandWord().equals("inventaire") ){
        if(vCommand.hasSecondWord()){
            this.aGui.println("inventaire de quoi?");
        }else{
            this.aGui.print("le poids actuel de votre inventaire est de ");
            this.aGui.println(this.aPlayer.getPlayerWeight() + "kilos");
            this.aGui.println(this.aPlayer.getPlayerInventory());
            this.aGui.print("le poids limite est de ");
            this.aGui.println(this.aPlayer.getMaxWeight() + "kilos");
        }
        
    }else if(vCommand.getCommandWord().equals("charger") ){
            
        this.charge(vCommand);
            
    }else if(vCommand.getCommandWord().equals("utiliser") ){
        
        this.use(vCommand);
        
    }else if(vCommand.getCommandWord().equals("alea") ){
        this.aGui.print("pièce qui est généré aléatoriement : ");
        this.aGui.print(this.aRandomizer.getRandomRoom().getDescription());
        this.aGui.print("Ceci est un exemple vous n'êtes pas dans cette pièce ");
    }
    
        return false;
    
 }//processCommand()
 
 /**
   *  Methode qui permet de charger un item de type beamer.
   */
  private void charge(final Command pCommand){
      if(pCommand.hasSecondWord()){
            Beamer vBeamer = this.beamerVerif(pCommand.getSecondWord());
            if(vBeamer!=null){
              vBeamer.setRoomToTeleport(this.aPlayer.getCurrentRoom());
              vBeamer.setCharge(true);
              this.aGui.println("le téléporteur est désormais chargé");
          }
        }else{
           this.aGui.println("charger quoi?"); 
        }
    }//charge()
      
 /**
   *  Methode qui permet d'utiliser un item de type beamer.
   */
  private void use(final Command pCommand){
         if(pCommand.hasSecondWord()){
          Beamer vBeamer = this.beamerVerif(pCommand.getSecondWord());
          
          if(vBeamer!=null){
              if(vBeamer.getCharge()){
          this.aPlayer.movePlayer(vBeamer.getRoomToTeleport());
          this.aGui.println("vous vous êtes téléporté grâce au téléporteur");
          this.printLocationInfo();
          this.showRoom();
          this.aActualTime += 1;
          this.aPlayer.clearPreviousRooms();
          vBeamer.setCharge(false);
            }else{
                  this.aGui.println("le transporteur n'est pas chargé"); 
              }
          }
        }else{
           this.aGui.println("utliser quoi?"); 
        }
    }//use()
    
    /**
   *  Methode qui permet d'éviter la dupplication de code dans use() et charge() 
   *  et vérifie la présence du téléporteur dans l'inventaire du joueur
   */
  private Beamer beamerVerif(final String pItem){
      
      Item vItem = this.aPlayer.searchInventory(pItem);
    
      if(vItem==null){
          this.aGui.println("vous n'avez pas de transporteur/téléporteur");
          return null;
      }else{
         if(vItem instanceof Beamer){
             return (Beamer)vItem;
         }else{
             this.aGui.println("cet item n'est pas un transporteur/téléporteur");
             return null;
         }
         
      }
    }//Beamerverif()
    
      
  /**
   *  Methode qui permet de revenir en arrière.
   */
  private void back(){
          if(this.aPlayer.isPreviousRoomEmpty()){
                    this.aGui.println( "Vous êtes revenus au point de départ" );
                }else 
                if(this.aPlayer.getCurrentRoom().isExit(this.aPlayer.getPreviousRoom()))
                {
                this.aPlayer.movePlayerBack();
                this.printLocationInfo();
                this.showRoom();
                }
                 else
                {
                this.aPlayer.clearPreviousRooms();
                this.aGui.println( "Vous êtes tombé, vous ne pouvez pas revenir en arrière" );
            }
            
  }//back()
  
  /**
   *  Methode qui permet de prendre un objet.
   */
  private void take(final Command pCommand){
        if(pCommand.hasSecondWord()){
            String vItemName = pCommand.getSecondWord();
            Item vItem = this.aPlayer.getCurrentRoom().searchRoomItem(vItemName);
            if(vItem !=null){
                this.aPlayer.addWeight(vItem.getItemWeight());
                if(this.aPlayer.isWeightGood()){
                this.aPlayer.playerTake(vItem);
                this.aPlayer.getCurrentRoom().removeItem(vItem);
                this.aGui.println( "vous avez pris l'objet"  );
                }else{
                    this.aPlayer.removeWeight(vItem.getItemWeight());
                    this.aGui.println( "vous ne pouvez pas prendre l'objet, vous êtes surchargés"  );
                }
            }else{
            this.aGui.println("l'objet n'existe pas"); 
            }
        }else{
            this.aGui.println( "prendre quoi?");
        }
  }//take()
  
  /**
   *  Methode qui permet de lâcher un objet.
   */
  private void drop(final Command pCommand){
       if(pCommand.hasSecondWord()){
            String vItemName = pCommand.getSecondWord();
            Item vItem = this.aPlayer.searchInventory(vItemName);
            if(vItem !=null){
            this.aPlayer.playerDrop(vItem);
            this.aPlayer.getCurrentRoom().addItem(vItem);
            this.aPlayer.removeWeight(vItem.getItemWeight());
            this.aGui.println( "vous avez abandonné l'objet"  );
            }else{
            this.aGui.println("l'objet n'existe pas"); 
            }
        }else{
            this.aGui.println( "abandonner quoi?");
        }   
  }//drop()
  
  /**
     * procédure s'occupant des informations des lieux créer afin
     * de supprimer une duplication de code dans cette classe.
     */    
  private void printLocationInfo()
  {
      this.aGui.println( this.aPlayer.getCurrentRoom().getLongDescription()  );
      this.aGui.println( "L'image de la pièce est " + this.aPlayer.getCurrentRoom().getImageName() );
  }//printLocationInfo()
  
  /**
     * Cette procédure retourne la description de la pièce.
     */ 
  private void look()
  {
      this.printLocationInfo();
  }// look()
  
  /**
     * Cette procédure retourne la description des objets de la pièce.
     */ 
  private void lookItem()
  {
      this.aGui.println( this.aPlayer.checkItems() );
  }// lookItem()
  
  /**
     * Cette procédure s'occupe de la commande manger
     */ 
  private void eat(final Command pCommand)
  {
      if(!pCommand.hasSecondWord())
    {
        this.aGui.println("manger quoi?");
        return;
    }   
    String vItem = pCommand.getSecondWord();
    Item vEatItem = this.aPlayer.searchInventory(vItem);

    if(vEatItem == null){
        this.aGui.println("I don't have it !");
    }
    else if(vEatItem.getItemDescription().equals("magic_cookie")){
        this.aPlayer.getItemList().removeInventoryItem(vEatItem);
        this.aPlayer.removeWeight(vEatItem.getItemWeight());
        this.aPlayer.changeMaxWeight(100);
        this.aGui.println("Vous avez manger le cookie mysterieux");
    }
    else{
       this.aGui.println("Ce n'est pas vraiment comestible..."); 
    }
  }// eat()
  
  /**
     * Cette procédure retourne toutes les commandes valides du jeu.
     */ 
  private void ShowCommands()
  {
      this.aGui.println(aCommand.getCommandList());
  }// ShowCommands()
  
  /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    public void interpretCommand( final String pCommandLine ) 
    {
        this.processCommand(pCommandLine);
    }//interpretCommand()
    
  /**
     * met fin au terminal permettant de rentrer des commandes
     */
  private void endGame()
    {
        this.aGui.println( "Merci d'avoir joué à Wrong Place (test edition).  Au revoir ;)." );
        this.aGui.enable( false );
    }//endGame()
  
} // Game


