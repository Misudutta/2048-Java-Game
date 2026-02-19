package TwentyFortyEight;

import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class App extends PApplet {

    public static final int CELLSIZE = 100;
    public static final int CELL_BUFFER = 8;
    public static Random random = new Random();
    public static final int TOPBAR = 50;
    private int gridSize = 4;
    private Cell[][] board;
    private long startTime;
    private boolean gameOver = false;

    public App() {
    }

    public static void main(String[] args) {
        int boardSize = 4;
        if (args.length > 0) {
            try {
                int sizeArg = Integer.parseInt(args[0]);
                if (sizeArg >= 2) {
                    boardSize = sizeArg;
                }
            } catch (NumberFormatException e) {
            }
        }
        App app = new App();
        app.gridSize = boardSize;
        PApplet.runSketch(new String[]{"TwentyFortyEight.App"}, app);
    }
    @Override
    public void settings() {
        size(gridSize * CELLSIZE, gridSize * CELLSIZE + TOPBAR); }
    @Override
    public void setup() {
        frameRate(30);
        board = new Cell[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                board[i][j] = new Cell(j, i);
            }  }
        resetGame(); }

    private void resetGame() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                board[i][j].setValue(0);
            } }
        spawnRandomBlock();
        spawnRandomBlock();
        gameOver = false;
        startTime = millis();
    }

    @Override
    public void draw() {
        background(250);
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                board[i][j].draw(this, TOPBAR);
            } }
        fill(0);
        textSize(20);
        textAlign(RIGHT, TOP);
        int seconds = (int)((millis() - startTime) / 1000);
        text("Time: " + seconds, width - 10, 10);
        if (gameOver) {
            text("Time: " + seconds, width - 10, 10);
            textSize(50);
            textAlign(CENTER, CENTER);
            fill(255, 0, 0);
            text("GAME OVER", width / 2, height / 2);
        } else {
            text("Time: " + seconds, width - 10, 10); } }

    @Override
    public void keyPressed(KeyEvent event) {
        if (gameOver && (key == 'r' || key == 'R')) {
            resetGame();
            return; }
        if (gameOver) return;
        boolean moved = false;
        if (keyCode == UP) {
            moved = moveUp(); } 
            else if (keyCode == DOWN) {
            moved = moveDown(); } 
            else if (keyCode == LEFT) {
            moved = moveLeft(); } 
            else if (keyCode == RIGHT) {
            moved = moveRight(); }
        if (moved) {
            spawnRandomBlock();
            if (!canMove()) {
                gameOver = true;
            } } }

  
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == LEFT) {
            int col = mouseX / CELLSIZE;
            int row = (mouseY - TOPBAR) / CELLSIZE;
            if (isValidCell(row, col) && board[row][col].getValue() == 0) {
                board[row][col].place();
            } } }

    private boolean moveLeft() {
        boolean moved = false;
        for (int i = 0; i < gridSize; i++) {
            int[] original = new int[gridSize];
            for (int j = 0; j < gridSize; j++) {
                original[j] = board[i][j].getValue(); }
            int[] merged = mergeLine(original);
            for (int j = 0; j < gridSize; j++) {
                board[i][j].setValue(merged[j]); }
            if (!arrayEquals(original, merged)) {
                moved = true; } }
        return moved;
    }

    private boolean moveRight() {
        boolean moved = false;
        for (int i = 0; i < gridSize; i++) {
            int[] original = new int[gridSize];
            for (int j = 0; j < gridSize; j++) {
                original[j] = board[i][j].getValue(); }
            reverseArray(original);
            int[] merged = mergeLine(original);
            reverseArray(merged);
            for (int j = 0; j < gridSize; j++) {
                board[i][j].setValue(merged[j]); }
            reverseArray(original);
            if (!arrayEquals(original, merged)) {
                moved = true; } }
        return moved;
    }

    private boolean moveUp() {
        transposeBoard();
        boolean moved = moveLeft();
        transposeBoard();
        return moved; }

    private boolean moveDown() {
        transposeBoard();
        boolean moved = moveRight();
        transposeBoard();
        return moved; }

    private int[] mergeLine(int[] oldLine) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : oldLine) {
            if (i != 0) {
                list.add(i);} }
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).equals(list.get(i + 1))) {
                list.set(i, list.get(i) * 2);
                list.remove(i + 1);
                list.add(0); } }
        while (list.size() < gridSize) {
            list.add(0); }
        int[] newLine = new int[gridSize];
        for (int i = 0; i < gridSize; i++) {
            newLine[i] = list.get(i); }
        return newLine;
    }

    private void reverseArray(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        } }

    private boolean arrayEquals(int[] a, int[] b) {
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) return false; }
        return true; }

    private void transposeBoard() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = i + 1; j < gridSize; j++) {
                int temp = board[i][j].getValue();
                board[i][j].setValue(board[j][i].getValue());
                board[j][i].setValue(temp);
            } } }
    private boolean isValidCell(int row, int col) {
        return row >= 0 && row < gridSize && col >= 0 && col < gridSize;
    }

    private void spawnRandomBlock() {
        ArrayList<Cell> emptyCells = new ArrayList<>();
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (board[i][j].getValue() == 0) {
                    emptyCells.add(board[i][j]);
                }  }  }
        if (emptyCells.size() > 0) {
            Cell chosen = emptyCells.get(random.nextInt(emptyCells.size()));
            chosen.setValue((random.nextInt(2) + 1) * 2); } }

   
    private boolean canMove() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (board[i][j].getValue() == 0) return true;
            } }
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize - 1; j++) {
                if (board[i][j].getValue() == board[i][j + 1].getValue()) return true;
            } }
        for (int j = 0; j < gridSize; j++) {
            for (int i = 0; i < gridSize - 1; i++) {
                if (board[i][j].getValue() == board[i + 1][j].getValue()) return true;
            } }
        return false; }

    public int getTileColor(int value) {
        switch(value) {
            case 2: return color(238, 228, 218);
            case 4: return color(237, 224, 200);
            case 8: return color(242, 177, 121);
            case 16: return color(245, 149, 99);
            case 32: return color(246, 124, 95);
            case 64: return color(246, 94, 59);
            case 128: return color(237, 207, 114);
            case 256: return color(237, 204, 97);
            case 512: return color(237, 200, 80);
            case 1024: return color(237, 197, 63);
            case 2048: return color(237, 194, 46);
            default: return (value > 2048) ? color(237, 194, 46) : color(189, 172, 151);
        } } }












