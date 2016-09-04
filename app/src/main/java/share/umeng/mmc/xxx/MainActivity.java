package share.umeng.mmc.xxx;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    /** Item数据实体集合 */
    private ArrayList<ItemEntity> itemEntities;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView) findViewById(R.id.listview);
        initData();
        listView.setAdapter(new ListItemAdaper(this,itemEntities));
        PackageManager manager = this.getPackageManager();
        PackageInfo info = null;
        int version = 0;
        try {
            info = manager.getPackageInfo(getApplication().getPackageName(), 0);
            version = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Log.i("version",version+"");

    }

    private void initData() {
        itemEntities = new ArrayList<ItemEntity>();
        // 1.无图片
        ItemEntity entity1 = new ItemEntity(//
                "http://file103.mafengwo.net/s7/M00/17/B6/wKgB6lTBKOOARHKRAAeXvhsqUhg37.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90", "盖伦", "这是凤凰古城呀，挺美的", null);
        itemEntities.add(entity1);
        // 2.1张图片
        ArrayList<String> urls_1 = new ArrayList<String>();
        urls_1.add("http://file108.mafengwo.net/s7/M00/17/CA/wKgB6lTBKSiAP3StAAwaYp1SbEM27.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90");
        ItemEntity entity2 = new ItemEntity(//
                "http://file108.mafengwo.net/s7/M00/17/CA/wKgB6lTBKSiAP3StAAwaYp1SbEM27.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90", "赵信", "这是凤凰古城呀，挺美的", urls_1);
        itemEntities.add(entity2);
        // 3.3张图片
        ArrayList<String> urls_2 = new ArrayList<String>();
        urls_2.add("http://file108.mafengwo.net/s7/M00/18/19/wKgB6lTBKgKAJKHXAAmCNbruEh029.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90");
        urls_2.add("http://file107.mafengwo.net/s7/M00/18/47/wKgB6lTBKlyAL4xNAAc9RDAv9ok41.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90");
        urls_2.add("http://file103.mafengwo.net/s7/M00/17/B6/wKgB6lTBKOOARHKRAAeXvhsqUhg37.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90");
        ItemEntity entity3 = new ItemEntity(//
                "http://file107.mafengwo.net/s7/M00/18/47/wKgB6lTBKlyAL4xNAAc9RDAv9ok41.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90", "蛮王", "凤凰古城", urls_2);
        itemEntities.add(entity3);
        // 4.6张图片
        ArrayList<String> urls_3 = new ArrayList<String>();
        urls_3.add("http://file102.mafengwo.net/s9/M00/8A/42/wKgBs1ahFI-AfNltAANL5B_D6Ag06.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90");
        urls_3.add("http://file105.mafengwo.net/s9/M00/8A/3F/wKgBs1ahFIWAbJ_lAAPxlQZ8xnk99.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90");
        urls_3.add("http://file102.mafengwo.net/s5/M00/D2/3A/wKgB3FHahUKAP928AAUQPifQcZ003.jpeg?imageView2%2F2%2Fw%2F310%2Fh%2F9999%2Fq%2F90%7Cwatermark%2F1%2Fimage%2FaHR0cDovL2ZpbGUxMTEubWFmZW5nd28ubmV0L3M5L00wMC9BRS9GNC93S2dCczFjSGtRLUFFRHN0QUFBVU16WWpyNE03NjYucG5n");
        urls_3.add("http://file107.mafengwo.net/s6/M00/FC/72/wKgB4lL1BL6AT1IvAAiL8ZID4lo08.jpeg?imageView2%2F2%2Fw%2F310%2Fh%2F9999%2Fq%2F90%7Cwatermark%2F1%2Fimage%2FaHR0cDovL2ZpbGUxMTEubWFmZW5nd28ubmV0L3M5L00wMC9BRS9GNC93S2dCczFjSGtRLUFFRHN0QUFBVU16WWpyNE03NjYucG5n");
        urls_3.add("http://file102.mafengwo.net/s5/M00/54/AD/wKgB3FE8MW2AWxOoAA_K2YjGdxI15.jpeg?imageView2%2F2%2Fw%2F310%2Fh%2F9999%2Fq%2F90%7Cwatermark%2F1%2Fimage%2FaHR0cDovL2ZpbGUxMTEubWFmZW5nd28ubmV0L3M5L00wMC9BRS9GNC93S2dCczFjSGtRLUFFRHN0QUFBVU16WWpyNE03NjYucG5n");
        urls_3.add("http://file106.mafengwo.net/s6/M00/38/EB/wKgB4lKmoyOAdJwWAAyyRmElAAY57.jpeg?imageView2%2F2%2Fw%2F310%2Fh%2F9999%2Fq%2F90%7Cwatermark%2F1%2Fimage%2FaHR0cDovL2ZpbGUxMTEubWFmZW5nd28ubmV0L3M5L00wMC9BRS9GNC93S2dCczFjSGtRLUFFRHN0QUFBVU16WWpyNE03NjYucG5n");
        ItemEntity entity4 = new ItemEntity(//
                "http://file102.mafengwo.net/s9/M00/8A/42/wKgBs1ahFI-AfNltAANL5B_D6Ag06.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90", "寒冰", "丽江真美，好想去...", urls_3);
        itemEntities.add(entity4);
    }
}
