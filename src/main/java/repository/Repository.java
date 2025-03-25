package repository;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Represents a repository to obtain an unmodifiable list of objects. Data is
 * read from a static JSON file that contains an array of JSON objects. The file
 * is loaded via the class loader.
 *
 * @param <T> the type of the elements
 */
public abstract class Repository<T> {

    private List<T> elements;

    /**
     * Creates a {@code Repository} object to obtain an unmodifiable list of
     * objects of {@code elementClass}. The class loader of {@code elementClass}
     * reads JSON data.
     *
     * @param elementClass represents the type of the elements
     * @param resourceName the name of the resource that contains JSON data to
     *                     be read
     */
    protected Repository(Class<T> elementClass, String resourceName) {
        try {
            var list = JacksonHelper.readList(elementClass.getResourceAsStream(resourceName), elementClass);
            elements = Collections.unmodifiableList(list);
        } catch(IOException e) {
            e.printStackTrace();
            throw new AssertionError("Failed to load resource " + resourceName, e); // Can't happen
        }
    }

    /**
     * {@return the unmodifiable list of objects}
     */
    public List<T> getAll() {
        return elements;
    }

}
