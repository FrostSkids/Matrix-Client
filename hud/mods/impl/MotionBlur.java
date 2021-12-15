package matrix.hud.mods.impl;

import matrix.Client;
import matrix.hud.mods.HudMod;
import net.minecraft.util.ResourceLocation;

public class MotionBlur extends HudMod {
   public MotionBlur() {
      super("Motion Blur", 6, 7);
   }

   public void draw() {
      if (Client.i.hudManager.mb.isEnabled()) {
         this.fr.drawString("[Motion Blur Toggled]", (float)this.getX(), (float)this.getY(), -1);
         this.mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
      }

      super.draw();
   }

   public void renderDummy(int mouseX, int mouseY) {
      super.renderDummy(mouseX, mouseY);
   }
}
