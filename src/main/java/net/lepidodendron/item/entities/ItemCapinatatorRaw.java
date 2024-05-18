
package net.lepidodendron.item.entities;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronSorter;
import net.lepidodendron.creativetab.TabLepidodendronMobile;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;

@ElementsLepidodendronMod.ModElement.Tag
public class ItemCapinatatorRaw extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:capinatator_raw")
	public static final Item block = null;
	public ItemCapinatatorRaw(ElementsLepidodendronMod instance) {
		super(instance, LepidodendronSorter.capinatator_raw);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemFoodCustom());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation("lepidodendron:entities/capinatator_raw", "inventory"));
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		OreDictionary.registerOre("mobdnaPNlepidodendron:prehistoric_flora_capinatator", ItemCapinatatorRaw.block);
		OreDictionary.registerOre("listAllmeatraw", ItemCapinatatorRaw.block);
		OreDictionary.registerOre("pnfurnaceSeafood", ItemCapinatatorRaw.block);
		OreDictionary.registerOre("pndietMeat", ItemCapinatatorRaw.block);
	}

	public static class ItemFoodCustom extends ItemPNTaxidermyItem {
		public ItemFoodCustom() {
			super(2, 0.1f, false);
			setTranslationKey("pf_capinatator_raw");
			setRegistryName("capinatator_raw");
			setCreativeTab(TabLepidodendronMobile.tab);
			setMaxStackSize(64);
		}

//		@Nullable
//		@Override
//		public String getMobStr() {
//			return "lepidodendron:prehistoric_flora_capinatator";
//		}

		@Nullable
		@Override
		public String getVariantStr() {
			return null;
		}
	}
}
