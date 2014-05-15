package vtsman.vmcraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

import org.jpc.emulator.pci.peripheral.DefaultVGACard;
import org.jpc.emulator.pci.peripheral.VGACard;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiComputer extends GuiScreen {

	protected int xSize = 176;
	/** The Y size of the inventory window in pixels. */
	protected int ySize = 166;

	private static final ResourceLocation field_147017_u = new ResourceLocation(
			"vmcraft", "textures/gui/computerGui.png");
	/**
	 * window height is calculated with these values; the more rows, the heigher
	 */
	private int inventoryRows;
	private static final String __OBFID = "CL_00000749";
	DefaultVGACard card;
	public GuiComputer(DefaultVGACard vga) {
		super();
		card = vga;
	}

	public void drawScreen(int par1, int par2, float par3) {
		this.drawGuiBackgroundLayer();
		this.drawGuiForegroundLayer();
	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of
	 * the items)
	 */
	protected void drawGuiForegroundLayer() {
		//this.fontRendererObj.drawString("Computer", 8, 6, 4210752);
		//Minecraft.getMinecraft().renderEngine.getTexture(par1ResourceLocation)
		int k = (this.width - xSize) / 2;
		int l = (this.height - ySize) / 2;
		TextureUtil.uploadTextureImage(TextureUtil.glGenTextures(), card.buffer);
		this.drawTexturedModalRect(k - 4, l - 4, 0, 0, xSize + 4, ySize + 4);
	}

	protected void drawGuiBackgroundLayer() {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().getTextureManager()
				.bindTexture(field_147017_u);
		int k = (this.width - xSize) / 2;
		int l = (this.height - ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
		// this.drawGradientRect(0, 0, this.width, this.height, -1072689136,
		// -804253680);
	}
	
	 public void drawTexturedRect(int par1, int par2, int par3, int par4, int par5, int par6)
	    {
	        float f = 0.00390625F;
	        float f1 = 0.00390625F;
	        Tessellator tessellator = Tessellator.instance;
	        tessellator.startDrawingQuads();
	        tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + par6), (double)this.zLevel, (double)((float)(par3 + 0) * f), (double)((float)(par4 + par6) * f1));
	        tessellator.addVertexWithUV((double)(par1 + par5), (double)(par2 + par6), (double)this.zLevel, (double)((float)(par3 + par5) * f), (double)((float)(par4 + par6) * f1));
	        tessellator.addVertexWithUV((double)(par1 + par5), (double)(par2 + 0), (double)this.zLevel, (double)((float)(par3 + par5) * f), (double)((float)(par4 + 0) * f1));
	        tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + 0), (double)this.zLevel, (double)((float)(par3 + 0) * f), (double)((float)(par4 + 0) * f1));
	        tessellator.draw();
	    }
}