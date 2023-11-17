package background_work;

public class Constants {
    public static int width;
    public static int height;
    public static boolean getScreenSize = false;
    public Constants(int width, int height, boolean getScreenSize) {
        Constants.width = width;
        Constants.height = height;
        Constants.getScreenSize = getScreenSize;
    }
    public static int getWidth(){
        return width;
    }
    public static int getHeight(){
        return height;
    }
}
