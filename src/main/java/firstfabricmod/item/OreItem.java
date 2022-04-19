package firstfabricmod.item;

import firstfabricmod.FirstFabricMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class OreItem extends Item {

    public OreItem(Settings settings) {
        super(settings);
    }

    public static final OreItem Amethyst = new OreItem(new FabricItemSettings().group(FirstFabricMod.LOSTsMOD).maxCount(64));

    public static void r_Amethyst(){
        Registry.register(Registry.ITEM, new Identifier("firstfabricmod","amethyst"), Amethyst);
    }
}
