package matrix.mod;

import java.util.ArrayList;
import matrix.mod.impl.PerspectiveCore;
import matrix.mod.impl.ToggleSneak;
import matrix.mod.impl.ToggleSprint;

public class ModManager {
   public static ArrayList<ModuleMain> modules = new ArrayList();
   public ToggleSprint toggleSprint;
   public ToggleSneak toggleSneak;
   public PerspectiveCore perspectiveCore;
   public ArrayList<ModuleMain> mods = new ArrayList();

   public ModManager() {
      this.mods.add(this.toggleSprint = new ToggleSprint());
      this.mods.add(this.toggleSneak = new ToggleSneak());
      this.mods.add(this.perspectiveCore = new PerspectiveCore());
   }
}
