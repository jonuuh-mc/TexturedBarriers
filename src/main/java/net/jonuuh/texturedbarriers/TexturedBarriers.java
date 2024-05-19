package net.jonuuh.texturedbarriers;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = "texturedbarriers",
        version = "1.0.2",
        acceptedMinecraftVersions = "[1.8.9]"
)
public class TexturedBarriers
{
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        Config.load(event.getSuggestedConfigurationFile());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        ClientCommandHandler.instance.registerCommand(new Command());
    }
}
