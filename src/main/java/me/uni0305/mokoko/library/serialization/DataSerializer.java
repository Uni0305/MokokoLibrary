package me.uni0305.mokoko.library.serialization;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Interface for serializing and deserializing data.
 *
 * @param <K> the type of the serialized form
 * @param <V> the type of the object to be serialized/deserialized
 */
public interface DataSerializer<K, V> {

    /**
     * Serializes the given object to its serialized form.
     *
     * @param obj the object to serialize
     * @return the serialized form of the object
     */
    @NotNull K serialize(@NotNull V obj);

    /**
     * Deserializes the given serialized form back to its object form.
     *
     * @param src the serialized form to deserialize
     * @return the deserialized object, or null if deserialization fails
     */
    @Nullable V deserialize(@NotNull K src);
}
