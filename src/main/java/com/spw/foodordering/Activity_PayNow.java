package com.spw.foodordering;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.spw.foodordering.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class Activity_PayNow extends Activity {
    //    @BindView(R2.id.iv_QRcode)
    ImageView ivQRcode;
    //    @BindView(R2.id.iv_close)
    ImageView ivClose;

    private String content;
    private static final String TAG = Activity_PayNow.class.getSimpleName();

    public static void actionStart(Activity_Payment context, String qrCode) {
        Intent intent = new Intent(context, Activity_Payment.class);
        intent.putExtra("content", qrCode);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_now);
//        ButterKnife.bind(this);
//        ivQRcode = (ImageView) findViewById(R.id.iv_QRcode);
//        ivClose = (ImageView) findViewById(R.id.iv_close);
        content = getIntent().getStringExtra("content");
        Bitmap bitmap = createQRCode(content);
        ivQRcode.setImageBitmap(bitmap);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * 将指定的内容生成成二维码
     *
     * @param content 将要生成二维码的内容
     * @return 返回生成好的二维码事件
     * @throws WriterException WriterException异常
     */
    public Bitmap createQRCode(String content) {
        int qrwidth = getResources().getDimensionPixelOffset(R.dimen.qrwidth);
        //生成二维矩阵，编码时指定大小，不要生成了图片以后再进行缩放，这样会模糊导致识别失败
        BitMatrix matrix = null;
        try {
            matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, qrwidth, qrwidth);

        } catch (WriterException e) {
            e.printStackTrace();
        }
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        //二维矩阵转为一维像素数组，也就是一直横着排了。
        int[] pixels = new int[width * height];
        //两个for循环是图片横列扫描的结果
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (matrix.get(x, y)) {
                    pixels[y * width + x] = 0xff000000;
                }
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//        Logger.t(TAG).d("width=" + width + ",height=" + height);
        //通过像素数组生成bitmap，具体参考api
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

//    @OnClick(R2.id.iv_close)
//    public void onClick() {
//        finish();
//    }
}
