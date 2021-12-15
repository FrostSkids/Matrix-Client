package matrix.ui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;

public class ClientButtons extends GuiButton {
   public ClientButtons(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
      super(buttonId, x, y, widthIn, heightIn, buttonText);
   }

   public void drawButton(Minecraft mc, int mouseX, int mouseY) {
      if (this.visible) {
         FontRenderer fr = mc.fontRendererObj;
         this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
         Gui.drawRect((double)(this.xPosition + this.width), (double)(this.yPosition + this.height), (double)this.xPosition, (double)this.yPosition, -1358954496);
         int j = -1;
         if (this.hovered) {
            j = -11184641;
         }

         drawCenteredString(fr, this.displayString, (double)(this.xPosition + this.width / 2), (double)(this.yPosition + (this.height - 8) / 2), j);
      }

   }
}
