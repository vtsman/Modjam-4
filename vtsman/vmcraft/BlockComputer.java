package vtsman.vmcraft;

import java.io.IOException;

import org.jpc.debugger.JPC;
import org.jpc.emulator.PC;
import org.jpc.emulator.pci.peripheral.DefaultVGACard;
import org.jpc.emulator.peripheral.Keyboard;
import org.jpc.j2se.PCMonitor;
import org.jpc.j2se.PCMonitorFrame;
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

public class BlockComputer extends Block {
	public static PC pc = null;
	public static JPC jpc = null;
	public static DefaultVGACard vga = null;
	public static Keyboard kb = null;
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
	public boolean onBlockActivated(World w, int x, int y, int z,
			EntityPlayer p, int meta, float hX, float hY, float hZ) {
		String[] args = new String[] { "-boot", "cdrom", "-cdrom",
				"/Users/Spencer/Documents/dsl.iso" };

		if (pc == null) {
			try {
				pc = new PC(new VirtualClock(), args);
			} catch (IOException e) {
				e.printStackTrace();
			}
			PCMonitor mon = new PCMonitor(pc);
			PCMonitorFrame frame = new PCMonitorFrame("hi", pc, args);
			frame.validate();
			frame.setVisible(false);
			//frame.setBounds(100, 100, 760, 500);
			frame.start();
			// new Thread(new PCThread(pc)).start();
			// DefaultVGACard vga = (DefaultVGACard) pc
			// .getComponent(DefaultVGACard.class);
			// vga.setMonitor(mon);
			// mon.setSize(100, 100);
			// mon.setVisible(true);
			vga = (DefaultVGACard) BlockComputer.pc.getComponent(DefaultVGACard.class);
			kb = (Keyboard) BlockComputer.pc.getComponent(Keyboard.class);
		}
		p.openGui(Base.instance, GuiHandler.computerGuiID, w, x, y, z);
		
		return true;
	}

}
