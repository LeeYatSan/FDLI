package UI_main;

//绘制的颜色
public class TColor
{
    int red;
    int green;
    int blue;
    public TColor(){red = 255;green = 0;blue = 0;}
    public TColor(int r,int g,int b){red = r;green = g;blue = b;}
    public void setValue(int r,int g,int b){
        red = r;green = g; blue = b;
    }
}