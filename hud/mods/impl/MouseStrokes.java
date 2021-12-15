package matrix.hud.mods.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import matrix.GuiUtil.GuiUtil;
import matrix.hud.mods.HudMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class MouseStrokes extends HudMod {
   List<Long> clicks = new ArrayList();
   List<Long> clicks2 = new ArrayList();
   boolean wasPresed;
   private long lastPress;
   boolean wasPresed2;
   private long lastPress2;
   private MouseStrokes.KeystrokesMode mode;

   public MouseStrokes() {
      super("Keys", 200, 100);
      this.mode = MouseStrokes.KeystrokesMode.MOUSE;
   }

   public void draw() {
      boolean pressedLMB = Mouse.isButtonDown(0);
      if (pressedLMB != this.wasPresed) {
         this.lastPress = System.currentTimeMillis();
         this.wasPresed = pressedLMB;
         if (pressedLMB) {
            this.clicks.add(this.lastPress);
         }
      }

      boolean pressedRMB = Mouse.isButtonDown(1);
      if (pressedRMB != this.wasPresed2) {
         this.lastPress2 = System.currentTimeMillis();
         this.wasPresed2 = pressedRMB;
         if (pressedRMB) {
            this.clicks2.add(this.lastPress2);
         }
      }

      GL11.glPushMatrix();
      MouseStrokes.Key[] var6;
      int var5 = (var6 = this.mode.getKeys()).length;

      for(int var4 = 0; var4 < var5; ++var4) {
         MouseStrokes.Key key = var6[var4];
         int textWidth = this.fr.getStringWidth(key.getName());
         GuiUtil.drawRoundedRect(this.getX() + key.getX(), this.getY() + key.getY(), this.getX() + key.getX() + key.getWidth(), this.getY() + key.getY() + key.getHeight(), 4.0F, key.isDown() ? (new Color(255, 255, 255, 100)).getRGB() : Integer.MIN_VALUE);
         GuiUtil.drawRoundedOutline(this.getX() + key.getX(), this.getY() + key.getY(), this.getX() + key.getX() + key.getWidth(), this.getY() + key.getY() + key.getHeight(), 2.0F, 2.0F, (new Color(214, 209, 209)).getRGB());
         this.fr.drawStringWithShadow(key.getName(), (double)(this.getX() + key.getX() + key.getWidth() / 2 - textWidth / 2), (double)(this.getY() + key.getY() + key.getHeight() / 2 - 4), key.isDown() ? Integer.MAX_VALUE : -1);
      }

      this.fr.drawStringWithShadow("LMB", (double)(this.getX() + this.getW() - this.fr.getStringWidth("LMB") * 3), (double)((float)(this.getY() + this.getH()) - (float)this.fr.FONT_HEIGHT * 1.9F), -1);
      this.fr.drawStringWithShadow("RMB", (double)((float)(this.getX() + this.getW()) - (float)this.fr.getStringWidth("RMB") * 1.3F), (double)((float)(this.getY() + this.getH()) - (float)this.fr.FONT_HEIGHT * 1.9F), -1);
      GL11.glPopMatrix();
      float hhg = 2.0F;
      if (this.getCPS() < 10) {
         this.fr.drawStringWithShadow("" + this.getCPS(), (double)((float)(this.getX() + this.getW()) - (float)this.fr.getStringWidth("LMB") * 1.9F - (float)this.fr.getStringWidth("" + (float)this.getCPS() / 2.0F)), (double)((float)(this.getY() + this.getH()) - (float)this.fr.FONT_HEIGHT * hhg / 2.0F), -1);
      } else {
         this.fr.drawStringWithShadow("" + this.getCPS(), (double)((float)(this.getX() + this.getW()) - (float)this.fr.getStringWidth("LMB") * 2.0F - (float)this.fr.getStringWidth("" + (float)this.getCPS() / 2.0F)), (double)((float)(this.getY() + this.getH()) - (float)this.fr.FONT_HEIGHT * hhg / 2.0F), -1);
      }

      if (this.getCPS2() < 10) {
         this.fr.drawStringWithShadow("" + this.getCPS2(), (double)((float)(this.getX() + this.getW()) - (float)this.fr.getStringWidth("RMB") * 1.0F), (double)((float)(this.getY() + this.getH()) - (float)this.fr.FONT_HEIGHT * hhg / 2.0F), -1);
      } else {
         this.fr.drawStringWithShadow("" + this.getCPS2(), (double)((float)(this.getX() + this.getW()) - (float)this.fr.getStringWidth("RMB") * 1.15F), (double)((float)(this.getY() + this.getH()) - (float)this.fr.FONT_HEIGHT * hhg / 2.0F), -1);
      }

      super.draw();
   }

   public void renderDummy(int mouseX, int mouseY) {
      boolean pressedLMB = Mouse.isButtonDown(0);
      if (pressedLMB != this.wasPresed) {
         this.lastPress = System.currentTimeMillis();
         this.wasPresed = pressedLMB;
         if (pressedLMB) {
            this.clicks.add(this.lastPress);
         }
      }

      boolean pressedRMB = Mouse.isButtonDown(1);
      if (pressedRMB != this.wasPresed2) {
         this.lastPress2 = System.currentTimeMillis();
         this.wasPresed2 = pressedRMB;
         if (pressedRMB) {
            this.clicks2.add(this.lastPress2);
         }
      }

      GL11.glPushMatrix();
      MouseStrokes.Key[] var8;
      int var7 = (var8 = this.mode.getKeys()).length;

      for(int var6 = 0; var6 < var7; ++var6) {
         MouseStrokes.Key key = var8[var6];
         int textWidth = this.fr.getStringWidth(key.getName());
         GuiUtil.drawRoundedRect(this.getX() + key.getX(), this.getY() + key.getY(), this.getX() + key.getX() + key.getWidth(), this.getY() + key.getY() + key.getHeight(), 4.0F, key.isDown() ? (new Color(255, 255, 255, 100)).getRGB() : Integer.MIN_VALUE);
         GuiUtil.drawRoundedOutline(this.getX() + key.getX(), this.getY() + key.getY(), this.getX() + key.getX() + key.getWidth(), this.getY() + key.getY() + key.getHeight(), 2.0F, 2.0F, (new Color(214, 209, 209)).getRGB());
         this.fr.drawStringWithShadow(key.getName(), (double)(this.getX() + key.getX() + key.getWidth() / 2 - textWidth / 2), (double)(this.getY() + key.getY() + key.getHeight() / 2 - 4), key.isDown() ? Integer.MAX_VALUE : -1);
      }

      float hhg = 2.0F;
      this.fr.drawStringWithShadow("LMB", (double)((float)(this.getX() + this.getW()) - (float)this.fr.getStringWidth("LMB") * 3.0F), (double)((float)(this.getY() + this.getH()) - (float)this.fr.FONT_HEIGHT * hhg), -1);
      this.fr.drawStringWithShadow("RMB", (double)((float)(this.getX() + this.getW()) - (float)this.fr.getStringWidth("RMB") * 1.3F), (double)((float)(this.getY() + this.getH()) - (float)this.fr.FONT_HEIGHT * hhg), -1);
      if (this.getCPS() < 10) {
         this.fr.drawStringWithShadow("" + this.getCPS(), (double)((float)(this.getX() + this.getW()) - (float)this.fr.getStringWidth("LMB") * 1.9F - (float)this.fr.getStringWidth("" + (float)this.getCPS() / 2.0F)), (double)((float)(this.getY() + this.getH()) - (float)this.fr.FONT_HEIGHT * hhg / 2.0F), -1);
      } else {
         this.fr.drawStringWithShadow("" + this.getCPS(), (double)((float)(this.getX() + this.getW()) - (float)this.fr.getStringWidth("LMB") * 2.0F - (float)this.fr.getStringWidth("" + (float)this.getCPS() / 2.0F)), (double)((float)(this.getY() + this.getH()) - (float)this.fr.FONT_HEIGHT * hhg / 2.0F), -1);
      }

      if (this.getCPS2() < 10) {
         this.fr.drawStringWithShadow("" + this.getCPS2(), (double)((float)(this.getX() + this.getW()) - (float)this.fr.getStringWidth("RMB") * 1.0F), (double)((float)(this.getY() + this.getH()) - (float)this.fr.FONT_HEIGHT * hhg / 2.0F), -1);
      } else {
         this.fr.drawStringWithShadow("" + this.getCPS2(), (double)((float)(this.getX() + this.getW()) - (float)this.fr.getStringWidth("RMB") * 1.15F), (double)((float)(this.getY() + this.getH()) - (float)this.fr.FONT_HEIGHT * hhg / 2.0F), -1);
      }

      GL11.glPopMatrix();
      super.renderDummy(mouseX, mouseY);
   }

   private int getCPS() {
      long time = System.currentTimeMillis();
      this.clicks.removeIf((aLong) -> {
         return aLong + 1000L < time;
      });
      return this.clicks.size();
   }

   private int getCPS2() {
      long time2 = System.currentTimeMillis();
      this.clicks2.removeIf((aLong2) -> {
         return aLong2 + 1000L < time2;
      });
      return this.clicks2.size();
   }

   public static class Key {
      private static Minecraft mc = Minecraft.getMinecraft();
      private static final MouseStrokes.Key LMB;
      private static final MouseStrokes.Key RMB;
      private final String name;
      private final KeyBinding keyBind;
      private final int x;
      private final int y;
      private final int w;
      private final int h;

      static {
         LMB = new MouseStrokes.Key("", mc.gameSettings.keyBindAttack, 1, 100, 28, 18);
         RMB = new MouseStrokes.Key("", mc.gameSettings.keyBindUseItem, 31, 41, 28, 18);
      }

      public Key(String name, KeyBinding keyBind, int x, int y, int w, int h) {
         this.name = name;
         this.keyBind = keyBind;
         this.x = x;
         this.y = y;
         this.w = w;
         this.h = h;
      }

      public boolean isDown() {
         return this.keyBind.isKeyDown();
      }

      public String getName() {
         return this.name;
      }

      public KeyBinding getKeyBind() {
         return this.keyBind;
      }

      public int getX() {
         return this.x;
      }

      public int getY() {
         return this.y;
      }

      public int getWidth() {
         return this.w;
      }

      public int getHeight() {
         return this.h;
      }
   }

   public static enum KeystrokesMode {
      MOUSE(new MouseStrokes.Key[]{MouseStrokes.Key.LMB, MouseStrokes.Key.RMB});

      private final MouseStrokes.Key[] keys;
      private int width;
      private int height;

      private KeystrokesMode(MouseStrokes.Key... keysIn) {
         this.keys = keysIn;
         MouseStrokes.Key[] var7;
         int var6 = (var7 = this.keys).length;

         for(int var5 = 0; var5 < var6; ++var5) {
            MouseStrokes.Key key = var7[var5];
            this.width = Math.max(this.width, key.getX() + key.getWidth());
            this.height = Math.max(this.height, key.getY() + key.getHeight());
         }

      }

      public int getWidth() {
         return this.width;
      }

      public int getHeight() {
         return this.height;
      }

      public MouseStrokes.Key[] getKeys() {
         return this.keys;
      }
   }
}
