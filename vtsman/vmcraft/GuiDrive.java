package vtsman.vmcraft;

import java.io.File;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiOptionSlider;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class GuiDrive extends GuiScreen {
	protected float scale = 1.4f;
	protected int xSize = 176;
	protected int ySize = 166;

	private static final ResourceLocation field_147017_u = new ResourceLocation(
			"vmcraft", "textures/gui/computerGui.png");

	private FileList keyBindingList;
	private GuiButton field_146493_s;
	int m;
	ItemStack st;
	EntityPlayer p;

	public GuiDrive(ItemStack s, EntityPlayer p) {
		m = s.getItemDamage();
		st = s;
		this.p = p;
	}

	/**
	 * Adds the buttons (and other controls) to the screen in question.
	 */

	public static String writePath;

	public void initGui() {
		switch (m) {
		case 0:
			this.keyBindingList = new FileList(this, "iso", Base.cdDir);
			break;
		case 1:
			this.keyBindingList = new FileList(this, "img", Base.floppyDir);
			break;
		case 2:
			this.keyBindingList = new FileList(this, "img", Base.hddDir);
			break;
		}
		this.buttonList.add(new GuiButton(200, this.width / 2 - 155,
				this.height - 29, 155 * 2, 20, "Select image"));
	}

	protected void actionPerformed(GuiButton p_146284_1_) {
		super.actionPerformed(p_146284_1_);
		if (writePath != null) {
			if (new File(writePath).exists()) {
				if (st.stackTagCompound == null)
					st.stackTagCompound = new NBTTagCompound();
				st.stackTagCompound.setString("path", writePath);
				p.closeScreen();
			}
		}
	}

	/**
	 * Called when the mouse is clicked.
	 */
	protected void mouseClicked(int par1, int par2, int par3) {
		super.mouseClicked(par1, par2, par3);
	}

	/**
	 * Called when the mouse is moved or a mouse button is released. Signature:
	 * (mouseX, mouseY, which) which==-1 is mouseMove, which==0 or which==1 is
	 * mouseUp
	 */
	protected void mouseMovedOrUp(int p_146286_1_, int p_146286_2_,
			int p_146286_3_) {
		if (p_146286_3_ != 0
				|| !this.keyBindingList.func_148181_b(p_146286_1_, p_146286_2_,
						p_146286_3_)) {
			super.mouseMovedOrUp(p_146286_1_, p_146286_2_, p_146286_3_);
		}
	}

	/**
	 * Fired when a key is typed. This is the equivalent of
	 * KeyListener.keyTyped(KeyEvent e).
	 */
	protected void keyTyped(char par1, int par2) {
		super.keyTyped(par1, par2);
	}

	public String top = "Please select a disk image";

	/**
	 * Draws the screen and all the components in it.
	 */
	public void drawScreen(int par1, int par2, float par3) {
		int k = (this.width - (int) (xSize * scale)) / 2;
		int l = (this.height - (int) (ySize * scale)) / 2;
		Minecraft.getMinecraft().getTextureManager()
		.bindTexture(field_147017_u);
		this.drawTexturedRect(k, l, (int) (xSize * scale),
				(int) (ySize * scale));
		this.keyBindingList.drawScreen(par1, par2, par3);
		this.drawCenteredString(this.fontRendererObj, top, this.width / 2, 8,
				16777215);

		super.drawScreen(par1, par2, par3);
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
}