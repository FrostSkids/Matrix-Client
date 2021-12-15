package matrix.Utils;

import com.mojang.authlib.Agent;
import com.mojang.authlib.AuthenticationService;
import com.mojang.authlib.UserAuthentication;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.util.UUIDTypeAdapter;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;

public class SessionChanger {
   private static SessionChanger instance;
   private final UserAuthentication auth;

   public static SessionChanger getInstance() {
      if (instance == null) {
         instance = new SessionChanger();
      }

      return instance;
   }

   private SessionChanger() {
      UUID notSureWhyINeedThis = UUID.randomUUID();
      AuthenticationService authService = new YggdrasilAuthenticationService(Minecraft.getMinecraft().getProxy(), notSureWhyINeedThis.toString());
      this.auth = authService.createUserAuthentication(Agent.MINECRAFT);
      authService.createMinecraftSessionService();
   }

   public void setUser(String email, String password) {
      if (!Minecraft.getMinecraft().getSession().getUsername().equals(email) || Minecraft.getMinecraft().getSession().getToken().equals("0")) {
         this.auth.logOut();
         this.auth.setUsername(email);
         this.auth.setPassword(password);

         try {
            this.auth.logIn();
            Session session = new Session(this.auth.getSelectedProfile().getName(), UUIDTypeAdapter.fromUUID(this.auth.getSelectedProfile().getId()), this.auth.getAuthenticatedToken(), this.auth.getUserType().getName());
            this.setSession(session);
         } catch (Exception var4) {
            var4.printStackTrace();
         }
      }

   }

   private void setSession(Session session) {
      Minecraft.getMinecraft().session = session;
   }

   public void setUserOffline(String username) {
      this.auth.logOut();
      Session session = new Session(username, username, "__BluePvP__", "__BluePvP__");
      this.setSession(session);
   }
}
