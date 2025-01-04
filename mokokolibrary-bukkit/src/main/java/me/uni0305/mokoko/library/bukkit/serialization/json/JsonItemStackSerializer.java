package me.uni0305.mokoko.library.bukkit.serialization.json;

import com.google.gson.JsonElement;
import me.uni0305.mokoko.library.common.JsonSerializer;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class JsonItemStackSerializer implements JsonSerializer<ItemStack> {
    private final JsonSerializer<ItemStack> serializer;

    public JsonItemStackSerializer() {
        String minecraftVersion = Bukkit.getMinecraftVersion();
        switch (minecraftVersion) {
            case "1.19.4" -> serializer = new me.uni0305.mokoko.library.nms.v1_19_r3.JsonItemStackSerializer();
            case "1.20", "1.20.1" -> serializer = new me.uni0305.mokoko.library.nms.v1_20_r1.JsonItemStackSerializer();
            case "1.20.2" -> serializer = new me.uni0305.mokoko.library.nms.v1_20_r2.JsonItemStackSerializer();
            case "1.20.3", "1.20.4" -> serializer = new me.uni0305.mokoko.library.nms.v1_20_r3.JsonItemStackSerializer();
            case "1.20.5", "1.20.6" -> serializer = new me.uni0305.mokoko.library.nms.v1_20_r4.JsonItemStackSerializer();
            case "1.21", "1.21.1" -> serializer = new me.uni0305.mokoko.library.nms.v1_21_r1.JsonItemStackSerializer();
            case "1.21.2", "1.21.3" -> serializer = new me.uni0305.mokoko.library.nms.v1_21_r2.JsonItemStackSerializer();
            case "1.21.4" -> serializer = new me.uni0305.mokoko.library.nms.v1_21_r3.JsonItemStackSerializer();
            default -> throw new UnsupportedOperationException("Unsupported Minecraft version: " + minecraftVersion);
        }
    }

    @Override
    public @NotNull JsonElement serialize(@NotNull ItemStack obj) {
        return serializer.serialize(obj);
    }

    @Override
    public @Nullable ItemStack deserialize(@NotNull JsonElement src) {
        return serializer.deserialize(src);
    }
}
