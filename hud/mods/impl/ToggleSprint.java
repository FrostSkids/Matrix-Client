package matrix.hud.mods.impl;

import matrix.hud.mods.HudMod;

public class ToggleSprint extends HudMod {
   public ToggleSprint() {
      super("Toggle Sprint", 2, 3);
   }

   public void draw() {
      this.fr.drawStringWithShadow("Sprinting(Toggled)", (double)this.getX(), (double)this.getY(), -1);
      super.draw();
   }

   public void renderDummy(int mouseX, int mouseY) {
      this.fr.drawStringWithShadow("Sprinting(Toggled)", (double)this.getX(), (double)this.getY(), -1);
      super.renderDummy(mouseX, mouseY);
   }
}
