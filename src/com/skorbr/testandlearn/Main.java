package com.skorbr.testandlearn;

import java.util.Scanner;

public class Main {

    static int xCount = 0;
    static int oCount = 0;
    static double counterMove = 0;
    static boolean validate = true;
    static boolean gameOver = false;
    static boolean xWins = false;
    static boolean oWins = false;
    static boolean draw = false;
    static int indexToChange = 0;
    static int xCoordinate;
    static int yCoordinate;
    static char[] chars = {'_', '_', '_', '_', '_', '_', '_', '_', '_', '_'};

    public static void main(String[] args) {
        //parse char array to string array that uses in printing game field
        String[][] arr = parseString(chars);
        //printing game field
        printField(arr);
        while (gameOver == false) {
            //check coordinates
            validateCoordinates();
            //*
            arr = parseString(chars);
            printField(arr);
            //game over check
            checkGameOver((int) counterMove);
            if (gameOver == true) {
                if (xWins == true) {
                    System.out.println("X wins");
                } else if (oWins == true) {
                    System.out.println("O wins");
                } else if (draw == true) {
                    System.out.println("Draw");
                }
            }
        }
    }

    public static String[][] parseString(char[] chars) {
        String[][] arr = new String[3][3];
        int n = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                arr[i][j] = String.valueOf(chars[j + n]);
                //считаем количество X и O
                if (arr[i][j].equals("X")) {
                    xCount++;
                } else if (arr[i][j].equals("O")) {
                    oCount++;
                }
            }
            n += 3;
        }
        return arr;
    }

    public static void printField(String[][] arr) {
        System.out.println("---------");
        for (int i = 0; i < arr.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");
    }

    public static void checkGameOver(int counterMove) {
        int lineOne = chars[0] + chars[1] + chars[2];
        int lineTwo = chars[3] + chars[4] + chars[5];
        int lineThree = chars[6] + chars[7] + chars[8];
        int lineOneHor = chars[0] + chars[3] + chars[6];
        int lineTwoHor = chars[1] + chars[4] + chars[7];
        int lineThreeHor = chars[2] + chars[5] + chars[8];
        int diagonalOne = chars[0] + chars[4] + chars[8];
        int diagonalTwo = chars[2] + chars[4] + chars[6];

        if (lineOne == 264 || lineTwo == 264 || lineThree == 264
                || lineOneHor == 264 || lineTwoHor == 264 || lineThreeHor == 264
                || diagonalOne == 264 || diagonalTwo == 264) {
            xWins = true;
            gameOver = true;
        }

        if (lineOne == 237 || lineTwo == 237 || lineThree == 237
                || lineOneHor == 237 || lineTwoHor == 237 || lineThreeHor == 237
                || diagonalOne == 237 || diagonalTwo == 237) {
            oWins = true;
            gameOver = true;
        }

        if (counterMove == 9) {
            draw = true;
            gameOver = true;
        }
    }

    public static void validateCoordinates() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String[] nums = scanner.nextLine().split(" ");
                xCoordinate = Integer.parseInt(nums[0]);
                yCoordinate = Integer.parseInt(nums[1]);
            } catch (Exception e) {
                System.err.println("You should enter numbers!");
                continue;
            }

            validate = true;
            switch (xCoordinate) {
                case 1:
                    break;
                case 2:
                    indexToChange += 1;
                    break;
                case 3:
                    indexToChange += 2;
                    break;
                default:
                    System.err.println("Coordinates should be from 1 to 3!");
                    validate = false;
                    continue;
            }
            switch (yCoordinate) {
                case 1:
                    indexToChange += 6;
                    break;
                case 2:
                    indexToChange += 3;
                    break;
                case 3:
                    break;
                default:
                    System.err.println("Coordinates should be from 1 to 3!");
                    validate = false;
                    continue;
            }

            for (int i = 0; i < chars.length; i++) {
                if (i == indexToChange) {
                    if (chars[i] == '_') {
                        chars[i] = counterMove % 2 == 0 ? 'X' : 'O';
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                        validate = false;
                    }
                    indexToChange = 0;
                }
            }

            if (validate == true) {
                counterMove++;
                break;
            }
        }
    }
}
