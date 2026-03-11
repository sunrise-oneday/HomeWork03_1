package com.example.androidapplication02;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ImageViewerActivity extends AppCompatActivity {
    // ==== View 引用 ====
    private ImageView ivDisplay;
    private Button btnPrev, btnAlphaInc, btnAlphaDec, btnNext;

    // ==== Model (数据状态) ====
    // 注意：请确保你在 drawable 文件夹里放入了 pic1, pic2, pic3 图片
    // 如果你用的名字不一样，请在这里修改为 R.drawable.你图片的名称

    //引用添加的图片
    private final int[] images = {R.drawable.autumn, R.drawable.cat_world, R.drawable.magic};


    private int currentIndex = 0;      // 当前展示的图片索引
    private float currentAlpha = 1.0f; // 当前透明度：1.0(完全不透明) ~ 0.0(完全透明)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);

        initView();      // 1. 初始化视图
        initData();      // 2. 初始化数据展示
        initListener();  // 3. 设置事件监听
    }

    private void initView() {
        ivDisplay = findViewById(R.id.iv_display);
        btnPrev = findViewById(R.id.btn_prev);
        btnAlphaInc = findViewById(R.id.btn_alpha_increase);
        btnAlphaDec = findViewById(R.id.btn_alpha_decrease);
        btnNext = findViewById(R.id.btn_next);
    }

    private void initData() {
        if (images.length > 0) {
            ivDisplay.setImageResource(images[currentIndex]);
            ivDisplay.setAlpha(currentAlpha);
        }
    }

    private void initListener() {
        // [上一张] 点击事件
        btnPrev.setOnClickListener(v -> {
            // 实现循环切换：加上数组长度再求余，防止负数越界
            currentIndex = (currentIndex - 1 + images.length) % images.length;
            ivDisplay.setImageResource(images[currentIndex]);
        });

        // [下一张] 点击事件
        btnNext.setOnClickListener(v -> {
            currentIndex = (currentIndex + 1) % images.length;
            ivDisplay.setImageResource(images[currentIndex]);
        });

        // [透明度增加] (即变得更清晰，接近 1.0)
        btnAlphaInc.setOnClickListener(v -> {
            currentAlpha += 0.2f;
            if (currentAlpha >= 1.0f) {
                currentAlpha = 1.0f;
                Toast.makeText(ImageViewerActivity.this, "已达到最大不透明度", Toast.LENGTH_SHORT).show();
            }
            ivDisplay.setAlpha(currentAlpha);
        });

        // [透明度减少] (即变得更透明，接近 0.0)
        btnAlphaDec.setOnClickListener(v -> {
            currentAlpha -= 0.2f;
            if (currentAlpha <= 0.0f) {
                currentAlpha = 0.0f;
                Toast.makeText(ImageViewerActivity.this, "已完全透明", Toast.LENGTH_SHORT).show();
            }
            ivDisplay.setAlpha(currentAlpha);
        });
    }
}
