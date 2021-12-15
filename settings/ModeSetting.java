package matrix.settings;

import java.util.Arrays;
import java.util.List;

public class ModeSetting extends Setting {
   public List<String> modes;
   public int index;

   public ModeSetting(String name, String defaultMode, String... modes) {
      this.name = name;
      this.modes = Arrays.asList(modes);
      this.index = this.modes.indexOf(defaultMode);
   }

   public String getMode() {
      return (String)this.modes.get(this.index);
   }

   public boolean is(String mode) {
      return this.index == this.modes.indexOf(mode) - 1;
   }

   public void cycle() {
      if (this.index < this.modes.size() - 1) {
         ++this.index;
      } else {
         this.index = 0;
      }

   }

   public void increment() {
      if (this.index < this.modes.size() - 1) {
         ++this.index;
      } else {
         this.index = 0;
      }

   }

   public String getValueName() {
      return (String)this.modes.get(this.index);
   }
}
