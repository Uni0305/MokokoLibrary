package me.uni0305.mokoko.library.serialization.json;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import me.uni0305.mokoko.library.serialization.DataSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Interface for JSON serialization and deserialization.
 *
 * @param <T> the type of the object to be serialized/deserialized
 */
public interface JsonSerializer<T> extends DataSerializer<JsonElement, T> {

    /**
     * Deserializes an object from a JSON string.
     *
     * @param json the JSON string to deserialize
     * @return the deserialized object, or null if deserialization fails
     */
    default @Nullable T deserializeFromString(@NotNull String json) {
        Gson gson = new Gson();
        JsonElement element = gson.fromJson(json, JsonElement.class);
        return deserialize(element);
    }
}
