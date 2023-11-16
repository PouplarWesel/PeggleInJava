import org.json.JSONArray;
import org.json.JSONObject;
import processing.core.PApplet;
import processing.core.PImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class Editor {
    public PImage currentPeg;
    public PImage currentPegGrey;
    public PImage pegBlock;
    public PImage pegBlockGrey;
    public PImage pegCurved;
    public PImage pegCurvedGrey;
    public PImage pegCircle;
    public PImage pegCircleGrey;
    public int width;
    public int height;
    public PApplet parent;
    public int mouseX;
    public int mouseY;
    public float xSize = 25;
    public float ySize = 14;
    public int rotation = 0;
    public String currentPegType;
    public ArrayList<String> imagePathList = new ArrayList<>();
    public ArrayList<Integer> xPoints = new ArrayList<>();
    public ArrayList<Integer> yPoints = new ArrayList<>();
    public ArrayList<PImage>  pegType = new ArrayList<>();
    public ArrayList<Integer> pointRotation = new ArrayList<>();
    public ArrayList<Float> xSizeList = new ArrayList<>();
    public ArrayList<Float> ySizeList = new ArrayList<>();
    public ArrayList<Integer> xPointsUndo = new ArrayList<>();
    public ArrayList<Integer> yPointsUndo = new ArrayList<>();
    public ArrayList<Integer> pointsRotationUndo = new ArrayList<>();
    public ArrayList<PImage>  pegTypeUndo = new ArrayList<>();
    public ArrayList<Float> xSizeListUndo = new ArrayList<>();
    public ArrayList<Float> ySizeListUndo = new ArrayList<>();
    public ArrayList<String> imagePathListUndo = new ArrayList<>();

    public Editor(PApplet parent) {
        this.parent = parent;
        this.width = parent.width;
        this.height = parent.height;
        this.pegBlock = parent.loadImage("images/PegBlock.png");
        this.pegBlockGrey = parent.loadImage("images/PegBlock.png");
        this.pegBlockGrey.filter(parent.GRAY);
        this.pegCircle = parent.loadImage("images/PegBall.png");
        this.pegCircleGrey = parent.loadImage("images/PegBall.png");
        this.pegCircleGrey.filter(parent.GRAY);
        this.pegCurved = parent.loadImage("images/PegCurved.png");
        this.pegCurvedGrey = parent.loadImage("images/PegCurved.png");
        this.pegCurvedGrey.filter(parent.GRAY);

        currentPeg = pegBlock;
        currentPegGrey = pegBlockGrey;
        currentPegType = "images/PegBlock.png";



    }

    public void editorUse(){
        undo();
        redo();
        rotate();
        mousePosition();
        display();
        changePegType();
        scale();
        save();
    }

    public void display(){
        for (int x = 0; x < xPoints.size(); x++){
            parent.pushMatrix();
            parent.translate(xPoints.get(x), yPoints.get(x));
            parent.rotate(pointRotation.get(x) * (parent.PI / 180));
            parent.image(pegType.get(x), 0 - xSizeList.get(x) /2, 0 - ySizeList.get(x) /2, xSizeList.get(x), ySizeList.get(x)); // Draw at the center (0, 0) of the image
            parent.popMatrix();
        }
        parent.pushMatrix();
        parent.translate(mouseX, mouseY); // Translate to the mouse position
        parent.rotate(rotation * (parent.PI / 180));
        parent.image(currentPegGrey, -xSize /2, -ySize /2, xSize, ySize); // Draw at the center (0, 0) of the image
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

            imagePathList.add(currentPegType);
            xPointsUndo = new ArrayList<>();
            yPointsUndo = new ArrayList<>();
            pointsRotationUndo = new ArrayList<>();
            pegTypeUndo = new ArrayList<>();
            xSizeListUndo = new ArrayList<>();
            ySizeListUndo = new ArrayList<>();
            imagePathListUndo = new ArrayList<>();

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
                imagePathListUndo.add(imagePathList.get(imagePathList.size() - 1));
                xPoints.remove(xPoints.size() - 1);
                yPoints.remove(yPoints.size() - 1);
                pointRotation.remove(pointRotation.size() - 1);
                pegType.remove(pegType.size() - 1);
                xSizeList.remove(xSizeList.size() - 1);
                ySizeList.remove(ySizeList.size() - 1);
                imagePathList.remove(imagePathList.size() - 1);
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
                imagePathList.add(imagePathListUndo.get(imagePathListUndo.size() - 1));
                xPointsUndo.remove(xPointsUndo.size() - 1);
                yPointsUndo.remove(yPointsUndo.size() - 1);
                pointsRotationUndo.remove(pointsRotationUndo.size() - 1);
                pegTypeUndo.remove(pegTypeUndo.size() - 1);
                xSizeListUndo.remove(xSizeListUndo.size() - 1);
                ySizeListUndo.remove(ySizeListUndo.size() - 1);
                imagePathListUndo.remove(imagePathListUndo.size() - 1);
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
            if (currentPeg != pegBlock) {
                currentPeg = pegBlock;
                currentPegGrey = pegBlockGrey;
                xSize = 25;
                ySize = 14;
                /// make it yellow
                currentPegType = "images/PegBlock.png";
            }
        }
        if (parent.keyPressed && parent.key == 'c') {
            currentPeg = pegCircle;
            currentPegGrey = pegCircleGrey;
            xSize = 25;
            ySize = 25;
            currentPegType = "images/PegBall.png";
        }
        if (parent.keyPressed && parent.key == 'u') {
            currentPeg = pegCurved;
            currentPegGrey = pegCurvedGrey;
            xSize = 23 * 2;
            ySize = 13 * 2;
            currentPegType = "images/PegCurved.png";
        }
    }
    public void scale(){
        if (parent.keyPressed && parent.key == 's') {
            xSize *= 1.1f;
            ySize *= 1.1f;
        }
        if (parent.keyPressed && parent.key == 'a') {
            xSize *= 0.9f;
            ySize *= 0.9f;
        }
        if (parent.keyPressed && parent.key == 'x') {
            rotation =0;
            if (currentPeg == pegBlock) {
                xSize = 25;
                ySize = 14;
            }
            if (currentPeg == pegCircle) {
                xSize = 25;
                ySize = 25;
            }
            if (currentPeg == pegCurved) {
                xSize = 23 * 2;
                ySize = 13 * 2;
            }
        }
    }
    public void save() {
        if (parent.keyPressed && parent.key == 'p') {
            JSONArray pegsArray = getObjects();
            String filename = "level/pegs.json";
            int counter = 1;
            while (new File(filename).exists()) {
                filename = "level/level_" + counter + ".json";
                counter++;
            }
            try (FileWriter file = new FileWriter(filename)) {
                file.write(pegsArray.toString());
                System.out.println("Successfully Copied JSON Object to File...");
                System.out.println("\nJSON Object: " + pegsArray);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private JSONArray getObjects() {
        JSONArray pegsArray = new JSONArray();
        for (int i = 0; i < xPoints.size(); i++) {
            JSONObject pegObject = new JSONObject();
            pegObject.put("xPoint", xPoints.get(i));
            pegObject.put("yPoint", yPoints.get(i));
            pegObject.put("rotation", pointRotation.get(i));
            pegObject.put("xSize", xSizeList.get(i));
            pegObject.put("ySize", ySizeList.get(i));
            pegObject.put("imagePath", imagePathList.get(i));
            pegsArray.put(pegObject);
        }
        return pegsArray;
    }
}

