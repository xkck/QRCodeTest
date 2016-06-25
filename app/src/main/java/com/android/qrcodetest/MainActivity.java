package com.android.qrcodetest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;


public class MainActivity extends AppCompatActivity {

    private TextView mTextResult;
    private EditText mInput;
    private CheckBox mLogo;
    private ImageView mImageResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextResult = (TextView) findViewById(R.id.tv_result);
        mLogo = (CheckBox) findViewById(R.id.cb_logo);
        mInput = (EditText) findViewById(R.id.et_text);
        mImageResult = (ImageView) findViewById(R.id.iv_result);

    }

    //扫描二维码
    public void scan(View view){
        startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class),0);
    }

    //将得到二维码信息显示出来
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Bundle bundle = data.getExtras();
            String result = bundle.getString("result");
            mTextResult.setText(result);
        }
    }

    //创建二维码
    public void create(View view){
        String input = mInput.getText().toString();
        if(input.equals("")){
            Toast.makeText(MainActivity.this,"输入不能为空",Toast.LENGTH_SHORT).show();
        }else{
            Bitmap bitmap = EncodingUtils.createQRCode(input,500,500,mLogo.isChecked() ? BitmapFactory.decodeResource(getResources(),R.drawable.logo) : null);
            mImageResult.setImageBitmap(bitmap);
        }
    }



}
