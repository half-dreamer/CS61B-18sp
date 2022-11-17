package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

public class Solver {
    private int solutionMoves;
    private WorldState startState;
    private searchNode targetNode;
    private int everEnqueueNum = 0;

    public int getEverEnqueueNum() {
        return everEnqueueNum;
    }

    private class searchNode {
        WorldState worldState;
        int moves;
        searchNode prevNode;
        int estimatedDist;
        boolean cached = false;

        public searchNode(WorldState worldState,int moves,searchNode prevNode) {
            this.worldState = worldState;
            this.moves = moves;
            this.prevNode = prevNode;
        }
    }


    /** Constructor which solves the puzzle, computing
        everything necessary for moves() and solution() to
        not have to solve the problem again. Solves the
        puzzle using the A* algorithm. Assumes a solution exists. */
    public Solver(WorldState initial){
        startState = initial;
        MinPQ<searchNode> worldStatesPQ = new MinPQ<>(new PriorityComparator());
        worldStatesPQ.insert(new searchNode(initial,0,null));
        while(!worldStatesPQ.isEmpty()) {
            searchNode delNode = worldStatesPQ.delMin();
            if (delNode.worldState.isGoal()) {
                solutionMoves = delNode.moves;
                targetNode = delNode;
                break;
            } else {
                for (WorldState neighbour : delNode.worldState.neighbors()) {
                    if (delNode.prevNode != null && neighbour.equals(delNode.prevNode.worldState)) {
                        continue;
                    }
                    worldStatesPQ.insert(new searchNode(neighbour,delNode.moves+1,delNode));
                    everEnqueueNum += 1;
                }
            }
        }


    }

    private class PriorityComparator implements Comparator<searchNode> {
        @Override
        public int compare(searchNode w1,searchNode w2) {
            /** caching trick is really important ,if you don't use the trick
             *  you will fail in the time efficiency test.
             */
            if (!w1.cached) {
                w1.estimatedDist = w1.worldState.estimatedDistanceToGoal();
                w1.cached = true;
            }
            if (!w2.cached) {
                w2.estimatedDist = w2.worldState.estimatedDistanceToGoal();
                w2.cached = true;
            }
                int p1 = w1.moves + w1.estimatedDist;
                int p2 = w2.moves + w2.estimatedDist;

            return p1 - p2;
        }
    }


    /** Returns the minimum number of moves to solve the puzzle starting
        at the initial WorldState.*/
    public int moves() {
            return solutionMoves;
    }

    /** Returns a sequence of WorldStates from the initial WorldState
         to the solution. */
    public Iterable<WorldState> solution() {
        Stack<WorldState> solutionStack = new Stack<>();
        searchNode curNode = targetNode;
        while (!curNode.worldState.equals(startState)) {
            solutionStack.add(curNode.worldState);
            curNode = curNode.prevNode;
        }
        solutionStack.add(curNode.worldState);
        Collections.reverse(solutionStack);
        return solutionStack;
    }
}
