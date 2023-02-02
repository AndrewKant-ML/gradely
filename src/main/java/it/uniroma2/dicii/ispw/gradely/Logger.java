package it.uniroma2.dicii.ispw.gradely;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private static final String pattern = "%s:\t%s\n";

    /**
     * Prints a message into standard output with the format: <br>
     * <i>datetime</i>:&emsp;<i>error message</i>
     *
     * @param message the message to be printed
     */
    public static synchronized void printOut(String message) {
        System.out.printf(pattern, getDate(), message);
    }

    /**
     * Prints an error message into standard error with the format: <br>
     * <i>datetime</i>:&emsp;<i>error message</i>
     *
     * @param errorMessage the message to be printed
     */
    public static synchronized void printErr(String errorMessage) {
        System.err.printf(pattern, getDate(), errorMessage);
    }

    /**
     * Gets the current time and returns it in the {@link DateTimeFormatter#ISO_LOCAL_DATE} format
     *
     * @return the formatter current time
     */
    private static synchronized String getDate() {
        return LocalDate.now().format(formatter);
    }
}
