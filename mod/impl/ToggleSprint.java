package matrix.mod.impl;

import matrix.Client;
import matrix.event.EventTarget;
import matrix.event.impl.EventUpdate;
import matrix.mod.Catagory;
import matrix.mod.ModuleMain;

public class ToggleSprint extends ModuleMain {
   public ToggleSprint() {
      super("ToggleSprint", "Toggle your sprinting status!", Catagory.MISC);
   }

   @EventTarget
   public void onUpdate(EventUpdate e) {
      if (Client.i.hudManager.TS.isEnabled()) {
         if (this.mc.thePlayer.onGround && !this.mc.thePlayer.isCollidedVertically && !this.mc.thePlayer.isSneaking()) {
            this.mc.thePlayer.isBlocking();
         }

         this.mc.thePlayer.setSprinting(true);
      }

   }

   public void onDisable() {
      super.onDisable();
      this.mc.thePlayer.setSprinting(false);
   }
}
