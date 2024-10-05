import java.util.StringTokenizer;

/**
 * Classe Parser qui s'occupe des commandes du jeu.
 * 
 * @author  Michael Kolling and David J. Barnes + D.Bureau + BRAUX Owen
 * @version 2022
 */
public class Parser 
{
    

    private CommandWords aCommandWords;  // holds all valid command words

    /**
     * Créer une classe Parser
     */
    public Parser() 
    {
        this.aCommandWords = new CommandWords();
    } // Parser()

    /**
     * Obtient une commande de l'utilisateur. cette commande est lu par
     * l' 'inputLine'.
     */
    public Command getCommand( final String pInputLine ) 
    {
        String vWord1;
        String vWord2;

        StringTokenizer tokenizer = new StringTokenizer( pInputLine );

        if ( tokenizer.hasMoreTokens() )
            vWord1 = tokenizer.nextToken();      // premier mot 
        else
            vWord1 = null;

        if ( tokenizer.hasMoreTokens() )
            vWord2 = tokenizer.nextToken();      // deuxième mot
        else
            vWord2 = null;

        // note: on ignore le reste de l'input line.

        // On vérifie si le mot existe et si oui, on créer une commande de ce mot
        // Pour le cas contraire, on créer une commande "null" (unknown command).
        
        
        //System.out.println("DEBUG1" + vWord1 + vWord2); 

        if ( this.aCommandWords.isCommand( vWord1 ) ) 
            return new Command( vWord1, vWord2 );
        else
            return new Command( null, vWord2 );
    } // getCommand(.)

    /**
     * Donne une String d'une commande valide
     */
    public String getCommandString() 
    {
        return this.aCommandWords.getCommandList();
    } // getCommandString()

   
    
    
} // Parser