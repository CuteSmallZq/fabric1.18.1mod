package firstfabricmod.entity;

import firstfabricmod.FirstFabricMod;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CubeEntityEgg {

    public static final Item CUBE_ENTITY_EGG = new SpawnEggItem(CubeEntity.CUBE_ENTITY,
            12895428, 11382189, new Item.Settings().group(FirstFabricMod.LOSTsMOD));

    public static void r_CubeEntityEgg(){
        Registry.register(Registry.ITEM, new Identifier("firstfabricmod","cube_entity_egg"), CUBE_ENTITY_EGG);
    }
}
