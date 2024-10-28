package twilightforest.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.DirectionalBlock;
import org.jetbrains.annotations.Nullable;
import twilightforest.TwilightForestMod;
import twilightforest.block.entity.WebwormBlockEntity;
import twilightforest.client.model.TFModelLayers;
import twilightforest.client.model.entity.WebwormModel;

public class WebwormRenderer implements BlockEntityRenderer<WebwormBlockEntity> {

	private static final ResourceLocation TEXTURE = TwilightForestMod.getModelTexture("webworm.png");
	private final WebwormModel webwormModel;

	public WebwormRenderer(BlockEntityRendererProvider.Context context) {
		this.webwormModel = new WebwormModel(context.bakeLayer(TFModelLayers.WEBWORM));
	}

	@Override
	public void render(@Nullable WebwormBlockEntity entity, float partialTicks, PoseStack stack, MultiBufferSource buffer, int light, int overlay) {
		float randRot = entity != null ? entity.randRot : 0.0F;

		stack.pushPose();
		Direction facing = entity != null ? entity.getBlockState().getValue(DirectionalBlock.FACING) : Direction.NORTH;

		stack.translate(0.5F, 0.5F, 0.5F);
		stack.mulPose(facing.getRotation());
		stack.mulPose(Axis.ZP.rotationDegrees(180.0F));
		stack.mulPose(Axis.YP.rotationDegrees(randRot));

		VertexConsumer consumer = buffer.getBuffer(this.webwormModel.renderType(TEXTURE));
		this.webwormModel.setRotationAngles(entity, partialTicks);
		this.webwormModel.renderToBuffer(stack, consumer, light, OverlayTexture.NO_OVERLAY);

		stack.popPose();
	}
}
