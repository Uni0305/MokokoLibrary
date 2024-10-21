package me.uni0305.mokoko.library.serialization.bytes;

import me.uni0305.mokoko.library.serialization.DataSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Base64;

/**
 * Interface for byte array serialization and deserialization.
 *
 * @param <T> the type of the object to be serialized/deserialized
 */
public interface ByteArraySerializer<T> extends DataSerializer<byte[], T> {

    /**
     * Serializes an object to a Base64 encoded string.
     *
     * @param obj the object to serialize
     * @return the Base64 encoded string representation of the object
     */
    default @NotNull String serializeToBase64(@NotNull T obj) {
        byte[] bytes = serialize(obj);
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * Deserializes an object from a Base64 encoded string.
     *
     * @param src the Base64 encoded string to deserialize
     * @return the deserialized object, or null if deserialization fails
     */
    default @Nullable T deserializeFromBase64(@NotNull String src) {
        byte[] bytes = Base64.getDecoder().decode(src);
        return deserialize(bytes);
    }
}
