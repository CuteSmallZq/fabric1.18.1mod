package firstfabricmod;

import firstfabricmod.item.ore.oreItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("firstfabricmod");

	// 创建一个Item类的对象Amethyst
	public static final oreItem Amethyst = new oreItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(64));

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		// 注册上方创建的物品对象
		Registry.register(Registry.ITEM, new Identifier("firstfabricmod","amethyst"), Amethyst);

		LOGGER.info("Hello Fabric world!");
	}
}
