package matrix.hud.mods;

public enum Catagory {
   HUD("Hud"),
   WORLD("World"),
   MISC("Misc"),
   COSMETIC("Cosmetic"),
   PLAYER("Player");

   public String name;
   public Boolean expanded;

   public Boolean switchExpanded() {
      this.expanded = !this.expanded;
      return this.expanded;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Boolean getExpanded() {
      return this.expanded;
   }

   public void setExpanded(Boolean expanded) {
      this.expanded = expanded;
   }

   private Catagory(String name) {
      this.name = name;
   }
}
