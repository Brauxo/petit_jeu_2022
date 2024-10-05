 /**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration table of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kolling and David J. Barnes + D.Bureau + BRAUX Owen
 * @version 2022
 */
public class CommandWords
{
    // a constant array that will hold all valid command words
    private final String[] aValidCommands;
    // final : pour indiquer qu'on ne modifiera pas cet attribut après son initialisation.
        // [] : pour indiquer qu'on ne veut pas une seule String mais plusieurs !
    
    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        this.aValidCommands = new String[13];
        this.aValidCommands[0] = "va";
        this.aValidCommands[1] = "aide";
        this.aValidCommands[2] = "quitter";
        this.aValidCommands[3] = "chercher";
        this.aValidCommands[4] = "manger";
        this.aValidCommands[5] = "revenir";
        this.aValidCommands[6] = "test";
        this.aValidCommands[7] = "prendre";
        this.aValidCommands[8] = "abandonner";
        this.aValidCommands[9] = "inventaire";
        this.aValidCommands[10] = "charger";
        this.aValidCommands[11] = "utiliser";
        this.aValidCommands[12] = "alea";
        
    } // CommandWords()

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand( final String pString )
    {
        for ( int vI=0; vI<this.aValidCommands.length; vI++ ) {
            if ( this.aValidCommands[vI].equals( pString ) )
                return true;
        } // for
        // if we get here, the string was not found in the commands :
        return false;
    } // isCommand()
    
    /**
     * Affiche toutes les commadnes valides 
     */
    public String getCommandList()
    {
        String vFinal = "";
        for(String vCommand : aValidCommands)
        {
            vFinal += vCommand + " | ";
        }
        return vFinal;
    }//showAll()
    
} // CommandWords
