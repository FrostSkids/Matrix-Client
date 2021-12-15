package matrix.event;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import matrix.Client;
import net.minecraft.client.gui.ChatLine;
import net.minecraft.util.IChatComponent;

public abstract class Event {
   private boolean cancelled;
   private IChatComponent component;
   private List<ChatLine> chatLines;

   public Event call() {
      this.cancelled = false;
      call(this);
      return this;
   }

   public boolean isCancelled() {
      return this.cancelled;
   }

   public void setCancelled(boolean cancelled) {
      this.cancelled = cancelled;
   }

   private static final void call(Event event) {
      EventManager var10000 = Client.i.eventManager;
      ArrayHelper<EventData> dataList = EventManager.get(event.getClass());
      if (dataList != null) {
         Iterator var3 = dataList.iterator();

         while(var3.hasNext()) {
            EventData data = (EventData)var3.next();

            try {
               data.target.invoke(data.source, event);
            } catch (IllegalAccessException var5) {
               var5.printStackTrace();
            } catch (InvocationTargetException var6) {
               var6.printStackTrace();
            }
         }
      }

   }

   public void ChatReceivedEvent(IChatComponent component, List<ChatLine> chatLines) {
      this.component = component;
      this.chatLines = chatLines;
   }

   public IChatComponent getComponent() {
      return this.component;
   }

   public List<ChatLine> getChatLines() {
      return this.chatLines;
   }

   public static enum State {
      PRE("PRE", 0),
      POST("POST", 1);

      private State(String string, int number) {
      }
   }
}
