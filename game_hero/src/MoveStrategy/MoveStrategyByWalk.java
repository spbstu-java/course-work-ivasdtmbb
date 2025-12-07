package MoveStrategy;

import Hero.Hero;

public class MoveStrategyByWalk implements MoveStrategy {
    public void move(Hero hero, Direction direction) {
        int walkSpeed = 1;

        System.out.println("The Hero walks " + walkSpeed + " m " + direction);


        switch (direction) {
            case NORTH:
                hero.setY(hero.getY() + walkSpeed);
                break;
            case SOUTH:
                hero.setY(hero.getY() - walkSpeed);
                break;
            case WEST:
                hero.setX(hero.getX() - walkSpeed);
                break;
            case EAST:
                hero.setX(hero.getX() + walkSpeed);
                break;
        }
    }
}