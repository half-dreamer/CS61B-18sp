package hw4.puzzle;
import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {
    private int[][] tiles;
    private final int BLANK = 0;

    public Board(int[][] tiles) {
        int size  = tiles.length;
        this.tiles = new int[size][size];
        for (int i = 0 ;i < size;i++) {
            for (int j = 0;j < size;j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    /** @Source http://joshh.ug/neighbors.html
     *  @Author joshua hug
     */
    public int tileAt(int i, int j) {
        if (i < 0 || i >= tiles.length || j < 0 || j >= tiles.length) {
            throw new IndexOutOfBoundsException();
        }
        return tiles[i][j];
    }
    public int size() {
        return tiles.length;
    }
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }
    /** @return  real num of the tile */
    private int castTo1D(int i, int j) {
        return (j + 1) + i * tiles.length;
    }

    public int hamming() {
        int sumOfWrongPosi = 0;
        for (int i = 0;i < size();i++) {
            for (int j = 0;j < size();j++) {
                if (tileAt(i,j) != castTo1D(i,j) && tileAt(i,j) != BLANK) {
                    sumOfWrongPosi += 1;
                }
            }
        }
        return sumOfWrongPosi;
    }

    public int manhattan() {
        int manhattanSum = 0;
        for (int i = 0;i < size();i++) {
            for (int j = 0;j < size();j++) {
                if (tileAt(i,j) == 0) {
                    continue;
                }
                int targetX = (tileAt(i,j) - 1) % size() ;
                int targetY = (tileAt(i,j) - 1) / size() ;
                manhattanSum += Math.abs(i - targetY) + Math.abs(j - targetX);
            }
        }
        return manhattanSum;
   }

    public int estimatedDistanceToGoal() {
        return manhattan();
    }
    public boolean equals(Object y) {
        if (!(y instanceof Board)) {
            return false;
        } else if(((Board) y).size() != this.size()) {
            return false;
        } else {
            for (int i = 0;i < size();i++) {
                for (int j = 0;j < size() ;j ++) {
                    if (this.tileAt(i,j) != ((Board) y).tileAt(i,j)) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }
    public int hashCode() {
        return this.hashCode();
    }

}
