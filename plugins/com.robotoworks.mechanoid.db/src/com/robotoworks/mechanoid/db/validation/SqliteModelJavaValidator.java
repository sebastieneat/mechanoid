package com.robotoworks.mechanoid.db.validation;

import java.util.ArrayList;
import java.util.HashSet;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.util.TypeReferences;
import org.eclipse.xtext.validation.Check;

import com.google.inject.Inject;
import com.robotoworks.mechanoid.db.sqliteModel.AlterTableAddColumnStatement;
import com.robotoworks.mechanoid.db.sqliteModel.AlterTableRenameStatement;
import com.robotoworks.mechanoid.db.sqliteModel.ColumnSource;
import com.robotoworks.mechanoid.db.sqliteModel.ColumnSourceRef;
import com.robotoworks.mechanoid.db.sqliteModel.CreateTableStatement;
import com.robotoworks.mechanoid.db.sqliteModel.CreateTriggerStatement;
import com.robotoworks.mechanoid.db.sqliteModel.CreateViewStatement;
import com.robotoworks.mechanoid.db.sqliteModel.DDLStatement;
import com.robotoworks.mechanoid.db.sqliteModel.DatabaseBlock;
import com.robotoworks.mechanoid.db.sqliteModel.DropTableStatement;
import com.robotoworks.mechanoid.db.sqliteModel.DropTriggerStatement;
import com.robotoworks.mechanoid.db.sqliteModel.DropViewStatement;
import com.robotoworks.mechanoid.db.sqliteModel.InitBlock;
import com.robotoworks.mechanoid.db.sqliteModel.MigrationBlock;
import com.robotoworks.mechanoid.db.sqliteModel.Model;
import com.robotoworks.mechanoid.db.sqliteModel.SelectCoreExpression;
import com.robotoworks.mechanoid.db.sqliteModel.SelectList;
import com.robotoworks.mechanoid.db.sqliteModel.SingleSourceTable;
import com.robotoworks.mechanoid.db.sqliteModel.SqliteModelPackage;
import com.robotoworks.mechanoid.db.util.ModelUtil;
import com.robotoworks.mechanoid.validation.MechanoidIssueCodes;

public class SqliteModelJavaValidator extends AbstractSqliteModelJavaValidator {
 
	@Inject TypeReferences typeReferences;
	
	@Inject StatementSequenceValidator statementSequenceValidator;
	
	@Check
	public void checkMechanoidLibOnClasspath(Model m) {
		JvmType type = typeReferences.findDeclaredType("com.robotoworks.mechanoid.Mechanoid", m);

		if(type == null) {
			error("mechanoid.jar is required in your /libs folder or on the classpath", SqliteModelPackage.Literals.MODEL__PACKAGE_NAME, MechanoidIssueCodes.MISSING_MECHANOID_LIBS);
		}
	}
	
	@Check
	public void checkStatementSequence(DatabaseBlock db) {
	    StatementSequenceValidatorResult result = statementSequenceValidator.validate(db);
	    
	    if(!result.valid) {
	        error(result.message, result.source, result.feature, -1);
	    }
	}
	
	@Check
	public void checkInitBlockStatementTypes(InitBlock initBlock) {
	    for(DDLStatement stmt : initBlock.getStatements()) {
	        if(!((stmt instanceof CreateViewStatement) ||
	                (stmt instanceof CreateTableStatement))) {
	            error("Only tables and view statements allowed in the init block", 
	                stmt, SqliteModelPackage.Literals.DDL_STATEMENT.getEStructuralFeature(SqliteModelPackage.DDL_STATEMENT), -1);
	        }
	    }
	}

	@Check
	public void checkColumnSourceRefComplete(ColumnSourceRef ref) {
		if(ref.isAll()) {
			return;
		}
		
		if(ref.getSource() != null && ref.getColumn() == null) {
			error("Incomplete reference", SqliteModelPackage.Literals.COLUMN_SOURCE_REF__COLUMN);
		}
	}
	
	@Check
	public void checkUniqueResultColumnAliases(SelectList list) {
		EList<ColumnSource> cols = list.getResultColumns();
		
		for(int i=0; i < cols.size(); i++) {
			ColumnSource subject = cols.get(i);
			int matches = 0;
			for(int j=0; j < cols.size(); j++) {
				ColumnSource target = cols.get(j);
				
				if(subject.getName() != null && 
						target.getName() != null &&
						subject.getName().equalsIgnoreCase(target.getName())) {
					matches++;
				}
				
				if(matches > 1) {
					error("Duplicate alias not allowed", target, SqliteModelPackage.Literals.COLUMN_SOURCE__NAME, -1);
					return;
				}
			}
		}
	}
}
