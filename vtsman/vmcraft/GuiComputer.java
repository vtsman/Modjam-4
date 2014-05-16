package vtsman.vmcraft;

import javax.swing.KeyStroke;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
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
import org.jpc.emulator.peripheral.Keyboard;
import org.jpc.j2se.KeyMapping;
import org.lwjgl.opengl.Display;
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
	Keyboard kb;
	
	public GuiComputer(DefaultVGACard vga, Keyboard kb) {
		super();
		card = vga;
		this.kb = kb;
		// this.
	}

	public void drawScreen(int par1, int par2, float par3) {
		this.drawGuiBackgroundLayer();
		this.drawGuiForegroundLayer();
	}

	@Override
	public void keyTyped(char par1, int par2) {
		
		if(this.isShiftKeyDown()){
			kb.keyPressed((byte) 0x36);
		}
		else{
			kb.keyReleased((byte) 0x36);
		}
		if(this.isCtrlKeyDown()){
			kb.keyPressed((byte) 0x1e);
		}
		else{
			kb.keyReleased((byte) 0x1e);
		}
		
		if('a' <= par1 && 'z' >= par1){
			par1 += 'A' - 'a';
		}
		switch(par1){
		case ':': par1 = ';'; break;
		case '"': par1 = '\''; break;
		case '<': par1 = ','; break;
		case '>': par1 = '.'; break;
		case '?': par1 = '/'; break;
		case '|': par1 = '\\'; break;
		case '{': par1 = '['; break;
		case '}': par1 = ']'; break;
		case '!': par1 = '1'; break;
		case '@': par1 = '2'; break;
		case '#': par1 = '3'; break;
		case '$': par1 = '4'; break;
		case '%': par1 = '5'; break;
		case '^': par1 = '6'; break;
		case '&': par1 = '7'; break;
		case '*': par1 = '8'; break;
		case '(': par1 = '9'; break;
		case ')': par1 = '0'; break;
		case '_': par1 = '-'; break;
		case '+': par1 = '='; break;
		}
		
		byte kc = KeyTable.getScancode(Integer.valueOf(KeyStroke.getKeyStroke(par1, 0).getKeyCode()));
		System.out.println(par1 + ":" + KeyStroke.getKeyStroke(par1, 0).getKeyCode() + ":" + kc);
		kb.keyPressed(kc);
		
		if (updateMHz(markTime, totalExec)) 
        {
            markTime = System.currentTimeMillis();
            totalExec = 0;
        }
	}
	
	long markTime = System.currentTimeMillis();
	int totalExec = 0;

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of
	 * the items)
	 */
	protected void drawGuiForegroundLayer() {
		// this.fontRendererObj.drawString("Computer", 8, 6, 4210752);
		// Minecraft.getMinecraft().renderEngine.getTexture(par1ResourceLocation)
		int k = (this.width - xSize) / 2;
		int l = (this.height - ySize) / 2;
		TextureUtil
				.uploadTextureImage(TextureUtil.glGenTextures(), card.buffer);
		// this.drawTexturedModalRect(k - 4, l - 4, 0, 0, xSize + 4, ySize + 4);
		this.drawTexturedModalRect(0, 0, 0, 0, this.width, this.height);
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

	public void drawTexturedRect(int par1, int par2, int par3, int par4,
			int par5, int par6) {
		float f = 0.00390625F;
		float f1 = 0.00390625F;
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV((double) (par1 + 0),
				(double) (par2 + par6), (double) this.zLevel,
				(double) ((float) (par3 + 0) * f),
				(double) ((float) (par4 + par6) * f1));
		tessellator.addVertexWithUV((double) (par1 + par5),
				(double) (par2 + par6), (double) this.zLevel,
				(double) ((float) (par3 + par5) * f),
				(double) ((float) (par4 + par6) * f1));
		tessellator.addVertexWithUV((double) (par1 + par5),
				(double) (par2 + 0), (double) this.zLevel,
				(double) ((float) (par3 + par5) * f),
				(double) ((float) (par4 + 0) * f1));
		tessellator.addVertexWithUV((double) (par1 + 0), (double) (par2 + 0),
				(double) this.zLevel, (double) ((float) (par3 + 0) * f),
				(double) ((float) (par4 + 0) * f1));
		tessellator.draw();
	}
	
	private static final int COUNTDOWN = 10000000;
	
	private boolean updateMHz(long time, long count)
    {
        long t2 = System.currentTimeMillis();
        if (t2 - time < 100)
            return false;
        
        count = COUNTDOWN - count;
        float mhz = count * 1000.0F / (t2 - time) / 1000000;

        float clockSpeed = 17.25F / 770 * mhz / 7.5F * 2.790F;
        
        //int percent = (int) (clockSpeed / 3000 * 1000 * 100 * 10);
        Display.setTitle("Minecraft - VM running at " + mhz + " MHz");
        return true;
    }
}