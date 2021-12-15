package matrix.Utils.config;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import matrix.Client;
import matrix.hud.mods.HudMod;

public class Config {
   public File configFolder = new File("MatrixClient");
   public File modsFolder = new File("MatrixClient/Mods");
   public Configuration config;
   public Configuration configToSave = ConfigurationAPI.newConfiguration(new File("MatrixClient/Mods/ModConfig.quick"));

   public void saveModConfig() {
      if (!this.configFolder.exists()) {
         this.configFolder.mkdirs();
      }

      if (!this.modsFolder.exists()) {
         this.modsFolder.mkdirs();
      }

      System.out.println("Saving Mod Config");
      Iterator var2 = Client.i.hudManager.hudMods.iterator();

      while(var2.hasNext()) {
         HudMod m = (HudMod)var2.next();
         this.configToSave.set(m.name + " x", m.getX());
         this.configToSave.set(m.name + " y", m.getY());
         this.configToSave.set(m.name + " enabled", m.isEnabled());
      }

      try {
         this.configToSave.save();
      } catch (IOException var3) {
         var3.printStackTrace();
      }

   }

   public void loadModConfig() {
      try {
         System.out.println("Loading Mod Config");
         this.config = ConfigurationAPI.loadExistingConfiguration(new File("MatrixClient/Mods/ModConfiguration.quick"));
      } catch (IOException var2) {
         var2.printStackTrace();
      }

   }
}
