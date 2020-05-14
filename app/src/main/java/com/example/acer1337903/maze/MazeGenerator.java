package com.example.acer1337903.maze;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

class MazeGenerator {
    public int z;
    public int s1, s2, s3, s4;
    private Stack<Node> stack = new Stack<>();
    private Random rand = new Random();
    private int[][] maze;
    private int dimension = MainActivity.wielkosc;
    boolean[][] wasHere = new boolean[dimension][dimension];
    boolean[][] correctPath = new boolean[dimension][dimension]; // Rozwiazanie labiryntu
    int startX, startY = 0; // Startowe wartosci wspolzednych
    int endX, endY = dimension - 1; // Koncowe wartosci wspolzednych
    int NstartX, NstartY = 0; // Wartosci poczatku sciezki dla najdluzszego przejscia
    int NendX, NendY = dimension - 1; // Wartosci konca sciezki dla najdluzszego przejscia
    int dlugoscDrogi, najdluzszaDlugoscDrogi=0; // wartosci zapisujaca dlugosc drogi w danym przejsciu oraz najdluzsza
    MazeGenerator(int dim) {
        maze = new int[dim][dim];
        dimension = dim;
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[row].length; col++) {
                wasHere[row][col] = false;
                correctPath[row][col] = false;
            }
        }
    }
//rendomowa generacja labiryntu
    public void generateMaze() {
        stack.push(new Node(0, 0));
        while (!stack.empty()) {
            Node next = stack.pop();
            if (validNextNode(next)) {
                int a = rand.nextInt(4);
                if (a == 1) {
                    maze[next.y][next.x] = 1;
                }
                ArrayList<Node> neighbors = findNeighbors(next);
                randomlyAddNodesToStack(neighbors);
            }
        }
    }
