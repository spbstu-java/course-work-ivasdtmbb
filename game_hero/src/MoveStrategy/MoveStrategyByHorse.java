package MoveStrategy;

import Hero.Hero;

public class MoveStrategyByHorse implements MoveStrategy {
    public void move(Hero hero, Direction direction) {
        int horseSpeed = 2;

        System.out.println("The Hero rides a horse " + horseSpeed + " m " + direction);


        switch (direction) {
            case NORTH:
                hero.setY(hero.getY() + horseSpeed);
                break;
            case SOUTH:
                hero.setY(hero.getY() - horseSpeed);
                break;
            case WEST:
                hero.setX(hero.getX() - horseSpeed);
                break;
            case EAST:
                hero.setX(hero.getX() + horseSpeed);
                break;
        }
    }
}