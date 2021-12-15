package matrix.hud;

import java.io.IOException;
import java.util.Iterator;
import matrix.Client;
import matrix.hud.mods.HudMod;
import matrix.ui.ClickGUI;
import matrix.ui.Cosmetics;
import matrix.ui.buttons.CosmeticsButton;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class HUDConfigScreen extends GuiScreen {
   public void initGui() {
      super.initGui();
      this.buttonList.add(new GuiButton(100, this.width / 2 - 50, this.height / 2 - 10, 100, 20, "Modules"));
      this.buttonList.add(new CosmeticsButton(200, this.width / 2 - 80, this.height / 2 - 10, 22, 20, ""));
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      Iterator var5 = Client.i.hudManager.hudMods.iterator();

      while(var5.hasNext()) {
         HudMod m = (HudMod)var5.next();
         if (m.isEnabled()) {
            m.renderDummy(mouseX, mouseY);
         }
      }

      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   protected void actionPerformed(GuiButton button) throws IOException {
      super.actionPerformed(button);
      switch(button.id) {
      case 100:
         this.mc.displayGuiScreen(new ClickGUI());
      default:
         super.actionPerformed(button);
         switch(button.id) {
         case 200:
            this.mc.displayGuiScreen(new Cosmetics());
         default:
         }
      }
   }
}
