package me.uni0305.mokoko.library.serialization.json;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class JsonLocationSerializer implements JsonSerializer<Location> {
    private final Gson gson = new Gson();

    @Override
    public @NotNull JsonElement serialize(@NotNull Location obj) {
        Map<String, Object> map = obj.serialize();
        return gson.toJsonTree(map);
    }

    @Override
    public @NotNull Location deserialize(@NotNull JsonElement src) throws IllegalArgumentException {
        TypeToken<Map<String, Object>> type = new TypeToken<>() {};
        Map<String, Object> map = gson.fromJson(src, type);
        return Location.deserialize(map);
    }
}