// poczatkowy rekurencyjny algorytm poszukiwania przejscia dla kazdych wartosci poczatkowych oraz koncowych i wyznacza najdluzsza
    public void solveMaze() {

        boolean b = recursiveSolve(startX, startY, true);
        z++;
        while (startX < dimension - 1 ) {
            startX++;
            endY = dimension - 1;
            endX = 0;
            while (endY > 0 ) {
                endY--;
                z++;
                if (startX != endX)
                    b = recursiveSolve(startX, startY, true);
                    if(dlugoscDrogi>najdluzszaDlugoscDrogi) {
                        najdluzszaDlugoscDrogi = dlugoscDrogi;
                        NstartX=startX;
                        NstartY=startY;
                        NendX=endX;
                        NendY=endY;
                    }
                    dlugoscDrogi=0;
            }
            for (int row = 0; row < maze.length; row++) {
                for (int col = 0; col < maze[row].length; col++) {
                    wasHere[row][col] = false;
                }
            }
            endX = dimension - 1;
            endY = dimension - 1;
            while (endY > 0 ) {
                endY--;
                z++;
                b = recursiveSolve(startX, startY, true);
                if(dlugoscDrogi>najdluzszaDlugoscDrogi) {
                    najdluzszaDlugoscDrogi = dlugoscDrogi;
                    NstartX=startX;
                    NstartY=startY;
                    NendX=endX;
                    NendY=endY;
                }
                dlugoscDrogi=0;
                for (int row = 0; row < maze.length; row++) {
                    for (int col = 0; col < maze[row].length; col++) {
                        wasHere[row][col] = false;
                    }
                }
            }
        }
        startX = 0;
        startY = dimension - 1;
        while (startX < dimension - 1 ) {
            startX++;
            endY = dimension - 1;
            endX = 0;
            while (endY > 0 ) {
                endY--;
                z++;
                if (startX != endX)
                    b = recursiveSolve(startX, startY, true);
                if(dlugoscDrogi>najdluzszaDlugoscDrogi) {
                    najdluzszaDlugoscDrogi = dlugoscDrogi;
                    NstartX=startX;
                    NstartY=startY;
                    NendX=endX;
                    NendY=endY;
                }
                dlugoscDrogi=0;
                for (int row = 0; row < maze.length; row++) {
                    for (int col = 0; col < maze[row].length; col++) {
                        wasHere[row][col] = false;
                    }
                }
            }
            endX = dimension - 1;
            endY = dimension - 1;
            while (endY > 0 ) {
                endY--;
                z++;
                b = recursiveSolve(startX, startY, true);
                if(dlugoscDrogi>najdluzszaDlugoscDrogi) {
                    najdluzszaDlugoscDrogi = dlugoscDrogi;
                    NstartX=startX;
                    NstartY=startY;
                    NendX=endX;
                    NendY=endY;
                }
                dlugoscDrogi=0;
                for (int row = 0; row < maze.length; row++) {
                    for (int col = 0; col < maze[row].length; col++) {
                        wasHere[row][col] = false;
                    }
                }
            }
        }
        startY = 0;
        startX = 0;
        while (startY < dimension - 1 ) {
            startY++;
            endX = dimension - 1;
            endY = 0;
            while (endX > 0 ) {
                endX--;
                z++;
                if (startY != endY)
                    b = recursiveSolve(startX, startY, true);
                if(dlugoscDrogi>najdluzszaDlugoscDrogi) {
                    najdluzszaDlugoscDrogi = dlugoscDrogi;
                    NstartX=startX;
                    NstartY=startY;
                    NendX=endX;
                    NendY=endY;
                }
                dlugoscDrogi=0;
                for (int row = 0; row < maze.length; row++) {
                    for (int col = 0; col < maze[row].length; col++) {
                        wasHere[row][col] = false;
                    }
                }
            }
            endY = dimension - 1;
            endX = dimension - 1;
            while (endX > 0 ) {
                endX--;
                z++;
                b = recursiveSolve(startX, startY, true);
                if(dlugoscDrogi>najdluzszaDlugoscDrogi) {
                    najdluzszaDlugoscDrogi = dlugoscDrogi;
                    NstartX=startX;
                    NstartY=startY;
                    NendX=endX;
                    NendY=endY;
                }
                dlugoscDrogi=0;
                for (int row = 0; row < maze.length; row++) {
                    for (int col = 0; col < maze[row].length; col++) {
                        wasHere[row][col] = false;
                    }
                }
            }
        }
        startY = 0;
        startX = dimension - 1;
        while (startY < dimension - 1 ) {
            startY++;
            endX = dimension - 1;
            endY = 0;
            while (endX > 0 ) {
                endX--;
                z++;
                if (startY != endY)
                    b = recursiveSolve(startX, startY, true);
                if(dlugoscDrogi>najdluzszaDlugoscDrogi) {
                    najdluzszaDlugoscDrogi = dlugoscDrogi;
                    NstartX=startX;
                    NstartY=startY;
                    NendX=endX;
                    NendY=endY;
                }
                dlugoscDrogi=0;
                for (int row = 0; row < maze.length; row++) {
                    for (int col = 0; col < maze[row].length; col++) {
                        wasHere[row][col] = false;
                    }
                }
            }
            endY = dimension - 1;
            endX = dimension - 1;
            while (endX > 0 ) {
                endX--;
                z++;
                b = recursiveSolve(startX, startY, true);
                if(dlugoscDrogi>najdluzszaDlugoscDrogi) {
                    najdluzszaDlugoscDrogi = dlugoscDrogi;
                    NstartX=startX;
                    NstartY=startY;
                    NendX=endX;
                    NendY=endY;
                }
                dlugoscDrogi=0;
                for (int row = 0; row < maze.length; row++) {
                    for (int col = 0; col < maze[row].length; col++) {
                        wasHere[row][col] = false;
                    }
                }
            }
        }
        endX=NendX;
        endY=NendY;
       b=recursiveSolve(NstartX, NstartY, false);
    }



    public String getRawMaze() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : maze) {
            sb.append(Arrays.toString(row) + "\n");
        }
        return sb.toString();
    }
