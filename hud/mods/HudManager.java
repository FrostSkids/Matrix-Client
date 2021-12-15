package matrix.hud.mods;

import java.util.ArrayList;
import java.util.Iterator;
import matrix.hud.mods.impl.Animations;
import matrix.hud.mods.impl.CPSMod;
import matrix.hud.mods.impl.CompactChat;
import matrix.hud.mods.impl.Coodrs;
import matrix.hud.mods.impl.Croshair;
import matrix.hud.mods.impl.FPS;
import matrix.hud.mods.impl.Fullbright;
import matrix.hud.mods.impl.ItemPhysics;
import matrix.hud.mods.impl.Keystrokes;
import matrix.hud.mods.impl.LeftHand;
import matrix.hud.mods.impl.MoreParticals;
import matrix.hud.mods.impl.MotionBlur;
import matrix.hud.mods.impl.MouseStrokes;
import matrix.hud.mods.impl.Overlay;
import matrix.hud.mods.impl.PackDisplay;
import matrix.hud.mods.impl.Perspective;
import matrix.hud.mods.impl.PingHud;
import matrix.hud.mods.impl.PotionHud;
import matrix.hud.mods.impl.TargetHUD;
import matrix.hud.mods.impl.ToggleSprint;

public class HudManager {
   public ArrayList<HudMod> hudMods = new ArrayList();
   public FPS fps;
   public Keystrokes keystrokes;
   public PingHud pingHUD;
   public ToggleSprint TS;
   public Perspective perspective;
   public Coodrs coords;
   public Overlay overlay;
   public Fullbright fullbright;
   public Croshair croshair;
   public Animations anims;
   public CPSMod cps;
   public PotionHud ph;
   public TargetHUD th;
   public ItemPhysics item;
   public MouseStrokes mouse;
   public LeftHand lhand;
   public PackDisplay pack;
   public MoreParticals mp;
   public MotionBlur mb;
   public CompactChat cc;
   public PingHud pinghud;

   public HudManager() {
      this.hudMods.add(this.fps = new FPS());
      this.hudMods.add(this.keystrokes = new Keystrokes());
      this.hudMods.add(this.pingHUD = new PingHud());
      this.hudMods.add(this.TS = new ToggleSprint());
      this.hudMods.add(this.perspective = new Perspective());
      this.hudMods.add(this.anims = new Animations());
      this.hudMods.add(this.coords = new Coodrs());
      this.hudMods.add(this.overlay = new Overlay());
      this.hudMods.add(this.fullbright = new Fullbright());
      this.hudMods.add(this.croshair = new Croshair());
      this.hudMods.add(this.cps = new CPSMod());
      this.hudMods.add(this.ph = new PotionHud());
      this.hudMods.add(this.th = new TargetHUD());
      this.hudMods.add(this.item = new ItemPhysics());
      this.hudMods.add(this.mouse = new MouseStrokes());
      this.hudMods.add(this.lhand = new LeftHand());
      this.hudMods.add(this.pack = new PackDisplay());
      this.hudMods.add(this.mp = new MoreParticals());
      this.hudMods.add(this.mb = new MotionBlur());
      this.hudMods.add(this.cc = new CompactChat());
      this.hudMods.add(this.pinghud = new PingHud());
   }

   public void renderMods() {
      Iterator var2 = this.hudMods.iterator();

      while(var2.hasNext()) {
         HudMod m = (HudMod)var2.next();
         if (m.isEnabled()) {
            m.draw();
         }
      }

   }
}
