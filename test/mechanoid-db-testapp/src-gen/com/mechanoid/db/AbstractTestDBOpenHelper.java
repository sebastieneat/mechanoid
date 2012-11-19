/*
 * Generated by Robotoworks Mechanoid
 */
package com.mechanoid.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.robotoworks.mechanoid.sqlite.MechanoidSQLiteOpenHelper;
import com.robotoworks.mechanoid.sqlite.SQLiteMigration;


public abstract class AbstractTestDBOpenHelper extends MechanoidSQLiteOpenHelper {
	public static final String DATABASE_NAME = "TestDB.db";

	public static final int VERSION = 1;

	public interface Tables {
		String TABLE_A = "table_a";
		String QUX = "qux";
		String MEH = "meh";
	}

	public AbstractTestDBOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(
			"create table table_a ( " +
			"_id integer primary key autoincrement " +
			") "
		);
		db.execSQL(
			"create view qux as " +
			"select *, " +
			"a as a,b as b,c as c,d as d, " +
			"count(_id, a, c) as y, " +
			"(case when a is null then b else null end) from table_a as a " +
			"left join c on a=b or a=c and (b=c) " +
			"group by a,b,c=0 " +
			"union " +
			"select a from qux " +
			"union " +
			"select meh from y " +
			"order by x desc "
		);
		
		db.execSQL(
			"create view meh as " +
			"select null as _id, " +
			"_id as product_id, " +
			"title as title, " +
			"null as quantity " +
			"from products as p " +
			"union " +
			"select li._id as _id, " +
			"product_id, " +
			"null as title, " +
			"1 as quantity " +
			"from line_items as li " +
			"where is_complex = 1 " +
			"union " +
			"select li._id as _id, " +
			"product_id, " +
			"null as title, " +
			"count( * ) as quantity " +
			"from line_items as li " +
			"where is_complex = 0 " +
			"group by product_id " +
			"order by product_id, _id "
		);
		
	}

	@Override
	protected SQLiteMigration createMigration(int version) {
		throw new IllegalStateException("No migrations for any version");
	}
}
