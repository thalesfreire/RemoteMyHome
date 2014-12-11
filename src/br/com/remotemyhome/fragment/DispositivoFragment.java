package br.com.remotemyhome.fragment;

import java.util.Map;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import br.com.remotemyhome.R;
import br.com.remotemyhome.adapter.DispositivoAdapter;
import br.com.remotemyhome.dao.DispositivoDAO;
import br.com.remotemyhome.model.Compartimento;
import br.com.remotemyhome.model.Dispositivo;

public class DispositivoFragment extends GenericFragment {
	
	private DispositivoDAO dispositivoDAO;
	private Dispositivo dispositivo;
	private DispositivoAdapter dispositoAdapter;
	
	private Compartimento compartimento;
	
	private View view;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Map<String, Object> params = (Map<String, Object>)getParams();
		dispositivo = (Dispositivo) params.get("dispositivo");
		compartimento = (Compartimento) params.get("compartimento");
		ActionBar actionBar = getActivity().getActionBar();
		actionBar.setTitle(dispositivo.getModelo().getNome());
		actionBar.setDisplayHomeAsUpEnabled(true);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		
//		if(this.dispositivo.getModelo().getId() == 1)
//			this.view = inflater.inflate(R.layout.sony_bravia, container, false);
//		if(this.dispositivo.getModelo().getId() == 2)
//			this.view = inflater.inflate(R.layout.h_buster, container, false);
		if(this.dispositivo.getModelo().getId() == 3)
			this.view = inflater.inflate(R.layout.decodificador_gvt, container, false);
//		if(this.dispositivo.getModelo().getId() == 4)
//			this.view = inflater.inflate(R.layout.ar_condicionado_lg, container, false);
		
		dispositivoDAO = new DispositivoDAO(getActivity());
//		List<Dispositivo> dispositivos = dispositivoDAO.carregarDispositivosPorCompartimento(compartimento.getIdCompartimento());
//		dispositoAdapter = new DispositivoAdapter(getActivity(), dispositivos);
//		ListView listaDispositivos = (ListView) view.findViewById(R.id.listDispositivos);
//		listaDispositivos.setAdapter(this.dispositoAdapter);
		
		return this.view;
		
	}

//	@Override
//	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//		super.onCreateOptionsMenu(menu, inflater);
//		inflater.inflate(R.menu.dispositivo_menu, menu);
//	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		CompartimentoFragment compartimentoFragment = new CompartimentoFragment();
		compartimentoFragment.setParams(this.compartimento);
		
		switch (item.getItemId()) {
		
			case android.R.id.home:
				getFragmentManager().beginTransaction().replace(R.id.content, compartimentoFragment).addToBackStack(null).commit();
			break;
			
			case R.id.action_excluir_dispositivo:
				this.dispositivoDAO.delete(dispositivo);
				getFragmentManager().beginTransaction().replace(R.id.content, compartimentoFragment).addToBackStack(null).commit();
				showMsg("Dispositivo excl’do com sucesso.");
			break;
		
		}
			
		return true;
	}
	
}
