package firstfabricmod.item.ore;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class OreItem extends Item {
    public static final OreItem Amethyst = new OreItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(64));

    public OreItem(Settings settings) {
        super(settings);
    }

    public static void c_r_Amethyst(){
        Registry.register(Registry.ITEM, new Identifier("firstfabricmod","amethyst"), Amethyst);
    }
}
