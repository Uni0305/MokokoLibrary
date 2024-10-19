package me.uni0305.mokoko.library.serialization.json;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import me.uni0305.mokoko.library.serialization.DataSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface JsonSerializer<T> extends DataSerializer<JsonElement, T> {
    default @Nullable T deserializeFromString(@NotNull String json) {
        Gson gson = new Gson();
        JsonElement element = gson.fromJson(json, JsonElement.class);
        return deserialize(element);
    }
}
