package vtsman.vmcraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class ItemDrive extends Item {
	public static IIcon[] icons = new IIcon[3];
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir) {
		this.itemIcon = ir.registerIcon("vmcraft:computer");
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int par1, int par2) {
		if(par1 >= icons.length){
			return icons[0];
		}
		return icons[par1];
	}
}
