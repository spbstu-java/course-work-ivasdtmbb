package MoveStrategy;

import Hero.Hero;

public class MoveStrategyByFlight implements MoveStrategy {
    public void move(Hero hero, Direction direction) {
        int flySpeed = 3;

        System.out.println("The Hero flies " + flySpeed + " m " + direction);


        switch (direction) {
            case NORTH:
                hero.setY(hero.getY() + flySpeed);
                break;
            case SOUTH:
                hero.setY(hero.getY() - flySpeed);
                break;
            case WEST:
                hero.setX(hero.getX() - flySpeed);
                break;
            case EAST:
                hero.setX(hero.getX() + flySpeed);
                break;
        }
    }
}