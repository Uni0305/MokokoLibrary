package me.uni0305.mokoko.library.serialization;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface DataSerializer<K, V> {
    @NotNull K serialize(@NotNull V obj);

    @Nullable V deserialize(@NotNull K src);
}
