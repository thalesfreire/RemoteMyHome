package br.com.remotemyhome.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.remotemyhome.R;
import br.com.remotemyhome.model.Compartimento;
import br.com.remotemyhome.model.Rele;

public class ReleAdapter extends BaseAdapter{

	private LayoutInflater mInflater;
	private List<Rele> itens;
	
	Context appContext;

	public ReleAdapter(Context context, List<Rele> itens) {
		this.itens = itens;
		appContext = context;
	}

	public int getCount() {
		return itens.size();
	}

	public Rele getItem(int position) {
		return itens.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View view, ViewGroup parent) {
		Rele item = itens.get(position);
		
		this.mInflater = (LayoutInflater) appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View viewAux = mInflater.inflate(R.layout.adpt_compartimento, parent, false);
		
		((TextView) viewAux.findViewById(R.id.descricaoRele)).setText(item.getDescricao());
		
		
		return viewAux;

	}
	
}
