package vtsman.vmcraft;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileComputer extends TileEntity {
	String[] p;

	public TileComputer() {

	}

	@Override
	public void readFromNBT(NBTTagCompound t) {
		if(!t.hasKey("l"))
			return;
		int l = t.getInteger("l");
		p = new String[l];
		for(int i = 0; i < l; i++){
			p[i] = t.getString("path" + i);
		}
		loadFromPath(p);
	}

	@Override
	public void writeToNBT(NBTTagCompound t) {
		t.setInteger("l", p.length);
		for (int i = 0; i < p.length; i++) {
			t.setString("path" + i, p[i]);
		}
	}

	/**
	 * 0th, 2nd, 4th... should be cdrom, hdb, hda, fda 1st, 3rd, 5th... should
	 * be directory path
	 * 
	 * @param drives
	 */
	public void loadFromPath(String[] drives) {

	}
}
