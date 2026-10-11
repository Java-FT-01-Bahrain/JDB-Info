# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Sudoku Spring Boot - Mini Project

| Title                 | Type         | Duration  | Creator       |
|-----------------------|--------------|-----------|---------------|
| Sudoku                | Mini Project | 4 days | Suresh Sigera |


## Introduction
Sudoku is a number puzzle consisting of a 9-by-9 grid of 81 cells, where each cell contains a single digit value from 1 to 9. The objective of the puzzle is to fill in the blanks to complete the grid, ensuring that each row, column, and sub-grid contains the numbers 1 through 9 exactly once.
You will set up the necessary classes and packages for the Sudoku puzzle application. This includes creating exception classes, defining the Sudoku class to handle the board, and implementing the basic functionality to load and display the puzzle.

## Objectives
- Create **custom exception** classes to handle specific errors.
- Implement the **Sudoku** class to load and display the Sudoku board.
- Define the **SudokuCell** class to represent individual cells on the board.
- Write a **SudokuSolver** class which aims to solves the game. 

## Overview

1. [The Project](#the-project)
2. [Example](#example)
3. [Puzzle Solving Strategy](#puzzle-solving-strategy)
4. [Project Overview](#project-overview)
5. [ASCII Grid Example](#ascii-grid-example)
6. [Game Design](#game-design)

## The Project

Sudoku is a number puzzle consisting of a 9-by-9 square grid of 81 cells, with each cell containing a single digit value
from 1 to 9. Initially, the grid has some cells already filled in with “hints” or “clues,” and the other cells are left
blank.

The objective of the puzzle is to fill in the blanks with numbers to complete the grid, subject to the constraints that
each row, column, and sub-grid must contain the numbers 1 through 9 exactly once. Most players use pencil with a paper
version like a crossword puzzle.

Sudoku Game Link (https://sudoku.com/).

The strategy is to focus on a specific blank cell and use the constraints to eliminate invalid choices. It is easier to
start in a sub-grid that has relatively more values than in a mostly empty part of the grid, since there will be less
constraint information available to eliminate some choices. Also, the fewer the initial hints, the more challenging the
puzzle.

### Example:

- **Column 1**
  - Present values: {3, 5, 8}
  - Missing values: {1, 2, 4, 6, 7, 9}
- **Row 7**:
  - Present values: {2, 5, 9}
  - Missing values: {1, 3, 4, 6, 7, 8}
- **Sub-grid**: 
  - Present values: {1, 2, 3, 5} 
  - Missing values: {4, 6, 7, 8, 9}

### Puzzle Solving Strategy

To fill in the element at [7][1], analyze the intersection of valid values from the row, column, and sub-grid.

For the upper example: Possible values are {4, 6, 7}. 

Continuously monitor valid options for multiple cells across rows, columns, or sub-grids to refine choices to a single value.
Initially, the puzzle is more challenging due to more blanks; it becomes easier as you progress.
Fewer initial clues make the puzzle more complex.

### Project Overview

In this Java Sudoku puzzle application project, you'll create a program that displays the Sudoku board in ASCII text format on the screen. Users can load a puzzle from a file. The development process requires no previous experience in solving Sudoku puzzles.

### ASCII Grid Example

Here’s a simple ASCII grid to represent a Sudoku board:

```text
+-------+-------+-------+
| . . . | . . . | . . . |
| . . . | . . . | . . . |
| . . . | . . . | . . . |
+-------+-------+-------+
| . . . | . . . | . . . |
| . . . | . . . | . . . |
| . . . | . . . | . . . |
+-------+-------+-------+
| . . . | . . . | . . . |
| . . . | . . . | . . . |
| . . . | . . . | . . . |
+-------+-------+-------+
```

### Game Design

- Create a Spring Boot project with:
- `exception`:
  - `InvalidCharacterException` class - To check if invalid character is present inside the game file
  - `SudokuFileNotFoundException` class - To check if the game file exists
- `Sudoku` class:
  - Method to read the `puzzle1.txt` file and generate sudoku grid
  - Method to save the generated solution file as `puzzle1.solution.txt`
  - Method to print the current game board
- `SudokuCell` class:
  - This class represents a cell of the sudoku board
- `SudokuSolver`
  - This class aims to solve the sudoku puzzle

### Game Play

```
    		Puzzle 1
0  0  0  |  1  5  0  |  0  7  0
1  0  6  |  0  0  0  |  8  2  0
3  0  0  |  8  6  0  |  0  4  0
----------------------------------

9  0  0  |  4  0  0  |  5  6  7
0  0  4  |  7  0  8  |  3  0  0
7  3  2  |  0  0  6  |  0  0  4
----------------------------------

0  4  0  |  0  8  1  |  0  0  9
0  1  7  |  0  0  0  |  2  0  8
0  5  0  |  0  3  7  |  0  0  0



		Puzzle 1 Solved
4  2  8  |  1  5  9  |  6  7  3
1  9  6  |  3  7  4  |  8  2  5
3  7  5  |  8  6  2  |  9  4  1
----------------------------------

9  8  1  |  4  2  3  |  5  6  7
5  6  4  |  7  1  8  |  3  9  2
7  3  2  |  5  9  6  |  1  8  4
----------------------------------

2  4  3  |  6  8  1  |  7  5  9
6  1  7  |  9  4  5  |  2  3  8
8  5  9  |  2  3  7  |  4  1  6



			Puzzle 2
0  2  1  |  0  0  0  |  5  4  3
0  6  0  |  5  1  0  |  8  7  2
8  7  0  |  4  0  2  |  0  6  1
----------------------------------

0  0  4  |  2  8  3  |  0  1  0
0  0  0  |  6  4  5  |  3  0  9
6  0  8  |  9  7  1  |  0  0  5
----------------------------------

3  0  9  |  0  2  7  |  1  0  6
2  5  0  |  1  9  4  |  7  3  8
1  0  0  |  0  5  0  |  0  9  0



		Puzzle 2 Solved
9  2  1  |  7  6  8  |  5  4  3
4  6  3  |  5  1  9  |  8  7  2
8  7  5  |  4  3  2  |  9  6  1
----------------------------------

5  9  4  |  2  8  3  |  6  1  7
7  1  2  |  6  4  5  |  3  8  9
6  3  8  |  9  7  1  |  4  2  5
----------------------------------

3  4  9  |  8  2  7  |  1  5  6
2  5  6  |  1  9  4  |  7  3  8
1  8  7  |  3  5  6  |  2  9  4



			Puzzle 3
0  0  0  |  7  0  8  |  0  0  0
0  6  0  |  0  0  9  |  8  0  0
0  0  0  |  4  3  0  |  0  0  0
----------------------------------

0  0  0  |  0  0  0  |  6  0  0
0  1  0  |  0  0  5  |  0  8  9
0  3  8  |  0  0  0  |  4  0  0
----------------------------------

3  4  0  |  8  0  7  |  1  0  6
0  5  6  |  0  9  0  |  7  3  0
1  0  0  |  0  5  0  |  0  9  0



		Puzzle 3 Solved
9  2  1  |  7  6  8  |  5  4  3
4  6  3  |  5  1  9  |  8  7  2
8  7  5  |  4  3  2  |  9  6  1
----------------------------------

5  9  4  |  2  8  3  |  6  1  7
7  1  2  |  6  4  5  |  3  8  9
6  3  8  |  9  7  1  |  4  2  5
----------------------------------

3  4  9  |  8  2  7  |  1  5  6
2  5  6  |  1  9  4  |  7  3  8
1  8  7  |  3  5  6  |  2  9  4



			Puzzle 4
5  3  0  |  0  7  0  |  0  0  0
6  0  0  |  1  9  5  |  0  0  0
0  9  8  |  0  0  0  |  0  6  0
----------------------------------

8  0  0  |  0  6  0  |  0  0  3
4  0  0  |  8  0  3  |  0  0  1
7  0  0  |  0  2  0  |  0  0  6
----------------------------------

0  6  0  |  0  0  0  |  2  8  0
0  0  0  |  4  1  9  |  0  0  5
0  0  0  |  0  8  0  |  0  7  9



		Puzzle 4 Solved
5  3  4  |  6  7  8  |  9  1  2
6  7  2  |  1  9  5  |  3  4  8
1  9  8  |  3  4  2  |  5  6  7
----------------------------------

8  5  9  |  7  6  1  |  4  2  3
4  2  6  |  8  5  3  |  7  9  1
7  1  3  |  9  2  4  |  8  5  6
----------------------------------

9  6  1  |  5  3  7  |  2  8  4
2  8  7  |  4  1  9  |  6  3  5
3  4  5  |  2  8  6  |  1  7  9




Process finished with exit code 0
```

## Bonus

- Add interactivity to your application.
- Difficulty Levels: Implement different difficulty levels (easy, medium, hard) by providing puzzles with varying numbers of initially filled cells.
- Timer: Add a timer to track how long it takes to solve the puzzle.
- Hint System: Implement a hint system that provides hints for solving difficult cells.
- Input Validation: Validate user input to ensure only numbers 1-9 are entered.
- Error Highlighting: Highlight cells that violate Sudoku rules.
- Score System: Introduce a scoring system based on the time taken and the number of hints used.
- Save/Load: Allow users to save their current game state and load it later.