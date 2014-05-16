package vtsman.vmcraft;

import org.jpc.emulator.pci.peripheral.DefaultVGACard;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{
	public static final int computerGuiID = 314;
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		switch(ID){
			default: return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		switch(ID){
			case computerGuiID: return new GuiComputer(BlockComputer.vga, BlockComputer.kb, player);
			default: return null;
		}
	}

}
