package zaafranigabriel.recipydesign.Exception;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import zaafranigabriel.recipydesign.Webservices.WsCore;

/**
 * Created by zaafranigabriel on 24/06/2016.
 */
public class SendException extends Exception {

    private int id ;
    private String message;

    private void sendData(){
        HashMap<String, String> listValue = new HashMap<>();
        listValue.put("url","http://10.211.55.4/database/WSlog.php");
        listValue.put("id",String.valueOf(id));
        listValue.put("message", this.message);
        try {
            String valueReturned = new WsCore().execute(listValue).get();
            int v = 0;
            v = 12;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public SendException(int id,String message){
        super(id+" - "+message);
        this.id = id;
        this.message = message;
        sendData();
    }


}
