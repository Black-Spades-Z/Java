/*
* Name : Azizbek Muminjonov
* ID : U2110207
*/


interface GeometricComparable
{
    public abstract boolean equalTo(Object o);
}

class Rectangle implements GeometricComparable
{
    private double height;
    private double width;
    static int numberOfRectangles = 0;

    Rectangle(){
        numberOfRectangles++;
}
    Rectangle(Rectangle r){
        numberOfRectangles++;
}
    Rectangle(double height, double width)
    {
        this.height = height;
        this.width = width;
        numberOfRectangles++;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }
    public double getArea()
    {
        return getHeight() * getWidth();
    }
    public static int getNumberOfRectangles()
    {
        return numberOfRectangles;
    }

    public boolean equalTo(Object o)
    {
        if (o instanceof  Rectangle)
        	if( this.height == ((Rectangle)o).getHeight() && this.width == ((Rectangle)o).getWidth())
		return true;
        return false;
    }

}



public class InterfaceDemo {

    public static void main(String[] args)
    {
        Rectangle rec1 = new Rectangle ();
        System.out.println ( "Rectangle object number "+Rectangle.getNumberOfRectangles());

        Rectangle rec2 = new Rectangle (10.4, 20.2);
        System.out.println ( "Rectangle object number "+Rectangle.getNumberOfRectangles());

        Rectangle rec3 = new Rectangle (rec1);
        System.out.println ( "Rectangle object number "+Rectangle.getNumberOfRectangles());

        System.out.println ( "Rectangle object rec1 and rec2 are equal " + rec1.equalTo(rec2));
        System.out.println ( "Rectangle object rec1 and rec3 are equal " + rec1.equalTo(rec3));


    }



}
