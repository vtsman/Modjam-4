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
import net.minecraft.entity.player.EntityPlayer;
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
	protected float scale = 1.4f;
	protected int xSize = 176;
	protected int ySize = 166;

	private static final ResourceLocation field_147017_u = new ResourceLocation(
			"vmcraft", "textures/gui/computerGui.png");
	DefaultVGACard card;
	Keyboard kb;
	EntityPlayer p;

	public GuiComputer(DefaultVGACard vga, Keyboard kb, EntityPlayer p) {
		super();
		card = vga;
		this.kb = kb;
		this.p = p;
		// this.
	}

	public void drawScreen(int par1, int par2, float par3) {
		this.drawGuiBackgroundLayer();
		this.drawGuiForegroundLayer();
	}

	@Override
	public void keyTyped(char par1, int par2) {
		super.keyTyped(par1, par2);
		if (this.isShiftKeyDown()) {
			kb.keyPressed((byte) 0x36);
		} else {
			kb.keyReleased((byte) 0x36);
		}
		if (this.isCtrlKeyDown()) {
			kb.keyPressed((byte) 0x1e);
		} else {
			kb.keyReleased((byte) 0x1e);
		}

		if ('a' <= par1 && 'z' >= par1) {
			par1 += 'A' - 'a';
		}
		switch (par1) {
		case ':':
			par1 = ';';
			break;
		case '"':
			par1 = '\'';
			break;
		case '<':
			par1 = ',';
			break;
		case '>':
			par1 = '.';
			break;
		case '?':
			par1 = '/';
			break;
		case '|':
			par1 = '\\';
			break;
		case '{':
			par1 = '[';
			break;
		case '}':
			par1 = ']';
			break;
		case '!':
			par1 = '1';
			break;
		case '@':
			par1 = '2';
			break;
		case '#':
			par1 = '3';
			break;
		case '$':
			par1 = '4';
			break;
		case '%':
			par1 = '5';
			break;
		case '^':
			par1 = '6';
			break;
		case '&':
			par1 = '7';
			break;
		case '*':
			par1 = '8';
			break;
		case '(':
			par1 = '9';
			break;
		case ')':
			par1 = '0';
			break;
		case '_':
			par1 = '-';
			break;
		case '+':
			par1 = '=';
			break;
		}

		byte kc = KeyTable.getScancode(Integer.valueOf(KeyStroke.getKeyStroke(
				par1, 0).getKeyCode()));
		if(kc == 0x36 || kc == 0x1e || kc == 0)
			return;
		kb.keyPressed(kc);
		
	}

	long markTime = System.currentTimeMillis();
	int totalExec = 0;

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of
	 * the items)
	 */
	protected void drawGuiForegroundLayer() {
		if (updateMHz(markTime, totalExec)) {
			markTime = System.currentTimeMillis();
			totalExec = 0;
		}
		// this.fontRendererObj.drawString("Computer", 8, 6, 4210752);
		// Minecraft.getMinecraft().renderEngine.getTexture(par1ResourceLocation)
		int k = (this.width - (int)(xSize * scale)) / 2;
		int l = (this.height - (int)(ySize * scale)) / 2;
		TextureUtil
				.uploadTextureImage(TextureUtil.glGenTextures(), card.buffer);
		this.drawTexturedRect(k + (int)(4 * scale), l + (int)(4 * scale), (int)(xSize * scale) - (int)(8 * scale), (int)(ySize * scale) - (int)(8 * scale));
	}

	protected void drawGuiBackgroundLayer() {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().getTextureManager()
				.bindTexture(field_147017_u);
		int k = (this.width - (int)(xSize * scale)) / 2;
		int l = (this.height - (int)(ySize * scale)) / 2;
		this.drawTexturedRect(k, l, (int)(xSize * scale), (int)(ySize * scale));
		// this.drawGradientRect(0, 0, this.width, this.height, -1072689136,
		// -804253680);
	}

	public void drawTexturedRect(int par1, int par2, int par5, int par6) {
		float f = 0.00390625F;
		float f1 = 0.00390625F;
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV((double) (par1 + 0),
				(double) (par2 + par6), (double) this.zLevel, (double) (0),
				(double) (1));
		tessellator.addVertexWithUV((double) (par1 + par5),
				(double) (par2 + par6), (double) this.zLevel, (double) (1),
				(double) (1));
		tessellator.addVertexWithUV((double) (par1 + par5),
				(double) (par2 + 0), (double) this.zLevel, (double) (1),
				(double) (0));
		tessellator.addVertexWithUV((double) (par1 + 0), (double) (par2 + 0),
				(double) this.zLevel, (double) (0), (double) (0));
		tessellator.draw();
	}

	private static final int COUNTDOWN = 10000000;

	private boolean updateMHz(long time, long count) {
		long t2 = System.currentTimeMillis();
		if (t2 - time < 100)
			return false;

		count = COUNTDOWN - count;
		float mhz = count * 1000.0F / (t2 - time) / 1000000;

		float clockSpeed = 17.25F / 770 * mhz / 7.5F * 2.790F;

		// int percent = (int) (clockSpeed / 3000 * 1000 * 100 * 10);
		Display.setTitle("Minecraft - VM running at " + mhz + " MHz");
		return true;
	}

	@Override
	public void onGuiClosed() {
		Display.setTitle("Minecraft");
	}

	@Override
	protected void mouseClicked(int par1, int par2, int par3) {
		super.mouseClicked(par1, par2, par3);
		kb.putMouseEvent(par1, par2, 0, par3);
	}
}