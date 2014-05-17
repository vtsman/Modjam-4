package vtsman.vmcraft;

import java.io.File;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
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
        return this.getUnlocalizedName() + par1ItemStack.getItemDamage();
    }
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack st, EntityPlayer par2EntityPlayer, List l, boolean par4) {
		String s = (String) l.get(0);
		l.clear();
		l.add(s);
		if(st.stackTagCompound != null){
			if(st.stackTagCompound.hasKey("path")){
				String s1 = st.stackTagCompound.getString("path");
				l.add("§a" + s1.substring(s1.lastIndexOf("/") + 1));
				return;
			}
		}
		l.add("§4No image found");
	}

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs t, List l)
    {
    	l.add(new ItemStack(item, 1, 0));
    	l.add(new ItemStack(item, 1, 1));
    	l.add(new ItemStack(item, 1, 2));
    }
}
