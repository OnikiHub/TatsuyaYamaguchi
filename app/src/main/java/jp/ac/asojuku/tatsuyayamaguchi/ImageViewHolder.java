package jp.ac.asojuku.tatsuyayamaguchi;

import android.graphics.Bitmap;
public class ImageViewHolder{
    private Bitmap Imageview = null;

public ImageViewHolder(){};

public ImageViewHolder(Bitmap Imageview){
    Imageview = imageview;
}

    public Bitmap getImageview() {
        return Imageview;
    }

}
