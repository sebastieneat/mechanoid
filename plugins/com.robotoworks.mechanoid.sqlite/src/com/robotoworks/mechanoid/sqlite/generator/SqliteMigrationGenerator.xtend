package com.robotoworks.mechanoid.sqlite.generator

import com.google.inject.Inject
import com.robotoworks.mechanoid.sqlite.sqliteModel.MigrationBlock
import com.robotoworks.mechanoid.sqlite.sqliteModel.Model

import static extension com.robotoworks.mechanoid.text.Strings.*

class SqliteMigrationGenerator {
	
		@Inject extension SqliteDatabaseStatementGenerator
		
		def CharSequence generate(Model model, MigrationBlock migration, int version) '''
			/*
			 * Generated by Robotoworks Mechanoid
			 */
			package ămodel.packageName╚.migrations;
			
			import android.database.sqlite.SQLiteDatabase;
			import com.robotoworks.mechanoid.sqlite.SQLiteMigration;
			
			public class Defaultămodel.database.name.pascalize╚MigrationVăversion╚ extends SQLiteMigration {
				@Override
				public void up(SQLiteDatabase db) {
					ămigration.statements.generateStatements╚
				}
			}
			'''
}