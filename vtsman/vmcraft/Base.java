package vtsman.vmcraft;

import java.io.File;

import vtsman.vmcraft.proxy.Common;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = Base.MODID, version = Base.VERSION)
public class Base
{
	@SidedProxy(clientSide="vtsman.vmcraft.proxy.Client", serverSide="vtsman.vmcraft.proxy.Common")
	public static Common proxy;
	
    public static final String MODID = "VMCraft";
    public static final String VERSION = "1.0";
    
    public static CreativeTabs tab;
    
    public static Base instance;
    
    public static Block computer;
    
    public static File config;
    public static File minecraftFolder;
    
    public static File floppyDir;
    public static File cdDir;
    public static File hddDir;
    
    @Mod.EventHandler
	public void preInit(FMLPreInitializationEvent evt) {
		config = evt.getSuggestedConfigurationFile();
		minecraftFolder = config.getParentFile().getParentFile();
		floppyDir = new File(minecraftFolder, "VMCraft Floppies");
		cdDir = new File(minecraftFolder, "VMCraft CDs");
		hddDir = new File(minecraftFolder, "VMCraft Hard Drives");
		
		mkDir(floppyDir);
		mkDir(cdDir);
		mkDir(hddDir);
	}
    
    private static void mkDir(File f){
    	if(!f.exists())
    		f.mkdir();
    }

    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	instance = this;
    	proxy.doStuff();
    	
    	NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    	
		tab = new TabComputer();
		
		LanguageRegistry.instance().addStringLocalization("itemGroup.VMCraft",
				"en_US", "VMCraft");
    	
    	computer = new BlockComputer();
        GameRegistry.registerBlock(computer, "Computer");
        computer.setCreativeTab(tab);
        LanguageRegistry.addName(computer, "Computer");
        
    }
}
