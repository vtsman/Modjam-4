package vtsman.vmcraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TabComputer extends CreativeTabs{

	public TabComputer() {
		super("VMCraft");
		// TODO Auto-generated constructor stub
	}
	@Override
	public ItemStack getIconItemStack() {
		ItemStack stack = new ItemStack(Base.computer, 1, 0);
		return stack;
	}
	
	@Override
	public Item getTabIconItem() {
		// TODO Auto-generated method stub
		return null;
	}
}
