package matrix.mod.impl;

import matrix.event.EventTarget;
import matrix.mod.Catagory;
import matrix.mod.ModuleMain;

public class ToggleSneak extends ModuleMain {
   public ToggleSneak() {
      super("ToggleSneak", "Toggle your sneaking status!", Catagory.MISC);
   }

   @EventTarget
   public void onUpdate() {
      if (this.isEnabled() && !this.mc.thePlayer.isBlocking() && !this.mc.thePlayer.isSprinting()) {
         this.mc.thePlayer.setSneaking(true);
      }

   }

   public void onDisable() {
      super.onDisable();
      this.mc.thePlayer.setSneaking(false);
   }
}