//labirynt z pokazana sciezka
    public String getSymbolicMaze() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (maze[i][j] == 1) {
                    sb.append("⬛");
                }
                if (maze[i][j] == 0) {
                    sb.append("⬜");
                }
                if (maze[i][j] == 2) {
                    sb.append("♥");
                }
                sb.append("");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    //Labirynt bez pokazanej sciezki
    public String getSymbolicMazeOld() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if((i==dimension-1 || j==dimension-1 || i ==0 || j==0)&& maze[i][j]==0)
                    maze[i][j]=1;
                if (maze[i][j] == 1) {
                    sb.append("⬛");
                }
                if (maze[i][j] == 0) {
                    sb.append("⬜");
                }
                if (maze[i][j] == 2) {
                    sb.append("⬜");
                }

                sb.append("");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private boolean validNextNode(Node node) {
        int numNeighboringOnes = 0;
        for (int y = node.y - 1; y < node.y + 2; y++) {
            for (int x = node.x - 1; x < node.x + 2; x++) {
                if (pointOnGrid(x, y) && pointNotNode(node, x, y) && maze[y][x] == 1) {
                    numNeighboringOnes++;
                }
            }
        }
        return (numNeighboringOnes < 3) && maze[node.y][node.x] != 1;
    }

    private void randomlyAddNodesToStack(ArrayList<Node> nodes) {
        int targetIndex;
        while (!nodes.isEmpty()) {
            targetIndex = rand.nextInt(nodes.size());
            stack.push(nodes.remove(targetIndex));
        }
    }

    private ArrayList<Node> findNeighbors(Node node) {
        ArrayList<Node> neighbors = new ArrayList<>();
        for (int y = node.y - 1; y < node.y + 2; y++) {
            for (int x = node.x - 1; x < node.x + 2; x++) {
                if (pointOnGrid(x, y) && pointNotCorner(node, x, y) &&
                        pointNotNode(node, x, y)) {
                    neighbors.add(new Node(x, y));
                }
            }
        }
        return neighbors;
    }
    //rekurencyjny algorytm poszukiwania przejscia
    public boolean recursiveSolve(int x, int y, boolean test) {
        if (x == endX && y == endY && maze[x][y] != 1) {
            if(!test)
            maze[x][y] = 2;
            dlugoscDrogi++;
            return true; // Jezeli dotarte do konca
        }
        if (maze[x][y] == 1 || wasHere[x][y]) return false;
        // Jezeli jestes na cianie lub juz tu byles
        wasHere[x][y] = true;
        if (x != 0) // Sprawdza czy na granicy lewej
            if (recursiveSolve(x - 1, y, test)) { //wywoluje metode rekurencyjna w lewo
                correctPath[x][y] = true; // Ustawia droge na prawdziwa
                if(!test)
                maze[x][y] = 2;
                dlugoscDrogi++;
                s1 = startX;
                s2 = startY;
                s3 = endX;
                s4 = endY;
                return true;
            }
        if (x != dimension - 1) // Sprawdza czy na granicy prawej
            if (recursiveSolve(x + 1, y, test)) {  //wywoluje metode rekurencyjna w prawo
                correctPath[x][y] = true;
                if(!test)
                maze[x][y] = 2;
                dlugoscDrogi++;
                s1 = startX;
                s2 = startY;
                s3 = endX;
                s4 = endY;
                return true;
            }
        if (y != 0) // Sprawdza czy na granicy gornej
            if (recursiveSolve(x, y - 1, test)) {  //wywoluje metode rekurencyjna w gore
                correctPath[x][y] = true;
                if(!test)
                maze[x][y] = 2;
                dlugoscDrogi++;
                s1 = startX;
                s2 = startY;
                s3 = endX;
                s4 = endY;
                return true;
            }
        if (y != dimension - 1) // Sprawdza czy na granicy dolnej
            if (recursiveSolve(x, y + 1, test)) {  //wywoluje metode rekurencyjna w dol
                correctPath[x][y] = true;
                if(!test)
                maze[x][y] = 2;
                dlugoscDrogi++;
                s1 = startX;
                s2 = startY;
                s3 = endX;
                s4 = endY;
                return true;
            }
        return false;
    }

    private Boolean pointOnGrid(int x, int y) {
        return x >= 0 && y >= 0 && x < dimension && y < dimension;
    }

    private Boolean pointNotCorner(Node node, int x, int y) {
        return (x == node.x || y == node.y);
    }

    private Boolean pointNotNode(Node node, int x, int y) {
        return !(x == node.x && y == node.y);
    }
}