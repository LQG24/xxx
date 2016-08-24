package share.umeng.mmc.xxx;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by abc on 2016/8/22.
 */
public class ListItemAdaper extends BaseAdapter {
    private Context mContext;
    private ArrayList<ItemEntity> itemEntities;

    public ListItemAdaper(Context context, ArrayList<ItemEntity> itemEntities) {
        this.mContext = context;
        this.itemEntities = itemEntities;
    }

    @Override
    public int getCount() {
        return itemEntities == null ? 0 : itemEntities.size();
    }

    @Override
    public Object getItem(int i) {
        return itemEntities.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_list, null);
            holder.iv_avatar = (ImageView) convertView.findViewById(R.id.iv_avatar);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            holder.gridview = (NoScrollGridView) convertView.findViewById(R.id.gridview);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ItemEntity itemEntity = itemEntities.get(position);
        holder.tv_title.setText(itemEntity.getTitle());
        holder.tv_content.setText(itemEntity.getContent());
        // 使用ImageLoader加载网络图片
        DisplayImageOptions options = new DisplayImageOptions.Builder()//
                .showImageOnLoading(R.drawable.ic_launcher) // 加载中显示的默认图片
                .showImageOnFail(R.drawable.ic_launcher) // 设置加载失败的默认图片
                .cacheInMemory(true) // 内存缓存
                .cacheOnDisk(true) // sdcard缓存
                .bitmapConfig(Bitmap.Config.RGB_565)// 设置最低配置
                .build();//
        ImageLoader.getInstance().displayImage(itemEntity.getAvatar(), holder.iv_avatar, options);
        final ArrayList<String> imageUrls = itemEntity.getImageUrls();
        if (imageUrls == null || imageUrls.size() == 0) { // 没有图片资源就隐藏GridView
            holder.gridview.setVisibility(View.GONE);
        } else {
            holder.gridview.setAdapter(new GridAdapter(mContext, imageUrls));
        }
        holder.gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                imageBrower(position,imageUrls);
            }
        });
        return convertView;
    }

    /**
     * 打开图片查看器
     *
     * @param position
     * @param urls2
     */
    protected void imageBrower(int position, ArrayList<String> urls2) {
        Intent intent = new Intent(mContext, ImagePagerActivity.class);
        // 图片url,为了演示这里使用常量，一般从数据库中或网络中获取
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, urls2);
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, position);
        mContext.startActivity(intent);
    }

    class ViewHolder {
        private ImageView iv_avatar;
        private TextView tv_title;
        private TextView tv_content;
        private NoScrollGridView gridview;
    }
}
