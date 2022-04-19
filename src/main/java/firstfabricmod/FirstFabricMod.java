package firstfabricmod;

import firstfabricmod.block.oreBlock.AmethystBlock;
import firstfabricmod.block.oreBlock.AmethystOre;
import firstfabricmod.entity.CubeEntity;
import firstfabricmod.fluid.BloodFluid;
import firstfabricmod.item.ore.OreItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirstFabricMod implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("firstfabricmod");

	public static final ItemGroup LOSTsMOD = FabricItemGroupBuilder.build(
			new Identifier("firstfabricmod","general"),
			() -> new ItemStack(Blocks.COBBLESTONE));

	@Override
	public void onInitialize() {

		OreItem.c_r_Amethyst(); // 紫水晶，AMETHYST
		AmethystBlock.c_r_Amethyst_Block(); // 紫水晶块，AMETHYST_BLOCK
		AmethystOre.c_r_AmethystOre(); // 紫水晶矿石与深层版本，AMETHYST_ORE，DEEPSLATE_AMETHYST_ORE
		BloodFluid.c_r_BloodFluid(); // 血流体，BLOOD
		CubeEntity.r_CubeEntity(); // 方块实体，CUBE

		LOGGER.info("Hello Fabric world!");
	}
}
