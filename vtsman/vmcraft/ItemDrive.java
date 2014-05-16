package vtsman.vmcraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemDrive extends Item {
	public static IIcon[] icons = new IIcon[3];

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir) {
		this.itemIcon = ir.registerIcon("vmcraft:computer");
		this.setUnlocalizedName("VMItemDrive");
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int par1, int par2) {
		if (par1 >= icons.length) {
			return icons[0];
		}
		return icons[par1];
	}

	public boolean onItemUse(ItemStack par1ItemStack,
			EntityPlayer p, World w, int x, int y,
			int z, int meta, float par8, float par9, float par10) {
		p.openGui(Base.instance, GuiHandler.selectGuiID, w, x, y, z);
		return true;
	}
	
	public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        return "item." + this.getUnlocalizedName();
    }
}
