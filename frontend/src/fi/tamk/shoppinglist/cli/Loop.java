package fi.tamk.shoppinglist.cli;

import java.util.Scanner;

/**
 * Implements the loop that asks user for input in CLi.
 *
 * @author Vili Kinnunen
 * @version 2016.1117
 * @since 1.8
 */
public class Loop implements Runnable {

    /**
     * Command listener to interact with.
     */
    CommandListener listener;

    /**
     * Scanner listening to CLI input.
     */
    private Scanner sc;

    /**
     * Initializes loop to use the command listener that created it.
     *
     * @param listener Command listener to interact with
     */
    public Loop(CommandListener listener) {
        this.sc = new Scanner(System.in);
        this.listener = listener;
    }

    /**
     * Starts the loop.
     */
    public void run() {
        while (listener.processCommands) {
            System.out.println("Give shopping list " +
                    "(example: 1 milk;2 tomato;3 carrot;)");
            listener.process(sc.nextLine());
        }
    }
}
