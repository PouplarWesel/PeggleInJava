import processing.core.PApplet;

public class Peggle extends PApplet{

  public int BACKGROUND = 0;

  public void settings()
  {
    fullScreen();
  }

  Editor editor = new Editor(this);

  public void setup()
  {    
    frameRate(60);
    background(color(BACKGROUND));
  }

  public void draw()
  {
    editor.editorUse();
  }

  
}