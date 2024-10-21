package me.uni0305.mokoko.library.serialization.bytes;

import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * A serializer for objects using Bukkit's object streams.
 *
 * @param <T> the type of the object to be serialized/deserialized
 */
public class BukkitObjectSerializer<T> implements ByteArraySerializer<T> {

    /**
     * Serializes an object to a byte array using Bukkit's object output stream.
     *
     * @param obj the object to serialize
     * @return the byte array representation of the serialized object
     * @throws RuntimeException if an I/O error occurs during serialization
     */
    @Override
    public byte @NotNull [] serialize(@NotNull T obj) {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream(); BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(output)) {
            dataOutput.writeObject(obj);
            return output.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deserializes an object from a byte array using Bukkit's object input stream.
     *
     * @param src the byte array to deserialize
     * @return the deserialized object, or null if deserialization fails
     * @throws RuntimeException if an I/O error or class not found exception occurs during deserialization
     */
    @SuppressWarnings("unchecked")
    @Override
    public @Nullable T deserialize(byte @NotNull [] src) {
        try (ByteArrayInputStream input = new ByteArrayInputStream(src); BukkitObjectInputStream dataInput = new BukkitObjectInputStream(input)) {
            return (T) dataInput.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
