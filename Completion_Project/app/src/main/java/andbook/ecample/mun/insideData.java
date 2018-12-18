package andbook.ecample.mun;

import android.content.Context;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class insideData {
    static void saveData (pollution Pollution,String Address,Context context)
    {
        String Setting = "";
        Setting += Pollution.stationName+"/";
        Setting += Pollution.pm10Value+"/";
        Setting += Pollution.pm10Grade+"/";
        Setting += Pollution.pm25Grade+"/";
        Setting += Pollution.pm25Value+"/";
        Setting += Pollution.khaiGrade+"/";
        Setting += Pollution.khaiValue+"/";
        Setting += Pollution.dataTime+"/";
        Setting += Pollution.so2Grade+"/";
        Setting += Pollution.coGrade+"/";
        Setting += Pollution.o3Grade+"/";
        Setting += Pollution.no2Grade+"/";
        Setting += Pollution.so2Value+"/";
        Setting += Pollution.coValue+"/";
        Setting += Pollution.no2Value+"/";
        Setting += Pollution.o3Value+"/";
        Setting += Pollution.pm10Value24+"/";
        Setting += Pollution.pm25Value24+"/";
        Setting += Address+"/";

        try {
            FileOutputStream outFs = context.openFileOutput("pollution.txt",
                    context.MODE_PRIVATE);

            outFs.write(Setting.getBytes());
            outFs.close();
            //Toast.makeText(context,
              //      "Data저장됨", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(context,
                    "내부 저장소 화면 데이터 저장 실패 오류", Toast.LENGTH_SHORT).show();
        }
    }


    static String readData (pollution Pollution,Context context){
        String diaryStr = null;
        FileInputStream inFs;
        try {
            inFs = context.openFileInput("pollution.txt");
            byte[] txt = new byte[500];
            inFs.read(txt);
            inFs.close();
            diaryStr = (new String(txt)).trim();
            String value ="";
            int _index = 0;
            for(int i=0;i<diaryStr.length();i++)
            {
                char Check=diaryStr.charAt(i);
                if(Check=='/') {
                    if(_index==0) {
                        Pollution.stationName = value;
                    } else if(_index==1){
                        Pollution.pm10Value = value;
                    } else if(_index==2){
                        Pollution.pm10Grade = value;
                    } else if(_index==3){
                        Pollution.pm25Grade =value;
                    } else if(_index==4){
                        Pollution.pm25Value =value;
                    } else if(_index==5){
                        Pollution.khaiGrade =value;
                    } else if(_index==6){
                        Pollution.khaiValue =value;
                    } else if(_index==7){
                        Pollution.dataTime =value;
                    } else if(_index==8){
                        Pollution.so2Grade =value;
                    } else if(_index==9){
                        Pollution.coGrade =value;
                    } else if(_index==10){
                        Pollution.o3Grade =value;
                    } else if(_index==11){
                        Pollution.no2Grade =value;
                    } else if(_index==12){
                        Pollution.so2Value =value;
                    }else if(_index==13){
                        Pollution.coValue =value;
                    }else if(_index==14){
                        Pollution.no2Value =value;
                    }else if(_index==15){
                        Pollution.o3Value =value;
                    }else if(_index==16){
                        Pollution.pm10Value24 =value;
                    }else if(_index==17){
                        Pollution.pm25Value24 =value;
                    }else if(_index==18){
                        return value;
                    }
                    value = "";
                    _index++;
                }
                else
                    value += diaryStr.charAt(i);
            }
        } catch (IOException e) {
            return "";
        }
        return "";
    }

    static void saveButtonInfo(Context context,String Name,boolean Switch){

        try {
            FileOutputStream outFs = context.openFileOutput(Name+".txt",
                    context.MODE_PRIVATE);

            if(Switch==true)
                outFs.write('1');
            else
                outFs.write('0');
            outFs.close();
            //Toast.makeText(context,
               //     "설정값 저장", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(context,
                    "설정 값 저장 실패 오류", Toast.LENGTH_SHORT).show();
        }
    }

    static boolean loadButtonInfo(Context context,String Name){
        String diaryStr = null;
        FileInputStream inFs;
        try {
            inFs = context.openFileInput(Name+".txt");
            byte[] txt = new byte[2];
            inFs.read(txt);
            inFs.close();
            diaryStr = (new String(txt)).trim();
            String value ="";
            int _index = 0;
            for(int i=0;i<diaryStr.length();i++)
            {
                value+=diaryStr.charAt(i);

            }
            if(value.equals("1")){
                return true;
            }
            else{
                return false;
            }
        } catch (IOException e) {
            return false;
        }
    }
    static void saveTime(Context context,int hour,int min){
        try {
            FileOutputStream outFs = context.openFileOutput("_Time.txt",
                    context.MODE_PRIVATE);

            int Time = hour*60+min;
            outFs.write(String.valueOf(Time).getBytes());
            outFs.close();
           // Toast.makeText(context,
             //       "시간값 저장", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(context,
                    "시간 값 저장 실패", Toast.LENGTH_SHORT).show();
        }
    }

    static int loadTime(Context context){
        String diaryStr = null;
        ArrayList<String> time = new ArrayList<>();
        time.add("0");
        time.add("0");
        FileInputStream inFs;
        try {
            inFs = context.openFileInput("_Time.txt");
            byte[] txt = new byte[10];
            inFs.read(txt);
            inFs.close();
            diaryStr = (new String(txt)).trim();
            if(!diaryStr.equals(""))
                return Integer.parseInt(diaryStr);
        } catch (IOException e) {
            return 0;
        }
        return 0;
    }
}

