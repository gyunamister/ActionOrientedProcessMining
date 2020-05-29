package org.processmining.actionorientedprocessmining.parser;
// Generated from ADL.g4 by ANTLR 4.4
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ADLParser}.
 */
public interface ADLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ADLParser#viewObj}.
	 * @param ctx the parse tree
	 */
	void enterViewObj(@NotNull ADLParser.ViewObjContext ctx);
	/**
	 * Exit a parse tree produced by {@link ADLParser#viewObj}.
	 * @param ctx the parse tree
	 */
	void exitViewObj(@NotNull ADLParser.ViewObjContext ctx);
	/**
	 * Enter a parse tree produced by {@link ADLParser#negation}.
	 * @param ctx the parse tree
	 */
	void enterNegation(@NotNull ADLParser.NegationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ADLParser#negation}.
	 * @param ctx the parse tree
	 */
	void exitNegation(@NotNull ADLParser.NegationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ADLParser#varName}.
	 * @param ctx the parse tree
	 */
	void enterVarName(@NotNull ADLParser.VarNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ADLParser#varName}.
	 * @param ctx the parse tree
	 */
	void exitVarName(@NotNull ADLParser.VarNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ADLParser#parameterDetails}.
	 * @param ctx the parse tree
	 */
	void enterParameterDetails(@NotNull ADLParser.ParameterDetailsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ADLParser#parameterDetails}.
	 * @param ctx the parse tree
	 */
	void exitParameterDetails(@NotNull ADLParser.ParameterDetailsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ADLParser#timeRangeDim}.
	 * @param ctx the parse tree
	 */
	void enterTimeRangeDim(@NotNull ADLParser.TimeRangeDimContext ctx);
	/**
	 * Exit a parse tree produced by {@link ADLParser#timeRangeDim}.
	 * @param ctx the parse tree
	 */
	void exitTimeRangeDim(@NotNull ADLParser.TimeRangeDimContext ctx);
	/**
	 * Enter a parse tree produced by {@link ADLParser#eos}.
	 * @param ctx the parse tree
	 */
	void enterEos(@NotNull ADLParser.EosContext ctx);
	/**
	 * Exit a parse tree produced by {@link ADLParser#eos}.
	 * @param ctx the parse tree
	 */
	void exitEos(@NotNull ADLParser.EosContext ctx);
	/**
	 * Enter a parse tree produced by {@link ADLParser#procName}.
	 * @param ctx the parse tree
	 */
	void enterProcName(@NotNull ADLParser.ProcNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ADLParser#procName}.
	 * @param ctx the parse tree
	 */
	void exitProcName(@NotNull ADLParser.ProcNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ADLParser#unaryPredicateName}.
	 * @param ctx the parse tree
	 */
	void enterUnaryPredicateName(@NotNull ADLParser.UnaryPredicateNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ADLParser#unaryPredicateName}.
	 * @param ctx the parse tree
	 */
	void exitUnaryPredicateName(@NotNull ADLParser.UnaryPredicateNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ADLParser#parameterName}.
	 * @param ctx the parse tree
	 */
	void enterParameterName(@NotNull ADLParser.ParameterNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ADLParser#parameterName}.
	 * @param ctx the parse tree
	 */
	void exitParameterName(@NotNull ADLParser.ParameterNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ADLParser#processEntityDim}.
	 * @param ctx the parse tree
	 */
	void enterProcessEntityDim(@NotNull ADLParser.ProcessEntityDimContext ctx);
	/**
	 * Exit a parse tree produced by {@link ADLParser#processEntityDim}.
	 * @param ctx the parse tree
	 */
	void exitProcessEntityDim(@NotNull ADLParser.ProcessEntityDimContext ctx);
	/**
	 * Enter a parse tree produced by {@link ADLParser#actName}.
	 * @param ctx the parse tree
	 */
	void enterActName(@NotNull ADLParser.ActNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ADLParser#actName}.
	 * @param ctx the parse tree
	 */
	void exitActName(@NotNull ADLParser.ActNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ADLParser#processEntityName}.
	 * @param ctx the parse tree
	 */
	void enterProcessEntityName(@NotNull ADLParser.ProcessEntityNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ADLParser#processEntityName}.
	 * @param ctx the parse tree
	 */
	void exitProcessEntityName(@NotNull ADLParser.ProcessEntityNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ADLParser#binaryPredicate}.
	 * @param ctx the parse tree
	 */
	void enterBinaryPredicate(@NotNull ADLParser.BinaryPredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link ADLParser#binaryPredicate}.
	 * @param ctx the parse tree
	 */
	void exitBinaryPredicate(@NotNull ADLParser.BinaryPredicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link ADLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicate(@NotNull ADLParser.PredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link ADLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicate(@NotNull ADLParser.PredicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link ADLParser#funcName}.
	 * @param ctx the parse tree
	 */
	void enterFuncName(@NotNull ADLParser.FuncNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ADLParser#funcName}.
	 * @param ctx the parse tree
	 */
	void exitFuncName(@NotNull ADLParser.FuncNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ADLParser#resName}.
	 * @param ctx the parse tree
	 */
	void enterResName(@NotNull ADLParser.ResNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ADLParser#resName}.
	 * @param ctx the parse tree
	 */
	void exitResName(@NotNull ADLParser.ResNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ADLParser#setPredicate}.
	 * @param ctx the parse tree
	 */
	void enterSetPredicate(@NotNull ADLParser.SetPredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link ADLParser#setPredicate}.
	 * @param ctx the parse tree
	 */
	void exitSetPredicate(@NotNull ADLParser.SetPredicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link ADLParser#proposition}.
	 * @param ctx the parse tree
	 */
	void enterProposition(@NotNull ADLParser.PropositionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ADLParser#proposition}.
	 * @param ctx the parse tree
	 */
	void exitProposition(@NotNull ADLParser.PropositionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ADLParser#truthValue}.
	 * @param ctx the parse tree
	 */
	void enterTruthValue(@NotNull ADLParser.TruthValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ADLParser#truthValue}.
	 * @param ctx the parse tree
	 */
	void exitTruthValue(@NotNull ADLParser.TruthValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ADLParser#action}.
	 * @param ctx the parse tree
	 */
	void enterAction(@NotNull ADLParser.ActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ADLParser#action}.
	 * @param ctx the parse tree
	 */
	void exitAction(@NotNull ADLParser.ActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ADLParser#startTime}.
	 * @param ctx the parse tree
	 */
	void enterStartTime(@NotNull ADLParser.StartTimeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ADLParser#startTime}.
	 * @param ctx the parse tree
	 */
	void exitStartTime(@NotNull ADLParser.StartTimeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ADLParser#viewDetails}.
	 * @param ctx the parse tree
	 */
	void enterViewDetails(@NotNull ADLParser.ViewDetailsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ADLParser#viewDetails}.
	 * @param ctx the parse tree
	 */
	void exitViewDetails(@NotNull ADLParser.ViewDetailsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ADLParser#viewProc}.
	 * @param ctx the parse tree
	 */
	void enterViewProc(@NotNull ADLParser.ViewProcContext ctx);
	/**
	 * Exit a parse tree produced by {@link ADLParser#viewProc}.
	 * @param ctx the parse tree
	 */
	void exitViewProc(@NotNull ADLParser.ViewProcContext ctx);
	/**
	 * Enter a parse tree produced by {@link ADLParser#timeRange}.
	 * @param ctx the parse tree
	 */
	void enterTimeRange(@NotNull ADLParser.TimeRangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ADLParser#timeRange}.
	 * @param ctx the parse tree
	 */
	void exitTimeRange(@NotNull ADLParser.TimeRangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ADLParser#opDesc}.
	 * @param ctx the parse tree
	 */
	void enterOpDesc(@NotNull ADLParser.OpDescContext ctx);
	/**
	 * Exit a parse tree produced by {@link ADLParser#opDesc}.
	 * @param ctx the parse tree
	 */
	void exitOpDesc(@NotNull ADLParser.OpDescContext ctx);
	/**
	 * Enter a parse tree produced by {@link ADLParser#unaryPredicate}.
	 * @param ctx the parse tree
	 */
	void enterUnaryPredicate(@NotNull ADLParser.UnaryPredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link ADLParser#unaryPredicate}.
	 * @param ctx the parse tree
	 */
	void exitUnaryPredicate(@NotNull ADLParser.UnaryPredicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link ADLParser#viewRes}.
	 * @param ctx the parse tree
	 */
	void enterViewRes(@NotNull ADLParser.ViewResContext ctx);
	/**
	 * Exit a parse tree produced by {@link ADLParser#viewRes}.
	 * @param ctx the parse tree
	 */
	void exitViewRes(@NotNull ADLParser.ViewResContext ctx);
	/**
	 * Enter a parse tree produced by {@link ADLParser#relationalPredicate}.
	 * @param ctx the parse tree
	 */
	void enterRelationalPredicate(@NotNull ADLParser.RelationalPredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link ADLParser#relationalPredicate}.
	 * @param ctx the parse tree
	 */
	void exitRelationalPredicate(@NotNull ADLParser.RelationalPredicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link ADLParser#assessPredicate}.
	 * @param ctx the parse tree
	 */
	void enterAssessPredicate(@NotNull ADLParser.AssessPredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link ADLParser#assessPredicate}.
	 * @param ctx the parse tree
	 */
	void exitAssessPredicate(@NotNull ADLParser.AssessPredicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link ADLParser#viewCf}.
	 * @param ctx the parse tree
	 */
	void enterViewCf(@NotNull ADLParser.ViewCfContext ctx);
	/**
	 * Exit a parse tree produced by {@link ADLParser#viewCf}.
	 * @param ctx the parse tree
	 */
	void exitViewCf(@NotNull ADLParser.ViewCfContext ctx);
	/**
	 * Enter a parse tree produced by {@link ADLParser#endTime}.
	 * @param ctx the parse tree
	 */
	void enterEndTime(@NotNull ADLParser.EndTimeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ADLParser#endTime}.
	 * @param ctx the parse tree
	 */
	void exitEndTime(@NotNull ADLParser.EndTimeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ADLParser#cfName}.
	 * @param ctx the parse tree
	 */
	void enterCfName(@NotNull ADLParser.CfNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ADLParser#cfName}.
	 * @param ctx the parse tree
	 */
	void exitCfName(@NotNull ADLParser.CfNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ADLParser#binaryPredicateName}.
	 * @param ctx the parse tree
	 */
	void enterBinaryPredicateName(@NotNull ADLParser.BinaryPredicateNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ADLParser#binaryPredicateName}.
	 * @param ctx the parse tree
	 */
	void exitBinaryPredicateName(@NotNull ADLParser.BinaryPredicateNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ADLParser#actionName}.
	 * @param ctx the parse tree
	 */
	void enterActionName(@NotNull ADLParser.ActionNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ADLParser#actionName}.
	 * @param ctx the parse tree
	 */
	void exitActionName(@NotNull ADLParser.ActionNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ADLParser#viewAct}.
	 * @param ctx the parse tree
	 */
	void enterViewAct(@NotNull ADLParser.ViewActContext ctx);
	/**
	 * Exit a parse tree produced by {@link ADLParser#viewAct}.
	 * @param ctx the parse tree
	 */
	void exitViewAct(@NotNull ADLParser.ViewActContext ctx);
}