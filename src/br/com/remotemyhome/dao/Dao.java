package br.com.remotemyhome.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public abstract class Dao {
	
	private static final String DATABASE_NAME = "db_remote_my_home";

	private static final String DROP_SCRIPT = "";

	private static final String[] CREATE_SCRIPT = new String[] { "" };

	protected SQLiteDatabase db;
	private SQLiteHelper dbHelper;

	public void init(Context context) {
		try {
			dbHelper = new SQLiteHelper(context, DATABASE_NAME, null, 1, DROP_SCRIPT, CREATE_SCRIPT);
			db = dbHelper.getWritableDatabase();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
