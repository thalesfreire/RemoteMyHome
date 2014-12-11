package br.com.remotemyhome.fragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import br.com.remotemyhome.R;
import br.com.remotemyhome.adapter.DispositivoAdapter;
import br.com.remotemyhome.dao.CompartimentoDAO;
import br.com.remotemyhome.dao.DispositivoDAO;
import br.com.remotemyhome.model.Compartimento;
import br.com.remotemyhome.model.Dispositivo;

public class CompartimentoFragment extends GenericFragment {
	
	private CompartimentoDAO compartimentoDAO;
	private DispositivoDAO dispositivoDAO;
	private Compartimento compartimento;
	private DispositivoAdapter dispositoAdapter;
	private List<Dispositivo> dispositivos;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		compartimento = (Compartimento) getParams();
		compartimentoDAO = new CompartimentoDAO(getActivity());
		ActionBar actionBar = getActivity().getActionBar();
		actionBar.setTitle(compartimento.getNome());
		actionBar.setDisplayHomeAsUpEnabled(true);
		//getActivity().setTitle(compartimento.getNome());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		View view = inflater.inflate(R.layout.sala_visitas, container, false);
		dispositivoDAO = new DispositivoDAO(getActivity());
		this.dispositivos = dispositivoDAO.carregarDispositivosPorCompartimento(compartimento.getIdCompartimento());
		dispositoAdapter = new DispositivoAdapter(getActivity(), dispositivos);
		ListView listaDispositivos = (ListView) view.findViewById(R.id.listDispositivos);
		listaDispositivos.setAdapter(this.dispositoAdapter);
		
		
		listaDispositivos.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int pos,
					long id) {
				DispositivoFragment dispositivoFragment = new DispositivoFragment();
				
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("dispositivo", dispositivos.get(pos));
				params.put("compartimento", compartimento);
				
				dispositivoFragment.setParams(params);
				getFragmentManager().beginTransaction().replace(R.id.content, dispositivoFragment).addToBackStack(null).commit();
			}
			
		});
		
		
		
		return view;
		
	}

//	@Override
//	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//		super.onCreateOptionsMenu(menu, inflater);
//		inflater.inflate(R.menu.compartimento_menu, menu);
//	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
			
			case R.id.action_add_dispositivo:
				NovoDispositivoFragment novoDispositivoFragment = new NovoDispositivoFragment();
				novoDispositivoFragment.setParams(this.compartimento);
				getFragmentManager().beginTransaction().replace(R.id.content, novoDispositivoFragment).addToBackStack(null).commit();
			break;
			
			case R.id.action_excluir_compartimento:
				this.compartimentoDAO.delete(compartimento);
				getFragmentManager().beginTransaction().replace(R.id.content, new ListCompartimentoFragment()).addToBackStack(null).commit();
				showMsg("Compartimento excl’do com sucesso.");
			break;
		
//			case android.R.id.home:
//				Toast.makeText(getActivity(), "entrou no back", Toast.LENGTH_LONG).show();
//			break;
		}
		
		return true;
	}
	
	
	
}
