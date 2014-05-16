package vtsman.vmcraft;

import java.io.File;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiOptionSlider;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

@SideOnly(Side.CLIENT)
public class GuiDrive extends GuiScreen {
	private static final GameSettings.Options[] field_146492_g = new GameSettings.Options[] {
			GameSettings.Options.INVERT_MOUSE,
			GameSettings.Options.SENSITIVITY, GameSettings.Options.TOUCHSCREEN };
	/**
	 * A reference to the screen object that created this. Used for navigating
	 * between screens.
	 */
	protected String field_146495_a = "Controls";
	/** Reference to the GameSettings object. */
	/** The ID of the button that has been pressed. */
	public KeyBinding buttonId = null;
	private FileList keyBindingList;
	private GuiButton field_146493_s;
	private static final String __OBFID = "CL_00000736";
	int m;
	ItemStack st;
	public GuiDrive(ItemStack s, Player p) {
		m = s.getItemDamage();
		st = s;
	}

	/**
	 * Adds the buttons (and other controls) to the screen in question.
	 */
	
	public static String writePath;
	
	public void initGui() {
		switch(m){
		case 0: this.keyBindingList = new FileList(this, "iso", Base.cdDir); break;
		case 1: this.keyBindingList = new FileList(this, "img", Base.floppyDir); break;
		case 2: this.keyBindingList = new FileList(this, "img", Base.hddDir); break;
		}
		this.buttonList.add(new GuiButton(200, this.width / 2 - 155,
				this.height - 29, 155 * 2, 20, "Select image"));
	}

	protected void actionPerformed(GuiButton p_146284_1_) {
		super.actionPerformed(p_146284_1_);
		if(writePath != null){
			if(new File(writePath).exists()){
				if(st.stackTagCompound == null)
					st.stackTagCompound = new NBTTagCompound();
				st.stackTagCompound.setString("path", writePath);
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
		if (this.buttonId != null) {
			this.buttonId = null;
			KeyBinding.resetKeyBindingArrayAndHash();
		} else {
			super.keyTyped(par1, par2);
		}
	}
	public String top = "Please select a disk image";
	/**
	 * Draws the screen and all the components in it.
	 */
	public void drawScreen(int par1, int par2, float par3) {
		this.drawDefaultBackground();
		this.keyBindingList.drawScreen(par1, par2, par3);
		this.drawCenteredString(this.fontRendererObj, top,
				this.width / 2, 8, 16777215);

		super.drawScreen(par1, par2, par3);
	}
}