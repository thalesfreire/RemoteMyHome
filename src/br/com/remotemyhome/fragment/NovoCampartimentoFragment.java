package br.com.remotemyhome.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import br.com.remotemyhome.R;
import br.com.remotemyhome.dao.CompartimentoDAO;
import br.com.remotemyhome.dao.ReleDAO;
import br.com.remotemyhome.model.Compartimento;
import br.com.remotemyhome.model.ItemSpinner;
import br.com.remotemyhome.model.Rele;

public class NovoCampartimentoFragment extends GenericFragment{

	private Integer tipoCompartimento;
	public Compartimento compartimento;
	public CompartimentoDAO compartimentoDAO;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActivity().getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle(R.string.titulo_novo_compartimento);
		this.compartimento = new Compartimento();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.novo_compartimento, container, false);
		
		Spinner tiposCompartimento = (Spinner) view.findViewById(R.id.txtTipoCompartimento);
		
		ArrayAdapter<ItemSpinner> adapterTipoCompartimento = new ArrayAdapter<ItemSpinner>(getActivity(), android.R.layout.simple_spinner_dropdown_item, loadTiposCompartimento());
		
		tiposCompartimento.setAdapter(adapterTipoCompartimento);
		
		tiposCompartimento.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View v,
					int pos, long id) {
				ItemSpinner item = (ItemSpinner)parent.getItemAtPosition(pos);
				tipoCompartimento = item.getId();
				
			}

			@Override public void onNothingSelected(AdapterView<?> arg0) {}
		});
		
		final EditText txtNomeCompartimento = (EditText) view.findViewById(R.id.txtNomeCompartimento);
		
		Button btnSalvarNovoCompartimento = (Button) view.findViewById(R.id.btnSalvarNovoCompartimento);
		
		btnSalvarNovoCompartimento.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if((txtNomeCompartimento.getText().toString() != null && !txtNomeCompartimento.getText().toString().equals("")) && tipoCompartimento != 0) {
					compartimento.setNome(txtNomeCompartimento.getText().toString());
					compartimento.setTipo(tipoCompartimento);
					compartimentoDAO = new CompartimentoDAO(getActivity());
					compartimentoDAO.insert(compartimento);
					getFragmentManager().beginTransaction().replace(R.id.content, new ListCompartimentoFragment()).addToBackStack(null).commit();
					showMsg("Compartimento incluído com sucesso.");
				} else {
					showMsg("Todos os campos devem ser preenchidos.");
				}
			}
		});
		
		
		return view;
		
	}
	
	private List<ItemSpinner> loadTiposCompartimento() {
		
		List<ItemSpinner> itensSpinner = new ArrayList<ItemSpinner>();
		
		itensSpinner.add(new ItemSpinner(0, "Selecione o tipo de compartimento"));
		
		itensSpinner.add(new ItemSpinner(1, "Sala"));
		
		itensSpinner.add(new ItemSpinner(2, "Quarto"));
		
		itensSpinner.add(new ItemSpinner(3, "Cozinha"));
		
		itensSpinner.add(new ItemSpinner(4, "Varanda"));
		
		itensSpinner.add(new ItemSpinner(5, "Corredor"));
		
		itensSpinner.add(new ItemSpinner(6, "Banheiro"));
		
		return itensSpinner;
	}
	
	private List<ItemSpinner> loadReles() {
		
		ReleDAO releDAO = new ReleDAO(getActivity());
		List<Rele> reles = releDAO.carregarRelesDisponiveis();
		List<ItemSpinner> itensSpinner = new ArrayList<ItemSpinner>();
		itensSpinner.add(new ItemSpinner(0, "Selecione o relé"));
		
		for (Rele rele : reles) {
			itensSpinner.add(new ItemSpinner(rele.getId(), rele.getDescricao()));
		}
		
		return itensSpinner;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
			
			case android.R.id.home:
				getFragmentManager().beginTransaction().replace(R.id.content, new ListCompartimentoFragment()).addToBackStack(null).commit();
			break;
		
		}
		
		return true;
	}
	
}
