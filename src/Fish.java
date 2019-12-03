import java.awt.Toolkit;
import java.io.File;

public class Fish extends Animal {
    static String filename = "src/fish.png";

    public Fish(int x, int y) {
        super(x, y);

        f = new File(filename);
        image = Toolkit.getDefaultToolkit().getImage(f.getAbsolutePath());
    }

    @Override
    protected Step[] getStepsSequence() {
        return new Step[]{Step.UP, Step.RIGHT, Step.DOWN, Step.LEFT};
    }

    public boolean canEat(Animal animal) {
        if (animal != null) {
            return animal.eatenBy(this);
        }
        return false;
    }

    @Override
    protected boolean eatenBy(Penguin penguin) {
        return true;
    }

    @Override
    protected boolean eatenBy(PlayerPenguin playerPenguin) {
        return true;
    }

    @Override
    protected boolean eatenBy(Whale whale) {
        return true;
    }

    @Override
    protected boolean eatenBy(LeopardSeal leopardSeal) {
        return true;
    }

    @Override
    protected boolean eatenBy(Fish fish) {
        return true;
    }
}
