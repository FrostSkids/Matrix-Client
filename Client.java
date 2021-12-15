package matrix;

import java.util.Iterator;
import matrix.Utils.SessionChanger;
import matrix.Utils.config.Config;
import matrix.Utils.font.FontUtil;
import matrix.event.EventManager;
import matrix.event.EventTarget;
import matrix.event.impl.ClientTick;
import matrix.hud.HUDConfigScreen;
import matrix.hud.mods.HudManager;
import matrix.mod.ModManager;
import matrix.mod.ModuleMain;
import matrix.mod.impl.PerspectiveCore;
import net.minecraft.client.Minecraft;

public class Client {
   public static final Client INSTANCE = new Client();
   public String NAME = "Matrix Client";
   public String VERSION = "V 0.1 / Development";
   public String AUTHOR = "BluePvP";
   public String NAMEVER;
   public static Client i = new Client();
   public static boolean buttonstyle;
   public Minecraft mc;
   public Config config;
   public EventManager eventManager;
   public ModManager moduleManager;
   public HudManager hudManager;

   public Client() {
      this.NAMEVER = this.NAME + this.VERSION;
      this.mc = Minecraft.getMinecraft();
   }

   public void startup() {
      this.eventManager = new EventManager();
      this.config = new Config();
      this.config.loadModConfig();
      this.moduleManager = new ModManager();
      this.hudManager = new HudManager();
      SessionChanger.getInstance().setUserOffline("BallsCocker");
      System.out.println("Starting " + this.NAMEVER);
      EventManager.register(this);
      FontUtil.bootstrap();
   }

   public static final Client getInstance() {
      return INSTANCE;
   }

   public void shutdown() {
      System.out.println("Shutting down " + this.NAMEVER);
      this.config.saveModConfig();
      EventManager.unregister(this);
   }

   public void onRender() {
      Iterator var2 = ModManager.modules.iterator();

      while(var2.hasNext()) {
         ModuleMain m = (ModuleMain)var2.next();
         m.onRender();
      }

   }

   @EventTarget
   public void onTick(ClientTick event) {
      if (this.mc.gameSettings.CLICK_GUI.isPressed()) {
         this.mc.displayGuiScreen(new HUDConfigScreen());
      }

      if (this.mc.gameSettings.keyBindSprint.isPressed()) {
         System.out.println("Enabled > ToggleSprint");
         Iterator var3 = ModManager.modules.iterator();

         while(var3.hasNext()) {
            ModuleMain m = (ModuleMain)var3.next();
            if (m.name.equals("ToggleSprint")) {
               m.toggle();
            }
         }
      }

      if (Minecraft.getMinecraft().gameSettings.GLHF.isPressed()) {
         Minecraft.getMinecraft().thePlayer.sendChatMessage("GLHF");
      }

      if (this.mc.gameSettings.keyBindSneak.isPressed()) {
         this.moduleManager.toggleSneak.toggle();
      }

      if (this.mc.gameSettings.keyBindSprint.isPressed()) {
         this.moduleManager.toggleSprint.toggle();
      }

      PerspectiveCore var10000;
      if (this.mc.gameSettings.CLIENT_GUI_HUD_MOD_PER.isKeyDown() && this.hudManager.perspective.enabled) {
         var10000 = this.moduleManager.perspectiveCore;
         PerspectiveCore.perspectTogl = true;
         this.mc.gameSettings.thirdPersonView = 1;
         System.out.println("Perspective key is down.");
      }

      if (!this.mc.gameSettings.CLIENT_GUI_HUD_MOD_PER.isKeyDown()) {
         var10000 = this.moduleManager.perspectiveCore;
         if (PerspectiveCore.perspectTogl && !this.mc.gameSettings.keyBindTogglePerspective.isPressed() && this.mc.thePlayer != null) {
            this.mc.gameSettings.thirdPersonView = 0;
            var10000 = this.moduleManager.perspectiveCore;
            PerspectiveCore.perspectTogl = false;
            var10000 = this.moduleManager.perspectiveCore;
            PerspectiveCore.cameraPitch = this.mc.thePlayer.rotationPitch;
            var10000 = this.moduleManager.perspectiveCore;
            PerspectiveCore.cameraYaw = this.mc.thePlayer.rotationYaw;
         }
      }

      if (!this.mc.gameSettings.CLIENT_GUI_HUD_MOD_PER.isKeyDown() && this.mc.thePlayer != null) {
         var10000 = this.moduleManager.perspectiveCore;
         PerspectiveCore.cameraPitch = this.mc.thePlayer.rotationPitch;
         var10000 = this.moduleManager.perspectiveCore;
         PerspectiveCore.cameraYaw = this.mc.thePlayer.rotationYaw;
      }

      if (this.mc.gameSettings.keyBindTogglePerspective.isPressed() && this.mc.thePlayer != null) {
         var10000 = this.moduleManager.perspectiveCore;
         PerspectiveCore.cameraPitch = this.mc.thePlayer.rotationPitch;
         var10000 = this.moduleManager.perspectiveCore;
         PerspectiveCore.cameraYaw = this.mc.thePlayer.rotationYaw;
         var10000 = this.moduleManager.perspectiveCore;
         PerspectiveCore.perspectTogl = false;
      }

   }
}
