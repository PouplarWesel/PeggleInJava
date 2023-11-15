import processing.core.PApplet;

public class Peggle extends PApplet{

  public int BACKGROUND = 0;

  public void settings()
  {
    fullScreen();
  }

  Editor editor;

  public void setup()
  {    
    frameRate(60);
    background(color(BACKGROUND));
    editor = new Editor(this);
  }

  public void draw()
  {
    background(color(BACKGROUND));
    editor.editorUse();
  }

  
}