package share.umeng.mmc.xxx;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * Created by abc on 2016/9/1.
 */
public class ImageUtils {
    /**
     * 处理图片
     *
     * @param bm
     *            所要转换的bitmap
     * @ newWidth新的宽
     * @ newHeight新的高
     * @return 指定宽高的bitmap
     */
    public static Bitmap zoomImg(Bitmap bm, int newWidth, int newHeight) {
        // 获得图片的宽高
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        return Bitmap.createBitmap(bm, 0, 0, width, height, matrix,true);
    }
}
