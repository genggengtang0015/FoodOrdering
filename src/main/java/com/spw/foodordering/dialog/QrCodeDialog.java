package com.spw.foodordering.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;

import com.spw.foodordering.R;

public class QrCodeDialog extends Dialog {
    public QrCodeDialog(Context context) {
        super(context);
    }

    public QrCodeDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private Context context;
        private Bitmap image;

        public Builder(Context context) {
            this.context = context;
        }

        public Bitmap getImage() {
            return image;
        }

        public void setImage(Bitmap image) {
            this.image = image;
        }

        public QrCodeDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final QrCodeDialog dialog = new QrCodeDialog(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.dialog_share_qrcode, null);
            dialog.addContentView(layout, new LayoutParams(
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT
                    , android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
            dialog.setContentView(layout);
            ImageView img = (ImageView) layout.findViewById(R.id.img_qrcode);
            img.setImageBitmap(getImage());
            return dialog;
        }
    }

}
