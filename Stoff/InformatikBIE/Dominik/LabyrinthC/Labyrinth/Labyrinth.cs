using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Labyrinth
{
    public class Labyrinth
    {
        public int[,] maze;
        bool[,] visited;

        public int top = 1;
        public int right = 1 << 1;
        public int bottom = 1 << 2;
        public int left = 1 << 3;

        public int[] dirs;

        Random rand;

        public Labyrinth()
        {
            maze = new int[20, 20];
            visited = new bool[20, 20];
            dirs = new int[] { top, right, bottom, left };
            rand = new Random();
            generate();
        }

        public bool hasWall(int x, int y, int n)
        {
            return (maze[x, y] & n) != 0;
        }

        public bool isNotVisitedNeighbour(int x, int y, int n)
        {
            if (n == top && y > 0 && !visited[x, y - 1]) return true;
            if (n == bottom && y < maze.GetLength(1) - 1 && !visited[x, y + 1]) return true;
            if (n == left && x > 0 && !visited[x - 1, y]) return true;
            if (n == right && x < maze.GetLength(0) - 1 && !visited[x + 1, y]) return true;
            return false;
        }

        public bool hasNeighbour(int x, int y ,int n)
        {
            if (n == top && y > 0) return true;
            if (n == bottom && y < maze.GetLength(1) - 1) return true;
            if (n == left && x > 0) return true;
            if (n == right && x < maze.GetLength(0) - 1) return true;
            return false;
        }

        public int wallCount(int x, int y)
        {
            return (hasWall(x, y, top) ? 1 : 0) + (hasWall(x, y, right) ? 1 : 0) + (hasWall(x, y, bottom) ? 1 : 0) + (hasWall(x, y, left) ? 1 : 0);
        }

        public int getNotVisitedNeighbourCount(int x, int y)
        {
            return (x > 0 && !visited[x - 1, y] ? 1 : 0) + (y > 0 && !visited[x, y - 1] ? 1 : 0) + (x < maze.GetLength(0) - 1 && !visited[x + 1, y] ? 1 : 0) + (y < maze.GetLength(1)-1 && !visited[x, y + 1] ? 1 : 0);
        }

        public void removeWall(int x, int y, int n)
        {
            if ((maze[x, y] & n) == 0) return;
            maze[x, y] = maze[x, y] & (~n);
            removeWall(n == left ? x - 1 : (n == right ? x + 1 : x), n == top ? y - 1 : (n == bottom ? y + 1 : y), n == top ? bottom : (n == bottom ? top : (n == left ? right : left)));
        }

        

        public void generate()
        {
            for (int x = 0; x < maze.GetLength(0); x++)
            {
                for (int y = 0; y < maze.GetLength(1); y++)
                {
                    maze[x, y] = top | right | bottom | left;
                    visited[x, y] = false;
                }
            }
            generateRec(rand.Next(maze.GetLength(0)), rand.Next(rand.Next(maze.GetLength(1))));
        }

        public void generateRec(int x, int y)
        {
            visited[x, y] = true;
            while (getNotVisitedNeighbourCount(x, y) > 0)
            {
                bool found = false;
                int dir = 0;
                while (!found)
                {
                    dir = dirs[rand.Next(4)];
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
}
