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
import br.com.remotemyhome.model.Dispositivo;

public class DispositivoAdapter extends BaseAdapter{

	private LayoutInflater mInflater;
	private List<Dispositivo> itens;
	
	Context appContext;

	public DispositivoAdapter(Context context, List<Dispositivo> itens) {
		this.itens = itens;
		appContext = context;
	}

	public int getCount() {
		return itens.size();
	}

	public Dispositivo getItem(int position) {
		return itens.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View view, ViewGroup parent) {
		Dispositivo item = itens.get(position);
		
		this.mInflater = (LayoutInflater) appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View viewAux = mInflater.inflate(R.layout.adpt_dispositivo, parent, false);
		
		//((TextView) viewAux.findViewById(R.id.nomeDispositivo)).setText(item.getNome());
		
		ImageView imageView = (ImageView) viewAux.findViewById(R.id.imgDispositivo);

		switch (item.getTipo()) {
		//TV
		case 1:
			imageView.setImageResource(R.drawable.tv);
			((TextView) viewAux.findViewById(R.id.nomeDispositivo)).setText("Televis‹o");
			break;
		//DECODIFICADOR TV	
		case 2:
			imageView.setImageResource(R.drawable.cabletv);
			((TextView) viewAux.findViewById(R.id.nomeDispositivo)).setText("Decodificador");
			break;
		//AR-CONDICIONADO	
		case 3:
			imageView.setImageResource(R.drawable.air_conditioning);
			((TextView) viewAux.findViewById(R.id.nomeDispositivo)).setText("Ar-Condicionado");
			break;
		}
		
		((TextView) viewAux.findViewById(R.id.modeloDispositivo)).setText(item.getModelo().getNome());
		
		return viewAux;

	}
	
}
