package matrix.ui;

import java.awt.Color;
import matrix.GuiUtil.GuiUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class Cosmetics extends GuiScreen {
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      ScaledResolution sr = new ScaledResolution(this.mc);
      GuiUtil.drawRoundedRect(200, 50, sr.getScaledWidth() - 200, sr.getScaledHeight() - 50, 4.0F, (new Color(10, 10, 10, 155)).getRGB());
      GuiUtil.drawRoundedOutline(200, 50, sr.getScaledWidth() - 200, sr.getScaledHeight() - 50, 4.0F, 2.0F, (new Color(255, 255, 255, 170)).getRGB());
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   public void initGui() {
      super.initGui();
   }
}
