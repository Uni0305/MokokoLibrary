package me.uni0305.mokoko.library.serialization.bytes;

import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * A serializer for arrays of objects using Bukkit's object streams.
 *
 * @param <T> the type of the objects in the array to be serialized/deserialized
 */
public class BukkitObjectArraySerializer<T> implements ByteArraySerializer<T[]> {

    /**
     * Serializes an array of objects to a byte array using Bukkit's object output stream.
     *
     * @param array the array of objects to serialize
     * @return the byte array representation of the serialized array
     * @throws RuntimeException if an I/O error occurs during serialization
     */
    @Override
    public byte @NotNull [] serialize(T @NotNull [] array) {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream(); BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(output)) {
            dataOutput.writeInt(array.length);
            for (T element : array) {
                dataOutput.writeObject(element);
            }
            return output.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deserializes an array of objects from a byte array using Bukkit's object input stream.
     *
     * @param bytes the byte array to deserialize
     * @return the deserialized array of objects, or null if deserialization fails
     * @throws RuntimeException if an I/O error or class not found exception occurs during deserialization
     */
    @SuppressWarnings("unchecked")
    @Override
    public T @Nullable [] deserialize(byte @NotNull [] bytes) {
        try (ByteArrayInputStream input = new ByteArrayInputStream(bytes); BukkitObjectInputStream dataInput = new BukkitObjectInputStream(input)) {
            int length = dataInput.readInt();
            T[] array = (T[]) new Object[length];
            for (int i = 0; i < length; i++) {
                array[i] = (T) dataInput.readObject();
            }
            return array;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
