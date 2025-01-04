package me.uni0305.mokoko.library.bukkit.serialization.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import me.uni0305.mokoko.library.common.JsonSerializer;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class JsonItemStackArraySerializer implements JsonSerializer<ItemStack[]> {
    private final JsonItemStackSerializer serializer = new JsonItemStackSerializer();

    @Override
    public @NotNull JsonElement serialize(ItemStack @NotNull [] items) throws RuntimeException {
        try {
            JsonArray array = new JsonArray();
            for (ItemStack item : items) {
                JsonElement element = serializer.serialize(item);
                array.add(element);
            }
            return array;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public @Nullable ItemStack @NotNull [] deserialize(@NotNull JsonElement json) throws RuntimeException {
        try {
            JsonArray array = json.getAsJsonArray();
            int length = array.size();
            ItemStack[] items = new ItemStack[length];
            for (int i = 0; i < length; i++) {
                JsonElement element = array.get(i);
                items[i] = serializer.deserialize(element);
            }
            return items;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
