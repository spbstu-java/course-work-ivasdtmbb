package Hero;

import MoveStrategy.*;

public class Hero {
    private MoveStrategy moveStrategy;
    private int x, y;

    public Hero() {
        this.x = 0;
        this.y = 0;
        this.moveStrategy = new MoveStrategyByWalk();
    }

    public void setMoveStrategy(MoveStrategy moveStrategy) {
        if (moveStrategy == null) {
            throw new IllegalArgumentException("MoveStrategy cannot be null");
        }
        this.moveStrategy = moveStrategy;
    }

    public void move(Direction direction) {
        moveStrategy.move(this, direction);
        System.out.println("Current position: x=" + x + " y=" + y);
    }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    public int getX() { return x; }
    public int getY() { return y; }
}
