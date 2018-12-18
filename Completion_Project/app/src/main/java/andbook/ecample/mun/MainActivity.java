package andbook.ecample.mun;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.kakao.kakaolink.KakaoLink;
import com.kakao.kakaolink.KakaoTalkLinkMessageBuilder;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private backpressedcontlor backpressed;
    private final int PERMISSIONS_ACCESS_FINE_LOCATION = 1000;
    private final int PERMISSIONS_ACCESS_COARSE_LOCATION = 1001;
    private boolean isAccessFineLocation = false;
    private boolean isAccessCoarseLocation = false;
    public double latitude;//위도 경도
    public double longitude;
    ArrayList<String> tomorrowinformGrade;
    ArrayList<String> aftertomorrowinformGrade;
    private boolean isPermission = false;
    private GpsInfo gps;
    static pollution Pollution = new pollution();
    TextView textUserlocation ;
    TextView textUserdatatime;
    TextView textKhaiGrade ;
    TextView textpm25Grade;
    TextView textcoGrade;
    TextView textno2Grade;
    TextView textso2Grade;
    TextView texto3Grade;
    TextView toolbartext;
    TextView toolbartext2;
    TextView today;
    TextView tomor;
    TextView ttomor;
    ImageView state1;
    ImageView state2;
    ImageView state3;
    TextView location;
    TextView pmnong;
    ImageView imageview;
    TextView ozone;
    TextView co2;
    TextView no2;
    TextView  o2;
    TextView avrpm10;
    SearchView searchView;
    TextView avrpm25;
    String my_Address = "";
    ImageView imageview2;
    Toolbar toolbar;
    Button button;
    ScrollView nestedScrollView;
    InputMethodManager imm;
    long now =System.currentTimeMillis(); //시간 추가
    int ImageValue;
    TextView no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = new Intent(this, loading.class);
        //로딩창 띄우기
        startActivity(intent);
        gps = new GpsInfo(MainActivity.this);
        future();
        startRequest("내부디비");



       textUserlocation = (TextView) findViewById(R.id.status);
         textUserdatatime = (TextView) findViewById(R.id.status2);
         textKhaiGrade = (TextView) findViewById(R.id.fir);
         textpm25Grade = (TextView) findViewById(R.id.fiv);
        textno2Grade = (TextView) findViewById(R.id.sec);
         textcoGrade = (TextView) findViewById(R.id.six);
         textso2Grade = (TextView) findViewById(R.id.thir);
         texto3Grade = (TextView) findViewById(R.id.sev);
         toolbartext = (TextView) findViewById(R.id.toolbartext);
        toolbartext2 = (TextView) findViewById(R.id.toolbartext2);
        no =(TextView)findViewById(R.id.editText1);

         today = (TextView) findViewById(R.id.today);
         tomor = (TextView) findViewById(R.id.tomorrow_date);
         ttomor = (TextView) findViewById(R.id.ttmorrow_date);
         state1 =(ImageView)findViewById(R.id.state1);
         state2 =(ImageView)findViewById(R.id.state2);
         state3 =(ImageView)findViewById(R.id.state3);
        imageview =(ImageView)findViewById(R.id.imageView01);
         location  = (TextView) findViewById(R.id.text10);
         pmnong = (TextView) findViewById(R.id.text11);
         ozone = (TextView) findViewById(R.id.text12);
         co2 = (TextView) findViewById(R.id.text13);
         no2 = (TextView) findViewById(R.id.text14);
          o2 = (TextView) findViewById(R.id.text15);
         avrpm10 =(TextView) findViewById(R.id.text16);
         avrpm25 = (TextView)findViewById(R.id.text17);
       toolbar = (Toolbar) findViewById(R.id.toolbar);
        button = (Button)findViewById(R.id.buttonex);



        setSupportActionBar(toolbar);


        final AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);

        Date date =new Date(now);
        Calendar today2 = Calendar.getInstance(); //내일 날짜 변수에 담기
        today2.add(Calendar.DATE,1);
        Calendar today3 = Calendar.getInstance(); //모레 시간 변수에 담기
        today3.add(Calendar.DATE,2);
        SimpleDateFormat day =new SimpleDateFormat("MM/dd");
        Date tomorrow =today2.getTime();
        Date ttomorrow =today3.getTime();

        String getTime = day.format(date); //각각 날짜 담기
        String getTime2 = day.format(tomorrow);
        String getTime3 = day.format(ttomorrow);

        imageview2 =(ImageView) findViewById(R.id.back); //먼지 표시할 화면 추가


        backpressed = new backpressedcontlor(this);


        /*Intent intent= getIntent();
        String stationName = intent.getStringExtra("stationName");
        String KhaiGrade=intent.getStringExtra("KhaiGrade");
        String KhaiValue=intent.getStringExtra("KhaiValue");
        String pm10Grade=intent.getStringExtra("pm10Grade");
        String pm10Value = intent.getStringExtra("pm10Value");
        String pm25Grade=intent.getStringExtra("pm25Grade");
        String pm25Value=intent.getStringExtra("pm25Value");
        String dataTime=intent.getStringExtra("dataTime");*/

        today.setText(getTime);
        tomor.setText(getTime2);
        ttomor.setText(getTime3);

        reconstruction_Display();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        ImageButton imageButton =(ImageButton)findViewById(R.id.loc);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                loadview();

            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_ACCESS_FINE_LOCATION
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            isAccessFineLocation = true;

        } else if (requestCode == PERMISSIONS_ACCESS_COARSE_LOCATION
                && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            isAccessCoarseLocation = true;
        }

        if (isAccessFineLocation && isAccessCoarseLocation) {
            isPermission = true;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchItem.getActionView();

        //  searchView.clearFocus();
        searchView.setQueryHint(getString(R.string.search));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(final String s) { //검색 했을때 이벤트 발생
                Log.w("1111" ,"이벤트발생");
              //  searchView.clearFocus();
                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); //툴바 아이디 찾고 툴바 만들기(위에 상단 메뉴바)
                toolbar.inflateMenu( R.menu.main );
                setSupportActionBar(toolbar);


                my_Address = transAddr.getDmXY(MainActivity.this,s,gps);
                 if(my_Address.equals("error")){
                    Toast.makeText(getApplicationContext(),"네트워크 또는 GPS 상태가 원활하지 않습니다.",Toast.LENGTH_SHORT).show();
                }
                else if(my_Address!=null&&!my_Address.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("현재위치");
                    builder.setMessage(my_Address+"가 맞으십니까?");
                    builder.setPositiveButton("예",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    startRequest(s);
                                }
                            });
                    builder.setNegativeButton("아니오",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                   //Toast.makeText(getApplicationContext(),"아니오를 선택했습니다.",Toast.LENGTH_LONG).show();
                                }
                            });
                    builder.show();
                }
                else{
                    Toast.makeText(MainActivity.this,"해당 주소를 찾을 수 없습니다.",Toast.LENGTH_LONG).show();
                }
                searchView.setQuery("", false);
                searchView.setIconified(true);
                return  false;

            }
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;
        FragmentTransaction ft =getSupportFragmentManager().beginTransaction();

        switch (id) {
            case R.id.home:
                fragment = new Fragment();
                break;
            case R.id.info:
                fragment = new likeservice_move();   //특정미세먼지정보
                break;
            case R.id.share:
                sharekakao();
                break;
            case R.id.help:
                fragment =new help_move();
                break;
            case R.id.homeAsUp:
                finish();
        }

       if(fragment != null) {
           ft.replace(R.id.frag, fragment);

           ft.commit();
       }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.main_content);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
    protected OnBackPressedListener onBackPressedListener;

    public void sharekakao(){
        try {
            final KakaoLink KakaoLink = com.kakao.kakaolink.KakaoLink.getKakaoLink(this);
            final KakaoTalkLinkMessageBuilder kakaoBuilder = KakaoLink.createKakaoTalkLinkMessageBuilder();

            String Date = "";
            String loc="";
            String Info = "";
            long mNow;
            java.util.Date mDate;
            SimpleDateFormat mFormat = new SimpleDateFormat("E.yyyy.MM.dd. \n"+"a " + "hh:mm ");
            mNow = System.currentTimeMillis();
            mDate = new Date(mNow);
            Date = mFormat.format(mDate);


            kakaoBuilder.addText("먼지가 먼지에서 알려드리는 미세먼지 농도에요!\n 마스크는 필수 인거 아시죠! \n현재 시각: "+Date +"\n현재 장소:"+loc+ "\n오늘의 미세먼지 농도:" + Info);
            kakaoBuilder.addAppButton("앱 실행 혹은 다운로드");
            KakaoLink.sendMessage(kakaoBuilder,this);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }




    public interface OnBackPressedListener {
    void doBack();
}

    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) this.findViewById(R.id.main_content);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else if (onBackPressedListener != null)
            onBackPressedListener.doBack();
        else
            backpressed.onBackPressed();

    }

    private void loadview() { //현재위치를 위경도로 가져옴
        gps = new GpsInfo(this);

        //if (isPermission == false)
            callPermission();
       // else {
          /*  latitude = gps.getLatitude();
            longitude = gps.getLongitude();
            Toast.makeText(
                    getApplicationContext(),
                    "당신의 위치 \n위도: " + latitude + "\n경도: " + longitude,
                    Toast.LENGTH_LONG).show();
            startRequest("현재위치");
            my_Address = transAddr.getAddress(this,latitude,longitude);*/
            //gps.showSettingsAlert();
            //onLoad("안서동"); //현재 위치로 onLoad 메서드 호출
            // callPermission();  // 권한 요청을 해야 함
     //   }

    }
    private void callPermission() { //쥐피에스 권한요청
        Log.w("222" ,"callper메소드안");
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_ACCESS_FINE_LOCATION);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){

            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    PERMISSIONS_ACCESS_COARSE_LOCATION);
        } else {
            isPermission = true;
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
           // Toast.makeText(
             //       getApplicationContext(),
               //     "당신의 위치 \n위도: " + latitude + "\n경도: " + longitude,
                 //   Toast.LENGTH_LONG).show();
            startRequest("현재위치");
            my_Address = transAddr.getAddress(this,latitude,longitude);
        }
    }

    private  void reconstruction_Display()
    {
        Date date =new Date(now);
        SimpleDateFormat day =new SimpleDateFormat("YYYY-MM-dd hh:mm");
        String h = day.format(date);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.main_content);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //알맞은 위치에 먼지 정보 넣는 구간
        Future_translate();
        // textUserdatatime.setText("측정시간:"+Pollution.dataTime+"\n현재위치 : "+my_Address);
        textUserlocation.setText("측정불가");
        textKhaiGrade.setText("통합대기등급:"+Pollution. khaiGrade);
        Log.w("222" ,"request는실패");
        textpm25Grade.setText("초미세먼지등급:"+ Pollution.pm25Grade);
        Log.w("222" ,"request는성공");
        location.setText("측정소 위치:       "+Pollution.stationName);
        pmnong.setText("미세먼지:       "+ Pollution.pm10Value);
        ozone.setText("오존:       "+Pollution.o3Value);
        no2.setText("이산화질소:       "+Pollution.no2Value);
        co2.setText("일산화탄소:       "+Pollution.coValue);
        o2.setText("이황산가스:       "+Pollution.so2Value);
        Log.w("222" ,"request는실패");
        avrpm10.setText("일 평균 미세먼지:  "+Pollution.pm10Value24);
        avrpm25.setText("일 평균 초미세먼지 :"+Pollution.pm25Value24);

        textUserdatatime.setText("통합  "+Pollution.khaiValue);
        toolbartext.setText(my_Address);
        toolbartext2.setText(Pollution.dataTime);

        Typeface typeFace = Typeface.createFromAsset(this.getAssets(), "NanumSquareL.otf");
        Typeface typeFace2 = Typeface.createFromAsset(this.getAssets(), "NanumSquareEB.otf");

        textUserdatatime.setTypeface(typeFace);
        textUserlocation.setTypeface(typeFace);
        button.setTypeface(typeFace2);
        no.setTypeface(typeFace2);


        if(Pollution.pm10Grade.equals("1")){
            textUserlocation.setText("좋음");
            toolbartext.setTextColor(getResources().getColor(R.color.color8));           //좋음일때 툴바(지역이름 색)
            toolbartext2.setTextColor(getResources().getColor(R.color.color9));
            Glide.with(this).load(0).into(imageview2);
            Glide.with(this).load(R.drawable.good).into(state1);
        }
        else if(Pollution.pm10Grade.equals("2"))
        {  Glide.with(this).load(R.drawable.mist1).into(imageview2); //등급에 따른 먼지 농도 추가
            textUserlocation.setText("보통");
            Glide.with(this).load(R.drawable.soso).into(state1);
            toolbartext.setTextColor(getResources().getColor(R.color.color8));           //일때 툴바(지역이름 색)
            toolbartext2.setTextColor(getResources().getColor(R.color.color9));}
        else if(Pollution.pm10Grade.equals("3"))
        { Glide.with(this).load(R.drawable.mist2).into(imageview2);
            textUserlocation.setTextColor(Color.WHITE);
            Glide.with(this).load(R.drawable.bad).into(state1);
            textUserlocation.setText("나쁨");
            toolbartext2.setTextColor(getResources().getColor(R.color.color8));}     //나쁨일때 툴바(시간날짜 색 색)
        else if(Pollution.pm10Grade.equals("4"))
        { Glide.with(this).load(R.drawable.mist3).into(imageview2);
            textUserlocation.setTextColor(Color.WHITE);
            Glide.with(this).load(R.drawable.verybad).into(state1);
            textUserlocation.setText("매우나쁨");
            toolbartext2.setTextColor(getResources().getColor(R.color.color8));}     //매우나쁨일때 툴바(시간날짜 색)
        else {
            textUserdatatime.setText("측정불가");
            Glide.with(this).load(0).into(imageview2);
        }

        Glide.with(this).load(R.drawable.notseoul).into(imageview);
        ImageValue=0;
        ifmon("도봉구",R.drawable.gibon2);
        ifmon("강서구",R.drawable.gangseo);
        ifmon("양천구",R.drawable.yangcheon);
        ifmon("구로구",R.drawable.guro);
        ifmon("영등포구",R.drawable.yeongdunpo);
        ifmon("동작구",R.drawable.dongjak);
        ifmon("관악구",R.drawable.kwanak);
        ifmon("금천구",R.drawable.kibon);
        ifmon("서초구",R.drawable.seocho);
        ifmon("강남구",R.drawable.gibon2);
        ifmon("송파구",R.drawable.songpa);
        ifmon("강동구",R.drawable.gibon2);
        ifmon("광진구",R.drawable.gwangjin);
        ifmon("성동구",R.drawable.sungdong);
        ifmon("용산구",R.drawable.yongsan);
        ifmon("마포구",R.drawable.mapogu);
        ifmon("중구",R.drawable.junggu);
        ifmon("노원구",R.drawable.kibon);
        ifmon("중랑구",R.drawable.jungrang);
        ifmon("은평구",R.drawable.eunpyeong);
        ifmon("종로구",R.drawable.jonglo);
        ifmon("성북구",R.drawable.kibon);
        ifmon("강북구",R.drawable.gangbuk);
        ifmon("서대문구",R.drawable.sudaemun);
        ifmon("동대문구",R.drawable.dongdaemun);

        if(ImageValue==1){ //서울인데 구이름이 없어서 배경설정이 안될 때
            ifmon2("도봉",R.drawable.gibon2);
            ifmon2("강서",R.drawable.gangseo);
            ifmon2("양천",R.drawable.yangcheon);
            ifmon2("구로",R.drawable.guro);
            ifmon2("영등포",R.drawable.yeongdunpo);
            ifmon2("동작",R.drawable.dongjak);
            ifmon2("관악",R.drawable.kwanak);
            ifmon2("금천",R.drawable.kibon);
            ifmon2("서초",R.drawable.seocho);
            ifmon2("강남",R.drawable.gibon2);
            ifmon2("송파",R.drawable.songpa);
            ifmon2("강동",R.drawable.gibon2);
            ifmon2("광진",R.drawable.gwangjin);
            ifmon2("성동",R.drawable.sungdong);
            ifmon2("용산",R.drawable.yongsan);
            ifmon2("마포",R.drawable.mapogu);
            ifmon2("중구",R.drawable.junggu);
            ifmon2("노원",R.drawable.kibon);
            ifmon2("중랑",R.drawable.jungrang);
            ifmon2("은평",R.drawable.eunpyeong);
            ifmon2("종로",R.drawable.jonglo);
            ifmon2("성북",R.drawable.kibon);
            ifmon2("강북",R.drawable.gangbuk);
            ifmon2("서대문",R.drawable.sudaemun);
            ifmon2("동대문",R.drawable.dongdaemun);
        }

        ifcl(textKhaiGrade,Pollution.khaiGrade);
        ifcl(textno2Grade,Pollution.no2Grade);
        ifcl(textso2Grade,Pollution.so2Grade);
        ifcl(textpm25Grade,Pollution.pm25Grade);
        ifcl(textcoGrade,Pollution.coGrade);
        ifcl(texto3Grade,Pollution.o3Grade);
        Glide.with(this).load(R.drawable.nopp).into(state2);
        Glide.with(this).load(R.drawable.nopp).into(state3);

        if( my_Address.contains("서울"))
        {
            tomorrowfuture(tomorrowinformGrade.get(0));
            aftertomorrowfuture(aftertomorrowinformGrade.get(0));
        }
        else if( my_Address.contains("제주"))
        {
            if(tomorrowinformGrade.size()>1)
                tomorrowfuture(tomorrowinformGrade.get(1));
            if(aftertomorrowinformGrade.size()>1)
                aftertomorrowfuture(aftertomorrowinformGrade.get(1));
        }
        else if( my_Address.contains("전남"))
        {

            if(tomorrowinformGrade.size()>2)
                tomorrowfuture(tomorrowinformGrade.get(2));
            if(aftertomorrowinformGrade.size()>2)
                aftertomorrowfuture(aftertomorrowinformGrade.get(2));
        }
        else if( my_Address.contains("전라북도"))
        {

            if(tomorrowinformGrade.size()>3)
                tomorrowfuture(tomorrowinformGrade.get(3));
            if(aftertomorrowinformGrade.size()>3)
                aftertomorrowfuture(aftertomorrowinformGrade.get(3));
        }
        else if( my_Address.contains("광주"))
        {

            if(tomorrowinformGrade.size()>4)
                tomorrowfuture(tomorrowinformGrade.get(4));
            if(aftertomorrowinformGrade.size()>4)
                aftertomorrowfuture(aftertomorrowinformGrade.get(4));
        }
        else if( my_Address.contains("경상남도"))
        {

            if(tomorrowinformGrade.size()>5)
                tomorrowfuture(tomorrowinformGrade.get(5));
            if(aftertomorrowinformGrade.size()>5)
                aftertomorrowfuture(aftertomorrowinformGrade.get(5));
        }
        else if( my_Address.contains("경상북도"))
        {

            if(tomorrowinformGrade.size()>6)
                tomorrowfuture(tomorrowinformGrade.get(6));
            if(aftertomorrowinformGrade.size()>6)
                aftertomorrowfuture(aftertomorrowinformGrade.get(6));
        }
        else if( my_Address.contains("울산"))
        {

            if(tomorrowinformGrade.size()>7)
                tomorrowfuture(tomorrowinformGrade.get(7));
            if(aftertomorrowinformGrade.size()>7)
                aftertomorrowfuture(aftertomorrowinformGrade.get(7));
        }
        else if( my_Address.contains("대구"))
        {

            if(tomorrowinformGrade.size()>8)
                tomorrowfuture(tomorrowinformGrade.get(8));
            if(aftertomorrowinformGrade.size()>8)
                aftertomorrowfuture(aftertomorrowinformGrade.get(8));
        }
        else if( my_Address.contains("부산"))
        {

            if(tomorrowinformGrade.size()>9)
                tomorrowfuture(tomorrowinformGrade.get(9));
            if(aftertomorrowinformGrade.size()>9)
                aftertomorrowfuture(aftertomorrowinformGrade.get(9));
        }
        else if( my_Address.contains("충청남도"))
        {

            if(tomorrowinformGrade.size()>10)
                tomorrowfuture(tomorrowinformGrade.get(10));
            if(aftertomorrowinformGrade.size()>10)
                aftertomorrowfuture(aftertomorrowinformGrade.get(10));
        }
        else if( my_Address.contains("충청북도"))
        {

            if(tomorrowinformGrade.size()>11)
                tomorrowfuture(tomorrowinformGrade.get(11));
            if(aftertomorrowinformGrade.size()>11)
                aftertomorrowfuture(aftertomorrowinformGrade.get(11));
        }
        else if( my_Address.contains("세종"))
        {

            if(tomorrowinformGrade.size()>12)
                tomorrowfuture(tomorrowinformGrade.get(12));
            if(aftertomorrowinformGrade.size()>12)
                aftertomorrowfuture(aftertomorrowinformGrade.get(12));
        }
        else if( my_Address.contains("대전"))
        {

            if(tomorrowinformGrade.size()>13)
                tomorrowfuture(tomorrowinformGrade.get(13));
            if(aftertomorrowinformGrade.size()>13)
                aftertomorrowfuture(aftertomorrowinformGrade.get(13));
        }
        else if( my_Address.contains("강원도"))
        {

            if(tomorrowinformGrade.size()>14)
                tomorrowfuture(tomorrowinformGrade.get(14));
            if(aftertomorrowinformGrade.size()>14)
                aftertomorrowfuture(aftertomorrowinformGrade.get(14));
        }
        else if( my_Address.contains("경기도"))
        {

            if(tomorrowinformGrade.size()>16)
                tomorrowfuture(tomorrowinformGrade.get(16));
            if(aftertomorrowinformGrade.size()>16)
                aftertomorrowfuture(aftertomorrowinformGrade.get(16));
        }
        else if( my_Address.contains("인천"))
        {

            if(tomorrowinformGrade.size()>18)
                tomorrowfuture(tomorrowinformGrade.get(18));
            if(aftertomorrowinformGrade.size()>18)
                aftertomorrowfuture(aftertomorrowinformGrade.get(18));
        }

    }
    public void tomorrowfuture(String s2)
    {

        if (s2.contains("좋음"))
        {
            Glide.with(this).load(R.drawable.good).into(state2);
        }
        else if (s2.contains(": 보통"))
        {
            Glide.with(this).load(R.drawable.soso).into(state2);
        }
        else if (s2.contains(": 나쁨"))
        {
            Glide.with(this).load(R.drawable.bad).into(state2);
        }
        else if (s2.contains(": 매우나쁨"))
        {
            Glide.with(this).load(R.drawable.verybad).into(state2);
        }
        else if (s2.contains(": 예보없음"))
        {
            Glide.with(this).load(R.drawable.nopp).into(state2);
        }


    }
    public  void aftertomorrowfuture(String s)
    {

        if (s.contains(": 좋음"))
        {
            Glide.with(this).load(R.drawable.good).into(state3);
        }
        else if (s.contains(": 보통"))
        {
            Glide.with(this).load(R.drawable.soso).into(state3);
        }
        else if (s.contains(": 나쁨"))
        {
            Glide.with(this).load(R.drawable.bad).into(state3);
        }
        else if (s.contains(": 매우나쁨"))
        {
            Glide.with(this).load(R.drawable.verybad).into(state3);
        }
        else if (s.contains(": 예보없음"))
        {
            Glide.with(this).load(R.drawable.nopp).into(state3);
        }

    }

    public void  ifmon(String a,int b){
        if(my_Address.contains("서울")&&ImageValue!=2){
            ImageValue = 1;
            if (my_Address.contains(a)) {
                Glide.with(this).load(b).into(imageview);
                ImageValue = 2;


            }}}
    public void ifmon2(String a,int b){
        if (Pollution.stationName.contains(a)) {
            Glide.with(this).load(b).into(imageview);
            ImageValue = 2;
        }
    }
    public void ifcl(TextView text,String grade){//등급에 따라 좋은 보통나쁨 구분


        if(grade.equals("1")){
            text.setText("좋음");
        }
        else if(grade.equals("2"))
        { //등급에 따른 먼지 농도 추가
            text.setText("보통");}
        else if(grade.equals("3"))
        {
            text.setText("나쁨");}
        else if(grade.equals("4"))
        {text.setText("매우나쁨");}
        else
            text.setText("측정불가");
    }
    public void startRequest(String S) { // S의 값이 현재위치 라고 오면 위치로 잡음

        final Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.w("222" ,"드러왔다" );
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    Log.w("222" ,"드러왔다" );
                    if (success) {
                        //성공했을때;
                        //String stationName = "성공은함";
                        Log.w("222" ,"드러왔다" );
                        String stationName = jsonResponse.getString("stationName");
                        Log.w("222" ,"드러왔다" );
                        String pm10Value = jsonResponse.getString("pm10Value");
                        String pm10Grade = jsonResponse.getString("pm10Grade");
                        String pm25Grade = jsonResponse.getString("pm25Grade");
                        String pm25Value = jsonResponse.getString("pm25Value");
                        String khaiGrade = jsonResponse.getString("khaiGrade");
                        String khaiValue = jsonResponse.getString("khaiValue");
                        String dataTime = jsonResponse.getString("dataTime");
                        String so2Value=jsonResponse.getString("so2Value");
                        String no2Value=jsonResponse.getString("no2Value");
                        String o3Value=jsonResponse.getString("o3Value");
                        String pm10Value24=jsonResponse.getString("pm10Value24");
                        String pm25Value24=jsonResponse.getString("pm25Value24");
                        String coGrade=jsonResponse.getString("coGrade");
                        String o3Grade=jsonResponse.getString("o3Grade");
                        String so2Grade=jsonResponse.getString("so2Grade");
                        String no2Grade=jsonResponse.getString("no2Grade");
                        String coValue=jsonResponse.getString("coValue");

                        Pollution.stationName=stationName;
                        Pollution.pm10Value=pm10Value;
                        Pollution.pm10Grade=pm10Grade;
                        Pollution.pm25Grade=pm25Grade;
                        Pollution.pm25Value=pm25Value;
                        Pollution.khaiGrade=khaiGrade;
                        Pollution.khaiValue=khaiValue;
                        Pollution.dataTime=dataTime;
                        Pollution.so2Grade=so2Grade;
                        Pollution.coGrade=coGrade;
                        Pollution.o3Grade=o3Grade;
                        Pollution.no2Grade=no2Grade;
                        Pollution.so2Value=so2Value;
                        Pollution.coValue=coValue;
                        Pollution.no2Value=no2Value;
                        Pollution.o3Value=o3Value;
                        Pollution.pm10Value24=pm10Value24;
                        Pollution.pm25Value24=pm25Value24;

                                /*editor.putString("userlocation", userlocation);
                                editor.putString("uservalue", uservalue);
                                editor.putString("usergrade", usergrade);
                                editor.commit();*/
                        //jdenew BackgroundTask().execute();

                        insideData.saveData(Pollution,my_Address,MainActivity.this);
                    } else {
                        Pollution.stationName += "성공은함";
                        //접속실패;
                   /* AlertDialog.Builder builder=new AlertDialog.Builder();
                    builder.setMessage("접속 실패")
                            .setNegativeButton("다시시도",null)
                            .create()
                            .show();*/
                    }
                  //  Toast.makeText(
                  //          getApplicationContext(),
                  //          "읽어오기 완료",
                  //          Toast.LENGTH_LONG).show();
                    reconstruction_Display();
                } catch (Exception e) {
                    //e.printStackTrace();
                    //reconstruction_Display();
                    Toast.makeText(getApplicationContext(),"네트워크 상태가 원활하지 않습니다.",Toast.LENGTH_SHORT).show();
                }

            }
        };
        if(S.equals("현재위치")){
            my_Address = transAddr.getAddress(this,latitude,longitude);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("현재위치");
            if(my_Address!=""){
            builder.setMessage(my_Address+"가 맞나요?");
            builder.setPositiveButton("예",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            future();
                            startRequest startRequest = new startRequest(Double.toString(longitude),Double.toString(latitude), responseListener);
                            RequestQueue queue = Volley.newRequestQueue(MainActivity.this); /// 여기서 문제가 생김 이거 해결해야지 값이 이 받아와짐
                            queue.add(startRequest); //여기포함
                        }
                    });
            builder.setNegativeButton("아니오",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //Toast.makeText(getApplicationContext(),"아니오를 선택했습니다.",Toast.LENGTH_LONG).show();
                        }
                    });
            builder.show();
            }
            else{
                Toast.makeText(getApplicationContext(),"네트워크 또는 GPS 상태가 원활하지 않습니다.",Toast.LENGTH_SHORT).show();
            }

        }
        else if(S.equals("내부디비")) {

            my_Address = insideData.readData(Pollution, this);
            if (my_Address == ""||my_Address.equals("error")) {
                //future();
                my_Address = transAddr.getDmXY(this, "구로구", gps);
                startRequest startRequest = new startRequest(Double.toString(gps.lon), Double.toString(gps.lat), responseListener);
                //startRequest startRequest = new startRequest("종로구", responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this); /// 여기서 문제가 생김 이거 해결해야지 값이 이 받아와짐
                queue.add(startRequest); //여기포함
            }
        }
        else {
            future();
            my_Address = transAddr.getDmXY(MainActivity.this,S,gps);
            startRequest startRequest = new startRequest(Double.toString(gps.lon),Double.toString(gps.lat), responseListener);
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this); /// 여기서 문제가 생김 이거 해결해야지 값이 이 받아와짐
            queue.add(startRequest); //여기포함
        }

    }

    private void future() { // S의 값이 현재위치 라고 오면 위치로 잡음

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.w("222" ,"드러왔다" );
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    Log.w("222" ,"드러왔다" );
                    if (success) {
                        //성공했을때;
                        //String stationName = "성공은함";
                        Log.w("222" ,"드러왔다" );

                        String dataTime1=jsonResponse.getString("dataTime1");
                        Pollution.dataTime1=dataTime1;
                        String informGrade1=jsonResponse.getString("informGrade1");
                        Pollution.informGrade1=informGrade1;
                        String dataTime2=jsonResponse.getString("dataTime2");
                        Pollution.dataTime2=dataTime2;
                        String informGrade2=jsonResponse.getString("informGrade2");
                        Pollution.informGrade2=informGrade2;
                        String dataTime3=jsonResponse.getString("dataTime3");
                        Pollution.dataTime3=dataTime3;
                        String informGrade3=jsonResponse.getString("informGrade3");
                        Pollution.informGrade3=informGrade3;


                      //  Toast.makeText(
                      //          getApplicationContext(),
                      //          "예보읽어오기 완료",
                      //          Toast.LENGTH_LONG).show();
                    } else {
                        Pollution.stationName += "성공은함";
                        //접속실패;
                   /* AlertDialog.Builder builder=new AlertDialog.Builder();
                    builder.setMessage("접속 실패")
참고                            .setNegativeButton("다시시도",null)
                            .create()
                            .show();*/
                    }

                    reconstruction_Display();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(
                            getApplicationContext(),
                            "새로운 예보 정보 발표가 필요합니다",
                            Toast.LENGTH_SHORT).show();
                    reconstruction_Display();
                }

            }
        };

        futureRequest future = new futureRequest(responseListener);
        RequestQueue queue1 = Volley.newRequestQueue(MainActivity.this); /// 여기서 문제가 생김 이거 해결해야지 값이 이 받아와짐
        queue1.add(future); //여기포함

    }
    void Future_translate(){
        tomorrowinformGrade = new ArrayList<>();
        aftertomorrowinformGrade = new ArrayList<>();
        try {
            String k[] = Pollution.informGrade2.split("\\,");
            for(int i=0;i<k.length;i++) {
                tomorrowinformGrade.add(Pollution.informGrade2.split(",")[i]);
            }

            String k2[] = Pollution.informGrade3.split("\\,");
            for(int i=0;i<k2.length;i++) {
                aftertomorrowinformGrade.add(Pollution.informGrade3.split(",")[i]);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

}
