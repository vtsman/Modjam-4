package vtsman.vmcraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockComputer extends Block{

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
		
        return true;
    }

	
}
