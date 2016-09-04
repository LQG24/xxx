package share.umeng.mmc.xxx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by abc on 2016/8/22.
 */
public class ImagePagerActivity extends FragmentActivity {
    private static final String STATE_POSITION = "STATE_POSITION";
    public static final String EXTRA_IMAGE_INDEX = "image_index";
    public static final String EXTRA_IMAGE_URLS = "image_urls";
    private ArrayList<Fragment> fragmentArrayList =new ArrayList<Fragment>();

    private CusViewPager mPager;
    private int pagerPosition;
    private TextView indicator;
    private List<View> imageViewList;
    ArrayList<String> urls;
    int screenWidth;
    int screenHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_detail_pager);
        pagerPosition = getIntent().getIntExtra(EXTRA_IMAGE_INDEX, 0);
        urls = getIntent().getStringArrayListExtra(EXTRA_IMAGE_URLS);

        mPager = (CusViewPager) findViewById(R.id.pager);
        ImagePagerAdapter imagePagerAdapter = new ImagePagerAdapter(getSupportFragmentManager(),urls);
        mPager.setAdapter(imagePagerAdapter);

        indicator = (TextView) findViewById(R.id.indicator);

     /*   imageViewList = new ArrayList<View>();

        for (int i = 0; i < urls.size(); i++) {
            String url = urls.get(i);
            View view = LayoutInflater.from(ImagePagerActivity.this).inflate(R.layout.image_detail_fragment, null);
            final PhotoView imageview = (PhotoView) view.findViewById(R.id.image);
            imageview.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return false;
                }
            });
            imageview.enable();
            final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.loading);
            ImageLoader.getInstance().displayImage(url, imageview, new SimpleImageLoadingListener() {
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    super.onLoadingComplete(imageUri, view, loadedImage);
                    DisplayMetrics dm = new DisplayMetrics();
                    getWindowManager().getDefaultDisplay().getMetrics(dm);
                    screenWidth = dm.widthPixels;// px 物理像素
                    int bitmapWidth = loadedImage.getWidth();
                    int bitmapHeight = loadedImage.getHeight();
                    int newHeight = screenWidth*bitmapHeight/bitmapWidth;
                    Bitmap bitmap = ImageUtils.zoomImg(loadedImage, screenWidth, newHeight);
                    imageview.setImageBitmap(bitmap);
                    imageview.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }


                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    super.onLoadingFailed(imageUri, view, failReason);
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    super.onLoadingStarted(imageUri, view);
                    progressBar.setVisibility(View.VISIBLE);
                }
            });
            imageViewList.add(view);
        }

        final MyPageAdapter myPageAdapter =new MyPageAdapter(imageViewList);
        mPager.setAdapter(myPageAdapter);*/
        CharSequence text = getString(R.string.viewpager_indicator, pagerPosition + 1, mPager.getAdapter().getCount());
        indicator.setText(text);
        mPager.setCurrentItem(pagerPosition);
        // 更新下标
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                CharSequence text = getString(R.string.viewpager_indicator, position + 1, mPager.getAdapter().getCount());
                indicator.setText(text);
//                ((PhotoView)imageViewList.get(position).findViewById(R.id.image)).reset();// 切换还原图片大小
                ImageFragment imageFragment = (ImageFragment) fragmentArrayList.get(position);
                imageFragment.getmImageView().reset();    //切换还原图片大小
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private class ImagePagerAdapter extends FragmentStatePagerAdapter {

        private ArrayList<String> fileList;
        public ImagePagerAdapter(FragmentManager fm,ArrayList<String> fileList) {
            super(fm);
            this.fileList = fileList;
        }

        @Override
        public void startUpdate(ViewGroup container) {
            super.startUpdate(container);
        }

        @Override
        public Fragment getItem(int position) {
            String url = fileList.get(position);
            ImageFragment imageFragment = (ImageFragment) ImageFragment.newInstance(url);
            fragmentArrayList.add(imageFragment);
            return imageFragment;
        }

        @Override
        public int getCount() {
            return fileList == null ? 0 : fileList.size();
        }
    }
}
