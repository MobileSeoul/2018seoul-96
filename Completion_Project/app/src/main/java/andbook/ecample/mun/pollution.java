package andbook.ecample.mun;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018-05-03.
 */

public class pollution {
    public String stationName="뭘가";
    public String sidoName = ""; //도명
    public String ssgName = "";//시 군 명
    public String umdName = "";// 읍면동
    public String tmX = ""; // 좌표 X
    public String tmY = ""; // 좌표 Y
    public ArrayList<String> tm = new ArrayList<String>(); // 측정소까지의 거리   |
    public String ErrorCheck = "";                                             //| 값이 없을 때를 대비해 가까운 순으로 다른 측정소 이름도 어레이로 저장
    public ArrayList<String> StationName = new ArrayList<String>(); //측정소 이름|
    public String dataTime = ""; // 측정일
    public  String so2Value = ""; // 아황산가스 농도
    public  String coValue = ""; //일산화탄소 농도
    public  String o3Value = ""; //오존 농도
    public  String no2Value = ""; //이산화질소 농도
    public  String pm10Value = ""; //미세먼지(Pm10)농도
    public  String pm10Value24 = ""; //미세먼지 (pm10) 24시간 예측 이동농도
    public  String pm25Value = ""; //미세먼지(pm25) 농도
    public  String pm25Value24 = ""; //미세먼지(pm25) 농도
    public  String khaiValue = ""; //통합대기환경수치
    public   String khaiGrade = ""; //통합대기환경지수
    public   String so2Grade = ""; //아황산가스 지수
    public   String coGrade = ""; //일산화탄소 지수
    public   String o3Grade = ""; //오존 지수
    public   String no2Grade = ""; //이산화질소 지수
    public   String pm10Grade = ""; //미세먼지(Pm10) 24시간 등급
    public   String pm10Grade1h = ""; //미세먼지(Pm10) 1시간 등급
    public  String pm25Grade = ""; //미세먼지(Pm25) 24시간 등급
    public   ArrayList<String> pm25Grade1h = new ArrayList<>(  ); //미세먼지(Pm25) 1시간 등급
    public String dataTime1 ="";
    public String dataTime2 ="";
    public String dataTime3 ="";

    public String informGrade1="";
    public String informGrade2="";
    public String informGrade3="";


}