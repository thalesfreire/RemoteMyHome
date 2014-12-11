package br.com.remotemyhome.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper{
	
	private String deleteScript;
	private String[] createScript;

	public SQLiteHelper(Context context, String name, CursorFactory factory,
			int version, String deleteScript, String[] createScript) {
		super(context, name, factory, version);
		this.deleteScript = deleteScript;
		this.createScript = createScript;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL("CREATE TABLE compartimento (_id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL, tipo INTEGER NOT NULL)");
		db.execSQL("INSERT INTO compartimento (nome, tipo) VALUES ('Sala de Visitas', 1)");
		db.execSQL("INSERT INTO compartimento (nome, tipo) VALUES ('Sala de Jantar', 1)");
		db.execSQL("INSERT INTO compartimento (nome, tipo) VALUES ('Su’te Casal', 2)");
		
		db.execSQL("CREATE TABLE rele (_id INTEGER PRIMARY KEY AUTOINCREMENT, portaLogica INTEGER NOT NULL, idCompartimento INTEGER NOT NULL)");
		db.execSQL("INSERT INTO rele (portaLogica, idCompartimento) VALUES (21, 0)");
		db.execSQL("INSERT INTO rele (portaLogica, idCompartimento) VALUES (22, 0)");
		db.execSQL("INSERT INTO rele (portaLogica, idCompartimento) VALUES (23, 0)");
		db.execSQL("INSERT INTO rele (portaLogica, idCompartimento) VALUES (24, 0)");
		db.execSQL("INSERT INTO rele (portaLogica, idCompartimento) VALUES (25, 0)");
		db.execSQL("INSERT INTO rele (portaLogica, idCompartimento) VALUES (26, 0)");
		db.execSQL("INSERT INTO rele (portaLogica, idCompartimento) VALUES (27, 0)");
		db.execSQL("INSERT INTO rele (portaLogica, idCompartimento) VALUES (28, 0)");
		
		db.execSQL("CREATE TABLE dispositivo (_id INTEGER PRIMARY KEY AUTOINCREMENT, idCompartimento INTEGER NOT NULL, idModelo INTEGER NOT NULL, tipo INTEGER NOT NULL)");
		db.execSQL("INSERT INTO dispositivo (idCompartimento, idModelo, tipo) VALUES (1,1,1)");
		db.execSQL("INSERT INTO dispositivo (idCompartimento, idModelo, tipo) VALUES (1,3,2)");
		db.execSQL("INSERT INTO dispositivo (idCompartimento, idModelo, tipo) VALUES (1,4,3)");
		
		db.execSQL("CREATE TABLE modelo (_id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL)");
		db.execSQL("INSERT INTO modelo (nome) VALUES ('Sony Bravia')");
		db.execSQL("INSERT INTO modelo (nome) VALUES ('H-Buster')");
		db.execSQL("INSERT INTO modelo (nome) VALUES ('Decodificador GVT')");
		db.execSQL("INSERT INTO modelo (nome) VALUES ('Ar-condicionado LG')");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS veiculo");
		db.execSQL("DROP TABLE IF EXISTS rastreador");
		db.execSQL("DROP TABBLE IF EXISTS modelorastreador");
		db.execSQL("DROP TABBLE IF EXISTS comando");
		db.execSQL("DROP TABBLE IF EXISTS fabricanterastreador");
		this.onCreate(db);
	}

}
