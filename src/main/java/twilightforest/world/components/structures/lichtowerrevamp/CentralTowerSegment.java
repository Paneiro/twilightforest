package twilightforest.world.components.structures.lichtowerrevamp;

import net.minecraft.core.BlockPos;
import net.minecraft.core.FrontAndTop;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructurePieceAccessor;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.neoforged.neoforge.common.world.PieceBeardifierModifier;
import twilightforest.TwilightForestMod;
import twilightforest.init.TFStructurePieceTypes;
import twilightforest.util.jigsaw.JigsawPlaceContext;
import twilightforest.util.jigsaw.JigsawRecord;
import twilightforest.world.components.structures.TwilightJigsawPiece;

public final class CentralTowerSegment extends TwilightJigsawPiece implements PieceBeardifierModifier {
	private final boolean putMobBridge;
	private final boolean continueAbove;

	public CentralTowerSegment(StructurePieceSerializationContext ctx, CompoundTag compoundTag) {
		super(TFStructurePieceTypes.CENTRAL_TOWER.get(), compoundTag, ctx, readSettings(compoundTag));

		TowerUtil.addDefaultProcessors(this.placeSettings, true);

		this.putMobBridge = compoundTag.getBoolean("put_bridge");
		this.continueAbove = compoundTag.getBoolean("seg_above");
	}

	public CentralTowerSegment(StructureTemplateManager structureManager, int genDepth, JigsawPlaceContext jigsawContext, boolean putMobBridge, boolean continueAbove) {
		super(TFStructurePieceTypes.CENTRAL_TOWER.get(), genDepth, structureManager, TwilightForestMod.prefix("lich_tower/tower_slice"), jigsawContext);

		TowerUtil.addDefaultProcessors(this.placeSettings, true);

		this.putMobBridge = putMobBridge;
		this.continueAbove = continueAbove;
	}

	@Override
	protected void addAdditionalSaveData(StructurePieceSerializationContext ctx, CompoundTag structureTag) {
		super.addAdditionalSaveData(ctx, structureTag);

		structureTag.putBoolean("put_bridge", this.putMobBridge);
		structureTag.putBoolean("seg_above", this.continueAbove);
	}

	public static void putTowerSegment(StructurePieceAccessor pieceAccessor, RandomSource random, BlockPos sourceJigsawPos, FrontAndTop sourceOrientation, TwilightJigsawPiece parent, StructureTemplateManager structureManager, boolean putMobBridge, boolean bossAbove) {
		JigsawPlaceContext placeableJunction = JigsawPlaceContext.pickPlaceableJunction(parent, sourceJigsawPos, sourceOrientation, structureManager, TwilightForestMod.prefix("lich_tower/tower_slice"), "twilightforest:lich_tower/tower_below", random);

		if (placeableJunction == null) return;

		StructurePiece towerBase = new CentralTowerSegment(structureManager, parent.getGenDepth() + 1, placeableJunction, putMobBridge, bossAbove);
		pieceAccessor.addPiece(towerBase);
		towerBase.addChildren(parent, pieceAccessor, random);
	}

	@Override
	protected void processJigsaw(StructurePiece parent, StructurePieceAccessor pieceAccessor, RandomSource random, JigsawRecord connection, int jigsawIndex) {
		switch (connection.target()) {
			case "twilightforest:lich_tower/tower_below" -> {
				if (this.continueAbove) {
					CentralTowerSegment.putTowerSegment(pieceAccessor, random, connection.pos(), connection.orientation(), this, this.structureManager, !this.putMobBridge && random.nextInt(3) != 0, this.genDepth < random.nextInt(8, 10));
				} else {
					JigsawPlaceContext placeableJunction = JigsawPlaceContext.pickPlaceableJunction(this, connection.pos(), connection.orientation(), this.structureManager, TwilightForestMod.prefix("lich_tower/tower_boss_room"), "twilightforest:lich_tower/tower_below", random);

					if (placeableJunction != null) {
						StructurePiece bossRoom = new BossRoom(this.structureManager, placeableJunction);
						pieceAccessor.addPiece(bossRoom);
						bossRoom.addChildren(this, pieceAccessor, random);
					}
				}
			}
			case "twilightforest:lich_tower/bridge" -> {
				int roomMaxSize = 4 - (random.nextInt((this.genDepth >> 1) + 1) >> 1) - Math.max((this.genDepth >> 2) - 1, 0);
				boolean genMagicGallery = !this.continueAbove && jigsawIndex == 1/* && random.nextInt(10) == 0*/;
				TowerBridge.tryRoomAndBridge(this, pieceAccessor, random, connection, this.structureManager, true, roomMaxSize, false, this.genDepth + 1, genMagicGallery);
			}
			case "twilightforest:mob_bridge" -> {
				if (this.putMobBridge) {
					ResourceLocation mobBridgeLocation = TowerUtil.rollRandomMobBridge(random);
					JigsawPlaceContext placeableJunction = JigsawPlaceContext.pickPlaceableJunction(this, connection.pos(), connection.orientation(), this.structureManager, mobBridgeLocation, "twilightforest:mob_bridge", random);

					if (placeableJunction != null) {
						StructurePiece mobBridgePiece = new TowerMobBridge(this.genDepth + 1, this.structureManager, mobBridgeLocation, placeableJunction);
						pieceAccessor.addPiece(mobBridgePiece);
						mobBridgePiece.addChildren(this, pieceAccessor, random);
					}
				}
			}
		}
	}

	@Override
	protected void handleDataMarker(String label, BlockPos pos, ServerLevelAccessor levelAccessor, RandomSource random, BoundingBox boundingBox) {
	}

	@Override
	public BoundingBox getBeardifierBox() {
		return this.boundingBox;
	}

	@Override
	public TerrainAdjustment getTerrainAdjustment() {
		return TerrainAdjustment.NONE;
	}

	@Override
	public int getGroundLevelDelta() {
		return 0;
	}
}
