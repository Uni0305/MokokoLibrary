package me.uni0305.mokoko.library.bukkit.serialization.json;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import me.uni0305.mokoko.library.common.JsonSerializer;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class JsonLocationSerializer implements JsonSerializer<Location> {
    private final Gson gson = new Gson();

    @Override
    public @NotNull JsonElement serialize(@NotNull Location obj) throws RuntimeException {
        try {
            Map<String, Object> map = obj.serialize();
            return gson.toJsonTree(map);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public @NotNull Location deserialize(@NotNull JsonElement src) throws RuntimeException {
        try {
            TypeToken<Map<String, Object>> type = new TypeToken<>() {
            };
            Map<String, Object> map = gson.fromJson(src, type);
            return Location.deserialize(map);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
