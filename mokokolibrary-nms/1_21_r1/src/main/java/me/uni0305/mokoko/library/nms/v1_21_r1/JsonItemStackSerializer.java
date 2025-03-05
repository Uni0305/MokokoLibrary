package me.uni0305.mokoko.library.nms.v1_21_r1;

import com.google.gson.JsonElement;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.JsonOps;
import me.uni0305.mokoko.library.common.JsonSerializer;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.RegistryOps;
import org.bukkit.craftbukkit.CraftRegistry;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class JsonItemStackSerializer implements JsonSerializer<ItemStack> {
    private static final RegistryOps<Tag> REGISTRY_OPS = RegistryOps.create(NbtOps.INSTANCE, CraftRegistry.getMinecraftRegistry());

    @Override
    public @NotNull JsonElement serialize(@NotNull ItemStack obj) {
        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(obj);
        DataResult<Tag> encoded = net.minecraft.world.item.ItemStack.CODEC.encodeStart(REGISTRY_OPS, nmsItem);
        Tag tag = encoded.getOrThrow();

        Dynamic<Tag> dynamicTag = new Dynamic<>(REGISTRY_OPS, tag);
        Dynamic<JsonElement> dynamicJson = dynamicTag.convert(JsonOps.INSTANCE);
        return dynamicJson.getValue();
    }

    @Override
    public @Nullable ItemStack deserialize(@NotNull JsonElement src) {
        Dynamic<JsonElement> dynamicJson = new Dynamic<>(JsonOps.INSTANCE, src);
        Dynamic<Tag> dynamicTag = dynamicJson.convert(REGISTRY_OPS);
        Tag tag = dynamicTag.getValue();

        DataResult<Pair<net.minecraft.world.item.ItemStack, Tag>> decoded = net.minecraft.world.item.ItemStack.CODEC.decode(REGISTRY_OPS, tag);
        net.minecraft.world.item.ItemStack nmsItem = decoded.getOrThrow().getFirst();
        return CraftItemStack.asBukkitCopy(nmsItem);
    }
}
