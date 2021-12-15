package matrix.hud.mods.impl;

import matrix.hud.mods.HudMod;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class Coodrs extends HudMod {
   Gui g = new Gui();
   ScaledResolution sr;

   public Coodrs() {
      super("Coords", 5, 5);
      this.sr = new ScaledResolution(this.mc);
   }

   public void draw() {
      this.fr.drawStringWithShadow("[X] > " + this.mc.thePlayer.getPosition().getX(), (double)this.getX(), (double)this.getY(), -1);
      this.fr.drawStringWithShadow("[Y] > " + this.mc.thePlayer.getPosition().getY(), (double)this.getX(), (double)(this.getY() + this.fr.FONT_HEIGHT + 3), -1);
      this.fr.drawStringWithShadow("[Z] > " + this.mc.thePlayer.getPosition().getZ(), (double)this.getX(), (double)(this.getY() + (this.fr.FONT_HEIGHT + 3) * 2), -1);
      super.draw();
   }

   public void renderDummy(int mouseX, int mouseY) {
      Gui.drawRect((double)(this.getX() - 2), (double)(this.getY() - 2), (double)(this.getW() + 3), (double)(this.getH() + 2), 1358954495);
      this.fr.drawStringWithShadow("[X] > 1000", (double)this.getX(), (double)this.getY(), -1);
      this.fr.drawStringWithShadow("[Y] > 1000", (double)this.getX(), (double)(this.getY() + this.fr.FONT_HEIGHT + 3), -1);
      this.fr.drawStringWithShadow("[Z] > 1000", (double)this.getX(), (double)(this.getY() + (this.fr.FONT_HEIGHT + 3) * 2), -1);
      super.renderDummy(mouseX, mouseY);
   }
}
