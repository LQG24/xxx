package share.umeng.mmc.xxx;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by abc on 2016/8/22.
 */
public class ImagePagerActivity  extends FragmentActivity {
    private static final String STATE_POSITION = "STATE_POSITION";
    public static final String EXTRA_IMAGE_INDEX = "image_index";
    public static final String EXTRA_IMAGE_URLS = "image_urls";

    private ViewPager mPager;
    private int pagerPosition;
    private TextView indicator;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_detail_pager);
        pagerPosition = getIntent().getIntExtra(EXTRA_IMAGE_INDEX,0);
        ArrayList<String> urls = getIntent().getStringArrayListExtra(EXTRA_IMAGE_URLS);

        mPager = (ViewPager) findViewById(R.id.pager);
        ImagePagerAdapter imagePagerAdapter = new ImagePagerAdapter(getSupportFragmentManager(),urls);
        mPager.setAdapter(imagePagerAdapter);

        indicator = (TextView) findViewById(R.id.indicator);
        CharSequence text = getString(R.string.viewpager_indicator,pagerPosition+1,mPager.getAdapter().getCount());
        indicator.setText(text);

        mPager.setCurrentItem(pagerPosition);
        // 更新下标
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                CharSequence text = getString(R.string.viewpager_indicator,position+1,mPager.getAdapter().getCount());
                indicator.setText(text);
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
        public Fragment getItem(int position) {
            String url = fileList.get(position);
            return ImageFragment.newInstance(url);
        }

        @Override
        public int getCount() {
            return fileList == null ? 0 : fileList.size();
        }
    }
}
