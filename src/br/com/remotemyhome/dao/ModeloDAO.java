package br.com.remotemyhome.dao;

import java.util.List;
import android.content.Context;
import android.database.Cursor;
import br.com.remotemyhome.model.Modelo;

public class ModeloDAO extends Dao implements InterfaceGenericDAO<Modelo> {

	private static final String TABLE_NAME = "modelo";
	
	public ModeloDAO(Context context) {
		super.init(context);
	}
	
	@Override
	public Long insert(Modelo obj) {
		return null;
	}

	@Override
	public Integer update(Modelo obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(Modelo obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Modelo load(Long id) {
		Modelo modelo = null;
		Cursor c = db.query(TABLE_NAME, null, "_id=?", new String[] { id.toString() }, null, null, null);
		if (c.getCount() > 0) {
			c.moveToFirst();
			modelo = new Modelo();
			modelo.setId(c.getInt(0));
			modelo.setNome(c.getString(1));
		}
		c.close();
		return modelo;
	}

	@Override
	public List<Modelo> loadAll() {
		return null;
	}
	


}
