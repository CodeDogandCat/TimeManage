package com.example.test;

import java.util.List;

import com.example.domain.Item;
import com.example.service.DBOpenHelper;
import com.example.service.ItemService;

import android.test.AndroidTestCase;
import android.util.Log;

public class ItemServiceTest extends AndroidTestCase {
	private  static String TAG	="ItemServiceTest";
	
	public void testCreateDB() throws Exception{
		DBOpenHelper dbOpenHelper=new DBOpenHelper(getContext());
		dbOpenHelper.getWritableDatabase();
		
	}
	public void testSave() throws Exception{
		ItemService service=new ItemService(this.getContext());
		//for(int i=0;i<12;i++)
		//{
			Item item=new Item("20150313"+"04",""+1,"2015","3","13","ÖÜÎå",4);
			service.save(item);
		//}
		
	}
	public void testDelete() throws Exception{
		ItemService service=new ItemService(this.getContext());
		service.delete("2015/3/11/5");
		service.delete("2015/3/11/6");
		service.delete("2015/3/11/7");
		service.delete("2015/3/11/8");
		service.delete("2015/3/11/9");
		service.delete("2015/3/11/10");
		service.delete("2015/3/11/11");
		
		
	}
	/*public void testUpdate() throws Exception{
		ItemService service=new ItemService(this.getContext());
		Item item=service.find("2015/3/7/0");
		item.setKind("gaoxiao");
		service.update( item);
	}*/
		
	
	/*public void testFind() throws Exception{
		ItemService service=new ItemService(this.getContext());
		Item item=service.find("2015/3/7/1");
		Log.i(TAG, item.toString());
		
	}*/
	public void testScrollData() throws Exception{
		ItemService service=new ItemService(this.getContext());
		List<Item> items=service.getScrollData(0, 11);
		for(Item item:items)
		{
			Log.i(TAG, item.toString());
		}
	}
	public void testCount() throws Exception{
		ItemService service=new ItemService(this.getContext());
		long result=service.getCount();
		Log.i(TAG, String.valueOf(result));;
		

	}

	/*public void testPayment() throws Exception{-
		ItemService service=new ItemService(this.getContext());
		service.payment();

	}*/
	/*public void testUpdateAmount() throws Exception{
		ItemService service=new ItemService(this.getContext());
		Person person1=service.find(1);
		Person person2=service.find(2);
		person1.setAmount(100);
		person2.setAmount(50);
		service.update(person1);
		service.update(person2);
		

	}*/
}
