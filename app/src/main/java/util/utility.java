package util;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import db.city;
import db.county;
import db.province;
import okhttp3.Response;

public class utility {
    public static boolean handleprovinceResponce(String response)  {
        if (!TextUtils.isEmpty((response))) {
            try {
                JSONArray allprovinces = new JSONArray(response);
                for (int i = 0; i < allprovinces.length(); i++) {
                    JSONObject provinceObject = allprovinces.getJSONObject(i);
                    province province = new province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }return false;
    }
    public static boolean handlecityResponce(String response,int ProvinceId)  {
        if (!TextUtils.isEmpty((response))) {
            try {
                JSONArray allcities = new JSONArray(response);
                for (int i = 0; i < allcities.length(); i++) {
                    JSONObject cityObject = allcities.getJSONObject(i);
                    city city = new city();
                    city.setcityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(ProvinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }return false;
    }

    public static boolean handlecountyResponce(String response,int cityId)  {
        if (!TextUtils.isEmpty((response))) {
            try {
                JSONArray allcounties = new JSONArray(response);
                for (int i = 0; i < allcounties.length(); i++) {
                    JSONObject countyObject = allcounties.getJSONObject(i);
                    county county = new county();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }return false;
    }
}

