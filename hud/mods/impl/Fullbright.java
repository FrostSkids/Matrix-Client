package matrix.hud.mods.impl;

import matrix.hud.mods.HudMod;

public class Fullbright extends HudMod {
   float gamma;

   public Fullbright() {
      super("Fullbright", 0, 0);
      this.gamma = this.mc.gameSettings.gammaSetting;
   }

   public void draw() {
      if (this.enabled) {
         this.mc.gameSettings.gammaSetting = 10000.0F;
      } else {
         this.mc.gameSettings.gammaSetting = this.gamma;
      }

      super.draw();
   }

   public void renderDummy(int mouseX, int mouseY) {
      if (this.enabled) {
         this.mc.gameSettings.gammaSetting = 10000.0F;
      } else {
         this.mc.gameSettings.gammaSetting = this.gamma;
      }

      super.renderDummy(mouseX, mouseY);
   }
}
