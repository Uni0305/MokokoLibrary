package me.uni0305.mokoko.library.serialization.json;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.iface.ReadWriteNBT;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class JsonItemStackSerializer implements JsonSerializer<ItemStack> {
    private final Gson gson = new Gson();

    @Override
    public @NotNull JsonElement serialize(@NotNull ItemStack obj) {
        try {
            ReadWriteNBT nbt = NBT.itemStackToNBT(obj);
            String json = nbt.toString();
            return gson.toJsonTree(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public @Nullable ItemStack deserialize(@NotNull JsonElement src) throws RuntimeException {
        try {
            String json = src.toString();
            ReadWriteNBT nbt = NBT.parseNBT(json);
            return NBT.itemStackFromNBT(nbt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
