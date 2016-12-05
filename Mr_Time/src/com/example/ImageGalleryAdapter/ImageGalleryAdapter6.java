package com.example.ImageGalleryAdapter;

import android.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class ImageGalleryAdapter6 extends BaseAdapter {
private Context context;
private int[]image=new int[]{0x7f020001,0x7f020003,0x7f02000f,0x7f02000a};//依次是蓝绿黄红
	public ImageGalleryAdapter6(Context context) {
	super();
	this.context = context;
}

	@Override
	public int getCount() {
		
		//return image.length;
		return Integer.MAX_VALUE;
	}

	@Override
	public Object getItem(int position) {//得到每个资源的位置
	
		return image[position];
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	@Override
	public View getView(int position, View ConvertView, ViewGroup parent) {
	
		/*if(position==getCount())
		{
			position=0;
		}*/
		
		ImageView iv=new ImageView(context);
		iv.setBackgroundColor(0xFFFFD1D1);
		iv.setImageResource(image[position%image.length]);
		//iv.setImageResource(image[position]);
		iv.setScaleType(ImageView.ScaleType.CENTER);
		iv.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.FILL_PARENT));
		return iv;
	}

}
