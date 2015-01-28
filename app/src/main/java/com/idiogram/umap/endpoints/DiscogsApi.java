import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Query;

/**
 * Created by Tre Murillo 1/27/2015
 */

//  Consumer Key	    JfdZojxprrQHOqIpHwpV
//  Consumer Secret	    uigJkstmlBPlLBFMoxAApEHgIdrCGQsx
//  Request Token URL	http://api.discogs.com/oauth/request_token
//  Authorize URL	    http://www.discogs.com/oauth/authorize
//  Access Token URL	http://api.discogs.com/oauth/access_token

public class DiscogsApi implements Api {

    public Record getRecord(String title, Callback<Response> callback){
        return new Record();
    }


}
