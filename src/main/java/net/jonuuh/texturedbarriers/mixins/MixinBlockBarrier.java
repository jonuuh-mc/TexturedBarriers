package net.jonuuh.texturedbarriers.mixins;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBarrier;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBarrier.class)
public abstract class MixinBlockBarrier extends Block
{
    // Unused, only needed because Block has no default constructor
    private MixinBlockBarrier(Material materialIn)
    {
        super(materialIn);
    }

    // Makes barriers able to be rendered at all (default barrier renderType = -1, which means they can't be rendered at all)
    @Inject(method = "getRenderType", at = @At("HEAD"), cancellable = true)
    private void modifyGetRenderType(CallbackInfoReturnable<Integer> cir)
    {
        cir.setReturnValue(3);
    }

    // Makes barriers render in the same layer as transparent blocks (like glass)
    @Override
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.CUTOUT;
    }

    // Makes barrier sides not render through other barriers (copied from BlockBreakable -> glass superclass)
    @Override
    public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        Block block = iblockstate.getBlock();

        if (worldIn.getBlockState(pos.offset(side.getOpposite())) != iblockstate)
        {
            return true;
        }

        if (block == this)
        {
            return false;
        }

        return super.shouldSideBeRendered(worldIn, pos, side);
    }
}
