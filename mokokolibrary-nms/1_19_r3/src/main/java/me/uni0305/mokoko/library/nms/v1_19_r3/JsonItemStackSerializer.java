package me.uni0305.mokoko.library.nms.v1_19_r3;

import com.google.gson.JsonElement;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.JsonOps;
import me.uni0305.mokoko.library.common.JsonSerializer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import org.bukkit.craftbukkit.v1_19_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class JsonItemStackSerializer implements JsonSerializer<ItemStack> {

    @Override
    public @NotNull JsonElement serialize(@NotNull ItemStack obj) {
        net.minecraft.world.item.ItemStack nms = CraftItemStack.asNMSCopy(obj);
        CompoundTag nbt = nms.save(new CompoundTag());
        Dynamic<Tag> tag = new Dynamic<>(NbtOps.INSTANCE, nbt);
        Dynamic<JsonElement> json = tag.convert(JsonOps.INSTANCE);
        return json.getValue();
    }

    @Override
    public @Nullable ItemStack deserialize(@NotNull JsonElement src) {
        Dynamic<JsonElement> json = new Dynamic<>(JsonOps.INSTANCE, src);
        Dynamic<Tag> tag = json.convert(NbtOps.INSTANCE);
        CompoundTag nbt = (CompoundTag) tag.getValue();
        net.minecraft.world.item.ItemStack nms = net.minecraft.world.item.ItemStack.of(nbt);
        return CraftItemStack.asBukkitCopy(nms);
    }
}
