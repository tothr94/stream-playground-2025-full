package repository;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Helper class for reading a list of objects from JSON.
 */
public class JacksonHelper {

    private static final ObjectMapper MAPPER = new ObjectMapper().registerModule(new JavaTimeModule());

    /**
     * Deserializes a list of objects from JSON.
     *
     * @param in the input stream from which JSON data will be read
     * @param elementClass represents the class of the elements
     * @return the list of objects deserialized from JSON
     * @throws IOException if any I/O error occurs
     */
    public static<T> List<T> readList(InputStream in, Class<T> elementClass) throws IOException {
        final JavaType type = MAPPER.getTypeFactory().constructCollectionType(List.class, elementClass);
        return MAPPER.readValue(in, type);
    }

}
