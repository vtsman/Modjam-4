package vtsman.vmcraft;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import io.netty.buffer.ByteBuf;

public class BufferedImagePacket implements IPacket {
	BufferedImage b;

	public BufferedImagePacket(BufferedImage bi) {
		b = bi;
	}

	@Override
	public void readBytes(ByteBuf bytes) {
		//int w = bytes.getInt(0);
		//int h = bytes.getInt(4);
		byte[] by = new byte[bytes.capacity()];
		bytes.readBytes(by);
		InputStream in = new ByteArrayInputStream(by);
		try {
			BufferedImage bImageFromConvert = ImageIO.read(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void writeBytes(ByteBuf bytes) {
		//bytes.writeInt(b.getWidth());
		//bytes.writeInt(b.getHeight());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(b, "png", baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();
			bytes.writeBytes(imageInByte);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
