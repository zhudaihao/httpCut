package cn.zdh.httpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void send(View view) {
        final String url = "http://route.showapi.com/25-3";
        Map<String, Object> params = new HashMap<>();
        params.put("showapi_appid","32554");
        params.put("showapi_sign","004462cc920a4d8bbfa3e34906c36b90");
        params.put("id","350301198906180060");

        HttpHelper.obtain().post(url, params, new HttpCallback<User>() {
            @Override
            public void onSuccess(User user) {
                Toast.makeText(MainActivity.this, user.getShowapi_res_body().getRetData().getBirthday(), Toast.LENGTH_SHORT).show();

            }

        });
    }


}
