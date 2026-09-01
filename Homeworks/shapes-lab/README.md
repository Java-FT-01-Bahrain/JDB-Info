# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Sub Classes with Shapes Lab

|          Title          | Type | Duration |       Creator        |
|:-----------------------:|:----:|:--------:|:--------------------:|
| Sub Classes with Shapes | Lab  |   1:30   | Suresh Melvin Sigera |


## Sub Classes with Shapes

### Part 1 (5 min)

Let's start by coding out this first part together. Say we have a superclass called `Shape`, as follows:

```java
package com.generalassembly.oop;

public class Shape {
    public double getCircumference() {
        return 0;
 }

    public double getArea() {
        return 0;
 }
}
```

Our `Shape` class has methods `getCircumference()` and `getArea()` that, depending on the shape, will calculate the
circumference and area of the shape.

As we define some subclasses to inherit from `Shape`, remember that each of these shapes has a slightly different
function to determine its area or circumference.

Let's start by defining a subclass of `Shape` called `Triangle` that takes three sides in its constructor:

```java
package com.generalassembly.oop;

public class Triangle extends Shape {
    private double side1;
    private double side2;
    private double side3;

    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
 }
}
```

### Part 2 (15 min)

Your assignment is implementing the `getCircumference()` and `getArea()` methods.

The circumference (or perimeter) is the length of the shape's outline. So, for a triangle, it's the sum
of the sides, and for a circle, it's 2πr, where r is the radius, and π is `Math.PI`.

The area is the square footage covered by the shape. For a triangle, we can use Heron's formula:

 ```
 √(p(p−a)*(p−b)*(p−c))    
 ```

In this case, `p = (a+b+c)/2`. Use the `Math.sqrt()` method to take a square root.

#### Part 2: Bonus

Do the same for `Circle` and `Rectangle`. Remember: These should all extend the `Shape` class.

### Part 3 (5 min)

Let's say we want to create a `Square` shape. We remember from geometry class that a square is a special kind of
rectangle in which all sides are equal.

Let's create a `Square` class that extends `Rectangle` and, instead of overriding the `getCircumference()`
and `getArea()` methods provide a constructor that will pass in the same value for all sides into the `Rectangle`
constructor.

> **Knowledge Check**: How do you think we should implement this? Shout out some possible solutions.

Now, this `Square` will automatically calculate its circumference and area.

### Part 4 (15 min)

Finally, let's test our code by calculating the circumference and area for each of the following four shapes:

* A triangle with sides 3, 4, 5.
* A rectangle with length = 4 and height = 5.
* A circle with radius = 4.
* A square with side = 4.

Assign each one of these shapes to a variable of the appropriate type, then pass that shape to a method with signature:

```java
private static String getCircumferenceAndArea(Shape shape){
    
}
```

That will return a `String` such as `ShapeType circumference, area = x, y`, with the correct values for each shape.

**Sample expected output:**

```text
Triangle circumference,area=12.0,6.0
Rectangle circumference,area=18.0,20.0
Circle circumference,area=25.132741228718345,50.26548245743669
Square circumference,area=16.0,16.0
```


