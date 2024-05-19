package net.jonuuh.texturedbarriers;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public abstract class Config
{
    public static boolean enabled = false;
    private static Configuration cfg;

    static void load(File cfgFile)
    {
        cfg = new Configuration(cfgFile);
        cfg.load();

        enabled = cfg.get("-", "enabled", enabled).getBoolean();
    }

    public static void save()
    {
        cfg.get("-", "enabled", enabled).setValue(enabled);

        if (cfg.hasChanged())
        {
            cfg.save();
        }
    }
}
