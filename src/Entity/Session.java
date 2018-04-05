
package Entity;


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

}
