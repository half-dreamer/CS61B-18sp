<<<<<<< HEAD
package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private Maze maze;
    private int startNode;
    private boolean cycleDetected = false;
    private int flag = 0;
    private int cycleFlag = 0;
    private int[] parents;
    private int cycleStartNode;
    private int cycleEndNode;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        startNode = maze.xyTo1D(1,1);
        parents= new int[maze.V()];
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        DFSfindCycle(startNode);
        drawCycle(cycleStartNode);
    }

    // Helper methods go here
    public void DFSfindCycle(int node) {
        announce();
        marked[node] = true;
        for (int neighbour : maze.adj(node)) {
            if (marked[neighbour] == true && neighbour != parents[node]) {
                cycleStartNode = node;
                cycleEndNode = neighbour;
                edgeTo[neighbour] = node;
                announce();
                flag = 1;
                return ;
            }
        }
        for (int neighbour : maze.adj(node)) {
            if (neighbour == parents[node]) {
                continue;
            }
            parents[neighbour] = node;
            marked[neighbour] = true;
            DFSfindCycle(neighbour);
            if (flag == 1) {
                return ;
            }
        }
    }
    private void drawCycle(int cycleNode) {
        if (cycleNode == cycleEndNode) {
            return;
        } else {
            edgeTo[cycleNode] = parents[cycleNode];
            announce();
            drawCycle(parents[cycleNode]);
        }

    }
}
=======
package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private Maze maze;
    private int startNode;
    private boolean cycleDetected = false;
    private int flag = 0;
    private int cycleFlag = 0;
    private int[] parents;
    private int cycleStartNode;
    private int cycleEndNode;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        startNode = maze.xyTo1D(1,1);
        parents= new int[maze.V()];
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        DFSfindCycle(startNode);
        drawCycle(cycleStartNode);
    }

    // Helper methods go here
    public void DFSfindCycle(int node) {
        announce();
        marked[node] = true;
        for (int neighbour : maze.adj(node)) {
            if (marked[neighbour] == true && neighbour != parents[node]) {
                cycleStartNode = node;
                cycleEndNode = neighbour;
                edgeTo[neighbour] = node;
                announce();
                flag = 1;
                return ;
            }
        }
        for (int neighbour : maze.adj(node)) {
            if (neighbour == parents[node]) {
                continue;
            }
            parents[neighbour] = node;
            marked[neighbour] = true;
            DFSfindCycle(neighbour);
            if (flag == 1) {
                return ;
            }
        }
    }
    private void drawCycle(int cycleNode) {
        if (cycleNode == cycleEndNode) {
            return;
        } else {
            edgeTo[cycleNode] = parents[cycleNode];
            announce();
            drawCycle(parents[cycleNode]);
        }

    }
}
>>>>>>> 7afade8f1c7ec3c5c20e6a621ffec6bb636477bf
