import processing.core.PApplet;
import background_work.Constants;

public class Peggle extends PApplet{

  public int BACKGROUND = 0;

  int width = 644;
  int height = 542;

  public void settings()
  {
    size(644,542);
  }

  Editor editor;

  public void setup()
  {
    surface.setResizable(true);
    frameRate(60);
    background(color(BACKGROUND));
    editor = new Editor(this);
    Constants.height = displayHeight;
    Constants.width = displayWidth;
  }

  public void draw()
  {
    background(color(BACKGROUND));
    editor.editorUse();
    surface.setSize(width, height);
    width ++;
    height ++;
  }

  public void keyReleased(){
    editor.pKeyPressed = true;
  }


}