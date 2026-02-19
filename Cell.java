package TwentyFortyEight;

public class Cell {

    private int x; 
    private int y; 
    private int value = 0;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y; }

    
    public void place() {
        if (this.value == 0) {
            this.value = (App.random.nextInt(2) + 1) * 2; } }

    public int getValue() {
        return value; }
    
    public void setValue(int newVal) {
        this.value = newVal; }

    
    public void draw(App app, int yOffset) {
        int posX = x * App.CELLSIZE;
        int posY = y * App.CELLSIZE + yOffset;
        boolean hovered = (app.mouseX > posX && app.mouseX < posX + App.CELLSIZE 
                           && app.mouseY > posY && app.mouseY < posY + App.CELLSIZE);

        if (value == 0) {
            if (hovered) {
                app.fill(232, 207, 184);} else {
                app.fill(189, 172, 151); }
        } else {
            app.fill(app.getTileColor(value));
        }
       
        app.stroke(156, 139, 124);
        app.rect(posX, posY, App.CELLSIZE, App.CELLSIZE);
        if (value != 0) {
            app.fill(0);
            app.textAlign(App.CENTER, App.CENTER);
            app.textSize(32);
            app.text(String.valueOf(value), posX + App.CELLSIZE / 2, posY + App.CELLSIZE / 2);
        }
    }
}








