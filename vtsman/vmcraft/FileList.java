package vtsman.vmcraft;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;
import com.google.common.io.Files;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;

public class FileList extends GuiListExtended{

	private final GuiDrive parent;
	private final List<FileEntry> entries = Lists.newArrayList();

	public FileList(GuiDrive parent, String fExt, File dir) {
		super(Minecraft.getMinecraft(), parent.width, parent.height, 33, parent.height - 32, 20);
		this.parent = parent;
		setShowSelectionBox(false);
		if(dir.listFiles().length == 0)
			return;
		for(File f : dir.listFiles()){
			if(f.isFile()){
				if(Files.getFileExtension(f.getName()).equals(fExt)){
					entries.add(new FileEntry(f));
				}
			}
		}
	}

	@Override public IGuiListEntry getListEntry(int index) {
		return entries.get(index);
	}

	@Override protected int getSize() {
		return entries.size();
	}

	@Override protected int getScrollBarX() {
		return width - 45;
	}

	@Override public int getListWidth() {
		return parent.width;
	}
	public int selected = -1;
	protected void elementClicked(int p_148144_1_, boolean p_148144_2_, int p_148144_3_, int p_148144_4_) {
		selected = p_148144_1_;
		parent.top = "You have selected " + entries.get(p_148144_1_).file.getName();
		parent.writePath = entries.get(p_148144_1_).file.getAbsolutePath();
	}

    /**
     * Returns true if the element passed in is currently selected
     */
    protected boolean isSelected(int p_148131_1_)
    {
        return p_148131_1_ == selected;
    }
    @Override
    protected void drawBackground() {}
    
   /* @Override
    protected void drawContainerBackground(Tessellator tessellator)
    {
       
    }*/

}
