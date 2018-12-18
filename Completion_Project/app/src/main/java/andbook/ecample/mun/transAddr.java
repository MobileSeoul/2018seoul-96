package andbook.ecample.mun;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

//import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class transAddr {

    static String getAddress(Context c,double lat, double lng )  {
        String address = "";
        String Address = "";
        //위치정보를 활용하기 위한 구글 API 객체
        Geocoder geocoder = new Geocoder( c, Locale.getDefault());

        //주소 목록을 담기 위한 HashMap
        List<Address> list = null;

        try{
            list = geocoder.getFromLocation(lat, lng, 1);;
        } catch(Exception e){
            e.printStackTrace();
        }

        if(list == null){
            Log.e("getAddress", "주소 데이터 얻기 실패");
            return "";
        }
        if(list.size() > 0) {
            Address addr = list.get(0);
            address = addr.getAddressLine(0);
            if(addr.getAdminArea()!=null) Address += addr.getAdminArea() +" ";
            if(addr.getLocality()!=null) Address += addr.getLocality()+" ";
            if(addr.getSubLocality()!=null) Address += addr.getSubLocality()+" ";
            if(addr.getThoroughfare()!=null) Address += addr.getThoroughfare()+" ";
            if(addr.getFeatureName()!=null && !(addr.getFeatureName().equals(addr.getSubLocality()))) Address += addr.getFeatureName()+" ";
        }


        return Address;
    }

    static String getDmXY(Context c,String my_Address ,GpsInfo gps) {
        String address = "";
        String Address = "";
        Address addr = null;
        //위치정보를 활용하기 위한 구글 API 객체
        Geocoder geocoder = new Geocoder( c, Locale.getDefault());
        double lng,lat;
        //주소 목록을 담기 위한 HashMap

        List<Address> list = null;
        try{
            list = geocoder.getFromLocationName(my_Address, 1);
            //  XML_Parsing.ApiExplorer.main(nextweather);
        } catch(Exception e){
            e.printStackTrace();
            return "error";
        }

        if(list == null){
            Log.e("getAddress", "주소 데이터 얻기 실패");
            return "";
        }
        if(list.size()>0) {
            addr = list.get(0);
            address = addr.getAddressLine(0) + " " + addr.getCountryName() + " " //대한민국
                    + addr.getPostalCode() + " " //우편번호
                    + addr.getLocality() + " " //시
                    + addr.getSubLocality() + " " //구
                    + addr.getAdminArea() + " " // 도
                    + addr.getThoroughfare() + " " // 동
                    + addr.getFeatureName(); // 지역 번호

           /* Address = addr.getAdminArea() + " " // 도
                    + addr.getLocality() + " " //시
                    + addr.getSubLocality() + " " //구
                    + addr.getThoroughfare() + " "
                    + addr.getFeatureName(); // 지역 번호;// 동;*/
            address = addr.getAddressLine(0);
            gps.lat = addr.getLatitude();
            gps.lon = addr.getLongitude();
            address = address.substring(4);
        }
        else{
                return "";
        }
        return address;
    }
}
