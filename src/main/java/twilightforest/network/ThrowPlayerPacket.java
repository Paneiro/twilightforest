package twilightforest.network;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ThrowPlayerPacket {
	private final float motionX;
	private final float motionY;
	private final float motionZ;

	public ThrowPlayerPacket(float motionX, float motionY, float motionZ) {
		this.motionX = motionX;
		this.motionY = motionY;
		this.motionZ = motionZ;
	}

	public ThrowPlayerPacket(PacketBuffer buf) {
		motionX = buf.readFloat();
		motionY = buf.readFloat();
		motionZ = buf.readFloat();
	}

	public void encode(PacketBuffer buf) {
		buf.writeFloat(motionX);
		buf.writeFloat(motionY);
		buf.writeFloat(motionZ);
	}

	public static class Handler {

		public static boolean onMessage(ThrowPlayerPacket message, Supplier<NetworkEvent.Context> ctx) {
			ctx.get().enqueueWork(new Runnable() {
				@Override
				public void run() {
					Minecraft.getInstance().player.addVelocity(message.motionX, message.motionY, message.motionZ);
				}
			});

			return true;
		}
	}
}