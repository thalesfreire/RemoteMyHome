package br.com.remotemyhome.dao;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import br.com.remotemyhome.model.Rele;

public class ReleDAO extends Dao implements InterfaceGenericDAO<Rele> {

	private static final String TABLE_NAME = "rele";
	
	public ReleDAO(Context context) {
		super.init(context);
	}
	
	@Override
	public Long insert(Rele obj) {
		return null;
	}

	@Override
	public Integer update(Rele obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(Rele obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rele load(Long id) {
		Rele rele = null;
		Cursor c = db.query(TABLE_NAME, null, "_id=?",
				new String[] { id.toString() }, null, null, null);
		if (c.getCount() > 0) {
			c.moveToFirst();
			rele = new Rele();
			rele.setId(c.getInt(0));
			rele.setPortaLogica(c.getInt(1));
		}
		c.close();
		return rele;
	}

	@Override
	public List<Rele> loadAll() {
		List<Rele> reles = new LinkedList<Rele>();
		Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
		if (c.getCount() > 0) {
			while (c.moveToNext()) {
				Rele rele = new Rele();
				rele.setId(c.getInt(0));
				rele.setPortaLogica(c.getInt(1));
				reles.add(rele);
			}
		}
		c.close();
		return reles;
	}
	
	public List<Rele> carregarRelesDisponiveis() {
		
		final String query = "SELECT * FROM rele WHERE _id NOT IN (SELECT DISTINCT idRele FROM compartimento) ORDER BY _id"; 
		List<Rele> reles = new LinkedList<Rele>();
		Cursor c = db.rawQuery(query, null);
		if (c.getCount() > 0) {
			while (c.moveToNext()) {
				Rele rele = new Rele();
				rele.setId(c.getInt(0));
				rele.setPortaLogica(c.getInt(1));
				reles.add(rele);
			}
		}
		c.close();
		return reles;
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
//	@Override
//	public Integer delete(Veiculo obj) {
//		
//		db.delete("rastreador", "idVeiculo = ?", new String[] { obj.getId().toString() });
//		return db.delete(TABLE_NAME, "_id = ?", new String[] { obj.getId()
//				.toString() });
//	}
//

//	@Override
//	public List<Veiculo> loadVeiculos() {
//		List<Veiculo> veiculos = new LinkedList<Veiculo>();
//		Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
//		if (c.getCount() > 0) {
//			while (c.moveToNext()) {
//				Veiculo v = new Veiculo();
//				v.setId(c.getInt(0));
//				v.setNome(c.getString(1));
//				v.setRastreador(loadRastreador(c.getInt(0)));
//				veiculos.add(v);
//			}
//		}
//		c.close();
//		return veiculos;
//	}
//	
//	public List<FabricanteRastreador> loadFabricates() {
//		List<FabricanteRastreador> fabricantes = new LinkedList<FabricanteRastreador>();
//		Cursor c = db.query("fabricanterastreador", null, null, null, null, null, null);
//		if (c.getCount() > 0) {
//			while (c.moveToNext()) {
//				FabricanteRastreador f = new FabricanteRastreador();
//				f.setId(c.getInt(0));
//				f.setDescricao(c.getString(1));
//				List<ModeloRastreador> modelos = loadModelosPorFabricate(c.getInt(0));
//				
//				f.setModelos(modelos);
//				fabricantes.add(f);
//			}
//		}
//		c.close();
//		return fabricantes;
//	}
//	
//	public List<ModeloRastreador> loadModelosPorFabricate(Integer idFabricante) {
//		List<ModeloRastreador> modelos = new LinkedList<ModeloRastreador>();
//		Cursor c = db.query("modelorastreador", null, "idFabricante=?", new String[] { idFabricante.toString() }, null, null, null);
//		if (c.getCount() > 0) {
//			while (c.moveToNext()) {
//				ModeloRastreador m = new ModeloRastreador();
//				m.setId(c.getInt(0));
//				m.setDescricao(c.getString(1));
//				modelos.add(m);
//			}
//		}
//		c.close();
//		return modelos;
//	}
//	
//	public List<ModeloRastreador> loadModelos() {
//		List<ModeloRastreador> modelos = new LinkedList<ModeloRastreador>();
//		Cursor c = db.query("modelorastreador", null, null, null, null, null, null);
//		if (c.getCount() > 0) {
//			while (c.moveToNext()) {
//				ModeloRastreador m = new ModeloRastreador();
//				m.setId(c.getInt(0));
//				m.setDescricao(c.getString(1));
//				modelos.add(m);
//			}
//		}
//		c.close();
//		return modelos;
//	}
//	
//	private Rastreador loadRastreador(Integer idVeiculo) {
//		Rastreador r = null;
//		Cursor c = db.query("rastreador", null, "idVeiculo=?", new String[] { idVeiculo.toString() }, null, null, null);
//		if (c.getCount() > 0) {
//			 c.moveToFirst();
//			 r = new Rastreador();
//			 r.setId(c.getInt(0));
//			 r.setNumeroFone(c.getString(1));
//			 r.setSenha(c.getString(2));
//			 r.setIdLogicoRastreador(c.getString(3));
//			 r.setModelo(loadModelo(c.getInt(4)));
//		}
//		c.close();
//		return r;
//	}
//	
//	private ModeloRastreador loadModelo(Integer idModelo) {
//		ModeloRastreador m = null;
//		Cursor c = db.query("modelorastreador", null, "_id=?", new String[] { idModelo.toString() }, null, null, null);
//		if (c.getCount() > 0) {
//			 c.moveToFirst();
//			 m = new ModeloRastreador();
//			 m.setId(c.getInt(0));
//			 m.setDescricao(c.getString(1));
//			 m.setFabricante(loadFabricante(c.getInt(2)));
//			 m.setComandos(loadComandosPorModelo(c.getInt(0)));
//		}
//		c.close();
//		return m;
//	}
//	
//	private List<Comando> loadComandosPorModelo(Integer idModelo) {
//		
//		List<Comando> comandos = new LinkedList<Comando>();
//		Cursor c = db.query("comando", null, "idModelo=?", new String[] { idModelo.toString() }, null, null, null);
//		if (c.getCount() > 0) {
//			while (c.moveToNext()) {
//				Comando comando = new Comando();
//				comando.setId(c.getInt(0));
//				comando.setDescricao(c.getString(1));
//				comando.setComandoSMS(c.getString(2));
//				comandos.add(comando);
//			}
//		}
//		c.close();
//		return comandos;
//		
//	}
//	
//	
//	private FabricanteRastreador loadFabricante(Integer idFabricante) {
//		FabricanteRastreador f = null;
//		Cursor c = db.query("fabricanterastreador", null, "_id=?", new String[] { idFabricante.toString() }, null, null, null);
//		if (c.getCount() > 0) {
//			 c.moveToFirst();
//			 f = new FabricanteRastreador();
//			 f.setId(c.getInt(0));
//			 f.setDescricao(c.getString(1));
//		}
//		c.close();
//		return f;
//	}
//	
//	public Cursor query(SQLiteQueryBuilder queryBuilder, String[] projection,
//			String selection, String[] selectionArgs, String sortOrder) {
//		return queryBuilder.query(db, projection, selection, selectionArgs,
//				null, null, sortOrder);
//	}
//
//	private ContentValues createContentValuesRele(Compartimento obj) {
//		ContentValues values = new ContentValues();
//		values.put("nome", obj.getNome());
//		values.put("tipo", obj.getTipo());
//		return values;
//		// return null;
//	}
//	
//	private ContentValues createContentValuesRastreador(Rastreador obj) {
//		ContentValues values = new ContentValues();
//		values.put("numeroFone", obj.getNumeroFone());
//		values.put("senha", obj.getSenha());
//		values.put("idLogicoRastreador", obj.getIdLogicoRastreador());
//		values.put("idVeiculo", obj.getVeiculo().getId());
//		values.put("idModelo", obj.getModelo().getId());
//		return values;
//		// return null;
//	}

}
