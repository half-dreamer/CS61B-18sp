import edu.princeton.cs.algs4.Picture;

import java.awt.*;

public class SeamCarver {
    private Picture picture;
    private double[][] energys;
    private double[][] costs;
    private int width;
    private int height;
    private boolean isVertival = true;
    public SeamCarver(Picture picture) {
        this.picture = new Picture(picture);
        // note : I didn't create a new Picture object
        width = width();
        height = height();

    }
    public Picture picture() {
        return picture;
    }
    public int width() {
        return picture.width();
    }
    public int height() {
        return picture.height();
    }
    private void validate(int x,int y) {
        if (x >= width || x < 0 || y < 0 || y >= height) {
            throw new IndexOutOfBoundsException();
        }
    }

    public double energy(int x, int y) {
        validate(x,y);
        Color up,down,left,right;
        double XEnergy,YEnergy;
        if (isVertival) {
            up = y >= 1 ? picture.get(x, y - 1) : picture.get(x, height - 1);
            down = y < height - 1 ? picture.get(x, y + 1) : picture.get(x, 0);
            left = x >= 1 ? picture.get(x - 1, y) : picture.get(width - 1, y);
            right = x < width - 1 ? picture.get(x + 1, y) : picture.get(0, y);
        } else {
            up = x > 0 ? picture.get(x - 1, y) : picture.get(height - 1, y);
            down = x < height - 1 ? picture.get(x + 1, y) : picture.get(0, y);
            left = y > 0 ? picture.get(x, y - 1) : picture.get(x, width - 1);
            right = y < width - 1 ? picture.get(x, y + 1) : picture.get(x, 0);
        }
        double Rx = right.getRed() - left.getRed();
        double Gx = right.getGreen() - left.getGreen();
        double Bx = right.getBlue() - left.getBlue();
        double Ry = down.getRed() - up.getRed();
        double Gy = down.getGreen() - up.getGreen();
        double By = down.getBlue() - up.getBlue();

        XEnergy = Rx * Rx + Gx * Gx + Bx * Bx;
        YEnergy = Ry * Ry + Gy * Gy + By * By;
        return XEnergy + YEnergy;
    }

    private void buildEnergyArray() {
        for (int x = 0 ; x < width ; x++) {
            for (int y = 0 ; y < height; y++) {
                    energys[x][y] = energy(x, y);
                }
            }
    }


    public int[] findVerticalSeam() {
        int y = 0;
        energys = new double[width][height];
        costs = new double[width][height];
        buildEnergyArray();
        /** initialize the first row of the costs array */
        for (int x = 0;x < width;x++) {
            costs[x][y] = energys[x][y];
        }
        /** compute the remaining part of the costs array */
        if (height > 1) {
            for (y = 1; y < height;y++) {
                for (int x = 0;x < width;x++) {
                    if (x == 0) {
                        costs[x][y] = energys[x][y] + Math.min(costs[x][y-1],costs[x+1][y-1]);
                    } else if (x == width - 1) {
                        costs[x][y] = energys[x][y] + Math.min(costs[x][y-1],costs[x-1][y-1]);
                    } else {
                        costs[x][y] = energys[x][y] + findMinOfThreeNum(costs[x-1][y-1],costs[x][y-1],costs[x+1][y-1]);
                    }
                }
            }
        }

        int lastX = getXOfMinCost(costs);

        /** get the array of min cost at height H */
        int[] result = new int[height];
        result = getPathOfMinCost(lastX);
        return result;
    }

    /** get the X index of min cost in the bottom row */
    private int getXOfMinCost(double[][] costs) {
        double min = Double.MAX_VALUE;
        int y = height - 1;
        int minIndex = 0;
        for (int x = 0;x < width;x++) {
            if (costs[x][y] < min) {
                min = costs[x][y];
                minIndex = x;
            }
        }
        return minIndex;
    }

    private int[] getPathOfMinCost(int lastX) {

        int[] path = new int[height];
        path[height -1] = lastX;
        int curX = lastX;
        int nextX;
        if (height == 1) {
            return path;
        } else {
            for (int y = height - 1; y >= 1; y--) {
                if (curX == 0) {
                    for (int x = curX;x <= curX+1;x++) {
                        if (costs[x][y-1] + energys[curX][y] == costs[curX][y]) {
                            curX = x;
                            path[y-1] = curX;
                            break;
                        }
                    }
                } else if (curX >= width -1) {
                    for (int x = curX-1; x <= curX;x++) {
                        if (costs[x][y-1] + energys[curX][y] == costs[curX][y]) {
                            curX = x;
                            path[y-1] = curX;
                            break;
                        }
                    }
                } else {
                    for (int x = curX-1; x <= curX+1; x++) {

                        if (costs[x][y-1] + energys[curX][y] == costs[curX][y]) {
                            curX = x;
                            path[y-1] = curX;
                            break;
                        }
                    }
                }
            }
        }
        return path;
    }

    private double findMinOfThreeNum(double x,double y,double z) {
        return Math.min(x,Math.min(y,z));
    }

    public int[] findHorizontalSeam() {
        swap();
        isVertival = false;
        int[] res = findVerticalSeam();
        swap();
        isVertival = true;
        return res;
    }
    private void swap() {
        int temp = width;
        width = height;
        height = temp;
        return;
    }

    public void removeHorizontalSeam(int[] seam) {
        SeamRemover.removeHorizontalSeam(picture,seam);
    }
    public void removeVerticalSeam(int[] seam) {
        SeamRemover.removeVerticalSeam(picture,seam);
    }
}
