package andbook.ecample.mun;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class startRequest extends StringRequest{
   //하는 역할 php로 로케이션 보내고 갑을 가지고 온다.
    final static private String URL= "http://dbwo4011.cafe24.com/t.php";
    private Map<String,String> parameters;

    public startRequest(String userlocation, Response.Listener<String> listener)
    {
        super(Method.POST, URL, listener,null);
        parameters =new HashMap<>();//해쉬맵을 통해 초기화
            parameters.put("userlocation", userlocation);
            parameters.put("Type", "stationName");
    }
    public startRequest(String longitude, String latitude, Response.Listener<String> listener) //오버라이딩 같은 메소드 이름의 다른 형태의 경우
    {
        super(Method.POST, URL, listener,null);
        parameters =new HashMap<>();//해쉬맵을 통해 초기화

            parameters.put("dmY", longitude);
            parameters.put("dmX", latitude);
            parameters.put("Type", "location");

    }
    @Override
    public Map<String , String> getParams()
    {

        return parameters;
    }


}
