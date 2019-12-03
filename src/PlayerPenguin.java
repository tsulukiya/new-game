public class PlayerPenguin extends Penguin {
    public PlayerPenguin(int x, int y) {
        super(x, y);
    }

    public boolean canEat(Animal animal) {
        return super.canEat(animal);
    }

    public boolean move(int event, Penguin lostPenguin) {
        boolean isMoved = true;
        if (event != -1) {
            Step step = convertEventToStep(event);
            int[] newPos = getPosByStep(step);

            if ((antarktis[newPos[X]][newPos[Y]] != null && antarktis[newPos[X]][newPos[Y]].canEat(this))
                    || lostPenguin == null
                    || (newPos[X] == lostPenguin.x && newPos[Y] == lostPenguin.y)) {
                isMoved = false;
            } else {
                antarktis[x][y] = null;

                x = newPos[X];
                y = newPos[Y];

                antarktis[x][y] = this;
            }
        }
        return isMoved;
    }

    private Step convertEventToStep(int event) {
        Step step = null;
        switch (event) {
            case 1:
                step = Step.LEFT;
                break;
            case 2:
                step = Step.UP;
                break;
            case 3:
                step = Step.RIGHT;
                break;
            case 4:
                step = Step.DOWN;
                break;
            default:
                break;
        }
        return step;
    }
}