package matrix.event.impl;

import matrix.event.Event;

public class KeyEvent extends Event {
   private final int key;

   public KeyEvent(int key) {
      this.key = key;
   }

   public int getKey() {
      return this.key;
   }
}
