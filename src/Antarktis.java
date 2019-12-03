
public class Antarktis extends Maze {
    private static int width, height;
    private static Penguin lostPenguin;
    private static Whale[] whales = new Whale[5];
    private static LeopardSeal[] leopardSeals = new LeopardSeal[20];
    private static Fish[] fishes = new Fish[500];
    private static PlayerPenguin playerPenguin;

    public static void main(String[] args) {
        width = 41;
        height = 41;
        antarktis = generateMaze(width, height);
        Animal.setAntarktis(antarktis);
        setupMaze();
        gameLoop();

// Close the opened frame
        closeFrame();
    }


    private static void gameLoop() {
        while (true) {
// TODO maybe
            if(currentEvent != NOTHING) {
                if(playerPenguin.move(currentEvent, lostPenguin)) {
                    moveAll();
                } else {
                    draw = false;
                }

                currentEvent = NOTHING;
            }
            draw();
// TODO maybe
        }
    }

    private static void moveAll() {
        for(Whale whale: whales) {
            whale.move();
        }

        for(LeopardSeal leopardSeal: leopardSeals) {
            leopardSeal.move();
        }

        lostPenguin.move();

        for(Fish fish: fishes) {
            fish.move();
        }
    }

    /**
     * Example Setup for easier Testing during development
     */
    private static void setupMaze() {
        int[] pos;
        playerPenguin = new PlayerPenguin(3, 3);
        antarktis[3][3] = playerPenguin;
        for (int i = 0; i < whales.length; i++) {
            pos = getRandomEmptyField();
            whales[i] = new Whale(pos[0], pos[1]);
            antarktis[pos[0]][pos[1]] = whales[i];

        }
        for (int i = 0; i < leopardSeals.length; i++) {
            pos = getRandomEmptyField();
            leopardSeals[i] = new LeopardSeal(pos[0], pos[1]);
            antarktis[pos[0]][pos[1]] = leopardSeals[i];
        }
        for (int i = 0; i < fishes.length; i++) {
            pos = getRandomEmptyField();
            fishes[i] = new Fish(pos[0], pos[1]);
            antarktis[pos[0]][pos[1]] = fishes[i];
        }

        antarktis[20][20] = new Penguin(20, 20);
        lostPenguin = (Penguin) antarktis[20][20];
    }
}
