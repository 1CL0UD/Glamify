//package com.project.glamify;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//import androidx.annotation.NonNull;
//import androidx.viewpager.widget.PagerAdapter;
//
//import java.util.List;
//
//public class ImagePagerAdapter extends PagerAdapter {
//    private List<Integer> imageList;
//    private LayoutInflater layoutInflater;
//    private Context context;
//
//    public ImagePagerAdapter(List<Integer> imageList, Context context) {
//        this.imageList = imageList;
//        this.context = context;
//        layoutInflater = LayoutInflater.from(context);
//    }
//
//    @Override
//    public int getCount() {
//        return imageList.size();
//    }
//
//
//    @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//        return view == object;
//    }
//
//    @NonNull
//    @Override
//    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        View view = layoutInflater.inflate(R.layout.item_image, container, false);
//        ImageView imageView = view.findViewById(R.id.imageView);
//        imageView.setImageResource(imageList.get(position));
//        container.addView(view);
//        return view;
//    }
//
//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        container.removeView((View) object);
//    }
//}
