package net.jonuuh.texturedbarriers.mixins;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBarrier;
import net.minecraft.client.renderer.block.statemap.BlockStateMapper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;

@Mixin(BlockStateMapper.class)
public class MixinBlockStateMapper
{
    @Shadow
    private final Set<Block> setBuiltInBlocks = Sets.newIdentityHashSet();

    // Remove barriers from a set of special blocks which won't have models created for them
    @Inject(method = "registerBuiltInBlocks", at = @At("TAIL"))
    private void modifyRegisterBuiltInBlocks(Block[] p_178448_1_, CallbackInfo ci)
    {
        this.setBuiltInBlocks.removeIf(block -> block instanceof BlockBarrier);
    }
}
