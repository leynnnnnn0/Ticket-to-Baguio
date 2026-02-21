package com.example.codefest.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
public class ImageHelper {

        public static  byte[] bitmapToByteArray(Bitmap bitmap){
            if ( bitmap == null) return null;

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
            return stream.toByteArray();
        }

        public static Bitmap byteArrayToBitmap(byte[] data){
            return BitmapFactory.decodeByteArray(data, 0, data.length);
        }
}
