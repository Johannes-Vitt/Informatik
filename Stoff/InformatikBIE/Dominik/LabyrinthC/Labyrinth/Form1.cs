using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace Labyrinth
{
    public partial class Form1 : Form
    {
        Labyrinth maze;

        int x, y, dir;
        bool topView = false;

        public Form1()
        {
            InitializeComponent();
            pictureBox1.Resize += (s, e) => pictureBox1.Refresh();
            maze = new Labyrinth();
            dir = 3;
            button1_Click(null, EventArgs.Empty);
        }

        void forward()
        {
            if (maze.hasNeighbour(x, y, maze.dirs[dir]) && !maze.hasWall(x, y, maze.dirs[dir]))
            {
                if (dir == 0) y--;
                if (dir == 1) x++;
                if (dir == 2) y++;
                if (dir == 3) x--;
            }
        }

        void backward()
        {
            turn(2);
            forward();
            turn(2);
        }

        void turn(int n)
        {
            dir = ((dir + n) % 4 + 4) % 4;
        }

        private void pictureBox1_Paint(object sender, PaintEventArgs e)
        {
            if (topView)
                drawTopView(e);
            else
                draw3dView(e);
        }

        void draw3dView(PaintEventArgs e)
        {
            int depth = 0;
            float height = pictureBox1.Height / 6f;
            float width = pictureBox1.Width / 6f;
            float x1 = 0;
            float x2 = pictureBox1.Width-1;
            float y1 = 0, y2 = pictureBox1.Height-1;
            Pen p = Pens.Black;

            while (true)
            {
                e.Graphics.DrawLine(p, x1, y1, x1, y2);
                e.Graphics.DrawLine(p, x2, y1, x2, y2);
                e.Graphics.DrawLine(p, x1, y1, x2, y1);
                e.Graphics.DrawLine(p, x1, y2, x2, y2);

                e.Graphics.DrawLine(p, x1 + width, y1 + height, x1 + width, y2 - height);
                e.Graphics.DrawLine(p, x2 - width, y1 + height, x2 - width, y2 - height);
                e.Graphics.DrawLine(p, x1 + width, y1 + height, x2 - width, y1 + height);
                e.Graphics.DrawLine(p, x1 + width, y2 - height, x2 - width, y2 - height);
                if (maze.hasWall(x, y, maze.dirs[((dir - 1) % 4 + 4) % 4]))
                {
                    e.Graphics.DrawLine(p, x1, y1, x1 + width, y1 + height);
                    e.Graphics.DrawLine(p, x1, y2, x1 + width, y2 - height);
                }
                else if (maze.hasNeighbour(x, y, maze.dirs[((dir - 1) % 4 + 4) % 4]))
                {
                    turn(-1);
                    forward();
                    turn(1);
                    if (maze.hasWall(x, y, maze.dirs[dir]))
                    {
                        e.Graphics.DrawLine(p, x1, y1+height, x1 + width, y1 + height);
                        e.Graphics.DrawLine(p, x1, y2-height, x1 + width, y2 - height);
                    }
                    turn(1);
                    forward();
                    turn(-1);
                }
                if (maze.hasWall(x, y, maze.dirs[((dir + 1) % 4 + 4) % 4]))
                {
                    e.Graphics.DrawLine(p, x2, y1, x2 - width, y1 + height);
                    e.Graphics.DrawLine(p, x2, y2, x2 - width, y2 - height);
                }
                else if (maze.hasNeighbour(x, y, maze.dirs[((dir + 1) % 4 + 4) % 4]))
                {
                    turn(1);
                    forward();
                    turn(-1);
                    if (maze.hasWall(x, y, maze.dirs[dir]))
                    {
                        e.Graphics.DrawLine(p, x2, y1 + height, x2 - width, y1 + height);
                        e.Graphics.DrawLine(p, x2, y2 - height, x2 - width, y2 - height);
                    }
                    turn(-1);
                    forward();
                    turn(1);
                }

                if (maze.hasWall(x, y, maze.dirs[dir]))
                {
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

        void drawTopView(PaintEventArgs e)
        {
            float cellWidth = (pictureBox1.Width - 1) / (float)maze.maze.GetLength(0);
            float cellHeight = (pictureBox1.Height - 1) / (float)maze.maze.GetLength(1);
            for (int x = 0; x < maze.maze.GetLength(0); x++)
            {
                for (int y = 0; y < maze.maze.GetLength(1); y++)
                {
                    if ((maze.maze[x, y] & maze.top) != 0)
                    {
                        e.Graphics.DrawLine(Pens.Black, cellWidth * x, cellHeight * y, cellWidth * (x + 1), cellHeight * y);
                    }
                    if ((maze.maze[x, y] & maze.left) != 0)
                    {
                        e.Graphics.DrawLine(Pens.Black, cellWidth * x, cellHeight * y, cellWidth * x, cellHeight * (y + 1));
                    }
                    if ((maze.maze[x, y] & maze.bottom) != 0)
                    {
                        e.Graphics.DrawLine(Pens.Black, cellWidth * x, cellHeight * (y + 1), cellWidth * (x + 1), cellHeight * (y + 1));
                    }
                    if ((maze.maze[x, y] & maze.right) != 0)
                    {
                        e.Graphics.DrawLine(Pens.Black, cellWidth * (x + 1), cellHeight * y, cellWidth * (x + 1), cellHeight * (y + 1));
                    }
                }
            }
            Pen p = new Pen(Color.Red, 5);
            p.EndCap = System.Drawing.Drawing2D.LineCap.Triangle;
            if (dir == 0)
            {
                e.Graphics.DrawLine(p, x * cellWidth + cellWidth / 2, (y + 1) * cellHeight - cellHeight / 6, x * cellWidth + cellWidth / 2, y * cellHeight + cellHeight / 6);
            }
            else if (dir == 1)
            {
                e.Graphics.DrawLine(p, x * cellWidth + cellWidth / 6, y * cellHeight + cellHeight / 2, (x + 1) * cellWidth - cellWidth / 6, y * cellHeight + cellHeight / 2);
            }
            else if (dir == 2)
            {
                e.Graphics.DrawLine(p, x * cellWidth + cellWidth / 2, y * cellHeight + cellHeight / 6, x * cellWidth + cellWidth / 2, (y + 1) * cellHeight - cellHeight / 6);
            }
            else if (dir == 3)
            {
                e.Graphics.DrawLine(p, (x + 1) * cellWidth - cellWidth / 6, y * cellHeight + cellHeight / 2, x * cellWidth + cellWidth / 6, y * cellHeight + cellHeight / 2);
            }
        }

        private void pictureBox1_MouseDoubleClick(object sender, MouseEventArgs e)
        {
            if (topView)
            {
                float cellWidth = (pictureBox1.Width - 2) / (float)maze.maze.GetLength(0);
                float cellHeight = (pictureBox1.Height - 2) / (float)maze.maze.GetLength(1);
                x = (int)(e.X / cellWidth);
                y = (int)(e.Y / cellHeight);
                pictureBox1.Refresh();
            }
        }

        private void button1_Click(object sender, EventArgs e)//switchToTopView
        {
            topView = true;
            button1.Enabled = false;
            button3.Enabled = true;
            pictureBox1.Refresh();
        }

        private void button2_Click(object sender, EventArgs e)//newMaze
        {
            maze.generate();
            button1_Click(null, e);
        }

        private void button3_Click(object sender, EventArgs e)
        {
            topView = false;
            button1.Enabled = true;
            button3.Enabled = false;
            pictureBox1.Refresh();
        }

        private void button4_Click(object sender, EventArgs e)//forward
        {
            forward();
            pictureBox1.Refresh();
        }

        private void button6_Click(object sender, EventArgs e)//left
        {
            turn(-1);
            pictureBox1.Refresh();
        }

        private void button7_Click(object sender, EventArgs e)//right
        {
            turn(1);
            pictureBox1.Refresh();
        }

        private void button5_Click(object sender, EventArgs e)//backward
        {
            backward();
            pictureBox1.Refresh();

        }
    }
}
