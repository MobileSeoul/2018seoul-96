package andbook.ecample.mun;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class futureRequest extends StringRequest{
    //하는 역할 php로 로케이션 보내고 갑을 가지고 온다.
    final static private String URL= "http://dbwo4011.cafe24.com/test.php";
    private Map<String,String> parameters;

    public futureRequest(Response.Listener<String> listener)
    {
        super(Method.POST, URL, listener,null);
        parameters =new HashMap<>();//해쉬맵을 통해 초기화

    }


    @Override
    public Map<String , String> getParams()
    {

        return parameters;
    }


}
