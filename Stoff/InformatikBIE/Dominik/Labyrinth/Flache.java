import javax.swing.*;
import java.awt.*;
/**
 * Klasse, in die das Labyrinth gezeichnet wird. 
 */
public class Flache extends JPanel
{

    Fenster f;
    /**
     * Konstruktor
     * @param f Fenster, das dieses Objekt enthält.
     */
    public Flache(Fenster f)
    {
        this.f=f;
        //setPreferredSize(new Dimension(500,500));
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        f.paintMain(g);
        
    }
}
