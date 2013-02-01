/*
 * generated by Xtext
 */
package com.robotoworks.mechanoid.sqlite.formatting;

import org.eclipse.xtext.formatting.impl.AbstractDeclarativeFormatter;
import org.eclipse.xtext.formatting.impl.FormattingConfig;

import com.robotoworks.mechanoid.sqlite.services.SqliteModelGrammarAccess;

/**
 * This class contains custom formatting description.
 * 
 * see : http://www.eclipse.org/Xtext/documentation/latest/xtext.html#formatting
 * on how and when to use it 
 * 
 * Also see {@link org.eclipse.xtext.xtext.XtextFormattingTokenSerializer} as an example
 */
public class SqliteModelFormatter extends AbstractDeclarativeFormatter {
	
	@Override
	protected void configureFormatting(FormattingConfig c) {
		SqliteModelGrammarAccess g = (SqliteModelGrammarAccess) getGrammarAccess();
		

		c.setLinewrap(0, 1, 2).before(g.getSL_COMMENTRule());
		c.setLinewrap(0, 1, 2).before(g.getML_COMMENTRule());
		c.setLinewrap(0, 1, 1).after(g.getML_COMMENTRule());
				
		c.setLinewrap().before(g.getDatabaseBlockRule());
		c.setLinewrap().after(g.getDatabaseBlockAccess().getLeftCurlyBracketKeyword_2());
		c.setLinewrap().before(g.getDatabaseBlockAccess().getRightCurlyBracketKeyword_5());
		
		c.setIndentationIncrement().before(g.getMigrationBlockRule());
		c.setLinewrap(2).before(g.getMigrationBlockRule());
		c.setLinewrap().after(g.getMigrationBlockAccess().getLeftCurlyBracketKeyword_2());		
		c.setLinewrap().before(g.getMigrationBlockAccess().getRightCurlyBracketKeyword_4());		
		c.setIndentationDecrement().after(g.getMigrationBlockRule());
		
		c.setIndentationIncrement().before(g.getDDLStatementRule());
		c.setIndentationDecrement().after(g.getDDLStatementRule());
		
		c.setLinewrap(2).before(g.getDDLStatementRule());
		c.setLinewrap().before(g.getCreateTableStatementAccess().getRightParenthesisKeyword_8());
		c.setLinewrap().before(g.getColumnDefRule());
		c.setNoSpace().before(g.getCreateTableStatementAccess().getCommaKeyword_6_0());
		
		c.setLinewrap().before(g.getDMLStatementRule());
		c.setLinewrap().before(g.getResultColumnRule());
		
		c.setLinewrap().before(g.getSelectExpressionAccess().getSelectKeyword_1());
		c.setLinewrap().after(g.getSelectExpressionAccess().getSelectKeyword_1());
		c.setLinewrap().before(g.getSelectExpressionAccess().getFromKeyword_4_0());
		c.setLinewrap().before(g.getSelectExpressionAccess().getWhereKeyword_5_0());
		c.setLinewrap().before(g.getSelectExpressionAccess().getGroupByKeyword_6_0());
		c.setLinewrap().before(g.getSelectExpressionAccess().getHavingKeyword_7_0());
		c.setLinewrap().before(g.getSelectStatementAccess().getOrderByKeyword_1_0());
		c.setNoSpace().before(g.getSelectListAccess().getCommaKeyword_1_0());
		c.setLinewrap().before(g.getJoinStatementRule());
		
		c.setLinewrap().before(g.getCompoundOperatorRule());
		c.setLinewrap().before(g.getCreateTriggerStatementAccess().getWhenExpressionAssignment_8_1());
		
		c.setLinewrap().after(g.getExprAndAccess().getOpAndKeyword_1_1_0());
		c.setLinewrap().after(g.getExprOrAccess().getOpOrKeyword_1_1_0());
		c.setLinewrap().after(g.getCaseRule());
		c.setNoSpace().after(g.getPrimaryExpressionAccess().getSourceAssignment_2_1());		
		c.setNoSpace().before(g.getPrimaryExpressionAccess().getColumnAssignment_0_2());		
		c.setNoSpace().before(g.getPrimaryExpressionAccess().getColumnAssignment_1_2());		
	}
}
