package matrix.hud.mods.impl;

import matrix.Utils.font.FontUtil;
import matrix.hud.mods.HudMod;
import net.minecraft.client.Minecraft;

public class FPS extends HudMod {
   public FPS() {
      super("FPS", 100, 100);
   }

   public void draw() {
      FontUtil.normal.drawString(this.name + " > " + Minecraft.getDebugFPS(), (double)this.getX(), (float)this.getY(), -1);
      super.draw();
   }

   public void renderDummy(int mouseX, int mouseY) {
      FontUtil.normal.drawString(this.name + " > " + "1000", (double)this.getX(), (float)this.getY(), -1);
      super.renderDummy(mouseX, mouseY);
   }

   public int getH() {
      return this.fr.FONT_HEIGHT;
   }

   public int getW() {
      return this.fr.getStringWidth(this.name + " > " + "1000");
   }
}
