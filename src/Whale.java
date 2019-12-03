import java.awt.Toolkit;
import java.io.File;

public class Whale extends Animal {
    static String filename = "src/whale.png";

    public Whale(int x, int y) {
        super(x, y);
        f = new File(filename);
        image = Toolkit.getDefaultToolkit().getImage(f.getAbsolutePath());
    }

    public boolean canEat(Animal animal) {
        if (animal != null) {
            return animal.eatenBy(this);
        }
        return false;
    }

    @Override
    protected boolean eatenBy(Penguin penguin) {
        return false;
    }

    @Override
    protected boolean eatenBy(PlayerPenguin playerPenguin) {
        return false;
    }

    @Override
    protected boolean eatenBy(Whale whale) {
        return false;
    }

    @Override
    protected boolean eatenBy(LeopardSeal leopardSeal) {
        return false;
    }

    @Override
    protected boolean eatenBy(Fish fish) {
        return false;
    }
}
