package matrix.event;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class EventManager {
   private static final Map<Class<? extends Event>, ArrayHelper<EventData>> REGISTRY_MAP = new HashMap();

   public static void register(Object o) {
      Method[] var4;
      int var3 = (var4 = o.getClass().getDeclaredMethods()).length;

      for(int var2 = 0; var2 < var3; ++var2) {
         Method method = var4[var2];
         if (!isMethodBad(method)) {
            register(method, o);
         }
      }

   }

   public static void register(Object o, Class<? extends Event> clazz) {
      Method[] var5;
      int var4 = (var5 = o.getClass().getDeclaredMethods()).length;

      for(int var3 = 0; var3 < var4; ++var3) {
         Method method = var5[var3];
         if (!isMethodBad(method, clazz)) {
            register(method, o);
         }
      }

   }

   private static void register(Method method, Object o) {
      Class<?> clazz = method.getParameterTypes()[0];
      EventData methodData = new EventData(o, method, ((EventTarget)method.getAnnotation(EventTarget.class)).value());
      if (!methodData.target.isAccessible()) {
         methodData.target.setAccessible(true);
      }

      if (REGISTRY_MAP.containsKey(clazz)) {
         if (!((ArrayHelper)REGISTRY_MAP.get(clazz)).contains(methodData)) {
            ((ArrayHelper)REGISTRY_MAP.get(clazz)).add(methodData);
            sortListValue(clazz);
         }
      } else {
         REGISTRY_MAP.put(clazz, new ArrayHelper<EventData>(methodData) {
            {
               this.add(var1);
            }
         });
      }

   }

   public static void unregister(Object o) {
      Iterator var2 = REGISTRY_MAP.values().iterator();

      while(var2.hasNext()) {
         ArrayHelper<EventData> flexibalArray = (ArrayHelper)var2.next();
         Iterator var4 = flexibalArray.iterator();

         while(var4.hasNext()) {
            EventData methodData = (EventData)var4.next();
            if (methodData.source.equals(o)) {
               flexibalArray.remove(methodData);
            }
         }
      }

      cleanMap(true);
   }

   public static void unregister(Object o, Class<? extends Event> clazz) {
      if (REGISTRY_MAP.containsKey(clazz)) {
         Iterator var3 = ((ArrayHelper)REGISTRY_MAP.get(clazz)).iterator();

         while(var3.hasNext()) {
            EventData methodData = (EventData)var3.next();
            if (methodData.source.equals(o)) {
               ((ArrayHelper)REGISTRY_MAP.get(clazz)).remove(methodData);
            }
         }

         cleanMap(true);
      }

   }

   public static void cleanMap(boolean b) {
      Iterator iterator = REGISTRY_MAP.entrySet().iterator();

      while(true) {
         do {
            if (!iterator.hasNext()) {
               return;
            }
         } while(b && !((ArrayHelper)((Entry)iterator.next()).getValue()).isEmpty());

         iterator.remove();
      }
   }

   public static void removeEnty(Class<? extends Event> clazz) {
      Iterator iterator = REGISTRY_MAP.entrySet().iterator();

      while(iterator.hasNext()) {
         if (((Class)((Entry)iterator.next()).getKey()).equals(clazz)) {
            iterator.remove();
            break;
         }
      }

   }

   private static void sortListValue(Class<? extends Event> clazz) {
      ArrayHelper<EventData> flexibleArray = new ArrayHelper();
      byte[] var5;
      int var4 = (var5 = Priority.VALUE_ARRAY).length;

      for(int var3 = 0; var3 < var4; ++var3) {
         byte b = var5[var3];
         Iterator var7 = ((ArrayHelper)REGISTRY_MAP.get(clazz)).iterator();

         while(var7.hasNext()) {
            EventData methodData = (EventData)var7.next();
            if (methodData.priority == b) {
               flexibleArray.add(methodData);
            }
         }
      }

      REGISTRY_MAP.put(clazz, flexibleArray);
   }

   private static boolean isMethodBad(Method method) {
      return method.getParameterTypes().length != 1 || !method.isAnnotationPresent(EventTarget.class);
   }

   private static boolean isMethodBad(Method method, Class<? extends Event> clazz) {
      return isMethodBad(method) || method.getParameterTypes()[0].equals(clazz);
   }

   public static ArrayHelper<EventData> get(Class<? extends Event> clazz) {
      return (ArrayHelper)REGISTRY_MAP.get(clazz);
   }

   public static void shutdown() {
      REGISTRY_MAP.clear();
   }
}
