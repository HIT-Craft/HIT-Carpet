package carpet.logging.logHelpers;

import carpet.logging.LoggerRegistry;
import carpet.utils.Messenger;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.block.entity.EndGatewayBlockEntity;
import net.minecraft.block.entity.PistonBlockEntity;
import net.minecraft.text.BaseText;
import net.minecraft.util.math.BlockPos;

public class BlockEntityLogHelper {
    public static void onSetBlockEntity(BlockPos pos, BlockEntity blockEntity, Long time){
        LoggerRegistry.getLogger("blockentity").log( (option) -> {
            if (option.equals("endislandfarm")) {
                if (blockEntity instanceof EndGatewayBlockEntity)
                    return new BaseText[]{Messenger.c(
                            String.format("g [%s] ", time),
                            "w A blockentity: ",
                            "y EndGatewayBlockEntity ",
                            "w is ", "l set ", "w at ", Messenger.dblt("r",pos.getX(), pos.getY(), pos.getZ()))};
                else if (blockEntity instanceof PistonBlockEntity)
                    return new BaseText[]{Messenger.c(
                            String.format("g [%s] ", time),
                            "w A blockentity: ",
                            "y PistonBlockEntity ",
                            "w is ", "l set ", "w at ", Messenger.dblt("r",pos.getX(), pos.getY(), pos.getZ()))};
            }
            return null;
        });
    }

    public static void onExitEndGatewayGenerate(BlockEntity blockEntity, BlockPos pos){
        LoggerRegistry.getLogger("blockentity").log( (option) -> {
            if (option.equals("endislandfarm")) {
                if (blockEntity instanceof BrewingStandBlockEntity)
                    return new BaseText[]{Messenger.c(
                            "w A blockentity: ",
                            "y BrewingStandBlockEntity ",
                            "w is ", "l replaced ", "w when generating new end gateway at ", Messenger.dblt("r",pos.getX(), pos.getY(), pos.getZ()))};
                else if (blockEntity instanceof PistonBlockEntity)
                    return new BaseText[]{Messenger.c(
                            "w A blockentity: ",
                            "y PistonBlockEntity ",
                            "w is ", "l replaced ", "w when generating new end gateway at ", Messenger.dblt("r",pos.getX(), pos.getY(), pos.getZ()))};
                else if (blockEntity == null)
                    return new BaseText[]{Messenger.c(
                            "w A blockentity: ",
                            "y EndGatewayBlockEntity ",
                            "w is ", "l generated ", "w when generating new end gateway at ", Messenger.dblt("r",pos.getX(), pos.getY(), pos.getZ()))};
            }
            return null;
        });
    }
}
