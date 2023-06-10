package file.logic;

import java.util.LinkedHashMap;

/**
 * Base Writer interface. Should be implemented for using in Saver class.
 *
 * @deprecated out of support since 3.0 / now we're maintaining database logic
 */
@Deprecated
public interface BaseWriter {
    /**
     * Base method for writing collection in file. You should provide Address-Value interpretation for normal writing.
     *
     * @param path Full path to file. If file will don't exist, method will try to create it automatically.
     * @param values Address-value interpretation of Collection.
     */
    void writeToFile(String path, LinkedHashMap<String[], String> values);
}
