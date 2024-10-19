package me.uni0305.mokoko.library.serialization.bytes;

import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class BukkitObjectSerializer<T> implements ByteArraySerializer<T> {

    @Override
    public byte @NotNull [] serialize(@NotNull T obj) {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream(); BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(output)) {
            dataOutput.writeObject(obj);
            return output.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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
