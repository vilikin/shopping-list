package fi.tamk.shoppinglist.cli;

import java.util.Scanner;

/**
 * Created by vilik on 14.11.2016.
 */
public class Loop implements Runnable {
    CommandListener listener;

    /**
     * Scanner listening to CLI input
     */
    private Scanner sc;

    public Loop(CommandListener listener) {
        this.sc = new Scanner(System.in);
        this.listener = listener;
    }

    public void run() {
        while (listener.processCommands) {
            System.out.println("Give shopping list " +
                    "(example: 1 milk;2 tomato;3 carrot;)");
            listener.process(sc.nextLine());
        }
    }
}
