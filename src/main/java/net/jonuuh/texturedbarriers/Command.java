package net.jonuuh.texturedbarriers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class Command extends CommandBase
{
    public Command()
    {
    }

    @Override
    public String getCommandName()
    {
        return "barriers";
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return "";
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 0;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args)
    {
        if (!(sender.getCommandSenderEntity() instanceof EntityPlayerSP))
        {
            return;
        }

        Config.enabled = !Config.enabled;
        Config.save();
        Minecraft.getMinecraft().refreshResources();
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("barriers: " + Config.enabled));
    }
}