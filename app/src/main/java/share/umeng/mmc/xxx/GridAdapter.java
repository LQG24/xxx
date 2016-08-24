package share.umeng.mmc.xxx;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by abc on 2016/8/22.
 */
public class GridAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> imageUrls;
    public GridAdapter(Context mContext, ArrayList<String> imageUrls) {
        this.mContext = mContext;
        this.imageUrls = imageUrls;
    }

    @Override
    public int getCount() {
        return imageUrls == null ? 0 : imageUrls.size();
    }

    @Override
    public Object getItem(int position) {
        return imageUrls.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View converview, ViewGroup viewGroup) {
        converview = View.inflate(mContext,R.layout.item_gridview,null);
        ImageView imageView = (ImageView) converview.findViewById(R.id.iv_image);
        DisplayImageOptions options = new DisplayImageOptions.Builder()//
                .cacheInMemory(true)//
                .cacheOnDisk(true)//
                .bitmapConfig(Bitmap.Config.RGB_565)//
                .build();
        ImageLoader.getInstance().displayImage(imageUrls.get(position), imageView, options);
        return converview;
    }
}
