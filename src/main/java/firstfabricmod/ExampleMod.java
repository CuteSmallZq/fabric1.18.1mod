package firstfabricmod;

import firstfabricmod.block.AmethystBlock;
import firstfabricmod.item.ore.OreItem;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("firstfabricmod");

	@Override
	public void onInitialize() {

		// 紫水晶，Amethyst
		OreItem.c_r_Amethyst();
		// 紫水晶块，Amethyst_Block
		AmethystBlock.c_r_Amethyst_Block();

		LOGGER.info("Hello Fabric world!");
	}
}
