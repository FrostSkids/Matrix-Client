package matrix.mod.impl;

import matrix.event.EventTarget;
import matrix.event.impl.ClientTick;
import matrix.mod.Catagory;
import matrix.mod.ModuleMain;
import org.lwjgl.opengl.Display;

public class PerspectiveCore extends ModuleMain {
   public static boolean perspectTogl = false;
   public static boolean returnOnRelease = true;
   public static float cameraYaw = 0.0F;
   public static float cameraPitch = 0.0F;
   public int prevPerspective = 0;

   public PerspectiveCore() {
      super("PerspectiveCore", "PMC", Catagory.MISC);
      this.enabled = true;
   }

   public void onEnable() {
      super.onEnable();
   }

   public void onDisable() {
      super.onDisable();
   }

   public float getCameraYaw() {
      return perspectTogl ? cameraYaw : this.mc.thePlayer.rotationYaw;
   }

   public float getCameraPitch() {
      return perspectTogl ? cameraPitch : this.mc.thePlayer.rotationPitch;
   }

   public boolean overrideMouse() {
      if (this.mc.inGameHasFocus && Display.isActive()) {
         if (!perspectTogl) {
            return true;
         }

         this.mc.mouseHelper.mouseXYChange();
         float f1 = this.mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
         float f2 = f1 * f1 * f1 * 8.0F;
         float f3 = (float)this.mc.mouseHelper.deltaX * f2;
         float f4 = (float)this.mc.mouseHelper.deltaY * f2;
         cameraYaw += f3 * 0.15F;
         cameraPitch -= f4 * 0.15F;
         if (cameraPitch > 90.0F) {
            cameraPitch = 90.0F;
         }

         if (cameraPitch < -90.0F) {
            cameraPitch = -90.0F;
         }
      }

      return false;
   }

   @EventTarget
   public void onUpdate(ClientTick e) {
      if (perspectTogl) {
         cameraYaw = this.mc.thePlayer.rotationYaw;
         cameraPitch = this.mc.thePlayer.rotationPitch;
         this.prevPerspective = this.mc.gameSettings.thirdPersonView;
         this.mc.gameSettings.thirdPersonView = 1;
      } else {
         this.mc.gameSettings.thirdPersonView = this.prevPerspective;
      }

      perspectTogl = false;
      this.mc.gameSettings.thirdPersonView = this.prevPerspective;
   }
}
