
package Entity;

import java.util.Properties;
import javax.mail.Authenticator;


public class Session {

    private static int idUser;
    
      public static void start(int currentUserID) {
        idUser = currentUserID;
    }

    public static int getCurrentSession() {
        if (idUser != -1) 
            
            return idUser;
        return -1;
        
    }
      public static void close() throws Exception {
        if (idUser != -1) {
            idUser = -1;
        } else {
            throw new Exception("Session has not started yet!");
        }
    }

    public static Session getDefaultInstance(Properties props, Authenticator authenticator) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
