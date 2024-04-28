package datautils;

/**
 * Classes that require loading a database may implement this interface and redefine the column attributes.
 */
public interface CSVLoadable {

    /**
     * Returns a sequence of corresponding attributes in the same order as given CSV columns.
     */
    public String[] colsToAttrs(String[] cols);

}