package lab11.graphs;

import java.beans.IntrospectionException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private Maze maze;
    private boolean targetFound = false;
    private Queue<Integer> fringe = new LinkedList<Integer>();

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX,sourceY);
        t = maze.xyTo1D(targetX,targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        fringe.add(s);
        // Add more variables here!
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        while (!fringe.isEmpty()){
            int curNode = fringe.remove();
            marked[curNode] = true;
            announce();
            if (curNode == t) {
                targetFound = true;
            }
            if (targetFound) {
                return ;
            }
            for (int neighbour : maze.adj(curNode)) {
                if (marked[neighbour]) {
                    continue;
                } else {
                    fringe.add(neighbour);
                    marked[neighbour] = true;
                    edgeTo[neighbour] = curNode;
                    distTo[neighbour] = distTo[curNode] + 1;
                    announce();

                }
            }
        }
        return;
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()

    }


    @Override
    public void solve() {
         bfs();
    }
}

