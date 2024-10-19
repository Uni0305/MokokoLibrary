package me.uni0305.mokoko.library.serialization.bytes;

import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class BukkitObjectArraySerializer<T> implements ByteArraySerializer<T[]> {

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
