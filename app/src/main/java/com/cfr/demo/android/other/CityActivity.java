package com.cfr.demo.android.other;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.cfr.demo.android.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CityActivity extends AppCompatActivity {
    OptionsPickerView pvOptions;
    // 省份
    ArrayList<ProvinceBean> provinceBeanList = new ArrayList<>();
    ArrayList<String> provinceList=new ArrayList<>();
    // 城市
    ArrayList<String> cities;
    ArrayList<List<String>> cityList = new ArrayList<>();
    // 区/县
    ArrayList<String> district;
    ArrayList<List<String>> districts;
    ArrayList<List<List<String>>> districtList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        pvOptions = new OptionsPickerView(this);
        // 获取json数据
        String province_data_json = JsonFileReader.getJson(this, "province_data.json");
        // 解析json数据
        parseJson(province_data_json);
        // 设置三级联动效
//        pvOptions.setPicker(provinceBeanList, cityList, districtList, true);
        pvOptions.setPicker(provinceList, cityList, districtList, true);
        // 设置选择的三级单位
//         pvOptions.setLabels("省", "市", "区");
         pvOptions.setTitle("选择城市");
        // 设置是否循环滚动
        pvOptions.setCyclic(false, false, false);
        // 设置默认选中的三级项目
        pvOptions.setSelectOptions(0, 0, 0);
        // 监听确定选择按钮
        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                // /返回的分别是三个级别的选中位置
                String city = provinceBeanList.get(options1).getPickerViewText();
                String address;
                // 如果是直辖市或者特别行政区只设置市和区/县 i
                if ("北京市".equals(city) || "上海市".equals(city) || "天津市".equals(city) || "重庆市".equals(city) || "澳门".equals(city) || "香港".equals(city)) {
                    address = provinceBeanList.get(options1).getPickerViewText() + " "
                            + districtList.get(options1).get(option2).get(options3);
                } else {
                    address = provinceBeanList.get(options1).getPickerViewText() + " " + cityList.get(options1).get(option2) + " "
                            + districtList.get(options1).get(option2).get(options3);
                }
                Toast.makeText(CityActivity.this, address, Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pvOptions.show();
            }
        });
    }


    // 解析json填充集合
    public void parseJson(String str) {
        try {
            // 获取json中的数组
            JSONArray jsonArray = new JSONArray(str);
            // 遍历数据组
            for (int i = 0; i < jsonArray.length(); i++) {
                // 获取省份的对象
                JSONObject provinceObject = jsonArray.optJSONObject(i);
                // 获取省份名称放入集合
                String provinceName = provinceObject.getString("name");
                provinceBeanList.add(new ProvinceBean(provinceName));
                provinceList.add(provinceName);
                // 获取城市数组
                JSONArray cityArray = provinceObject.optJSONArray("city");
                cities = new ArrayList<>();
                // 声明存放城市的集合
                districts = new ArrayList<>();
                //声明存放区县集合的集合
                // 遍历城市数组
                for (int j = 0; j < cityArray.length(); j++) {
                    // 获取城市对象
                    JSONObject cityObject = cityArray.optJSONObject(j);
                    // 将城市放入集合
                    String cityName = cityObject.optString("name");
                    cities.add(cityName);
                    district = new ArrayList<>();
                    // 声明存放区县的集合
                    // 获取区县的数组
                    JSONArray areaArray = cityObject.optJSONArray("area");
                    // 遍历区县数组，获取到区县名称并放入集合
                    for (int k = 0; k < areaArray.length(); k++) {
                        String areaName = areaArray.getString(k);
                        district.add(areaName);
                    }
                    // 将区县的集合放入集合
                    districts.add(district);
                }
                // 将存放区县集合的集合放入集合
                districtList.add(districts);
                // 将存放城市的集合放入集合
                cityList.add(cities);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}



