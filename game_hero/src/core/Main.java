package game_hero.core;

import Hero.*;
import MoveStrategy.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    play();
    }

    static void play() {
        Scanner scanner = new Scanner(System.in);
        Hero famousHero = new Hero();

        String instruction = """
            Choose action:
            1 - Move by walk
            2 - Move by horse
            3 - Move by flight
            4 - Do move
            0 - Exit
            Your choise:\s""";

        System.out.println(instruction);
        while (true) {
            if(scanner.hasNextInt()){
                int userInput = scanner.nextInt();

                switch (userInput) {
                    case 0:
                        System.out.println("The Hero has finished his journey!");
                        System.out.println("Final position: x=" + famousHero.getX() + " y=" + famousHero.getY());
                        scanner.close();
                        return;

                    case 1:
                        famousHero.setMoveStrategy(new MoveStrategyByWalk());
                        System.out.println("By walk");
                        break;

                    case 2:
                        famousHero.setMoveStrategy(new MoveStrategyByHorse());
                        System.out.println("On horse");
                        break;

                    case 3:
                        famousHero.setMoveStrategy(new MoveStrategyByFlight());
                        System.out.println("By flight");
                        break;

                    case 4:
                        String instructionDirection = """
                                Choose direction:
                                1 - North
                                2 - South
                                3 - West
                                4 - East
                                Your choice:\s""";
                        System.out.println(instructionDirection);

                        if (scanner.hasNextInt()) {
                            int directionChoice = scanner.nextInt();
                            Direction direction = getDirection(directionChoice);

                            if (direction != null) {
                                famousHero.move(direction);
                            } else {
                                System.out.println("Invalid direction!");
                            }
                        } else {
                            System.out.println("Please enter a number!");
                            scanner.next();
                        }
                        break;

                    default:
                        System.out.println("Unknown command!");
                }

                System.out.println("\n" + instruction);

            } else {
                System.out.println("Please enter a number!");
                System.out.println(instruction);
                scanner.next();
            }
        }
    }

    private static Direction getDirection(int choice) {
        return switch (choice) {
            case 1 -> Direction.NORTH;
            case 2 -> Direction.SOUTH;
            case 3 -> Direction.WEST;
            case 4 -> Direction.EAST;
            default -> null;
        };
    }
}
