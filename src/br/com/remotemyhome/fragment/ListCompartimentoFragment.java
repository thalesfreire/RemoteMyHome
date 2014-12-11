package br.com.remotemyhome.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;
import br.com.remotemyhome.R;
import br.com.remotemyhome.adapter.CompartimentoAdapter;
import br.com.remotemyhome.dao.CompartimentoDAO;
import br.com.remotemyhome.model.Compartimento;

public class ListCompartimentoFragment extends GenericFragment {

	private CompartimentoAdapter adapter;
	private List<Compartimento> compartimentos = new ArrayList<Compartimento>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Compartimento salaVisitas = new Compartimento();
		salaVisitas.setNome("Sala de Visitas");
		salaVisitas.setIdCompartimento(1);
		salaVisitas.setTipo(1);
		
		Compartimento suiteCasal = new Compartimento();
		suiteCasal.setNome("Su’te Casal");
		suiteCasal.setIdCompartimento(2);
		suiteCasal.setTipo(2);
		
		
		this.compartimentos.add(salaVisitas);
		this.compartimentos.add(suiteCasal);
		
		this.adapter = new CompartimentoAdapter(getActivity(), this.compartimentos);
		ActionBar actionBar = getActivity().getActionBar();
		actionBar.setTitle(R.string.app_name);
		actionBar.setDisplayHomeAsUpEnabled(false);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		View view = inflater.inflate(R.layout.list_compartimentos, container, false);
		GridView gridView = (GridView) view.findViewById(R.id.gridViewCompartimentos);
		gridView.setAdapter(this.adapter);
		
		
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int pos,
					long id) {
				
				CompartimentoFragment compartimentoFragment = new CompartimentoFragment();
				compartimentoFragment.setParams(compartimentos.get(pos));
				getFragmentManager().beginTransaction().replace(R.id.content, compartimentoFragment).addToBackStack(null).commit();
			}
		});
		
		
		return view;
		
	}

//	@Override
//	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//		super.onCreateOptionsMenu(menu, inflater);
//		inflater.inflate(R.menu.list_compartimentos_menu, menu);
//	}
	
}
