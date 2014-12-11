package br.com.remotemyhome.dao;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import br.com.remotemyhome.model.Compartimento;
import br.com.remotemyhome.model.Dispositivo;

public class DispositivoDAO extends Dao implements InterfaceGenericDAO<Dispositivo> {

	private static final String TABLE_NAME = "dispositivo";
	
	private CompartimentoDAO compartimentoDAO;
	
	private ModeloDAO modeloDAO;
	
	public DispositivoDAO(Context context) {
		super.init(context);
		compartimentoDAO = new CompartimentoDAO(context);
		modeloDAO = new ModeloDAO(context);
	}
	
	@Override
	public Long insert(Dispositivo obj) {
		return db.insert(TABLE_NAME, null, createContentValuesCompartimento(obj));
	}

	@Override
	public Integer update(Dispositivo obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(Dispositivo obj) {
		return db.delete(TABLE_NAME, "_id = ?", new String[] { obj.getId().toString() });
	}

	@Override
	public Dispositivo load(Long id) {
		Dispositivo dispositivo = null;
		Cursor c = db.query(TABLE_NAME, null, "_id=?", new String[] { id.toString() }, null, null, null);
		if (c.getCount() > 0) {
			c.moveToFirst();
			dispositivo = new Dispositivo();
			dispositivo.setId(c.getInt(0));
			dispositivo.setCompartimento(compartimentoDAO.load(new Long(c.getInt(1))));
			dispositivo.setModelo(modeloDAO.load(new Long(c.getInt(2))));
			dispositivo.setTipo(c.getInt(3));
		}
		c.close();
		return dispositivo;
	}

	@Override
	public List<Dispositivo> loadAll() {
		List<Dispositivo> dispositivos = new LinkedList<Dispositivo>();
		Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
		if (c.getCount() > 0) {
			while (c.moveToNext()) {
				Dispositivo dispositivo = new Dispositivo();
				dispositivo.setId(c.getInt(0));
				dispositivo.setCompartimento(compartimentoDAO.load(new Long(c.getInt(1))));
				dispositivo.setModelo(modeloDAO.load(new Long(c.getInt(2))));
				dispositivo.setTipo(c.getInt(3));
				dispositivos.add(dispositivo);
			}
		}
		c.close();
		return dispositivos;
	}
	
	public List<Dispositivo> carregarDispositivosPorCompartimento(Integer idCompartimento) {
		
		final String query = "SELECT * FROM dispositivo WHERE idCompartimento = "+idCompartimento; 
		List<Dispositivo> dispositivos = new LinkedList<Dispositivo>();
		Cursor c = db.rawQuery(query, null);
		if (c.getCount() > 0) {
			while (c.moveToNext()) {
				Dispositivo dispositivo = new Dispositivo();
				dispositivo.setId(c.getInt(0));
				dispositivo.setCompartimento(compartimentoDAO.load(new Long(c.getInt(1))));
				dispositivo.setModelo(modeloDAO.load(new Long(c.getInt(2))));
				dispositivo.setTipo(c.getInt(3));
				dispositivos.add(dispositivo);
			}
		}
		c.close();
		return dispositivos;
	}
	
	private ContentValues createContentValuesCompartimento(Dispositivo obj) {
		ContentValues values = new ContentValues();
		values.put("idCompartimento", obj.getCompartimento().getIdCompartimento());
		values.put("idModelo", obj.getModelo().getId());
		values.put("tipo", obj.getTipo());
		return values;
	}
	
}
