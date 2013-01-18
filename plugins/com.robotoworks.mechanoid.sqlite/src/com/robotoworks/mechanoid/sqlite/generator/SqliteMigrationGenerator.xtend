package com.robotoworks.mechanoid.sqlite.generator

import com.robotoworks.mechanoid.sqlite.sqliteModel.MigrationBlock
import com.robotoworks.mechanoid.sqlite.sqliteModel.Model

import static extension com.robotoworks.mechanoid.sqlite.generator.Extensions.*
import com.google.inject.Inject
import org.eclipse.xtext.serializer.ISerializer
import static extension com.robotoworks.mechanoid.common.util.Strings.*

class SqliteMigrationGenerator {
	
		@Inject extension ISerializer
		
		def CharSequence generate(Model model, MigrationBlock migration, int version) '''
			/*
			 * Generated by Robotoworks Mechanoid
			 */
			package �model.packageName�.migrations;
			
			import android.database.sqlite.SQLiteDatabase;
			import com.robotoworks.mechanoid.sqlite.SQLiteMigration;
			
			public abstract class Abstract�model.database.name.pascalize�MigrationV�version� extends SQLiteMigration {
				@Override
				public void up(SQLiteDatabase db) {
					�FOR stmt : migration.statements�
					db.execSQL(
						�FOR line : stmt.serialize.trim.split("\\r?\\n") SEPARATOR " +"�
						"�line.trim� "
						�ENDFOR�
					);
					�ENDFOR�
				}
			}
			'''
		def CharSequence generateStub(Model model, MigrationBlock snapshot, int version) '''
			/*
			 * Generated by Robotoworks Mechanoid
			 */
			package �model.packageName�.migrations;
			
			import �model.packageName�.migrations.Abstract�model.database.name.pascalize�MigrationV�version�;
			
			public class �model.database.name.pascalize�MigrationV�version� extends Abstract�model.database.name.pascalize�MigrationV�version� {
			}
		'''
}