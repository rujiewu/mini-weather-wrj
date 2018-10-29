package cn.edu.pku.wurujie.miniweather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.edu.pku.wurujie.app.MyApplication;
import cn.edu.pku.wurujie.bean.City;

/**
 * Created by wurujie on 2018/10/16
 */
public class SelectCity extends Activity implements View.OnClickListener {
    private ImageView mBackBtn;

    private ListView mListView;
    private TextView title_name_Tv;
    private ArrayList<City> mCityList;
    private ArrayList<String> mCityNameList = new ArrayList<String>();
    private String selectedCity;
    private String selectedCityCode = "101160101";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.select_city);

        mBackBtn = (ImageView) findViewById(R.id.title_back);
        mBackBtn.setOnClickListener(this);

        mCityList = MyApplication.getInstance().getCityList();
        //mCityList = MyApplication.getCityList();
        initViews();
    }

    private void initViews() {
        title_name_Tv = (TextView) findViewById(R.id.title_name);

        for (City city : mCityList) {
            mCityNameList.add(city.getCity());
        }

        mListView = (ListView) findViewById(R.id.list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                SelectCity.this, android.R.layout.simple_list_item_1, mCityNameList);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedCity = mCityList.get(position).getCity();
                selectedCityCode = mCityList.get(position).getNumber();
                Toast.makeText(SelectCity.this, "你选择了：" +
                        selectedCity, Toast.LENGTH_SHORT).show();
                title_name_Tv.setText("当前城市：" + selectedCity);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                Intent i = new Intent(this,MainActivity.class);
                i.putExtra("cityCode",selectedCityCode);
                setResult(RESULT_OK,i);
                finish();
                break;
            default:
                break;
        }
    }
}
