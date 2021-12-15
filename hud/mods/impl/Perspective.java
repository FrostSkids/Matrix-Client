package matrix.hud.mods.impl;

import matrix.Client;
import matrix.hud.mods.HudMod;

public class Perspective extends HudMod {
   public Perspective() {
      super("FreeLook", 28, 11);
   }

   public void renderDummy(int mouseX, int mouseY) {
      this.fr.drawStringWithShadow("[" + this.name + "]", (double)this.getX(), (double)this.getY(), -1);
      super.renderDummy(mouseX, mouseY);
   }

   public void draw() {
      if (Client.i.moduleManager.perspectiveCore.enabled) {
         this.fr.drawStringWithShadow("[" + this.name + "]", (double)this.getX(), (double)this.getY(), -1);
      }

      super.draw();
   }

   public int getW() {
      return this.fr.getStringWidth("[Perspective]");
   }

   public int getH() {
      return this.fr.FONT_HEIGHT;
   }
}
