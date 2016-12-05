package com.example.ImageGalleryAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.domain.Item;
import com.example.service.ItemService;

import android.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

public class ImageGalleryAdapter extends BaseAdapter {
private Context context;
private ItemService itemService;
private int[]image=new int[]{0x7f020001,0x7f020008,0x7f02001a,0x7f020011};//���������̻ƺ�
	public ImageGalleryAdapter(Context context) {
	super();
	this.context = context;
	itemService=new ItemService(context);
	 SimpleDateFormat    formatter    =   new    SimpleDateFormat    ("yyyy/MM/dd ");  
	 Date    curDate =   new    Date(System.currentTimeMillis());//��ȡ��ǰʱ��       
	 String  date  =   formatter.format(curDate); 
	Item item=new Item( date+"/"+0, "", "", "",  "", "", 0);
}

	@Override
	public int getCount() {
		
		//return image.length;
		return Integer.MAX_VALUE;
	}

	@Override
	public Object getItem(int position) {//�õ�ÿ����Դ��λ��
	
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
		
		/*if(itemService.find(item.getTime()))
		{
			itemService.update(item);
			Toast.makeText(getApplicationContext(), kind[position%4]+"�Ѿ�����", Toast.LENGTH_SHORT).show();
		}
		else{
			itemService.save(item);
			Toast.makeText(getApplicationContext(), kind[position%4]+"�Ѿ�����", Toast.LENGTH_SHORT).show();
		}*/
		iv.setImageResource(image[position%image.length]);
		//iv.setImageResource(image[position]);
		iv.setScaleType(ImageView.ScaleType.CENTER);
		iv.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.FILL_PARENT));
		return iv;
	}

}
