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
import br.com.remotemyhome.constants.TipoCompartimento;
import br.com.remotemyhome.model.Compartimento;

public class CompartimentoAdapter extends BaseAdapter{

	private LayoutInflater mInflater;
	private List<Compartimento> itens;
	
	Context appContext;

	public CompartimentoAdapter(Context context, List<Compartimento> itens) {
		this.itens = itens;
		appContext = context;
	}

	public int getCount() {
		return itens.size();
	}

	public Compartimento getItem(int position) {
		return itens.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View view, ViewGroup parent) {
		Compartimento item = itens.get(position);
		
		this.mInflater = (LayoutInflater) appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View viewAux = mInflater.inflate(R.layout.adpt_compartimento, parent, false);
		
		((TextView) viewAux.findViewById(R.id.nomeCompartimento)).setText(item.getNome());
		
		ImageView imageView = (ImageView) viewAux.findViewById(R.id.imagemCompartimento);

		switch (item.getTipo()) {
		//SALA
		case 1:
			imageView.setImageResource(R.drawable.living);
			break;
		//QUARTO	
		case 2:
			imageView.setImageResource(R.drawable.bed);
			break;
		//COZINHA	
		case 3:
			imageView.setImageResource(R.drawable.kitchen);
			break;
		//VARANDA	
		case 4:
			imageView.setImageResource(R.drawable.veranda);
			break;
		//CORREDOR	
		case 5:
			imageView.setImageResource(R.drawable.hall);
			break;
		//BANHEIRO	
		case 6:
			imageView.setImageResource(R.drawable.bath);
			break;
		}
		
		
		return viewAux;

	}
	
}
