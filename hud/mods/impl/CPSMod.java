package matrix.hud.mods.impl;

import java.util.ArrayList;
import java.util.List;
import matrix.Utils.font.FontUtil;
import matrix.hud.mods.HudMod;
import net.minecraft.client.gui.Gui;
import org.lwjgl.input.Mouse;

public class CPSMod extends HudMod {
   private List<Long> leftClicks = new ArrayList();
   private List<Long> rightClicks = new ArrayList();
   private boolean leftWasPressed;
   private boolean rightWasPressed;
   private long leftLastPressed;
   private long rightLastPressed;
   Gui g = new Gui();

   public CPSMod() {
      super("CPS Mod", 5, 5);
   }

   public void draw() {
      boolean leftPressed = Mouse.isButtonDown(0);
      boolean rightPressed = Mouse.isButtonDown(1);
      if (leftPressed != this.leftWasPressed) {
         this.leftLastPressed = System.currentTimeMillis();
         this.leftWasPressed = leftPressed;
         if (leftPressed) {
            this.leftClicks.add(this.leftLastPressed);
         }
      } else if (rightPressed != this.rightWasPressed) {
         this.rightLastPressed = System.currentTimeMillis();
         this.rightWasPressed = rightPressed;
         if (rightPressed) {
            this.rightClicks.add(this.rightLastPressed);
         }
      }

      if (this.leftClicks != null) {
         FontUtil.normal.drawString(this.getLeftCPS() + " | " + this.getRightCPS() + " CPS", (double)this.getX(), (float)this.getY(), -1);
      } else {
         FontUtil.normal.drawString("0 | 0 CPS", (double)this.getX(), (float)this.getY(), -1);
      }

      super.draw();
   }

   public void renderDummy(int mouseX, int mouseY) {
      this.g.drawHollowRect((double)(this.getX() - 2), (double)(this.getY() - 2), (double)(this.getW() - 2), (double)(this.getH() - 40), 1358954495);
      if (this.leftClicks != null) {
         FontUtil.normal.drawString(this.getLeftCPS() + " | " + this.getRightCPS() + " CPS", (double)this.getX(), (float)this.getY(), -1);
      } else {
         FontUtil.normal.drawString("0 | 0 CPS", (double)this.getX(), (float)this.getY(), -1);
      }

      super.renderDummy(mouseX, mouseY);
   }

   public int getWidth() {
      return this.fr.getStringWidth("0 | 0 CPS") + 1;
   }

   public int getHeight() {
      return this.fr.FONT_HEIGHT + 1;
   }

   private int getLeftCPS() {
      long leftTime = System.currentTimeMillis();
      this.leftClicks.removeIf((beenLeftTime) -> {
         return beenLeftTime + 1000L < leftTime;
      });
      return this.leftClicks.size();
   }

   private int getRightCPS() {
      long rightTime = System.currentTimeMillis();
      this.rightClicks.removeIf((beenRightTime) -> {
         return beenRightTime + 1000L < rightTime;
      });
      return this.rightClicks.size();
   }
}
