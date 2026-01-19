/**
 * Q.  Write a Java program to create an interface Resizable with methods resizeWidth(int width) and resizeHeight(int height) that allow an object to be resized. Create a class Rectangle that implements the Resizable interface and implements the resize methods.


### Explanation:

1. **Resizable Interface:**
   - The `Resizable` interface is defined with two methods: `resizeWidth(int width)` and `resizeHeight(int height)`.

2. **Rectangle Class:**
   - The `Rectangle` class implements the `Resizable` interface.
   - It has private fields `width` and `height` to store the dimensions of the rectangle.
   - The class constructor initializes these fields.
   - Getter methods `getWidth()` and `getHeight()` are provided to access the dimensions.
   - The `resizeWidth` and `resizeHeight` methods are overridden to update the dimensions of the rectangle and print the new dimensions.
   - The `toPrint` method is to return a string representation of the rectangle's dimensions.

3. **Main Class:**
   - In the `main` method, a `Rectangle` object is created with initial dimensions.
   - The initial dimensions are printed.
   - The `resizeWidth` and `resizeHeight` methods are called to change the dimensions.
   - The updated dimensions are printed.
*/



interface Resizable {
    abstract void resizeWidth(int width);
    abstract void resizeHeight(int width);
}

class Rectangle implements Resizable {
    private int height;
    private int width;

    public Rectangle(int height, int width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public void resizeWidth(int width){
        this.width = width;
    }
    @Override
    public void resizeHeight(int height){
        this.height = height;
    }

    public int getWidth(){
        return this.width;
    }
    public int getHeight(){
        return this.height;
    }

    public String toPrint(){
        return String.format("Height: %d, Width, %d", this.height, this.width);
    }
}


public class ResizeableInterface{
    
    public static void main(String[] args) {
        Rectangle rec = new Rectangle(10,20);
        System.out.println("Dimensions: " + rec.toPrint());

        rec.resizeHeight(12);
        rec.resizeWidth(15);
        System.out.println("Dimensions: " + rec.toPrint());

        System.out.println("Area: " + rec.getHeight()*rec.getWidth());
    }
}