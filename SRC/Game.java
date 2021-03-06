import java.awt.geom.Point2D;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

public class Game implements Finals {

    //________________ATTRIBUTS_____________//

    static Player human, computer;
    static UI ui;
    static boolean firstRun = true;
    static String[] options;
    static String whoWin;
    static boolean over, fog;
    static PlayWave musicPlayer;


    /**
     * methode appelee dans UI.
     */
    public static void run() {

        if (Building.gameOver())
            end();
        else
            middle();
    }


    /**
     * Methode appelee au debut du jeu.
     * @param gui
     */
    public static void beginning(UI gui) {

        Item.aliveItems.clear();
        Item.deadItems.clear();
        Building.buildings.clear();
        SimpleUnit.aliveSimpleUnits.clear();
        SimpleUnit.deadSimpleUnits.clear();
        SoldierGroup.list.clear();
        SimpleUnitGroup.list.clear();
        Unit.dyingUnits.clear();
        SimpleUnitGroup.list.clear();
        SoldierGroup.list.clear();

        options = getOptions();

        musicPlayer = (PlayWave.alreadyExists) ? PlayWave.firstOne : new PlayWave(options[2].equals("MusicOn"));

        over = false;

        setUI(gui);

        fog = options[3].equals("FogOn");

        setHuman(new Player(options[0], BASE_LOCATION, "The Human"));
        setComputer(new Player(options[1], new Point2D.Double(WIDTH - BASE_LOCATION_X, HEIGHT - BASE_LOCATION_Y),
                               "The Intelligence"));

        for (int i = 0; i < 5; i++) {
            new SimpleUnit(human, new Point2D.Double(30, 15 + 2 * i));
            new SimpleUnit(computer, new Point2D.Double(WIDTH - 30, HEIGHT - 25 + 2 * i));
        }

        IA.computer = computer;
        IA.player = human;
        IA.beginning();

        startMusic();
    }


    /**
     * Methode appelee a chaque tour de jeu.
     */
    public static void middle() {

        for (int i = 0; i < Unit.dyingUnits.size(); i++) {
            Unit.dyingUnits.get(i).die();
        }
        for (int i = 0; i < Item.aliveItems.size(); i++) {
            Item.aliveItems.get(i).execute();
        }
        IA.execut();
    }


    /**
     * Methode appelee au debut du jeu.
     */
    public static void end() {
        whoWin = (human.base.isDead()) ? "LOOSE" : "WIN";
        over = true;
        ui.timer.stop();
        IA.end();
    }

    static void startMusic() {
        if (!musicPlayer.isAlive())
            musicPlayer.start();
    }

    @SuppressWarnings("deprecation")
    static void stopMusic() {
        musicPlayer.stop();
    }

    //______________ACCESSEURS__________//

    static Player getHuman() {
        return human;
    }

    static Player getComputer() {
        return computer;
    }

    static UI getUI() {
        return ui;
    }

    /**
     * renvoie un tableau de taille de 4.
     * @return [0] = couleur du joueur : [1] = couleur de l’IA : [2] = Music on ou off : [3] = Fog on ou off
     */
    public static String[] getOptions() {

        Scanner scanner;

        try {
            scanner = new Scanner(new File("options.txt"));
            String[] toReturn = new String[4];
            for (int i = 0; i < 4; i++) {
                toReturn[i] = scanner.nextLine();
            }
            scanner.close();
            return toReturn;

        } catch (FileNotFoundException e) {
        }
        return null;
    }

    //___________MUTATEURS___________//

    static void setHuman(Player p) {
        human = p;
    }

    static void setComputer(Player p) {
        computer = p;
    }

    static void setUI(UI u) {
        ui = u;
    }


    /**
     * recupère un tableau de taille 2.
     * @param s [0] = couleur du joueur : [1] = couleur de l’IA : [2] = Music on ou off : [3] = Fog on ou off
     */
    public static void setOptions(String[] s) {
        try {
            File saveFile = new File("options.txt");
            if (!saveFile.exists()) {
                saveFile.createNewFile();
            } else {
                saveFile.delete();
                saveFile.createNewFile();
            }
            FileWriter scribe = new FileWriter(saveFile);
            for (int i = 0; i < s.length; i++) {
                scribe.write("" + s[i] + "\n");
            }
            scribe.close();

        } catch (IOException e) {
        }
    }


}
