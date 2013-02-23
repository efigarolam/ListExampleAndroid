package com.example.listexample;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.listexample.utils.GetTweetsTask;
import com.example.listexample.utils.HttpUtils;
import com.example.listexample.utils.JsonParser;
import com.example.listexample.utils.Tweet;

public class MainActivity extends Activity {
	
	private final static String mTAG = "MainActivity";
	private ListView mList = null;
	private MyAdapter mAdapter = null;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mAdapter = new MyAdapter();
		mList = (ListView)findViewById(R.id.lvLista);
		mList.setAdapter(mAdapter);
		
		//Agregamos elementos
		GetTweetsTask task = new GetTweetsTask();
		task.setAdapter(mAdapter);
		task.execute("http://www.recursosdelweb.com/feed_twitter.json");
	}
	
	
	public class MyAdapter extends BaseAdapter{
	
		private ArrayList<Tweet> mData = new ArrayList<Tweet>();
		
		public void addElements(ArrayList<Tweet> tweets){
			mData = tweets;
			notifyDataSetChanged();
		}
		
		public void addElement(Tweet tweet){
			mData.add(tweet);
			notifyDataSetChanged();
		}
		
		public void clear(){
			mData.clear();
		}
		
		@Override
		public int getCount() {
			return mData.size();
		}

		@Override
		public Object getItem(int position) {
			return mData.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder = null;
			
			if (convertView == null){
				Log.d(mTAG, "getView: creando nueva vista para posicion: " 
						+ position);
				//Primera vez de creacion de esta vista
				convertView = getLayoutInflater().inflate(R.layout.fila1, null);
				
				viewHolder = new ViewHolder();
				viewHolder.setTextView((TextView)convertView.findViewById(R.id.txtName));
				convertView.setTag(viewHolder);
			}else{
				Log.d(mTAG, "getView: vista existente. Cambiando contenido " +
						"para posicion: " + position);
				
				viewHolder = (ViewHolder)convertView.getTag();
			}
			
			//Cambiar contenido
			Tweet tweet = (Tweet)getItem(position);
			viewHolder.getTextView().setText(tweet.getTweet());
			
			return convertView;
		}
		
		
		//Holder Pattern
		private class ViewHolder{
			private TextView mTextView = null;
			
			public TextView getTextView() {
				return mTextView;
			}

			public void setTextView(TextView mTextView) {
				this.mTextView = mTextView;
			}
		}
	}
}
