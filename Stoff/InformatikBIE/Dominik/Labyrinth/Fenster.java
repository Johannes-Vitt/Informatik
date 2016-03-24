import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;
/**
 * Hauptklasse, das Fenster. Die Klasse zeichnet das Labyrinth auf panel.
 */
public class Fenster extends JFrame implements ActionListener, KeyListener, MouseListener
{
    Flache panel;
    
    JButton buttonTop, buttonGenerate, button3D, buttonForward, buttonBackward, buttonLeft,
        buttonRight;
    
    Image exit, linkeWand, rechteWand, geradeWand, halbeWand, linkerExit, rechterExit;
        
    Labyrinth maze;
    /**
     * Position und Ausrichtung des Spielers
     */
    int x, y, dir;
    /**
     * Ansicht des Feldes (Vogelperspektive oder 3D-Ansicht)
     */
    boolean topView = true;
    /**
     * Ist true, wenn der Spieler gewonnen hat
     */
    boolean gewonnen = false;
    
    int schritte = 0;
    long start, end;
    
    
    /**
     * Konstruktor: Initialisiert und öffnet Fenster 
     */
    public Fenster()
    {
        super("Labyrinth");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new Flache(this);
        panel.setBounds(12,12,600,600);
        panel.addMouseListener(this);
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(935, 627));
        setContentPane(p);
        dir=3;
        getContentPane().setLayout(null);
        pack();
        getContentPane().add(panel);
        setResizable(false);
        maze = new Labyrinth();
        
        exit = loadImage("exit.jpg");
        linkeWand = loadImage("linkewandmitte.png");
        rechteWand = loadImage("rechtewandmitte.png");
        geradeWand = loadImage("geradeansicht.png");
        halbeWand = loadImage("rechtehalb.png");
        linkerExit = loadImage("linkeexit.png");
        rechterExit = loadImage("rechteexit.png");
        
        buttonTop = new JButton("Draufsicht");
        buttonTop.setBounds(618,12,145,23);
        buttonTop.addActionListener(this);
        getContentPane().add(buttonTop);
        
        buttonGenerate = new JButton("Neu Starten");
        buttonGenerate.setBounds(618,588,305,23);
        buttonGenerate.addActionListener(this);
        getContentPane().add(buttonGenerate);
        
        button3D = new JButton("3D Ansicht");
        button3D.setBounds(778,12,145,23);
        button3D.addActionListener(this);
        getContentPane().add(button3D);
        
        buttonForward = new JButton("Vor");
        buttonForward.setBounds(733,92,75,23);
        buttonForward.addActionListener(this);
        getContentPane().add(buttonForward);
        
        buttonBackward = new JButton("Zurück");
        buttonBackward.setBounds(733,150,75,23);
        buttonBackward.addActionListener(this);
        getContentPane().add(buttonBackward);
        
        buttonLeft = new JButton("Links");
        buttonLeft.setBounds(688,121,75,23);
        buttonLeft.addActionListener(this);
        getContentPane().add(buttonLeft);
        
        buttonRight = new JButton("Rechts");
        buttonRight.setBounds(778,121,75,23);
        buttonRight.addActionListener(this);
        getContentPane().add(buttonRight);
        
        this.addKeyListener(this);
        setFocusable(true);
        
        buttonTop.setEnabled(false);
        start = System.currentTimeMillis();
        
