package vtsman.vmcraft;

import java.io.IOException;

import org.jpc.debugger.JPC;
import org.jpc.emulator.PC;
import org.jpc.emulator.pci.peripheral.DefaultVGACard;
import org.jpc.j2se.VirtualClock;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import org.jpc.j2se.VirtualClock;
import org.jpc.support.Clock;
import org.jpc.support.DriveSet;
import org.jpc.support.DriveSet.BootType;

public class BlockComputer extends Block{
	public static PC pc = null;
	public static JPC jpc = null;
	public BlockComputer() {
		super(Material.iron);
		this.setHardness(1f);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir) {
		this.blockIcon = ir.registerIcon("vmcraft:computer");
	}
	
	@Override
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int meta, float hX, float hY, float hZ)
    {
		String[] args = new String[]{"-boot", "cdrom", "-cdrom", "/Users/Spencer/Documents/SLU.iso"};
		
		if(jpc == null){
			JPC.initialise();
			jpc = new JPC(true);
			JPC.instance = jpc;
			jpc.validate();
	        jpc.setVisible(true);

	        if (args.length > 0) {
	        	try {
					pc = jpc.createPC(args);
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
		}
		
		DefaultVGACard vga = (DefaultVGACard) pc.getComponent(DefaultVGACard.class);
		System.out.println(vga);
        return true;
    }

	
}
