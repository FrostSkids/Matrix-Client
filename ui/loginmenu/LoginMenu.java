package matrix.ui.loginmenu;

import java.io.IOException;
import matrix.GuiUtil.GuiUtil;
import matrix.Utils.SessionChanger;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.EnumChatFormatting;

public class LoginMenu extends GuiScreen {
   protected CustomTextField mailInput;
   protected CustomTextField passInput;

   public LoginMenu(GuiMainMenu guiMainMenu) {
   }

   public void initGui() {
      ScaledResolution sr = new ScaledResolution(this.mc);
      this.mailInput = new CustomTextField(101, this.mc.fontRendererObj, sr.getScaledWidth() / 2 - 75 + 40, sr.getScaledHeight() / 2 - 35, 100, 15);
      this.mailInput.setMaxStringLength(100);
      this.passInput = new CustomTextField(101, this.mc.fontRendererObj, sr.getScaledWidth() / 2 - 75 + 40, sr.getScaledHeight() / 2 - 15, 100, 15);
      this.buttonList.add(new GuiButton(1, sr.getScaledWidth() / 2 - 40 - 12, sr.getScaledHeight() / 2 + 15, 100, 15, "Login"));
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      ScaledResolution sr = new ScaledResolution(this.mc);
      GuiUtil.drawRoundedRect(sr.getScaledWidth() / 2 - 80, sr.getScaledHeight() / 2 - 40, sr.getScaledWidth() / 2 + 80, sr.getScaledHeight() / 2 + 35, 1.0F, -116585203);
      this.mc.fontRendererObj.drawString("Mail:", (float)(sr.getScaledWidth() / 2 - 75 + 10), (float)(sr.getScaledHeight() / 2 - 40 + this.mc.fontRendererObj.FONT_HEIGHT), -1);
      this.mc.fontRendererObj.drawString("Pass:", (float)(sr.getScaledWidth() / 2 - 75 + 10), (float)(sr.getScaledHeight() / 2 - 20 + this.mc.fontRendererObj.FONT_HEIGHT), -1);
      this.mailInput.drawTextBox();
      this.passInput.drawTextBox();
      String strText = "Logged in as: " + EnumChatFormatting.GREEN + this.mc.getSession().getUsername();
      this.mc.fontRendererObj.drawString(strText, (float)(sr.getScaledWidth() / 2 - this.mc.fontRendererObj.getStringWidth(strText) / 2), 5.0F, -1);
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   protected void keyTyped(char typedChar, int keyCode) throws IOException {
      this.mailInput.textboxKeyTyped(typedChar, keyCode);
      this.passInput.textboxKeyTyped(typedChar, keyCode);
      super.keyTyped(typedChar, keyCode);
   }

   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      ScaledResolution sr = new ScaledResolution(this.mc);
      if (mouseY > sr.getScaledHeight() / 2 + 15 && mouseY < sr.getScaledHeight() / 2 + 30 && mouseX > sr.getScaledWidth() / 2 - 40 - 12 && mouseX < sr.getScaledWidth() / 2 - 40 - 12 + 100 && !this.mailInput.getText().isEmpty()) {
         if (this.passInput.getText().isEmpty()) {
            SessionChanger.getInstance().setUserOffline(this.mailInput.getText());
         } else {
            SessionChanger.getInstance().setUser(this.mailInput.getText(), this.passInput.getText());
         }
      }

      this.mailInput.mouseClicked(mouseX, mouseY, mouseButton);
      this.passInput.mouseClicked(mouseX, mouseY, mouseButton);
   }

   public void updateScreen() {
      this.mailInput.updateCursorCounter();
      this.passInput.updateCursorCounter();
   }
}
