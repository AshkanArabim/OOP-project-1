package datautils;

public interface CSVLoadable {

    /**
     * Returns a sequence of corresponding attributes in the same order as given CSV columns.
     */
    public String[] colsToAttrs(String[] cols);

}
