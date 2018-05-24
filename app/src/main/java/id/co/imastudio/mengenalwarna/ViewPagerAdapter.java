package id.co.imastudio.mengenalwarna;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by idn on 5/7/2017.
 */

public class ViewPagerAdapter extends PagerAdapter {

    //generate > constructor
    Context context;
    int[] gambarbilal;
    int[] suarabilal;

    private ImageView ivAdab;
    String kondisi;
    private ImageView ivText;

    public ViewPagerAdapter(Context context, int[] gambarbilal, int[] suarabilal) {
        this.context = context;
        this.gambarbilal = gambarbilal;
        this.suarabilal = suarabilal;
    }

    //jumlah gambarnya
    @Override
    public int getCount() {
        return gambarbilal.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout) object;
    }

    //generate > oveeride method
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowview = inflater.inflate(R.layout.item_adab, null, true);

        ivAdab = (ImageView) rowview.findViewById(R.id.ivAdab);
        //ivAdab.setImageResource(gambarbilal[position]);

        Glide.with(context).load(gambarbilal[position]).diskCacheStrategy(DiskCacheStrategy.RESULT).priority(Priority.HIGH).into(ivAdab);

//        ivText = (ImageView) rowview.findViewById(R.id.ivText);
////        ivText.setImageResource(textAdab[position]);
//
//        Glide.with(context).load(textAdab[position]).into(ivText);
        rowview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer player = MediaPlayer.create(context, suarabilal[position]);
                player.start();

                if(kondisi == "lanjut"){
                    player.stop();
                    kondisi = "berhenti";
                } else {
                    player.start();
                    kondisi = "lanjut";
                }

            }
        });

        //tambahkan view
        container.addView(rowview);
        return rowview;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }

}
