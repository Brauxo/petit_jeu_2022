import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.awt.image.*;
import javax.swing.JPanel;

/**
 * This class implements a simple graphical user interface with a text entry
 * area, a text output area and an optional image.
 * 
 * @author Michael Kolling
 * @version 1.0 (Jan 2003) DB edited (2019) + BRAUX Owen (2022)
 */
public class UserInterface implements ActionListener
{
    private GameEngine aEngine;
    private JFrame     aMyFrame;
    private JTextField aEntryField;
    private JTextArea  aLog;
    private JLabel     aImage;

    /**
     * Construct a UserInterface. As a parameter, a Game Engine
     * (an object processing and executing the game commands) is
     * needed.
     * 
     * @param gameEngine  The GameEngine object implementing the game logic.
     */
    public UserInterface( final GameEngine pGameEngine )
    {
        this.aEngine = pGameEngine;
        this.createGUI();
    } // UserInterface(.)

    
    
    /**
     * Print out some text into the text area.
     */
    public void print( final String pText )
    {
        this.aLog.append( pText );
        this.aLog.setCaretPosition( this.aLog.getDocument().getLength() );
    } // print(.)

    /**
     * Print out some text into the text area, followed by a line break.
     */
    public void println( final String pText )
    {
        this.print( pText + "\n" );
    } // println(.)

    /**
     * Show an image file in the interface.
     */
    public void showImage( final String pImageName )
    {
        String vImagePath = "" + pImageName; // to change the directory
        URL vImageURL = this.getClass().getClassLoader().getResource( vImagePath );
        if ( vImageURL == null )
            System.out.println( "Image non trouvé : " + vImagePath );
        else {
            ImageIcon vIcon = new ImageIcon( vImageURL );
            this.aImage.setIcon( vIcon );
            this.aMyFrame.pack();
        }
    } // showImage(.)

    /**
     * Enable or disable input in the input field.
     */
    public void enable( final boolean pOnOff )
    {
        this.aEntryField.setEditable( pOnOff ); // enable/disable
        if ( ! pOnOff ) { // disable
            this.aEntryField.getCaret().setBlinkRate( 0 ); // cursor won't blink
            this.aEntryField.removeActionListener( this ); // won't react to entry
        }
    } // enable(.)

    /**
     * Set up graphical user interface.
     */
    private void createGUI()
    {
        this.aMyFrame = new JFrame( "Wrong Place" ); 
        this.aEntryField = new JTextField( 40 );

        this.aLog = new JTextArea();
        this.aLog.setEditable( false );
        JScrollPane vListScroller = new JScrollPane( this.aLog );
        vListScroller.setPreferredSize( new Dimension(800, 200) );
        vListScroller.setMinimumSize( new Dimension(400,100) );

        JPanel vPanel = new JPanel();
        this.aImage = new JLabel();

        // on initialise nos boutons        
        JButton vButton1 = new JButton("va nord");
        JButton vButton2 = new JButton("va est");
        JButton vButton3 = new JButton("va ouest");
        JButton vButton4 = new JButton("va sud");
        JButton vButton5 = new JButton("aide");
        JButton vButton6 = new JButton("va haut");
        JButton vButton7 = new JButton("va bas");
        JButton vButton8 = new JButton("quitter");
        
        // On ajoute notre série de boutons numéro 1 à un interfaces spécial pour eux.
        JPanel vPanel2 = new JPanel();
        vPanel2.setLayout( new BorderLayout() );
        vPanel2.add(vButton1, BorderLayout.NORTH);
        vPanel2.add(vButton2, BorderLayout.EAST);
        vPanel2.add(vButton3, BorderLayout.WEST);
        vPanel2.add(vButton4, BorderLayout.SOUTH);
        vPanel2.add(vButton5, BorderLayout.CENTER);
        
        // On ajoute notre série de boutons numéro 2 à un interfaces spécial pour eux.
        JPanel vPanel3 = new JPanel();
        vPanel3.setLayout( new BorderLayout() );
        vPanel3.add(vButton6, BorderLayout.NORTH);
        vPanel3.add(vButton8, BorderLayout.CENTER);
        vPanel3.add(vButton7, BorderLayout.SOUTH);
    
        //Cette interface rajoute un Pnnel s'occupant de tout les boutons.
        JPanel vPanel4 = new JPanel();
        vPanel4.setLayout( new BorderLayout() );
        vPanel4.add( vPanel2, BorderLayout.WEST );
        vPanel4.add( vPanel3, BorderLayout.CENTER );
        
        JPanel vPanel4plus = new JPanel();
        vPanel4plus.setLayout( new BorderLayout() );
        vPanel4plus.add( vPanel4, BorderLayout.CENTER );
        
        //Cette interface s'occupe de créer une fenetre textuelle
        JPanel vPanel5 = new JPanel();
        vPanel5.setLayout( new BorderLayout() );
        vPanel5.add( vListScroller, BorderLayout.NORTH );
        vPanel5.add( this.aEntryField, BorderLayout.SOUTH );
        
        
        
        //fenêtre finale
        vPanel.setLayout( new BorderLayout() ); // ==> seulement 5 places
        vPanel.add( this.aImage, BorderLayout.NORTH );
        vPanel.add( vPanel5, BorderLayout.EAST );
        vPanel.add( vPanel4, BorderLayout.SOUTH );
        
        
        


        this.aMyFrame.getContentPane().add( vPanel, BorderLayout.CENTER );

        // add some event listeners to some components
        this.aEntryField.addActionListener( this );
        vButton1.addActionListener( this );
        vButton2.addActionListener( this );
        vButton3.addActionListener( this );
        vButton4.addActionListener( this );
        vButton5.addActionListener( this );
        vButton6.addActionListener( this );
        vButton7.addActionListener( this );
        vButton8.addActionListener( this );
        

        
        
        // to end program when window is closed
        this.aMyFrame.addWindowListener( new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        } );

        this.aMyFrame.pack();
        this.aMyFrame.setVisible( true );
        this.aEntryField.requestFocus();
    } // createGUI()

    /**
     * Actionlistener interface for entry textfield.
     */
    public void actionPerformed( final ActionEvent pE ) 
    {
        
        if(pE.getSource().equals(this.aEntryField)){
        // no need to check the type of action at the moment
        // because there is only one possible action (text input) :
        this.processCommand(); // never suppress this line
        
        }else{
        this.aEntryField.setText( "" );
        this.aEngine.interpretCommand( pE.getActionCommand() );
        }
    } // actionPerformed(.)

    /**
     * A command has been entered. Read the command and do whatever is 
     * necessary to process it.
     */
    private void processCommand()
    {
        
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText( "" );
        this.aEngine.interpretCommand( vInput );

    } // processCommand()
} // UserInterface

