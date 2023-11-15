import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
public class Editor {
    public PImage currentPeg;
    public PImage currentPegGrey;
    public PImage pegBlock;
    public PImage pegBlockGrey;
    public PImage pegCircle;
    public PImage pegCircleGrey;
    public int width;
    public int height;
    public PApplet parent;
    public int mouseX;
    public int mouseY;
    public int xSize = 25;
    public int ySize = 14;
    public int rotation = 0;
    public ArrayList<Integer> xPoints = new ArrayList<>();
    public ArrayList<Integer> yPoints = new ArrayList<>();
    public ArrayList<PImage>  pegType = new ArrayList<>();
    public ArrayList<Integer> pointRotation = new ArrayList<>();
    public ArrayList<Integer> xSizeList = new ArrayList<>();
    public ArrayList<Integer> ySizeList = new ArrayList<>();
    public ArrayList<Integer> xPointsUndo = new ArrayList<>();
    public ArrayList<Integer> yPointsUndo = new ArrayList<>();
    public ArrayList<Integer> pointsRotationUndo = new ArrayList<>();
    public ArrayList<PImage>  pegTypeUndo = new ArrayList<>();
    public ArrayList<Integer> xSizeListUndo = new ArrayList<>();
    public ArrayList<Integer> ySizeListUndo = new ArrayList<>();

    public Editor(PApplet parent) {
        this.parent = parent;
        this.width = parent.width;
        this.height = parent.height;
        this.pegBlock = parent.loadImage("images/PegBlock.png");
        this.pegBlockGrey = parent.loadImage("images/PegBlock.png");
        this.pegBlockGrey.filter(parent.GRAY);
        this.pegCircle = parent.loadImage("images/PegBall.png");
        this.pegCircleGrey = parent.loadImage("images/PegBall.png");
        this.pegBlockGrey.filter(parent.GRAY);

        currentPeg = pegBlock;
        currentPegGrey = pegBlockGrey;

    }

    public void editorUse(){
        undo();
        redo();
        rotate();
        mousePosition();
        display();
        changePegType();
    }

    public void display(){
        for (int x = 0; x < xPoints.size(); x++){
            parent.pushMatrix();
            parent.translate(xPoints.get(x), yPoints.get(x));
            parent.rotate(pointRotation.get(x) * (parent.PI / 180));
            parent.image(pegType.get(x), 0 - (float) xSizeList.get(x) /2, 0 - (float) ySizeList.get(x) /2, xSizeList.get(x), ySizeList.get(x)); // Draw at the center (0, 0) of the image
            parent.popMatrix();
        }
        parent.pushMatrix();
        parent.translate(mouseX, mouseY); // Translate to the mouse position
        parent.rotate(rotation * (parent.PI / 180));
        parent.image(currentPegGrey, (float) -xSize /2, (float) -ySize /2, xSize, ySize); // Draw at the center (0, 0) of the image
        parent.popMatrix();
    }

    public void mousePosition(){
        this.mouseX = parent.mouseX;
        this.mouseY = parent.mouseY;
        if (parent.mousePressed) {
            xPoints.add(mouseX);
            yPoints.add(mouseY);
            pointRotation.add(rotation);
            pegType.add(currentPeg);
            xSizeList.add(xSize);
            ySizeList.add(ySize);
            System.out.println(rotation);
            xPointsUndo = new ArrayList<>();
            yPointsUndo = new ArrayList<>();
            pointsRotationUndo = new ArrayList<>();
            pegTypeUndo = new ArrayList<>();
        }
    }


    public void undo() {
        if (parent.keyPressed && parent.key == 'z') {
            if (!xPoints.isEmpty()) {
                xPointsUndo.add(xPoints.get(xPoints.size() - 1));
                yPointsUndo.add(yPoints.get(yPoints.size() - 1));
                pointsRotationUndo.add(pointRotation.get(pointRotation.size() - 1));
                pegTypeUndo.add(pegType.get(pegType.size() - 1));
                xSizeListUndo.add(xSizeList.get(xSizeList.size() - 1));
                ySizeListUndo.add(ySizeList.get(ySizeList.size() - 1));
                xPoints.remove(xPoints.size() - 1);
                yPoints.remove(yPoints.size() - 1);
                pointRotation.remove(pointRotation.size() - 1);
                pegType.remove(pegType.size() - 1);
                xSizeList.remove(xSizeList.size() - 1);
                ySizeList.remove(ySizeList.size() - 1);
            }
        }
    }
    public void redo() {
        if (parent.keyPressed && parent.key == 'y') {
            System.out.println("redo");
            if (!xPointsUndo.isEmpty()) {
                xPoints.add(xPointsUndo.get(xPointsUndo.size() - 1));
                yPoints.add(yPointsUndo.get(yPointsUndo.size() - 1));
                pointRotation.add(pointsRotationUndo.get(pointsRotationUndo.size() - 1));
                pegType.add(pegTypeUndo.get(pegTypeUndo.size() - 1));
                xSizeList.add(xSizeListUndo.get(xSizeListUndo.size() - 1));
                ySizeList.add(ySizeListUndo.get(ySizeListUndo.size() - 1));
                xPointsUndo.remove(xPointsUndo.size() - 1);
                yPointsUndo.remove(yPointsUndo.size() - 1);
                pointsRotationUndo.remove(pointsRotationUndo.size() - 1);
                pegTypeUndo.remove(pegTypeUndo.size() - 1);
                xSizeListUndo.remove(xSizeListUndo.size() - 1);
                ySizeListUndo.remove(ySizeListUndo.size() - 1);
            }
        }
    }

    public void rotate(){
        if (parent.keyPressed && parent.key == 'r') {
            rotation += 1;
        }
        if (rotation >= 360) {
            rotation = 0;
        }
    }
    public void changePegType(){
        if (parent.keyPressed && parent.key == 'b') {
            currentPeg = pegBlock;
            currentPegGrey = pegBlockGrey;
            xSize = 25;
            ySize = 14;
        }
        if (parent.keyPressed && parent.key == 'c') {
            currentPeg = pegCircle;
            currentPegGrey = pegCircleGrey;
            xSize = 25;
            ySize = 25;
        }
    }
}

