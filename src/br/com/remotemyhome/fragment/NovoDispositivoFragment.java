package br.com.remotemyhome.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import br.com.remotemyhome.R;
import br.com.remotemyhome.dao.DispositivoDAO;
import br.com.remotemyhome.model.Compartimento;
import br.com.remotemyhome.model.Dispositivo;
import br.com.remotemyhome.model.ItemSpinner;
import br.com.remotemyhome.model.Modelo;

public class NovoDispositivoFragment extends GenericFragment{

	private Integer tipoDispositivo;
	
	private Integer modeloDispositivo;
	
	private Compartimento compartimento;
	
	private DispositivoDAO dispositivoDAO;
	
	View view;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActivity().getActionBar();
		actionBar.setTitle(R.string.titulo_novo_dispositivo);
		actionBar.setDisplayHomeAsUpEnabled(true);
		this.compartimento = (Compartimento) getParams();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		this.view = inflater.inflate(R.layout.novo_dispositivo, container, false);
		
		Spinner tiposDispositivo = (Spinner) view.findViewById(R.id.txtTipoDispositivo);
		
		ArrayAdapter<ItemSpinner> adapterTipoDispositivo = new ArrayAdapter<ItemSpinner>(getActivity(), android.R.layout.simple_spinner_dropdown_item, loadTiposDispositivo());
		
		tiposDispositivo.setAdapter(adapterTipoDispositivo);
		
		tiposDispositivo.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View v,
					int pos, long id) {
				ItemSpinner item = (ItemSpinner)parent.getItemAtPosition(pos);
				tipoDispositivo = item.getId();
				createSpinnerModelosDispositivos(view, tipoDispositivo);
				
			}

			@Override public void onNothingSelected(AdapterView<?> arg0) {}
		});
		
		createSpinnerModelosDispositivos(this.view, 0);
		
		Button btnSalvarNovoDispositivo = (Button) view.findViewById(R.id.btnSalvarNovoDispositivo);
		
		btnSalvarNovoDispositivo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(modeloDispositivo != 0 && tipoDispositivo != 0) {
					CompartimentoFragment compartimentoFragment = new CompartimentoFragment();
					compartimentoFragment.setParams(compartimento);
					
					Modelo modelo = new Modelo();
					modelo.setId(modeloDispositivo);
					
					Dispositivo dispositivo = new Dispositivo();
					dispositivo.setCompartimento(compartimento);
					dispositivo.setModelo(modelo);
					dispositivo.setTipo(tipoDispositivo);
					dispositivoDAO = new DispositivoDAO(getActivity());
					dispositivoDAO.insert(dispositivo);
					getFragmentManager().beginTransaction().replace(R.id.content, compartimentoFragment).addToBackStack(null).commit();
					showMsg("Dispositivo inclu’do com sucesso.");
				} else {
					showMsg("Todos os campos devem ser preenchidos.");
				}
			}
		});
		
		
		return this.view;
		
	}
	
	private List<ItemSpinner> loadTiposDispositivo() {
		
		List<ItemSpinner> itensSpinner = new ArrayList<ItemSpinner>();
		
		itensSpinner.add(new ItemSpinner(0, "Selecione o tipo de compartimento"));
		
		itensSpinner.add(new ItemSpinner(1, "Televis‹o"));
		
		itensSpinner.add(new ItemSpinner(2, "Decodificador"));
		
		itensSpinner.add(new ItemSpinner(3, "Ar-condicionado"));
		
		return itensSpinner;
	}
	
	private List<ItemSpinner> loadModelosDispositivos(Integer tipoDispositivo) {
		
		List<ItemSpinner> itensSpinner = new ArrayList<ItemSpinner>();
		itensSpinner.add(new ItemSpinner(0, "Selecione um modelo"));
		
		if(tipoDispositivo != 0) {
			switch (tipoDispositivo) {
				case 1:
					itensSpinner.add(new ItemSpinner(1, "Sony Bravia"));
					itensSpinner.add(new ItemSpinner(2, "H-Buster"));
					break;
					
				case 2:
					itensSpinner.add(new ItemSpinner(3, "Decodificador GVT"));
					break;
	
				case 3:
					itensSpinner.add(new ItemSpinner(4, "Ar-condicionado LG"));
					break;
			}
		}
		
		return itensSpinner;
	}

	private void createSpinnerModelosDispositivos(View view, Integer tipoDispositivo) {
		Spinner modelosDispositivo = (Spinner) view.findViewById(R.id.txtModeloDispositivo);
		
		ArrayAdapter<ItemSpinner> adapterModeloDispositivo = new ArrayAdapter<ItemSpinner>(getActivity(), android.R.layout.simple_spinner_dropdown_item, loadModelosDispositivos(tipoDispositivo));
		
		modelosDispositivo.setAdapter(adapterModeloDispositivo);
		
		modelosDispositivo.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View v,
					int pos, long id) {
				ItemSpinner item = (ItemSpinner)parent.getItemAtPosition(pos);
				modeloDispositivo = item.getId();
				
			}

			@Override public void onNothingSelected(AdapterView<?> arg0) {}
		});
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
			
			case android.R.id.home:
				CompartimentoFragment compartimentoFragment = new CompartimentoFragment();
				compartimentoFragment.setParams(this.compartimento);
				getFragmentManager().beginTransaction().replace(R.id.content, compartimentoFragment).addToBackStack(null).commit();
			break;
		
		}
		
		return true;
	}
}
