package firstfabricmod;

import firstfabricmod.block.oreBlock.AmethystBlock;
import firstfabricmod.block.oreBlock.AmethystOre;
import firstfabricmod.item.ore.OreItem;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("firstfabricmod");

	@Override
	public void onInitialize() {

		OreItem.c_r_Amethyst(); // 紫水晶，AMETHYST
		AmethystBlock.c_r_Amethyst_Block(); // 紫水晶块，AMETHYST_BLOCK
		AmethystOre.c_r_AmethystOre(); // 紫水晶矿石与深层版本，AMETHYST_ORE，DEEPSLATE_AMETHYST_ORE

		LOGGER.info("Hello Fabric world!");
	}
}
