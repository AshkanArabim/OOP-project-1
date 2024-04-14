package datautils;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Log class that logs all customer activity to a log file.
 */
public class Log extends DataHandler{

    private final File logfile;

    private FileWriter logWriter;

    private String username;

    // constructor

    public Log(String username) {
        this.username = username;

        // note that this path is relative to where you run the `java` command from.
        this.logfile = new File(DATADIR + "/log.log");

        try {
            this.logWriter = new FileWriter(this.logfile, true);
        } catch (IOException e) {
            System.out.println("Error while creating and opening log file!");
            e.printStackTrace();
            System.exit(1);
        }
    }

    
    // public methods
    
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
        
        // add extra comma & description if needed
        if (description.length() > 0) {
            toLog += "," + description;
        }
        
        toLog += "\n";
        
        try {
            this.logWriter.write(toLog);
            this.logWriter.flush();
        } catch (Exception e) {
            System.out.println("Error while writing to log file!");
            e.printStackTrace();
            System.exit(1);
        }
    }

    // getters

    public File getLogfile() {
        return logfile;
    }

    public FileWriter getLogWriter() {
        return logWriter;
    }

    public String getUsername() {
        return username;
    }

    // setters

    public void setUsername(String username) {
        this.username = username;
    }
}
