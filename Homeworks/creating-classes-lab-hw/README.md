# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Creating Classes HW

## Java Object Modeling

### Objectives

- Practice with classes, objects, methods.

### Activity

- Create a file named `MBTA.java` to calculate the number of stops between
  stations on the "MBTA". Write additional functions used by this function as
  needed.
- The function takes the line and stop that a rider is getting on at and the
  line and stop that a rider is getting off at and **returns the total number of
  stops for the trip**.

There are 3 subway lines:

- Red
  - South Station
  - Park Street
  - Kendall
  - Central
  - Harvard
  - Porter
  - Davis
  - Alewife
- Green
  - Government Center
  - Park Street
  - Boylston
  - Arlington
  - Copley
  - Hynes
  - Kenmore
- Orange
  - North Station
  - Haymarket
  - Park Street
  - State
  - Downtown Crossing
  - Chinatown
  - Back Bay
  - Forest Hills
- All 3 subway lines intersect at _Park Street_, but there are no other intersection points.

The function will be invoked using the following pattern of arguments:

`Start Line`, `Start Station`, `End Line`, `End Station`

Here are some examples of how should be invoked.

```java
stopsBetweenStations("Red", "Alewife", "Red", "Alewife"); // 0 stops
stopsBetweenStations("Red", "Alewife", "Red", "South Station"); // 7 stops
stopsBetweenStations("Red", "South Station", "Green", "Kenmore"); // 6 stops
```

### Hints

- Consider diagramming the lines by sketching out the subway lines and their
  stops and intersection.
- Solve an easier problem first like only traveling on the Red line.
- Assume good input. Your function does not need to check the validity of the line or
  stop.
- Subway lines are keys in a dictionary (object literal or HashMap), while the values are
  a list (array) of all the stops on each line.
- The key to the practice is to find the **intersection** of the lines at
  _Park Street_.

## Bonus (Optional)

- Add validation so that the function only accepts Red, Green, and Orange for lines and only accepts the correct stops for each of their stops.
- Have the function print the line and stops as they ride the train.
- Example:

```java
stopsBetweenStations("Red", "South Station", "Green", "Copley");

// Rider boards the train a Red Line and South Station.
// Rider arrives at Red Line and Park Street.
// Rider transfers from Red Line to Green Line at Park Street.
// Rider arrives at Green Line and Boylston.
// Rider arrives at Green Line and Arlington.
// Rider arrives at Green Line and Copley.
// Rider exits the train at Green Line and Copley.
```

### [License](LICENSE)

1. All content is licensed under a CC­BY­NC­SA 4.0 license.
1. All software code is licensed under GNU GPLv3. For commercial use or
   alternative licensing, please contact legal@ga.co.
