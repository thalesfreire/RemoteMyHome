package br.com.remotemyhome.dao;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import br.com.remotemyhome.model.Compartimento;

public class CompartimentoDAO extends Dao implements InterfaceGenericDAO<Compartimento> {

	private static final String TABLE_NAME = "compartimento";
	
	private Context contexto;
	
	public CompartimentoDAO(Context context) {
		this.contexto = context;
		super.init(context);
	}
	
	@Override
	public Long insert(Compartimento obj) {
		Long id = db.insert(TABLE_NAME, null, createContentValuesCompartimento(obj));
		return id;
	}

	@Override
	public Integer update(Compartimento obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(Compartimento obj) {
		db.delete("dispositivo", "idCompartimento = ?", new String[] { obj.getIdCompartimento().toString() });
		db.delete("rele", "idCompartimento = ?", new String[] { obj.getIdCompartimento().toString() });
		return db.delete(TABLE_NAME, "_id = ?", new String[] { obj.getIdCompartimento().toString() });
	}

	@Override
	public Compartimento load(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Compartimento> loadAll() {
		List<Compartimento> compartimentos = new LinkedList<Compartimento>();
		Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
		if (c.getCount() > 0) {
			while (c.moveToNext()) {
				Compartimento compartimento = new Compartimento();
				compartimento.setIdCompartimento(c.getInt(0));
				compartimento.setNome(c.getString(1));
				compartimento.setTipo(c.getInt(2));
				compartimentos.add(compartimento);
			}
		}
		c.close();
		return compartimentos;
	}
	
//
//	@Override
//	public Integer update(Veiculo obj) {
//		Rastreador rastreador = obj.getRastreador();
//		rastreador.setVeiculo(obj);
//		db.update("rastreador", createContentValuesRastreador(obj.getRastreador()), "_id = ?", new String[] { obj.getId().toString() });
//		return db.update(TABLE_NAME, createContentValuesVeiculo(obj), "_id = ?",
//				new String[] { obj.getId().toString() });
//	}
//



	private ContentValues createContentValuesCompartimento(Compartimento obj) {
		ContentValues values = new ContentValues();
		values.put("nome", obj.getNome());
		values.put("tipo", obj.getTipo());
		return values;
	}

}
