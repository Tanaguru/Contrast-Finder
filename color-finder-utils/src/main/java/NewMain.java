
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 *
 * @author alingua
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        HttpClient hc = new HttpClient();
//        hc.
        GetMethod get = new GetMethod("http://localhost:8080/gs-rest-service-1.0-SNAPSHOT/testColor");
        try {
            hc.executeMethod(get);
            String response = get.getResponseBodyAsString();
            System.out.println(response);
//            JSONObject myJson = new JSONObject(response);
//            JSONArray json = new JSONArray(myJson.getJSONArray("suggestedColors"));
            
//            for (int i=0 ; i<json.length();i++) {
//                System.out.println(json.get(i).toString());
//            }
            
        } catch (IOException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
