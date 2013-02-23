package com.example.listexample;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

public class MultiListActivity extends Activity {
	
	private final static String mTAG = "MainActivity";
	private ListView mList = null;
	private MyAdapter mAdapter = null;
	
	public enum TipoFila{
		FILA1,
		FILA2,
		FILA3
	}
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multi);
		
		mAdapter = new MyAdapter();
		mList = (ListView)findViewById(R.id.lvLista);
		mList.setAdapter(mAdapter);
		
		//Agregamos elementos
		mAdapter.addElement("Juan", TipoFila.FILA1);
		mAdapter.addElement("John", TipoFila.FILA3);
		mAdapter.addElement("Jose", TipoFila.FILA3);
		mAdapter.addElement("Angel", TipoFila.FILA2);
		mAdapter.addElement("Luis", TipoFila.FILA2);
		mAdapter.addElement("Marcos", TipoFila.FILA1);
		mAdapter.addElement("Mariano", TipoFila.FILA3);
		mAdapter.addElement("Carlos", TipoFila.FILA1);
		mAdapter.addElement("Paco", TipoFila.FILA3);
		mAdapter.addElement("Joel", TipoFila.FILA1);
		mAdapter.addElement("Christian", TipoFila.FILA2);
		mAdapter.addElement("Eduardo", TipoFila.FILA2);
		mAdapter.addElement("Ramiro", TipoFila.FILA3);
	}
	
	
	private class MyAdapter extends BaseAdapter{
		
		final ArrayList<String> mData = new ArrayList<String>();
//		private final static int TIPO_FILA_1 = 0;
//		private final static int TIPO_FILA_2 = 1;
//		private final static int TIPO_FILA_3 = 2;
		private ArrayList<Integer> mArrayPositionsFila1 = new ArrayList<Integer>();
		private ArrayList<Integer> mArrayPositionsFila2 = new ArrayList<Integer>();
		private ArrayList<Integer> mArrayPositionsFila3 = new ArrayList<Integer>();
		
		
		public void addElement(String name, TipoFila tipo){
			int position = getCount();
			switch (tipo){
				case FILA1:
					mArrayPositionsFila1.add(position);
					break;
				case FILA2:
					mArrayPositionsFila2.add(position);
					break;
				case FILA3:
					mArrayPositionsFila3.add(position);
					break;
			}
			
			mData.add(name);
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
			int tipoFila = getItemViewType(position);
			
			if (convertView == null){
				Log.d(mTAG, "getView: creando nueva vista para posicion: " 
						+ position);
				
				int layoutId = 0;
				if (tipoFila == 0){ //Fila1
					layoutId = R.layout.fila1;
					viewHolder = new ViewHolderTV();
				}else if (tipoFila == 1){ //Fila2
					layoutId = R.layout.fila2;
					viewHolder = new ViewHolderTV();
				}else{ //Fila3
					layoutId = R.layout.fila3;
					viewHolder = new ViewHolderRB();
				}
				
				//Primera vez de creacion de esta vista
				convertView = getLayoutInflater().inflate(layoutId, null);
				
				if (tipoFila == 2){ //Fila3
					ViewHolderRB viewHolderRB = (ViewHolderRB)viewHolder;
					viewHolderRB.setRadioButton(
						(RadioButton)convertView.findViewById(R.id.rbName)
					);
				}else{ //Fila1 o Fila2
					ViewHolderTV viewHolderTV = (ViewHolderTV)viewHolder;
					viewHolderTV.setTextView(
						(TextView)convertView.findViewById(R.id.txtName)
					);
				}
				
				convertView.setTag(viewHolder);
			}else{
				Log.d(mTAG, "getView: vista existente. Cambiando contenido " +
						"para posicion: " + position);
				
				viewHolder = (ViewHolder)convertView.getTag();
			}
			
			//Cambiar contenido
			String name = (String)getItem(position);
			
			if (tipoFila == 2){ //Fila3
				ViewHolderRB viewHolderRB = (ViewHolderRB)viewHolder;
				viewHolderRB.getRadioButton().setChecked(false);
				viewHolderRB.getRadioButton().setText(name);
			}else{ //Fila1 o Fila2
				ViewHolderTV viewHolderTV = (ViewHolderTV)viewHolder;
				viewHolderTV.getTextView().setText(name);
			}
			
			return convertView;
		}
		
		@Override
		public int getViewTypeCount() {
			return 3;
		}
		
		@Override
		public int getItemViewType(int position) {
			if (mArrayPositionsFila1.contains(position)){
				return TipoFila.FILA1.ordinal();
			}else if (mArrayPositionsFila2.contains(position)){
				return TipoFila.FILA2.ordinal();
			}else if (mArrayPositionsFila3.contains(position)){
				return TipoFila.FILA3.ordinal();
			}
			
			return TipoFila.FILA1.ordinal();
		}
		
		
		private class ViewHolder{}
		
		//Holder Pattern
		private class ViewHolderTV extends ViewHolder{
			private TextView mTextView = null;
			
			public TextView getTextView() {
				return mTextView;
			}

			public void setTextView(TextView mTextView) {
				this.mTextView = mTextView;
			}
		}
		
		private class ViewHolderRB extends ViewHolder{
			private RadioButton mRadioButton = null;

			public RadioButton getRadioButton() {
				return mRadioButton;
			}

			public void setRadioButton(RadioButton mRadioButton) {
				this.mRadioButton = mRadioButton;
			}
		}
	}
}
