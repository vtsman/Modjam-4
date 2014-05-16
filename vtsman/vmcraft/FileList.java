package vtsman.vmcraft;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiScreen;

public class FileList extends GuiListExtended{

	private final GuiScreen parent;
	private final List<IGuiListEntry> entries = Lists.newArrayList();

	public FileList(GuiScreen parent) {
		super(Minecraft.getMinecraft(), parent.width, parent.height, 33, parent.height - 32, 20);
		this.parent = parent;
		setShowSelectionBox(false);

	}

	public void addEntries(IGuiListEntry... entries) {
		this.entries.addAll(Arrays.asList(entries));
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

}
