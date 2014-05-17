package vtsman.vmcraft;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import cpw.mods.fml.common.network.FMLIndexedMessageToMessageCodec;

public class ClientPacketHandler extends FMLIndexedMessageToMessageCodec<IPacket>{

	@Override
	public void encodeInto(ChannelHandlerContext ctx, IPacket msg,
			ByteBuf target) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf source,
			IPacket msg) {
		// TODO Auto-generated method stub
		
	}

}
