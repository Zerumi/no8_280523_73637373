package file.logic;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * Base Reader interface. Should be implemented for using in Loader class.
 *
 * @author zerumi
 * @see Loader
 * @since 1.0
 * @deprecated out of support since 3.0 / now we're maintaining database logic
 */
@Deprecated
public interface BaseReader {

    /**
     * Base method for reading file and providing Address-Value interpretation of this File.
     *
     * @param path Full path to file.
     * @return Collection of values with full address from File.
     * @throws IOException When something will go wrong during file handling
     */
    LinkedHashMap<String[], String> readFromFile(String path) throws IOException;
}
