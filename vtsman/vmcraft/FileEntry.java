package vtsman.vmcraft;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended.IGuiListEntry;
import net.minecraft.client.renderer.Tessellator;

public class FileEntry implements IGuiListEntry{
	String n;
	public File file;
	public FileEntry(File f){
		n = f.getName();
		file = f;
	}

	@Override
	public void drawEntry(int var1, int var2, int var3, int var4, int var5,
			Tessellator var6, int var7, int var8, boolean var9) {
		Minecraft.getMinecraft().fontRenderer.drawString(n, var2, var3, 16777215);
	}

	@Override
	public boolean mousePressed(int var1, int var2, int var3, int var4,
			int var5, int var6) {
		
		return true;
	}

	@Override
	public void mouseReleased(int var1, int var2, int var3, int var4, int var5,
			int var6) {
		// TODO Auto-generated method stub
		
	}

}
