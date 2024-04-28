package datautils;

/**
 * Updates the appropriate CSV file.
 */
public abstract class CSVHandler implements DataHandler {

    /**
     * Updates the CSV file holder user data.
     * Call this in every method of this class that modifies the internal data structure.
     */
    protected abstract void updateCSV();
}
