package matrix.mod.impl;

import matrix.mod.Catagory;
import matrix.mod.ModuleMain;

public class MotionBlur extends ModuleMain {
   public MotionBlur() {
      super("Motion Blur", "Adds Motion Blur", Catagory.COSMETIC);
   }

   public void onDisable() {
      super.onDisable();
      this.mc.thePlayer.setSprinting(false);
   }
}
