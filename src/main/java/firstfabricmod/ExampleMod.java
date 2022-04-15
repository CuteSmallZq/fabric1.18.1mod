package firstfabricmod;

import firstfabricmod.item.ore.oreItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("firstfabricmod");

	// 紫水晶，Amethyst
	public static final oreItem Amethyst = new oreItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(64));

	// 紫水晶块（9个紫水晶合成），Amethyst_Block
	public static final Block Amethyst_Block = new Block(FabricBlockSettings.of(Material.METAL).hardness(4.0F));

	@Override
	public void onInitialize() {

		// 紫水晶，Amethyst
		Registry.register(Registry.ITEM, new Identifier("firstfabricmod","amethyst"), Amethyst);
		// 紫水晶块，Amethyst_Block
		Registry.register(Registry.BLOCK, new Identifier("firstfabricmod","amethyst_block"), Amethyst_Block);
		Registry.register(Registry.ITEM, new Identifier("firstfabricmod","amethyst_block"),
				new BlockItem(Amethyst_Block, new Item.Settings().group(ItemGroup.MISC)));

		LOGGER.info("Hello Fabric world!");
	}
}
