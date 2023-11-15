import processing.core.PApplet;

public class Editor {
    public int BACKGROUND = 0;
    public int width;
    public int height;
    public PApplet parent;
    public int mouseX;
    public int mouseY;
    public Editor(PApplet parent) {
        this.parent = parent;
        this.width = parent.width;
        this.height = parent.height;
    }

    public void editorUse(){
        mousePostion();
        display();
    }
    public void display(){
        if (parent.mousePressed) {
            parent.ellipse(mouseX, mouseY, 50, 50);
        }

    }

    public void mousePostion(){
        this.mouseX = parent.mouseX;
        this.mouseY = parent.mouseY;
    }


}
