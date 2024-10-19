package me.uni0305.mokoko.library.serialization.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class JsonItemStackArraySerializer implements JsonSerializer<ItemStack[]> {
    private final JsonItemStackSerializer serializer = new JsonItemStackSerializer();

    @Override
    public @NotNull JsonElement serialize(ItemStack @NotNull [] items) {
        JsonArray array = new JsonArray();
        for (ItemStack item : items) {
            JsonElement element = serializer.serialize(item);
            array.add(element);
        }
        return array;
    }

    @Override
    public ItemStack @NotNull [] deserialize(@NotNull JsonElement json) {
        JsonArray array = json.getAsJsonArray();
        int length = array.size();
        ItemStack[] items = new ItemStack[length];
        for (int i = 0; i < length; i++) {
            JsonElement element = array.get(i);
            items[i] = serializer.deserialize(element);
        }
        return items;
    }
}
