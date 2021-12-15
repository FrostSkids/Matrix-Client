package matrix.mod;

import matrix.Client;
import matrix.event.EventManager;
import matrix.event.impl.EventUpdate;
import net.minecraft.client.Minecraft;

public class ModuleMain {
   public Minecraft mc = Minecraft.getMinecraft();
   public String name;
   public String description;
   public boolean enabled;
   public Catagory catagory;

   public ModuleMain(String name, String description, Catagory catagory) {
      this.name = name;
      this.description = description;
      this.catagory = catagory;
      this.enabled = true;
   }

   public void onUpdate(EventUpdate e) {
      EventManager var10000 = Client.i.eventManager;
      EventManager.register(this);
      var10000 = Client.i.eventManager;
      EventManager.register(e);
   }

   public void onEnable() {
      EventManager var10000 = Client.i.eventManager;
      EventManager.register(this);
   }

   public void onDisable() {
      EventManager var10000 = Client.i.eventManager;
      EventManager.unregister(this);
   }

   public void setEnabled(boolean enabled) {
      this.enabled = enabled;
      if (enabled) {
         this.onEnable();
      } else {
         this.onDisable();
      }

   }

   public void toggle() {
      this.setEnabled(this.enabled);
   }

   public String getName() {
      return this.name;
   }

   public String getDescription() {
      return this.description;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public Catagory getCatagory() {
      return this.catagory;
   }

   public void onRender() {
   }
}
