package me.uni0305.mokoko.library.serialization.bytes;

import me.uni0305.mokoko.library.serialization.DataSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Base64;

public interface ByteArraySerializer<T> extends DataSerializer<byte[], T> {
    default @NotNull String serializeToBase64(@NotNull T obj) {
        byte[] bytes = serialize(obj);
        return Base64.getEncoder().encodeToString(bytes);
    }

    default @Nullable T deserializeFromBase64(@NotNull String src) {
        byte[] bytes = Base64.getDecoder().decode(src);
        return deserialize(bytes);
    }
}
