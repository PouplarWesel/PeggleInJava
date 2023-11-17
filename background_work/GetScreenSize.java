package background_work;

import processing.core.PApplet;

public class GetScreenSize extends PApplet{

    public void settings()
    {
        fullScreen();
    }

    public void setup()
    {
    }

    public void draw()
    {
        System.out.println(height + " " +  width);
        Constants constants = new Constants(width, height, true);
        exit();
    }




}