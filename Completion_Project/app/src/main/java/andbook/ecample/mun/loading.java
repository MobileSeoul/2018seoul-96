package andbook.ecample.mun;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;


public class loading extends Activity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);




        Handler hd = new Handler();
        hd.postDelayed(new splashhandler() , 3000); // 3초 후에 splashHandler 실행
    }

    private class splashhandler implements Runnable{
        public void run() {
           /* Intent intent =new Intent(loading.this , MainActivity.class);
            intent.putExtra("stationName", stationName);
            intent.putExtra("pm10Value",pm10Value);
            intent.putExtra("pm10Grade",pm10Grade);
            intent.putExtra("pm25Grade",pm25Grade);
            intent.putExtra("pm25Value", pm25Value);
            intent.putExtra("KhaiGrade",KhaiGrade);
            intent.putExtra("KhaiValue",KhaiValue);
            intent.putExtra("dataTime",dataTime);
            loading.this.startActivity(intent);
            finish();*/
           //startActivity(new Intent(getApplication(), MainActivity.class)); // 로딩이 끝난후 이동할 Activity
           loading.this.finish(); // 로딩페이지 Activity Stack에서 제거
        }
    }


}
