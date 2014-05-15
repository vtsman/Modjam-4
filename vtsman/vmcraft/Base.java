package vtsman.vmcraft;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = Base.MODID, version = Base.VERSION)
public class Base
{
    public static final String MODID = "VMCraft";
    public static final String VERSION = "1.0";
    
    public static CreativeTabs tab;
    
    public static Block computer;
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		tab = new TabComputer();
		
		LanguageRegistry.instance().addStringLocalization("itemGroup.VMCraft",
				"en_US", "VMCraft");
    	
    	computer = new BlockComputer();
        GameRegistry.registerBlock(computer, "Computer");
        computer.setCreativeTab(tab);
        
    }
}
