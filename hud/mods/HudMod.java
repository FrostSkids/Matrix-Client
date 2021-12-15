package matrix.hud.mods;

import java.awt.Color;
import java.util.ArrayList;
import matrix.Client;
import matrix.hud.DraggableComponent;
import matrix.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class HudMod {
   public Minecraft mc = Minecraft.getMinecraft();
   public FontRenderer fr;
   public ArrayList<Setting> settings;
   public String name;
   public boolean enabled;
   public DraggableComponent drag;
   public int x;
   public int y;

   public HudMod(String name, int x, int y) {
      this.fr = this.mc.fontRendererObj;
      this.enabled = true;
      this.name = name;

      try {
         this.x = (Integer)Client.i.config.config.get(name + " x");
         this.y = (Integer)Client.i.config.config.get(name + " y");
         this.setEnabled((Boolean)Client.i.config.config.get(name + " enabled"));
      } catch (NullPointerException var5) {
         var5.printStackTrace();
         this.x = x;
         this.y = y;
         this.enabled = false;
      }

      this.settings = new ArrayList();
      this.drag = new DraggableComponent(this.x, this.y, this.getW(), this.getH(), (new Color(0, 0, 0, 0)).getRGB());
   }

   public void addSettings(Setting... sets) {
   }

   public int getW() {
      return 50;
   }

   public int getH() {
      return 50;
   }

   public void draw() {
   }

   public void renderDummy(int mouseX, int mouseY) {
      this.drag.draw(mouseX, mouseY);
   }

   public int getX() {
      return this.drag.getxPosition();
   }

   public int getY() {
      return this.drag.getyPosition();
   }

   public void setEnabled(boolean enabled) {
      this.enabled = enabled;
   }

   public void toggle() {
      this.setEnabled(!this.enabled);
   }

   public boolean isEnabled() {
      return this.enabled;
   }
}
