/**
 * Klasse für das Labyrinth. 
 */
public class Labyrinth
{
    int top = 1;
    int right = 1<<1;
    int bottom = 1<<2;
    int left = 1<<3;
    int[][] maze;
    boolean[][] visited;
    int[] dirs;
    
    /**
     * Konstruktor für das Labyrinth.
     */
    public Labyrinth()
    {
        maze = new int[20][20];
        visited = new boolean[20][20]; 
        dirs = new int[]{top,right, bottom, left};
        generate();
    }
    /**
     * Überprüft, ob eine bestimmte Wand existiert. 
     * @param x x-Koordinate
     * @param y y-Koordinate
     * @param n Richtung. n ist entweder top, right, bottom oder left.
     * @return Ob die Wand existiert.
     */
    public boolean hasWall(int x, int y, int n)
    {
        return (maze[x][y] & n)!=0;
    }
    
    /**
     * Überprüft, ob ein bestimmter Nachbar unbesucht ist. 
     * @param x x-Koordinate
     * @param y y-Koordinate
     * @param n Richtung. n ist entweder top, right, bottom oder left. 
     * @return Ob der Nachbar unbesucht ist.
     */
    public boolean isNotVisitedNeighbour(int x, int y, int n)
    {
        if (n == top && y > 0 && !visited[x][y - 1]) return true;
        if (n == bottom && y < maze[0].length - 1 && !visited[x][y + 1]) return true;
        if (n == left && x > 0 && !visited[x - 1][y]) return true;
        if (n == right && x < maze.length - 1 && !visited[x + 1][y]) return true;
        return false;
    }
    
    /**
     * Überprüft, ob ein bestimmter Nachbar existiert. 
     * @param x x-Koordinate
     * @param y y-Koordinate
     * @param n Richtung. n ist entweder top, right, bottom oder left.
     * @return Ob es den Nachbarn gibt.
     */
    public boolean hasNeighbour(int x, int y, int n)
    {
        if (n == top && y > 0) return true;
        if (n == bottom && y < maze[0].length - 1) return true;
        if (n == left && x > 0) return true;
        if (n == right && x < maze.length - 1) return true;
        return false;
    }
    
    /**
     * Zählt Wände.
     * @param x x-Koordinate
     * @param y y-Koordinate
     * @return Anzahl Wände.
     */
    public int wallCount(int x, int y)
    {
        return (hasWall(x, y, top) ? 1 : 0) + (hasWall(x, y, right) ? 1 : 0) + (hasWall(x, y, bottom) ? 1 : 0) + (hasWall(x, y, left) ? 1 : 0);
    }
    
    /**
     * Zählt unbesuchte Nachbarn.
     * @param x x-Koordinate
     * @param y y-Koordinate
     * @return Anzahl unbesuchte Nachbarn.
     */
    public int getNotVisitedNeighbourCount(int x, int y)
    {
        return ((x > 0 && !visited[x - 1][y]) ? 1 : 0) + ((y > 0 && !visited[x][y - 1]) ? 1 : 0) + (x < maze.length - 1 && !visited[x + 1][y] ? 1 : 0) + (y < maze[0].length-1 && !visited[x][y + 1] ? 1 : 0);
    }
    
    /**
     * Entfernt eine bestimmte Wand.
     * @param x x-Koordinate
     * @param y y-Koordinate
     * @param n Richtung. n ist entweder top, right, bottom oder left.
     */
    public void removeWall(int x, int y, int n)
    {
        if ((maze[x][y] & n) == 0) return;
        maze[x][y] = maze[x][y] & (~n);
        removeWall(n == left ? x - 1 : (n == right ? x + 1 : x), n == top ? y - 1 : (n == bottom ? y + 1 : y), n == top ? bottom : (n == bottom ? top : (n == left ? right : left)));
    }
    
    /**
     * Startet Generation eines neuen Labyrinths.
     */
    public void generate()
    {
        for (int x = 0; x < maze.length; x++)
        {
            for (int y = 0; y < maze[0].length; y++)
            {
                maze[x][y] = top | right | bottom | left;
                visited[x][y] = false;
            }
        }
        generateRec((int)(Math.random()*maze.length), (int)(Math.random()*maze[0].length));
    }
    
    /**
     * Rekursive Methode zur Labyrinthgeneration.
     * @param x x-Koordinate
     * @param y y-Koordinate
     */
    public void generateRec(int x, int y)
    {
        visited[x][y] = true;
        while (getNotVisitedNeighbourCount(x, y) > 0)
        {
            boolean found = false;
            int dir = 0;
            while (!found)
            {
                dir = dirs[(int)(Math.random()*4)];
                if (isNotVisitedNeighbour(x, y, dir))
                {
                    found = true;
                }
            }
            removeWall(x, y, dir);
            generateRec(dir == left ? x - 1 : (dir == right ? x + 1 : x), dir == top ? y - 1 : (dir == bottom ? y + 1 : y));
        }
    }
}
