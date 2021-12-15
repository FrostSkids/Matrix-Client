package matrix.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import org.lwjgl.input.Mouse;

public class DraggableComponent {
   private Minecraft mc = Minecraft.getMinecraft();
   private int x;
   private int y;
   private int width;
   private int height;
   private int color;
   private int lastX;
   private int lastY;
   private boolean dragging;

   public DraggableComponent(int x, int y, int width, int height, int color) {
      this.width = width;
      this.height = height;
      this.x = x;
      this.y = y;
      this.color = color;
   }

   public int getxPosition() {
      return this.x < 2 ? 2 : this.x;
   }

   public int getyPosition() {
      return this.y < 2 ? 2 : this.y;
   }

   public void setxPosition(int x) {
      this.x = x;
   }

   public void setyPosition(int y) {
      this.y = y;
   }

   public int getHeight() {
      return this.height;
   }

   public int getWidth() {
      return this.width > this.mc.displayWidth ? this.mc.displayWidth : this.width;
   }

   public int getColor() {
      return this.color;
   }

   public void setColor(int color) {
      this.color = color;
   }

   public void draw(int mouseX, int mouseY) {
      this.draggingFix(mouseX, mouseY);
      Gui.drawRect((double)this.getxPosition(), (double)this.getyPosition(), (double)(this.getxPosition() + this.getWidth()), (double)(this.getyPosition() + this.getHeight()), this.getColor());
      boolean mouseOverX = mouseX >= this.getxPosition() && mouseX <= this.getxPosition() + this.getWidth();
      boolean mouseOverY = mouseY >= this.getyPosition() && mouseY <= this.getyPosition() + this.getHeight();
      if (mouseOverX && mouseOverY && Mouse.isButtonDown(0) && !this.dragging) {
         this.lastX = this.x - mouseX;
         this.lastY = this.y - mouseY;
         this.dragging = true;
      }

   }

   private void draggingFix(int mouseX, int mouseY) {
      if (this.dragging) {
         this.x = mouseX + this.lastX;
         this.y = mouseY + this.lastY;
         if (!Mouse.isButtonDown(0)) {
            this.dragging = false;
         }
      }

   }
}
