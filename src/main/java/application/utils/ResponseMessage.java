package application.utils;

/**
 * Utility class used for the response messages.
 */

public class ResponseMessage {
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";
    public static final String ERROR_ENTRY_NOT_PRESENT = "error: entry with the given id not present";

    public static final String APP_RUNNING = "Blood Clinic Backend Application is up and running!";

    /**
     * Prints the error location of the service errors.
     *
     * @param className  Current class name
     * @param methodName Current method name
     * @param exception  The given exception
     */
    public static void printMethodErrorString(String className, String methodName, Exception exception) {
        System.out.println("[" + className + "/" + methodName + "] error: " + exception);
    }
}
