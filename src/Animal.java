import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.io.File;


public abstract class Animal {

    protected static final int X = 0;
    protected static final int Y = 1;
    public static final int DEFAULT_INVALID_POINT = -2;

    protected int x, y;
    static String filename;
    protected File f;
    protected Image image;
    protected static Animal[][] antarktis;

    public Animal(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        boolean isMovedToEat = false;
        int firstWithoutPredatorX = DEFAULT_INVALID_POINT;
        int firstWithoutPredatorY = DEFAULT_INVALID_POINT;

        for (Step step : getStepsSequence()) {

            int[] pos = getPosByStep(step);

            int dx = pos[X];
            int dy = pos[Y];

            if (!isPredator(dx, dy)) {

                if (firstWithoutPredatorX == DEFAULT_INVALID_POINT
                        && firstWithoutPredatorY == DEFAULT_INVALID_POINT) {
                    firstWithoutPredatorX = dx;
                    firstWithoutPredatorY = dy;
                }

                if (canEat(antarktis[dx][dy])) {
                    antarktis[x][y] = null;
                    x = dx;
                    y = dy;
                    antarktis[x][y] = this;
                    isMovedToEat = true;
                    break;
                }
            }
        }

        if (!isMovedToEat
            && firstWithoutPredatorX != DEFAULT_INVALID_POINT
                && firstWithoutPredatorY != DEFAULT_INVALID_POINT) {
            antarktis[x][y] = null;
            x = firstWithoutPredatorX;
            y = firstWithoutPredatorY;
            antarktis[x][y] = this;
        }
    }

    protected boolean isPredator(int x, int y) {
        boolean isPredator = false;
        for (Step step : getStepsSequence()) {
            int[] predatorPos = getPosByStep(step);
            Animal predator = antarktis[predatorPos[X]][predatorPos[Y]];
            if (predator != null
                    && predator.canEat(this)) {
                isPredator = true;
                break;
            }
        }
        return isPredator;
    }

    protected int[] getPosByStep(Step step) {
        int dx = x;
        int dy = y;

        switch (step) {
            case UP:
                dy = dy - 1;
                break;
            case DOWN:
                dy = dy + 1;
                break;
            case RIGHT:
                dx = dx + 1;
                break;
            case LEFT:
                dx = dx - 1;
                break;
            default:
                throw new IllegalArgumentException("Unsupported step: " + step.name());
        }

        if(dx < 0) {
            dx = antarktis.length - 1;
        } else if (dx >= antarktis.length) {
            dx = 0;
        }

        if(dy < 0) {
            dy = antarktis[x].length - 1;
        } else if (dy >= antarktis[x].length) {
            dy = 0;
        }
        return new int[]{dx, dy};
    }

    protected Step[] getStepsSequence() {
        return new Step[]{Step.LEFT, Step.DOWN, Step.RIGHT, Step.UP};
    }

    public abstract boolean canEat(Animal animal);

    protected abstract boolean eatenBy(Penguin penguin);

    protected abstract boolean eatenBy(PlayerPenguin playerPenguin);

    protected abstract boolean eatenBy(Whale whale);

    protected abstract boolean eatenBy(LeopardSeal leopardSeal);

    protected abstract boolean eatenBy(Fish fish);

    public static void setAntarktis(Animal[][] antarktis) {
        Animal.antarktis = antarktis;
    }

    // Graphics Stuff - You don't have to do anything here
    private void paintSymbol(Graphics g, Color c, int height, int width) {
        GradientPaint gradient = new GradientPaint(15, 0, c, width, 0,
                Color.LIGHT_GRAY);
        ((Graphics2D) g).setPaint(gradient);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.fillOval((int) (width * 0.3), (int) (height * 0.3), (int) (width * 0.5),
                (int) (height * 0.5));
    }

    public void draw(Graphics g, int height, int width) {
        if (image == null) {
            paintSymbol(g, Color.YELLOW, height, width);
            return;
        }
        ((Graphics2D) g).drawImage(image, 0, 0, width, height, 0, 0,
                image.getWidth(null),
                image.getHeight(null), null);
    }
}