        setVisible(true);
        repaint();
    }
    
    Image loadImage(String name)
    {
        Image img = null;
        try
        {
            img = ImageIO.read(new File(name));
        } catch(IOException e)
        {
            System.out.println(e);
        }
        return img;
    }
    
    /**
     * Vorwärtsbewegung des Spielers
     */
    void forward()
    {
        
        if(x==19&&y==19&&dir==2)
        {
            gewonnen = true;
        }
        if (maze.hasNeighbour(x, y, maze.dirs[dir]) && !maze.hasWall(x, y, maze.dirs[dir]))
        {
            if (dir == 0) y--;
            if (dir == 1) x++;
            if (dir == 2) y++;
            if (dir == 3) x--;
        }
    }
    /**
     * Drehung nach rechts
     * @param n Anzahl der Drehungen
     */
    void turn(int n)
    {
        dir = ((dir + n) % 4 + 4) % 4;
    }
    /**
     * Rückwärtsbewegung des Spielers
     */
    void backward()
    {
        turn(2);
        forward();
        turn(2);
    }
    /**
     * Zeichnet in das panel. Wird von der Klasse Flache aus aufgerufen.
     * @param g Graphics Objekt zum Zeichnen auf panel
     */
    public void paintMain(Graphics g)
    {
        try
        {
            if(!gewonnen)
            {
                g.clearRect(0,0,getWidth(),getHeight());
                if(topView)
                    drawTopView(g);
                else
                    draw3dView(g);
            }
            else
            {
                g.setColor(Color.RED);
                g.drawString("GEWONNEN",30,30);
                g.setColor(Color.BLACK);
                g.drawString("Schritte: "+schritte,30,45);
                if(end==0) end = System.currentTimeMillis();
                int diff=(int)(end-start)/1000;
                g.drawString("Zeit: "+diff+"s",30,60);
            }
        }
        catch(Exception e){}
    }
    /**
     * Zeichnet 3D-Ansicht
     * @param g Graphics Objekt zum Zeichnen auf panel
     */
    void draw3dView(Graphics g)
    {
        int depth = 0;
        float height = panel.getHeight() / 6f;
        float width = panel.getWidth() / 6f;
        float x1 = 0;
        float x2 = panel.getWidth()-1;
        float y1 = 0, y2 = panel.getHeight()-1;
        g.setColor(Color.lightGray);
        g.fillRect(0,panel.getHeight()/2,panel.getWidth(),panel.getHeight()/2);
        g.setColor(Color.BLACK);
        
        
        while (true)
        {

            //Senkrechte Linien
            //g.drawLine(Math.round(x1), Math.round(y1), Math.round(x1), Math.round(y2));
            //g.drawLine(Math.round(x2), Math.round(y1), Math.round(x2), Math.round(y2));
            //g.drawLine(Math.round(x1), Math.round(y1), Math.round(x2), Math.round(y1));
            //g.drawLine(Math.round(x1), Math.round(y2), Math.round(x2), Math.round(y2));

            //Innere Senkrechte Linien
            //g.drawLine(Math.round(x1 + width), Math.round(y1 + height), Math.round(x1 + width), Math.round(y2 - height));
            //g.drawLine(Math.round(x2 - width), Math.round(y1 + height), Math.round(x2 - width), Math.round(y2 - height));
            //g.drawLine(Math.round(x1 + width), Math.round(y1 + height), Math.round(x2 - width), Math.round(y1 + height));
            //g.drawLine(Math.round(x1 + width), Math.round(y2 - height), Math.round(x2 - width), Math.round(y2 - height))
            if (maze.hasWall(x, y, maze.dirs[((dir - 1) % 4 + 4) % 4]))//Wand links
            {
                if(depth>0)
                    g.drawImage(x==19&&y==19&&dir==3?linkerExit:linkeWand, Math.round(x1),Math.round(y1),Math.round(x1+width),Math.round(y2),0,0,101,400,null);
                else
                    g.drawImage(x==19&&y==19&&dir==3?linkerExit:linkeWand, Math.round(x1-width),Math.round(y1-height),Math.round(x1+width),Math.round(y2+height),0,0,101,400,null);
                g.drawLine(Math.round(x1), Math.round(y1), Math.round(x1 + width), Math.round(y1 + height));
                g.drawLine(Math.round(x1), Math.round(y2), Math.round(x1 + width), Math.round(y2 - height));
            }
            else if (maze.hasNeighbour(x, y, maze.dirs[((dir - 1) % 4 + 4) % 4]))//Keine Wand links
            {
                turn(-1);
                forward();
                turn(1);
                if (maze.hasWall(x, y, maze.dirs[dir]))
                {
                    if(depth>0)
                        g.drawImage(halbeWand,Math.round(x1),Math.round(y1+height),Math.round(x1 + width)-Math.round(x1),Math.round(y2 - height)-Math.round(y1+height),null);
                    else
                        g.drawImage(halbeWand,Math.round(x1-width),Math.round(y1+height),Math.round(x1 + width)-Math.round(x1-width),Math.round(y2 - height)-Math.round(y1+height),null);
                    g.drawLine(Math.round(x1), Math.round(y1+height), Math.round(x1 + width), Math.round(y1 + height));
                    g.drawLine(Math.round(x1), Math.round(y2-height), Math.round(x1 + width), Math.round(y2 - height));
                }
                turn(1);
                forward();
                turn(-1);
            }
            if (maze.hasWall(x, y, maze.dirs[((dir + 1) % 4 + 4) % 4]))//Wand rechts
            {
                if(depth>0)
                    g.drawImage(x==19&&y==19&&dir==1?rechterExit:rechteWand,Math.round(x2-width),Math.round(y1),Math.round(x2),Math.round(y2),0,0,101,400,null);
                else
                    g.drawImage(x==19&&y==19&&dir==1?rechterExit:rechteWand,Math.round(x2-width),Math.round(y1-height),Math.round(x2+width),Math.round(y2+height),0,0,101,400,null);
                g.drawLine(Math.round(x2), Math.round(y1), Math.round(x2 - width), Math.round(y1 + height));
                g.drawLine(Math.round(x2), Math.round(y2), Math.round(x2 - width), Math.round(y2 - height));
            }
            else if (maze.hasNeighbour(x, y, maze.dirs[((dir + 1) % 4 + 4) % 4]))//Keine Wand rechts
            {
                turn(1);
                forward();
                turn(-1);
                if (maze.hasWall(x, y, maze.dirs[dir]))
                {
                    if(depth>0)
                        g.drawImage(halbeWand,Math.round(x2),Math.round(y1+height),Math.round(x2 - width)-Math.round(x2),Math.round(y2 - height)-Math.round(y1+height),null);
                    else
                        g.drawImage(halbeWand,Math.round(x2-width),Math.round(y1+height),Math.round(x2 + width)-Math.round(x2-width),Math.round(y2 - height)-Math.round(y1+height),null);
                    g.drawLine(Math.round(x2), Math.round(y1 + height), Math.round(x2 - width), Math.round(y1 + height));
                    g.drawLine(Math.round(x2), Math.round(y2 - height), Math.round(x2 - width), Math.round(y2 - height));
                }
                turn(-1);
                forward();
                turn(1);
            }

            /*if(x==19&&y==19)//Ausgang
            {
                g.setColor(Color.RED);
                if(dir==2)
                {
                    g.fillRect(Math.round(x1 + width),Math.round(y1 + height),Math.round(x2 - width)-Math.round(x1 + width),Math.round(y2 - height)-Math.round(y1 + height));
                    //g.drawImage(exit,Math.round(x1 + width)+(Math.round(x2 - width)-Math.round(x1 + width))/2-150/2,Math.round(y2-height-261), null);
                }
                else if(dir==3)
                {
                    g.fillPolygon(new int[]{Math.round(x1),Math.round(x1 + width),Math.round(x1 + width),Math.round(x1)},new int[]{Math.round(y1),Math.round(y1 + height),Math.round(y2 - height),Math.round(y2)},4);
                }
                else if(dir==1)
                {
                    g.fillPolygon(new int[]{Math.round(x2),Math.round(x2 - width),Math.round(x2 - width),Math.round(x2)},new int[]{Math.round(y1),Math.round(y1 + height),Math.round(y2 - height),Math.round(y2)},4);
                }
                g.setColor(Color.BLACK);
            }*/
            
            if (maze.hasWall(x, y, maze.dirs[dir]))//Wand direkt vor dir
            {

                g.drawLine(Math.round(x1 + width), Math.round(y1 + height), Math.round(x2 - width), Math.round(y1 + height));
                g.drawLine(Math.round(x1 + width), Math.round(y2 - height), Math.round(x2 - width), Math.round(y2 - height));
                    
                g.drawImage(x==19&&y==19&&dir==2?exit:geradeWand,Math.round(x1+width),Math.round(y1+height),Math.round(x2-width),Math.round(y2-height),0,0,450,450,null);
                while (depth > 0)
                {
                    backward();
                    depth--;
                }
                break;
            }
            else
            {
                x1 += width;
                x2 -= width;
                y1 += height;
                y2 -= height;
                if (depth > 0)
                {
                    height /= 2;
                    width /= 2;
                }
                depth++;
                forward();
            }
        }
    }
    /**
     * Zeichnet die Vogelperspektive
     * @param g Graphics Objekt zum Zeichnen auf panel
     */
    void drawTopView(Graphics g)
    {
        g.setColor(Color.black);
        float cellWidth = (panel.getWidth() - 1) / (float)maze.maze.length;
        float cellHeight = (panel.getHeight() - 1) / (float)maze.maze[0].length;
        for (int x = 0; x < maze.maze.length; x++)
        {
            for (int y = 0; y < maze.maze[0].length; y++)
            {
                if ((maze.maze[x][y] & maze.top) != 0)
                {
                    g.drawLine(Math.round(cellWidth * x), Math.round(cellHeight * y), Math.round(cellWidth * (x + 1)), Math.round(cellHeight * y));
                }
                if ((maze.maze[x][y] & maze.left) != 0)
                {
                    g.drawLine(Math.round(cellWidth * x), Math.round(cellHeight * y), Math.round(cellWidth * x), Math.round(cellHeight * (y + 1)));
                }
                if ((maze.maze[x][y] & maze.bottom) != 0)
                {
                    if(x<maze.maze.length-1||y<maze.maze[x].length-1)
                        g.drawLine(Math.round(cellWidth * x), Math.round(cellHeight * (y + 1)), Math.round(cellWidth * (x + 1)), Math.round(cellHeight * (y + 1)));
                }
                if ((maze.maze[x][y] & maze.right) != 0)
                {
                    g.drawLine(Math.round(cellWidth * (x + 1)), Math.round(cellHeight * y), Math.round(cellWidth * (x + 1)), Math.round(cellHeight * (y + 1)));
                }
            }
        }
        
        g.setColor(Color.red);
        if (dir == 0) // Top
        {
            g.fillPolygon(new int[]{Math.round(x * cellWidth + cellWidth / 2), Math.round(x * cellWidth + cellWidth / 2) +8, Math.round(x * cellWidth + cellWidth / 2) -8}, new int[]{Math.round(y * cellHeight + cellHeight / 6), Math.round((y + 1) * cellHeight - cellHeight / 6), Math.round((y + 1) * cellHeight - cellHeight / 6)}, 3);
        }
        else if (dir == 1) // Right
        {
            g.fillPolygon(new int[]{Math.round((x + 1) * cellWidth - cellWidth / 6), Math.round(x * cellWidth + cellWidth / 6), Math.round(x * cellWidth + cellWidth / 6)}, new int[]{Math.round(y * cellHeight + cellHeight / 2), Math.round(y * cellHeight + cellHeight / 2) +8, Math.round(y * cellHeight + cellHeight / 2) -8}, 3);
        }
        else if (dir == 2) // Bot
        {
            g.fillPolygon(new int[]{Math.round(x * cellWidth + cellWidth / 2), Math.round(x * cellWidth + cellWidth / 2) +8, Math.round(x * cellWidth + cellWidth / 2) -8}, new int[]{Math.round((y + 1) * cellHeight - cellHeight / 6), Math.round(y * cellHeight + cellHeight / 6), Math.round(y * cellHeight + cellHeight / 6)}, 3);
        }
        else if (dir == 3) // Left
        {
            g.fillPolygon(new int[]{Math.round(x * cellWidth + cellWidth / 6), Math.round((x + 1) * cellWidth - cellWidth / 6), Math.round((x + 1) * cellWidth - cellWidth / 6)}, new int[]{Math.round(y * cellHeight + cellHeight / 2), Math.round(y * cellHeight + cellHeight / 2) +8, Math.round(y * cellHeight + cellHeight / 2) -8}, 3);
        }
        
    }
    /**
     * Methode, die aufgerufen wird, wenn auf buttonTop geklickt wird. 
     * Dadurch wechselt das Programm in die Vogelperspektive.
     */
    void buttonTop_Click()
    {
        topView = true;
        buttonTop.setEnabled(false);
        button3D.setEnabled(true);
        panel.repaint();
    }
    /**
     * Methode, die aufgerufen wird, wenn auf button3D geklickt wird. 
     * Dadurch wechselt das Programm in die 3D-Ansicht. 
     */
    void button3D_Click()
    {
        topView = false;
        buttonTop.setEnabled(true);
        button3D.setEnabled(false);
        panel.repaint();
    }
    
    /**
     * Methode, die aufgerufen wird, wenn auf buttonLeft geklickt wird. 
     * Dadurch wird der Spieler nach links gedreht.
     */
    void buttonLeft_Click()
    {
        if(gewonnen) return;
        turn(-1);
        panel.repaint();
    }
    
    /**
     * Methode, die aufgerufen wird, wenn auf buttonRight geklickt wird. 
     * Dadurch wird der Spieler nach rechts gedreht.
     */
    void buttonRight_Click()
    {
        if(gewonnen) return;
        turn(1);
        panel.repaint();
    }
    
    /**
     * Methode, die aufgerufen wird, wenn auf buttonForward geklickt wird. 
     * Dadurch wird der Spieler nach vorne bewegt.
     */
    void buttonForward_Click()
    {
        if(gewonnen) return;
        forward();
        schritte++;
        panel.repaint();
    }
    
    /**
     * Methode, die aufgerufen wird, wenn auf buttonBackward geklickt wird. 
     * Dadurch wird der Spieler nach hinten bewegt.
     */
    void buttonBackward_Click()
    {
        if(gewonnen) return;
        backward();
        schritte++;
        panel.repaint();
    }
    
    /**
     * Methode, die aufgerufen wird, wenn auf buttonGenerate geklickt wird. 
     * Dadurch wird ein neues Labyrinth generiert.
     */
    void buttonGenerate_Click()
    {
        maze.generate();
        gewonnen = false;
        schritte = 0;
        x=0; y=0;
        start = System.currentTimeMillis();
        end=0;
        buttonTop_Click();
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==buttonTop) buttonTop_Click();
        else if(e.getSource()==buttonLeft) buttonLeft_Click();
        else if(e.getSource()==buttonRight) buttonRight_Click();
        else if(e.getSource()==buttonForward) buttonForward_Click();
        else if(e.getSource()==buttonBackward) buttonBackward_Click();
        else if(e.getSource()==button3D) button3D_Click();
        else if(e.getSource()==buttonGenerate) buttonGenerate_Click();
        requestFocus();
    }

    public void keyPressed(KeyEvent e)
    {
        int keyCode = e.getKeyCode();
        switch(keyCode)
        {
            case KeyEvent.VK_LEFT:
                buttonLeft_Click();
                break;
            case KeyEvent.VK_RIGHT:
                buttonRight_Click();
                break;
            case KeyEvent.VK_UP:
                buttonForward_Click();
                break;
            case KeyEvent.VK_DOWN:
                buttonBackward_Click();
                break;
        }
    }
    
    public void keyReleased(KeyEvent e){}
    
    public void keyTyped(KeyEvent e){}
    
    public void mouseClicked(MouseEvent e){}
    
    public void mouseEntered(MouseEvent e){}
    
    public void mouseExited(MouseEvent e){}
    
    public void mousePressed(MouseEvent e){}
    
    /**
     * Methode die nach dem Klicken auf das Labyrinth aufgerufen wird
     */
    public void mouseReleased(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();
        if(topView)
        {
            float cellWidth = (panel.getWidth()) / (float)maze.maze.length;
            float cellHeight = (panel.getHeight()) / (float)maze.maze[0].length;
            this.x = (int)(x / cellWidth);
            this.y = (int)(y / cellHeight);
            panel.repaint();
        }
    }
    
}
