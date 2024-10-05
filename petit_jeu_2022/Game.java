/**
 *  C'est la classe qui permet de lancer et initiliser le jeu.
 * @author  Michael Kolling and David J. Barnes + BRAUX Owen
 * @version 2.0 (Jan 2003) DB edited (2019) 
 */

public class Game
{
    private UserInterface aGui;
    private GameEngine aEngine;

    /**
     * Créer et initialise le jeu.
     */
    public Game() 
    {
        this.aEngine = new GameEngine();
        this.aGui = new UserInterface( this.aEngine );
        this.aEngine.setGUI( this.aGui );
    }
}
