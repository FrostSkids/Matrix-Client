package matrix.ui;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import matrix.Client;
import matrix.GuiUtil.GuiUtil;
import matrix.Utils.font.FontUtil;
import matrix.ui.comp.ModButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class ClickGUI extends GuiScreen {
   ArrayList<ModButton> modButtons = new ArrayList();

   public void initGui() {
      super.initGui();
      this.modButtons.add(new ModButton(280, 60, 65, this.mc.fontRendererObj.FONT_HEIGHT + 25, Client.i.hudManager.keystrokes));
      this.modButtons.add(new ModButton(350, 60, 65, this.mc.fontRendererObj.FONT_HEIGHT + 25, Client.i.hudManager.anims));
      this.modButtons.add(new ModButton(420, 60, 65, this.mc.fontRendererObj.FONT_HEIGHT + 25, Client.i.hudManager.perspective));
      this.modButtons.add(new ModButton(490, 60, 65, this.mc.fontRendererObj.FONT_HEIGHT + 25, Client.i.hudManager.cps));
      this.modButtons.add(new ModButton(560, 60, 65, this.mc.fontRendererObj.FONT_HEIGHT + 25, Client.i.hudManager.ph));
      this.modButtons.add(new ModButton(630, 60, 65, this.mc.fontRendererObj.FONT_HEIGHT + 25, Client.i.hudManager.overlay));
      this.modButtons.add(new ModButton(700, 60, 65, this.mc.fontRendererObj.FONT_HEIGHT + 25, Client.i.hudManager.fullbright));
      this.modButtons.add(new ModButton(280, 120, 65, this.mc.fontRendererObj.FONT_HEIGHT + 25, Client.i.hudManager.fps));
      this.modButtons.add(new ModButton(350, 120, 65, this.mc.fontRendererObj.FONT_HEIGHT + 25, Client.i.hudManager.item));
      this.modButtons.add(new ModButton(420, 120, 65, this.mc.fontRendererObj.FONT_HEIGHT + 25, Client.i.hudManager.TS));
      this.modButtons.add(new ModButton(490, 120, 65, this.mc.fontRendererObj.FONT_HEIGHT + 25, Client.i.hudManager.mouse));
      this.modButtons.add(new ModButton(560, 120, 65, this.mc.fontRendererObj.FONT_HEIGHT + 25, Client.i.hudManager.lhand));
      this.modButtons.add(new ModButton(630, 120, 65, this.mc.fontRendererObj.FONT_HEIGHT + 25, Client.i.hudManager.pack));
      this.modButtons.add(new ModButton(700, 120, 65, this.mc.fontRendererObj.FONT_HEIGHT + 25, Client.i.hudManager.mp));
      this.modButtons.add(new ModButton(280, 180, 65, this.mc.fontRendererObj.FONT_HEIGHT + 25, Client.i.hudManager.mb));
      this.modButtons.add(new ModButton(350, 180, 65, this.mc.fontRendererObj.FONT_HEIGHT + 25, Client.i.hudManager.croshair));
      this.modButtons.add(new ModButton(420, 180, 65, this.mc.fontRendererObj.FONT_HEIGHT + 25, Client.i.hudManager.cc));
      this.modButtons.add(new ModButton(490, 180, 65, this.mc.fontRendererObj.FONT_HEIGHT + 25, Client.i.hudManager.coords));
      this.modButtons.add(new ModButton(560, 180, 65, this.mc.fontRendererObj.FONT_HEIGHT + 25, Client.i.hudManager.th));
      this.modButtons.add(new ModButton(630, 180, 65, this.mc.fontRendererObj.FONT_HEIGHT + 25, Client.i.hudManager.pinghud));
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      ScaledResolution sr = new ScaledResolution(this.mc);
      super.drawScreen(mouseX, mouseY, partialTicks);
      GuiUtil.drawRoundedRect(186, 50, sr.getScaledWidth() - 186, sr.getScaledHeight() - 50, 4.0F, (new Color(10, 10, 10, 155)).getRGB());
      GuiUtil.drawRoundedOutline(186, 50, sr.getScaledWidth() - 186, sr.getScaledHeight() - 50, 4.0F, 2.0F, (new Color(255, 255, 255, 170)).getRGB());
      FontUtil.normal.drawString("Matrix Client", 210.0D, 60.0F, (new Color(255, 255, 255, 255)).getRGB());
      FontUtil.normal.drawString("Credits:", 206.0D, 120.0F, (new Color(255, 255, 255, 255)).getRGB());
      FontUtil.normal.drawString("Ninjego", 206.0D, 130.0F, (new Color(255, 255, 255, 255)).getRGB());
      FontUtil.normal.drawString("QuickDaffy", 206.0D, 140.0F, (new Color(255, 255, 255, 255)).getRGB());
      GuiUtil.drawRoundedRect(200, 50, sr.getScaledWidth() - 685, sr.getScaledHeight() - 50, 4.0F, (new Color(10, 10, 10, 155)).getRGB());
      GuiUtil.drawRoundedOutline(200, 50, sr.getScaledWidth() - 685, sr.getScaledHeight() - 50, 4.0F, 2.0F, (new Color(255, 255, 255, 170)).getRGB());
      Iterator var6 = this.modButtons.iterator();

      while(var6.hasNext()) {
         ModButton m = (ModButton)var6.next();
         m.draw();
      }

   }

   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      Iterator var5 = this.modButtons.iterator();

      while(var5.hasNext()) {
         ModButton m = (ModButton)var5.next();
         m.onClick(mouseX, mouseY, mouseButton);
      }

   }
}
