package ganymedes01.etfuturum.configuration;

import cpw.mods.fml.client.config.DummyConfigElement;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.ConfigCategory;
import ganymedes01.etfuturum.lib.Reference;
import net.minecraft.client.gui.GuiScreen;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ConfigGUI extends GuiConfig {

	public ConfigGUI(GuiScreen parent) {
		super(parent, getElements(), Reference.MOD_ID, Reference.MOD_ID, false, false, GuiConfig.getAbridgedConfigPath(getGameConfigPath()));
	}

  private static String getGameConfigPath() {
    return new File(Launch.minecraftHome, "config" + File.separator + Reference.MOD_ID).getAbsolutePath();
  }

	@SuppressWarnings("rawtypes")
	private static List<IConfigElement> getElements() {
		List<IConfigElement> list = new ArrayList<IConfigElement>();

		for (ConfigBase config : ConfigBase.getConfigs()) {
			List<IConfigElement> children = new ArrayList<IConfigElement>();
			for (ConfigCategory cat : config.getConfigCats()) {
				children.add(new ConfigElement(cat));
			}
			if (!children.isEmpty()) {
				list.add(new DummyConfigElement.DummyCategoryElement(
						config.getConfigName(),
						Reference.MOD_ID.toLowerCase() + ".configgui.category." + config.getConfigName(),
						children
				));
			}
		}
		return list;
	}
}
