package vtsman.vmcraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiComputer extends GuiScreen
{
    private static final ResourceLocation field_147017_u = new ResourceLocation("textures/gui/container/generic_54.png");
    /**
     * window height is calculated with these values; the more rows, the heigher
     */
    private int inventoryRows;
    private static final String __OBFID = "CL_00000749";

    public GuiComputer()
    {
        super();
    }
    
    public void drawScreen(int par1, int par2, float par3)
    {
    	this.drawGuiBackgroundLayer();
        this.drawGuiForegroundLayer();
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiForegroundLayer()
    {
       this.fontRendererObj.drawString("Computer", 8, 6, 4210752);
    }
    
    protected void drawGuiBackgroundLayer()
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(field_147017_u);
        int k = (this.width) / 2;
        int l = (this.height) / 2;
        this.drawTexturedModalRect(0, 0, 0, 0, 100, 100);
        System.out.println("here");
        //this.drawGradientRect(0, 0, this.width, this.height, -1072689136, -804253680);
    }
}