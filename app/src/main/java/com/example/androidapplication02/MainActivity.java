package com.example.androidapplication02;

import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btRegRegist = null;
    private Button btRegLogin = null;
    private EditText etRegName=null;
    private EditText etRegPwd =null;
    private CheckBox ckRegSexFemale=null;
    private CheckBox ckRegSexMale=null;
    private  CheckBox getCkRegSexmale=null;
    private TextView tvRegShow = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btRegRegist = findViewById(R.id.reg_regist_bt);  // 确保 XML 中有这个 id
        btRegLogin = findViewById(R.id.reg_login_bt);
        etRegName = findViewById(R.id.reg_name_et);
        etRegPwd = findViewById(R.id.reg_pwd_et);
        ckRegSexFemale = findViewById(R.id.reg_sex_female_cb);
        ckRegSexMale = findViewById(R.id.reg_sex_male_cb);
        tvRegShow = findViewById(R.id.reg_show_tv);

        btRegRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtShow="姓名:"+etRegName.getText()+"，密码："+etRegPwd.getText();
                if(ckRegSexMale.isChecked())
                    txtShow+="男";
                if(ckRegSexFemale.isChecked())
                    txtShow+="女";
                tvRegShow.setText(txtShow);
            }
        });
        btRegLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtShow="姓名:"+etRegName.getText()+", 密码:"+etRegPwd.getText();
                if(ckRegSexMale.isChecked())
                    txtShow+=", 性别:"+"男";
                if(ckRegSexFemale.isChecked())
                    txtShow+=", 性别:"+"女";

                Toast toast = Toast.makeText(MainActivity.this,txtShow,Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        ckRegSexMale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // 如果选中了男，取消女的选中状态
                    ckRegSexFemale.setChecked(false);
                }
            }
        });
        ckRegSexFemale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // 如果选中了女，取消男的选中状态
                    ckRegSexMale.setChecked(false);
                }
            }
        });

        etRegName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 输入变化前的回调，此处暂不需要实现
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 输入正在变化时的回调，此处暂不需要实现
            }

            @Override
            public void afterTextChanged(Editable s) {
                // 输入完成后的回调，可以在这里做实时校验或提示
                // 示例：当姓名输入框内容改变时，在TextView显示当前输入的内容
                if (tvRegShow != null) {
                    tvRegShow.setText("正在输入姓名: " + s.toString());
                }
            }
        });

        // 监听密码输入框
        etRegPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // 示例：简单的密码长度提示
                if (s.length() < 6) {
                    etRegPwd.setError("密码长度不能小于6位");
                } else {
                    etRegPwd.setError(null);
                }
            }
        });
    }
}