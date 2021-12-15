package matrix.ui.comp;

import java.awt.Color;
import matrix.GuiUtil.GuiUtil;
import matrix.Utils.font.FontUtil;
import matrix.hud.mods.HudMod;

public class ModButton {
   public int x;
   public int y;
   public int w;
   public int h;
   public HudMod m;

   public ModButton(int x, int y, int w, int h, HudMod m) {
      this.x = x;
      this.y = y;
      this.w = w;
      this.h = h;
      this.m = m;
   }

   public void draw() {
      GuiUtil.drawRoundedRect(this.x, this.y, this.x + this.w, this.y + this.h, 4.0F, (new Color(255, 255, 255, 70)).getRGB());
      GuiUtil.drawRoundedOutline(this.x, this.y, this.x + this.w, this.y + this.h, 4.0F, 1.0F, this.getColor());
      FontUtil.normal.drawCenteredString(this.m.name, (float)(this.x + 32), (float)(this.y + 11), (new Color(255, 255, 255, 50)).getRGB());
      GuiUtil.drawRoundedRect(this.x, this.y + 24, this.x + this.w, this.y + this.h, 4.0F, this.getColor());
      FontUtil.normal.drawCenteredString(this.getText(), (float)(this.x + 32), (float)(this.y + 26), this.getTextColor());
   }

   private int getColor() {
      return this.m.isEnabled() ? (new Color(0, 255, 0, 255)).getRGB() : (new Color(255, 0, 0, 255)).getRGB();
   }

   private int getTextColor() {
      return this.m.isEnabled() ? (new Color(0, 150, 0, 150)).getRGB() : (new Color(150, 0, 0, 150)).getRGB();
   }

   private String getText() {
      return this.m.isEnabled() ? new String("Enabled") : new String("Disabled");
   }

   public void onClick(int mouseX, int mouseY, int button) {
      if (mouseX >= this.x && mouseX <= this.x + this.w && mouseY >= this.y && mouseY <= this.y + this.h && button == 0) {
         if (this.m.isEnabled()) {
            this.m.setEnabled(false);
            System.out.println(this.m.name);
         } else {
            this.m.setEnabled(true);
            System.out.println(this.m.name);
         }
      }

   }
}
