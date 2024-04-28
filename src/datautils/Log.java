package datautils;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Log class that logs all customer activity to a log file.
 */
public class Log implements DataHandler{

    /**
     * Instance of log for writing to files.
     */
    private static Log instance;

    /**
     * File for filewriting.
     */
    private final File logfile;

    /**
     * Filewriter for writing to files.
     */
    private FileWriter logWriter;

    /**
     * The username to be added to the log.
     */
    private String username;

    /**
     * Get the log file.
     * @return File to add log.
     */
    public File getLogfile() {
        return logfile;
    }

    /**
     * Get the logwriter.
     * @return Logwriter.
     */
    public FileWriter getLogWriter() {
        return logWriter;
    }

    /**
     * Get the username to be added to log.
     * @return The username to be added to log.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the username to be added to log.
     * @param username The username to be added to log.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Singleton constructor
     * Be sure to call this when the active user changes.
     * @param username The username to log.
     */
    public static Log getInstance(String username) {
        if (instance == null) {
            instance = new Log(username);
        } 
        else {
            instance.username = username;
        }

        return instance;
    }

    /**
     * Get the singleton instance of the Log class.
     * @return The instance of the class.
     */
    public static Log getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Log instance has not been initialized!");
        }

        return instance;
    }

    /**
     * Private constructor to use with getInstance
     * @param username The username to log.
     */
    private Log(String username) {
        setUsername(username);

        // This path is relative to where you run the `java` command from.
        this.logfile = new File(DATADIR + "/log.log");

        try {
            this.logWriter = new FileWriter(this.logfile, true);
        } 
        catch (IOException e) {
            System.out.println("Error while creating and opening log file!");
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Adds a log entry to the log file.
     * BE SURE TO CALL THIS FOR EVERY OPTION AVAILABLE TO USERS!!
     * Each log entry will use the following format:
     * `date, time, username, action, description`
     * 
     * @param action      The action performed. Keep it two words or less.
     * @param description More details about the action performed, including the
     *                    subject.
     *                    For example, if user bought car X, simply write "User
     *                    bought car <carname>".
     *                    DO NOT explain what an action is. That's just clutter.
     */
    public void addLogEntry(String action, String description) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter datefmt = DateTimeFormatter.ISO_LOCAL_DATE;
        DateTimeFormatter timefmt = DateTimeFormatter.ISO_LOCAL_TIME;
        String toLog = now.format(datefmt) + ","
        + now.format(timefmt) + ","
        + this.username + ","
        + action;
        
        // Add extra comma & description if needed.
        if (description.length() > 0) {
            toLog += "," + description;
        }
        
        toLog += "\n";
        
        try {
            this.logWriter.write(toLog);
            this.logWriter.flush();
        } 
        catch (Exception e) {
            System.out.println("Error while writing to log file!");
            e.printStackTrace();
            System.exit(1);
        }
    }
   
}