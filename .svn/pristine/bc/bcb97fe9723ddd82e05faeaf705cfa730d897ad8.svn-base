package org.processmining.actionorientedprocessmining.parser;
import java.util.List;

import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.TokenStream;
// Generated from CDL.g4 by ANTLR 4.4
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CDLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__6=1, T__5=2, T__4=3, T__3=4, T__2=5, T__1=6, T__0=7, VARIABLE_NAME=8, 
		STRING=9, NUMBER=10, DEFINE=11, AS=12, FILTER=13, EVALUATE=14, ACQUIRE=15, 
		ASSOCIATE=16, CONTEXT=17, VALIDATE=18, CONDITION=19, CALCULATE=20, ACQUIRED_BY=21, 
		IN=22, LP=23, RP=24, LB=25, RB=26, LSB=27, RSB=28, LTB=29, RTB=30, DQ=31, 
		SEP=32, ASSIGN=33, MAP=34, TILDE=35, NOT=36, AND=37, OR=38, TRUE=39, FALSE=40, 
		OCCUR=41, NEXT=42, EVENTUALLY=43, WS=44;
	public static final String[] tokenNames = {
		"<INVALID>", "'omap'", "'Act'", "'&'", "'vmap'", "';'", "'Proc'", "'Res'", 
		"VARIABLE_NAME", "STRING", "NUMBER", "'DEFINE'", "'AS'", "'FILTER'", "'EVALUATE'", 
		"'ACQUIRE'", "'ASSOCIATE'", "'CONTEXT'", "'VALIDATE'", "'CONDITION'", 
		"'CALCULATE'", "'ACQUIRED BY'", "'IN'", "'('", "')'", "'{'", "'}'", "'['", 
		"']'", "'<'", "'>'", "'\"'", "','", "'='", "':'", "'~'", "'NOT'", "'AND'", 
		"'OR'", "'TRUE'", "'FALSE'", "'OCCUR'", "'NEXT'", "'EVENTUALLY'", "WS"
	};
	public static final int
		RULE_constraint = 0, RULE_acquireQuery = 1, RULE_constraintName = 2, RULE_objectName = 3, 
		RULE_contextDetails = 4, RULE_ctxProc = 5, RULE_ctxAct = 6, RULE_ctxRes = 7, 
		RULE_ctxOmap = 8, RULE_omap = 9, RULE_ctxVmap = 10, RULE_vmap = 11, RULE_procName = 12, 
		RULE_actName = 13, RULE_resName = 14, RULE_objName = 15, RULE_objValue = 16, 
		RULE_attrName = 17, RULE_attrValue = 18, RULE_entityName = 19, RULE_filterName = 20, 
		RULE_acqName = 21, RULE_validatePredicate = 22, RULE_evaluatePredicate = 23, 
		RULE_predicate = 24, RULE_proposition = 25, RULE_unaryPredicate = 26, 
		RULE_unaryPredicateName = 27, RULE_controlflowPredicate = 28, RULE_truthValue = 29, 
		RULE_negation = 30, RULE_relationalPredicate = 31, RULE_setPredicate = 32, 
		RULE_varName = 33, RULE_funcName = 34, RULE_eos = 35;
	public static final String[] ruleNames = {
		"constraint", "acquireQuery", "constraintName", "objectName", "contextDetails", 
		"ctxProc", "ctxAct", "ctxRes", "ctxOmap", "omap", "ctxVmap", "vmap", "procName", 
		"actName", "resName", "objName", "objValue", "attrName", "attrValue", 
		"entityName", "filterName", "acqName", "validatePredicate", "evaluatePredicate", 
		"predicate", "proposition", "unaryPredicate", "unaryPredicateName", "controlflowPredicate", 
		"truthValue", "negation", "relationalPredicate", "setPredicate", "varName", 
		"funcName", "eos"
	};

	@Override
	public String getGrammarFileName() { return "CDL.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CDLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ConstraintContext extends ParserRuleContext {
		public ContextDetailsContext contextDetails(int i) {
			return getRuleContext(ContextDetailsContext.class,i);
		}
		public TerminalNode AS(int i) {
			return getToken(CDLParser.AS, i);
		}
		public TerminalNode EVALUATE(int i) {
			return getToken(CDLParser.EVALUATE, i);
		}
		public EvaluatePredicateContext evaluatePredicate(int i) {
			return getRuleContext(EvaluatePredicateContext.class,i);
		}
		public List<EosContext> eos() {
			return getRuleContexts(EosContext.class);
		}
		public EosContext eos(int i) {
			return getRuleContext(EosContext.class,i);
		}
		public List<ConstraintNameContext> constraintName() {
			return getRuleContexts(ConstraintNameContext.class);
		}
		public List<TerminalNode> AS() { return getTokens(CDLParser.AS); }
		public TerminalNode DEFINE(int i) {
			return getToken(CDLParser.DEFINE, i);
		}
		public List<TerminalNode> DEFINE() { return getTokens(CDLParser.DEFINE); }
		public List<ContextDetailsContext> contextDetails() {
			return getRuleContexts(ContextDetailsContext.class);
		}
		public ConstraintNameContext constraintName(int i) {
			return getRuleContext(ConstraintNameContext.class,i);
		}
		public List<TerminalNode> EVALUATE() { return getTokens(CDLParser.EVALUATE); }
		public List<EvaluatePredicateContext> evaluatePredicate() {
			return getRuleContexts(EvaluatePredicateContext.class);
		}
		public TerminalNode CONTEXT(int i) {
			return getToken(CDLParser.CONTEXT, i);
		}
		public List<TerminalNode> CONTEXT() { return getTokens(CDLParser.CONTEXT); }
		public ConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitConstraint(this);
		}
	}

	public final ConstraintContext constraint() throws RecognitionException {
		ConstraintContext _localctx = new ConstraintContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_constraint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72); match(DEFINE);
			setState(73); constraintName();
			setState(74); match(AS);
			setState(75); match(CONTEXT);
			setState(76); contextDetails();
			setState(81);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(77); match(T__4);
				setState(78); contextDetails();
				}
				}
				setState(83);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(84); match(EVALUATE);
			setState(85); evaluatePredicate();
			setState(86); eos();
			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DEFINE) {
				{
				{
				setState(87); match(DEFINE);
				setState(88); constraintName();
				setState(89); match(AS);
				setState(90); match(CONTEXT);
				setState(91); contextDetails();
				setState(96);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__4) {
					{
					{
					setState(92); match(T__4);
					setState(93); contextDetails();
					}
					}
					setState(98);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(99); match(EVALUATE);
				setState(100); evaluatePredicate();
				setState(101); eos();
				}
				}
				setState(107);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AcquireQueryContext extends ParserRuleContext {
		public TerminalNode CALCULATE() { return getToken(CDLParser.CALCULATE, 0); }
		public AcqNameContext acqName() {
			return getRuleContext(AcqNameContext.class,0);
		}
		public TerminalNode STRING() { return getToken(CDLParser.STRING, 0); }
		public AcquireQueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_acquireQuery; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterAcquireQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitAcquireQuery(this);
		}
	}

	public final AcquireQueryContext acquireQuery() throws RecognitionException {
		AcquireQueryContext _localctx = new AcquireQueryContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_acquireQuery);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108); acqName();
			setState(109); match(CALCULATE);
			setState(110); match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstraintNameContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CDLParser.STRING, 0); }
		public ConstraintNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constraintName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterConstraintName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitConstraintName(this);
		}
	}

	public final ConstraintNameContext constraintName() throws RecognitionException {
		ConstraintNameContext _localctx = new ConstraintNameContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_constraintName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112); match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjectNameContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CDLParser.STRING, 0); }
		public ObjectNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterObjectName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitObjectName(this);
		}
	}

	public final ObjectNameContext objectName() throws RecognitionException {
		ObjectNameContext _localctx = new ObjectNameContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_objectName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114); match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ContextDetailsContext extends ParserRuleContext {
		public CtxResContext ctxRes() {
			return getRuleContext(CtxResContext.class,0);
		}
		public CtxProcContext ctxProc() {
			return getRuleContext(CtxProcContext.class,0);
		}
		public CtxVmapContext ctxVmap() {
			return getRuleContext(CtxVmapContext.class,0);
		}
		public CtxActContext ctxAct() {
			return getRuleContext(CtxActContext.class,0);
		}
		public CtxOmapContext ctxOmap() {
			return getRuleContext(CtxOmapContext.class,0);
		}
		public ContextDetailsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_contextDetails; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterContextDetails(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitContextDetails(this);
		}
	}

	public final ContextDetailsContext contextDetails() throws RecognitionException {
		ContextDetailsContext _localctx = new ContextDetailsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_contextDetails);
		try {
			setState(121);
			switch (_input.LA(1)) {
			case T__1:
				enterOuterAlt(_localctx, 1);
				{
				setState(116); ctxProc();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 2);
				{
				setState(117); ctxAct();
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 3);
				{
				setState(118); ctxRes();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 4);
				{
				setState(119); ctxOmap();
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 5);
				{
				setState(120); ctxVmap();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CtxProcContext extends ParserRuleContext {
		public TerminalNode ASSIGN() { return getToken(CDLParser.ASSIGN, 0); }
		public List<TerminalNode> SEP() { return getTokens(CDLParser.SEP); }
		public ProcNameContext procName(int i) {
			return getRuleContext(ProcNameContext.class,i);
		}
		public TerminalNode RB() { return getToken(CDLParser.RB, 0); }
		public TerminalNode LB() { return getToken(CDLParser.LB, 0); }
		public TerminalNode SEP(int i) {
			return getToken(CDLParser.SEP, i);
		}
		public List<ProcNameContext> procName() {
			return getRuleContexts(ProcNameContext.class);
		}
		public CtxProcContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ctxProc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterCtxProc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitCtxProc(this);
		}
	}

	public final CtxProcContext ctxProc() throws RecognitionException {
		CtxProcContext _localctx = new CtxProcContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_ctxProc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123); match(T__1);
			setState(124); match(ASSIGN);
			setState(125); match(LB);
			setState(126); procName();
			setState(131);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEP) {
				{
				{
				setState(127); match(SEP);
				setState(128); procName();
				}
				}
				setState(133);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(134); match(RB);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CtxActContext extends ParserRuleContext {
		public TerminalNode ASSIGN() { return getToken(CDLParser.ASSIGN, 0); }
		public List<ActNameContext> actName() {
			return getRuleContexts(ActNameContext.class);
		}
		public List<TerminalNode> SEP() { return getTokens(CDLParser.SEP); }
		public ActNameContext actName(int i) {
			return getRuleContext(ActNameContext.class,i);
		}
		public TerminalNode RB() { return getToken(CDLParser.RB, 0); }
		public TerminalNode LB() { return getToken(CDLParser.LB, 0); }
		public TerminalNode SEP(int i) {
			return getToken(CDLParser.SEP, i);
		}
		public CtxActContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ctxAct; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterCtxAct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitCtxAct(this);
		}
	}

	public final CtxActContext ctxAct() throws RecognitionException {
		CtxActContext _localctx = new CtxActContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_ctxAct);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136); match(T__5);
			setState(137); match(ASSIGN);
			setState(138); match(LB);
			setState(139); actName();
			setState(144);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEP) {
				{
				{
				setState(140); match(SEP);
				setState(141); actName();
				}
				}
				setState(146);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(147); match(RB);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CtxResContext extends ParserRuleContext {
		public TerminalNode ASSIGN() { return getToken(CDLParser.ASSIGN, 0); }
		public ResNameContext resName(int i) {
			return getRuleContext(ResNameContext.class,i);
		}
		public List<TerminalNode> SEP() { return getTokens(CDLParser.SEP); }
		public TerminalNode RB() { return getToken(CDLParser.RB, 0); }
		public TerminalNode LB() { return getToken(CDLParser.LB, 0); }
		public TerminalNode SEP(int i) {
			return getToken(CDLParser.SEP, i);
		}
		public List<ResNameContext> resName() {
			return getRuleContexts(ResNameContext.class);
		}
		public CtxResContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ctxRes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterCtxRes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitCtxRes(this);
		}
	}

	public final CtxResContext ctxRes() throws RecognitionException {
		CtxResContext _localctx = new CtxResContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_ctxRes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149); match(T__0);
			setState(150); match(ASSIGN);
			setState(151); match(LB);
			setState(152); resName();
			setState(157);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEP) {
				{
				{
				setState(153); match(SEP);
				setState(154); resName();
				}
				}
				setState(159);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(160); match(RB);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CtxOmapContext extends ParserRuleContext {
		public TerminalNode ASSIGN() { return getToken(CDLParser.ASSIGN, 0); }
		public OmapContext omap(int i) {
			return getRuleContext(OmapContext.class,i);
		}
		public List<TerminalNode> SEP() { return getTokens(CDLParser.SEP); }
		public List<OmapContext> omap() {
			return getRuleContexts(OmapContext.class);
		}
		public TerminalNode RB() { return getToken(CDLParser.RB, 0); }
		public TerminalNode LB() { return getToken(CDLParser.LB, 0); }
		public TerminalNode SEP(int i) {
			return getToken(CDLParser.SEP, i);
		}
		public CtxOmapContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ctxOmap; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterCtxOmap(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitCtxOmap(this);
		}
	}

	public final CtxOmapContext ctxOmap() throws RecognitionException {
		CtxOmapContext _localctx = new CtxOmapContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_ctxOmap);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162); match(T__6);
			setState(163); match(ASSIGN);
			setState(164); match(LB);
			setState(165); omap();
			setState(170);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEP) {
				{
				{
				setState(166); match(SEP);
				setState(167); omap();
				}
				}
				setState(172);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(173); match(RB);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OmapContext extends ParserRuleContext {
		public ObjNameContext objName() {
			return getRuleContext(ObjNameContext.class,0);
		}
		public TerminalNode MAP() { return getToken(CDLParser.MAP, 0); }
		public ObjValueContext objValue(int i) {
			return getRuleContext(ObjValueContext.class,i);
		}
		public List<TerminalNode> SEP() { return getTokens(CDLParser.SEP); }
		public List<ObjValueContext> objValue() {
			return getRuleContexts(ObjValueContext.class);
		}
		public TerminalNode RB() { return getToken(CDLParser.RB, 0); }
		public TerminalNode LB() { return getToken(CDLParser.LB, 0); }
		public TerminalNode SEP(int i) {
			return getToken(CDLParser.SEP, i);
		}
		public OmapContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_omap; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterOmap(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitOmap(this);
		}
	}

	public final OmapContext omap() throws RecognitionException {
		OmapContext _localctx = new OmapContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_omap);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175); objName();
			setState(176); match(MAP);
			setState(177); match(LB);
			setState(178); objValue();
			setState(183);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEP) {
				{
				{
				setState(179); match(SEP);
				setState(180); objValue();
				}
				}
				setState(185);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(186); match(RB);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CtxVmapContext extends ParserRuleContext {
		public TerminalNode ASSIGN() { return getToken(CDLParser.ASSIGN, 0); }
		public List<TerminalNode> SEP() { return getTokens(CDLParser.SEP); }
		public TerminalNode RB() { return getToken(CDLParser.RB, 0); }
		public TerminalNode LB() { return getToken(CDLParser.LB, 0); }
		public List<VmapContext> vmap() {
			return getRuleContexts(VmapContext.class);
		}
		public VmapContext vmap(int i) {
			return getRuleContext(VmapContext.class,i);
		}
		public TerminalNode SEP(int i) {
			return getToken(CDLParser.SEP, i);
		}
		public CtxVmapContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ctxVmap; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterCtxVmap(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitCtxVmap(this);
		}
	}

	public final CtxVmapContext ctxVmap() throws RecognitionException {
		CtxVmapContext _localctx = new CtxVmapContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_ctxVmap);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188); match(T__3);
			setState(189); match(ASSIGN);
			setState(190); match(LB);
			setState(191); vmap();
			setState(196);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEP) {
				{
				{
				setState(192); match(SEP);
				setState(193); vmap();
				}
				}
				setState(198);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(199); match(RB);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VmapContext extends ParserRuleContext {
		public TerminalNode ASSIGN() { return getToken(CDLParser.ASSIGN, 0); }
		public AttrValueContext attrValue(int i) {
			return getRuleContext(AttrValueContext.class,i);
		}
		public AttrNameContext attrName() {
			return getRuleContext(AttrNameContext.class,0);
		}
		public List<TerminalNode> SEP() { return getTokens(CDLParser.SEP); }
		public TerminalNode RB() { return getToken(CDLParser.RB, 0); }
		public TerminalNode LB() { return getToken(CDLParser.LB, 0); }
		public TerminalNode SEP(int i) {
			return getToken(CDLParser.SEP, i);
		}
		public List<AttrValueContext> attrValue() {
			return getRuleContexts(AttrValueContext.class);
		}
		public VmapContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vmap; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterVmap(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitVmap(this);
		}
	}

	public final VmapContext vmap() throws RecognitionException {
		VmapContext _localctx = new VmapContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_vmap);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201); attrName();
			setState(202); match(ASSIGN);
			setState(203); match(LB);
			setState(204); attrValue();
			setState(209);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEP) {
				{
				{
				setState(205); match(SEP);
				setState(206); attrValue();
				}
				}
				setState(211);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(212); match(RB);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProcNameContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CDLParser.STRING, 0); }
		public ProcNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_procName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterProcName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitProcName(this);
		}
	}

	public final ProcNameContext procName() throws RecognitionException {
		ProcNameContext _localctx = new ProcNameContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_procName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214); match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ActNameContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CDLParser.STRING, 0); }
		public ActNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterActName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitActName(this);
		}
	}

	public final ActNameContext actName() throws RecognitionException {
		ActNameContext _localctx = new ActNameContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_actName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216); match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ResNameContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CDLParser.STRING, 0); }
		public ResNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_resName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterResName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitResName(this);
		}
	}

	public final ResNameContext resName() throws RecognitionException {
		ResNameContext _localctx = new ResNameContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_resName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(218); match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjNameContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CDLParser.STRING, 0); }
		public ObjNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterObjName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitObjName(this);
		}
	}

	public final ObjNameContext objName() throws RecognitionException {
		ObjNameContext _localctx = new ObjNameContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_objName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220); match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjValueContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CDLParser.STRING, 0); }
		public ObjValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterObjValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitObjValue(this);
		}
	}

	public final ObjValueContext objValue() throws RecognitionException {
		ObjValueContext _localctx = new ObjValueContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_objValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222); match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttrNameContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CDLParser.STRING, 0); }
		public AttrNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attrName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterAttrName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitAttrName(this);
		}
	}

	public final AttrNameContext attrName() throws RecognitionException {
		AttrNameContext _localctx = new AttrNameContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_attrName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224); match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttrValueContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CDLParser.STRING, 0); }
		public AttrValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attrValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterAttrValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitAttrValue(this);
		}
	}

	public final AttrValueContext attrValue() throws RecognitionException {
		AttrValueContext _localctx = new AttrValueContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_attrValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226); match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EntityNameContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CDLParser.STRING, 0); }
		public EntityNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entityName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterEntityName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitEntityName(this);
		}
	}

	public final EntityNameContext entityName() throws RecognitionException {
		EntityNameContext _localctx = new EntityNameContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_entityName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228); match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FilterNameContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CDLParser.STRING, 0); }
		public FilterNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filterName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterFilterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitFilterName(this);
		}
	}

	public final FilterNameContext filterName() throws RecognitionException {
		FilterNameContext _localctx = new FilterNameContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_filterName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230); match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AcqNameContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CDLParser.STRING, 0); }
		public AcqNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_acqName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterAcqName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitAcqName(this);
		}
	}

	public final AcqNameContext acqName() throws RecognitionException {
		AcqNameContext _localctx = new AcqNameContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_acqName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232); match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValidatePredicateContext extends ParserRuleContext {
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public ValidatePredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_validatePredicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterValidatePredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitValidatePredicate(this);
		}
	}

	public final ValidatePredicateContext validatePredicate() throws RecognitionException {
		ValidatePredicateContext _localctx = new ValidatePredicateContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_validatePredicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234); predicate();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EvaluatePredicateContext extends ParserRuleContext {
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public EvaluatePredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_evaluatePredicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterEvaluatePredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitEvaluatePredicate(this);
		}
	}

	public final EvaluatePredicateContext evaluatePredicate() throws RecognitionException {
		EvaluatePredicateContext _localctx = new EvaluatePredicateContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_evaluatePredicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(236); predicate();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PredicateContext extends ParserRuleContext {
		public PropositionContext proposition() {
			return getRuleContext(PropositionContext.class,0);
		}
		public PredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitPredicate(this);
		}
	}

	public final PredicateContext predicate() throws RecognitionException {
		PredicateContext _localctx = new PredicateContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_predicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238); proposition();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropositionContext extends ParserRuleContext {
		public TruthValueContext truthValue() {
			return getRuleContext(TruthValueContext.class,0);
		}
		public NegationContext negation() {
			return getRuleContext(NegationContext.class,0);
		}
		public RelationalPredicateContext relationalPredicate() {
			return getRuleContext(RelationalPredicateContext.class,0);
		}
		public UnaryPredicateContext unaryPredicate() {
			return getRuleContext(UnaryPredicateContext.class,0);
		}
		public SetPredicateContext setPredicate() {
			return getRuleContext(SetPredicateContext.class,0);
		}
		public ControlflowPredicateContext controlflowPredicate() {
			return getRuleContext(ControlflowPredicateContext.class,0);
		}
		public PropositionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_proposition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterProposition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitProposition(this);
		}
	}

	public final PropositionContext proposition() throws RecognitionException {
		PropositionContext _localctx = new PropositionContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_proposition);
		try {
			setState(246);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(240); unaryPredicate();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(241); setPredicate();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(242); truthValue();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(243); negation();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(244); relationalPredicate();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(245); controlflowPredicate();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnaryPredicateContext extends ParserRuleContext {
		public TerminalNode RP() { return getToken(CDLParser.RP, 0); }
		public UnaryPredicateNameContext unaryPredicateName() {
			return getRuleContext(UnaryPredicateNameContext.class,0);
		}
		public TerminalNode LP() { return getToken(CDLParser.LP, 0); }
		public TerminalNode STRING() { return getToken(CDLParser.STRING, 0); }
		public UnaryPredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryPredicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterUnaryPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitUnaryPredicate(this);
		}
	}

	public final UnaryPredicateContext unaryPredicate() throws RecognitionException {
		UnaryPredicateContext _localctx = new UnaryPredicateContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_unaryPredicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(248); unaryPredicateName();
			setState(249); match(LP);
			setState(250); match(STRING);
			setState(251); match(RP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnaryPredicateNameContext extends ParserRuleContext {
		public TerminalNode OCCUR() { return getToken(CDLParser.OCCUR, 0); }
		public UnaryPredicateNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryPredicateName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterUnaryPredicateName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitUnaryPredicateName(this);
		}
	}

	public final UnaryPredicateNameContext unaryPredicateName() throws RecognitionException {
		UnaryPredicateNameContext _localctx = new UnaryPredicateNameContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_unaryPredicateName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(253); match(OCCUR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ControlflowPredicateContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CDLParser.STRING, 0); }
		public ControlflowPredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_controlflowPredicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterControlflowPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitControlflowPredicate(this);
		}
	}

	public final ControlflowPredicateContext controlflowPredicate() throws RecognitionException {
		ControlflowPredicateContext _localctx = new ControlflowPredicateContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_controlflowPredicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255); match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TruthValueContext extends ParserRuleContext {
		public TerminalNode FALSE() { return getToken(CDLParser.FALSE, 0); }
		public TerminalNode TRUE() { return getToken(CDLParser.TRUE, 0); }
		public TruthValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_truthValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterTruthValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitTruthValue(this);
		}
	}

	public final TruthValueContext truthValue() throws RecognitionException {
		TruthValueContext _localctx = new TruthValueContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_truthValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257);
			_la = _input.LA(1);
			if ( !(_la==TRUE || _la==FALSE) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NegationContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(CDLParser.NOT, 0); }
		public PropositionContext proposition() {
			return getRuleContext(PropositionContext.class,0);
		}
		public NegationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_negation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterNegation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitNegation(this);
		}
	}

	public final NegationContext negation() throws RecognitionException {
		NegationContext _localctx = new NegationContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_negation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(259); match(NOT);
			setState(260); proposition();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationalPredicateContext extends ParserRuleContext {
		public FuncNameContext funcName() {
			return getRuleContext(FuncNameContext.class,0);
		}
		public List<TerminalNode> SEP() { return getTokens(CDLParser.SEP); }
		public TerminalNode STRING() { return getToken(CDLParser.STRING, 0); }
		public TerminalNode NUMBER() { return getToken(CDLParser.NUMBER, 0); }
		public TerminalNode SEP(int i) {
			return getToken(CDLParser.SEP, i);
		}
		public TerminalNode ACQUIRED_BY() { return getToken(CDLParser.ACQUIRED_BY, 0); }
		public RelationalPredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationalPredicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterRelationalPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitRelationalPredicate(this);
		}
	}

	public final RelationalPredicateContext relationalPredicate() throws RecognitionException {
		RelationalPredicateContext _localctx = new RelationalPredicateContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_relationalPredicate);
		int _la;
		try {
			setState(276);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(262); match(STRING);
				setState(263); match(SEP);
				setState(264);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LTB) | (1L << RTB) | (1L << ASSIGN))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(265); match(SEP);
				setState(266); match(NUMBER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(267); match(STRING);
				setState(268); match(SEP);
				setState(269); match(ACQUIRED_BY);
				setState(270); funcName();
				setState(271); match(SEP);
				setState(272);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LTB) | (1L << RTB) | (1L << ASSIGN))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(273); match(SEP);
				setState(274); match(NUMBER);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SetPredicateContext extends ParserRuleContext {
		public VarNameContext varName() {
			return getRuleContext(VarNameContext.class,0);
		}
		public List<TerminalNode> SEP() { return getTokens(CDLParser.SEP); }
		public TerminalNode STRING() { return getToken(CDLParser.STRING, 0); }
		public TerminalNode RB() { return getToken(CDLParser.RB, 0); }
		public TerminalNode SEP(int i) {
			return getToken(CDLParser.SEP, i);
		}
		public TerminalNode LB() { return getToken(CDLParser.LB, 0); }
		public TerminalNode IN() { return getToken(CDLParser.IN, 0); }
		public SetPredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setPredicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterSetPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitSetPredicate(this);
		}
	}

	public final SetPredicateContext setPredicate() throws RecognitionException {
		SetPredicateContext _localctx = new SetPredicateContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_setPredicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(278); match(STRING);
			setState(279); match(SEP);
			setState(280); match(IN);
			setState(281); match(SEP);
			setState(282); match(LB);
			setState(283); varName();
			setState(284); match(RB);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarNameContext extends ParserRuleContext {
		public TerminalNode VARIABLE_NAME() { return getToken(CDLParser.VARIABLE_NAME, 0); }
		public VarNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterVarName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitVarName(this);
		}
	}

	public final VarNameContext varName() throws RecognitionException {
		VarNameContext _localctx = new VarNameContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_varName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(286); match(VARIABLE_NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncNameContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CDLParser.STRING, 0); }
		public FuncNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterFuncName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitFuncName(this);
		}
	}

	public final FuncNameContext funcName() throws RecognitionException {
		FuncNameContext _localctx = new FuncNameContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_funcName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(288); match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EosContext extends ParserRuleContext {
		public EosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterEos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitEos(this);
		}
	}

	public final EosContext eos() throws RecognitionException {
		EosContext _localctx = new EosContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_eos);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(290); match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3.\u0127\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2R\n\2\f\2"+
		"\16\2U\13\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2a\n\2\f\2\16\2"+
		"d\13\2\3\2\3\2\3\2\3\2\7\2j\n\2\f\2\16\2m\13\2\3\3\3\3\3\3\3\3\3\4\3\4"+
		"\3\5\3\5\3\6\3\6\3\6\3\6\3\6\5\6|\n\6\3\7\3\7\3\7\3\7\3\7\3\7\7\7\u0084"+
		"\n\7\f\7\16\7\u0087\13\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u0091\n\b"+
		"\f\b\16\b\u0094\13\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u009e\n\t\f\t"+
		"\16\t\u00a1\13\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u00ab\n\n\f\n\16"+
		"\n\u00ae\13\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u00b8\n\13\f"+
		"\13\16\13\u00bb\13\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00c5\n\f"+
		"\f\f\16\f\u00c8\13\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u00d2\n\r\f\r"+
		"\16\r\u00d5\13\r\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22"+
		"\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31"+
		"\3\31\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\5\33\u00f9\n\33\3\34\3\34"+
		"\3\34\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3 \3!\3!\3!\3!\3!"+
		"\3!\3!\3!\3!\3!\3!\3!\3!\3!\5!\u0117\n!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3"+
		"\"\3#\3#\3$\3$\3%\3%\3%\2\2&\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \""+
		"$&(*,.\60\62\64\668:<>@BDFH\2\4\3\2)*\4\2\37 ##\u0116\2J\3\2\2\2\4n\3"+
		"\2\2\2\6r\3\2\2\2\bt\3\2\2\2\n{\3\2\2\2\f}\3\2\2\2\16\u008a\3\2\2\2\20"+
		"\u0097\3\2\2\2\22\u00a4\3\2\2\2\24\u00b1\3\2\2\2\26\u00be\3\2\2\2\30\u00cb"+
		"\3\2\2\2\32\u00d8\3\2\2\2\34\u00da\3\2\2\2\36\u00dc\3\2\2\2 \u00de\3\2"+
		"\2\2\"\u00e0\3\2\2\2$\u00e2\3\2\2\2&\u00e4\3\2\2\2(\u00e6\3\2\2\2*\u00e8"+
		"\3\2\2\2,\u00ea\3\2\2\2.\u00ec\3\2\2\2\60\u00ee\3\2\2\2\62\u00f0\3\2\2"+
		"\2\64\u00f8\3\2\2\2\66\u00fa\3\2\2\28\u00ff\3\2\2\2:\u0101\3\2\2\2<\u0103"+
		"\3\2\2\2>\u0105\3\2\2\2@\u0116\3\2\2\2B\u0118\3\2\2\2D\u0120\3\2\2\2F"+
		"\u0122\3\2\2\2H\u0124\3\2\2\2JK\7\r\2\2KL\5\6\4\2LM\7\16\2\2MN\7\23\2"+
		"\2NS\5\n\6\2OP\7\5\2\2PR\5\n\6\2QO\3\2\2\2RU\3\2\2\2SQ\3\2\2\2ST\3\2\2"+
		"\2TV\3\2\2\2US\3\2\2\2VW\7\20\2\2WX\5\60\31\2Xk\5H%\2YZ\7\r\2\2Z[\5\6"+
		"\4\2[\\\7\16\2\2\\]\7\23\2\2]b\5\n\6\2^_\7\5\2\2_a\5\n\6\2`^\3\2\2\2a"+
		"d\3\2\2\2b`\3\2\2\2bc\3\2\2\2ce\3\2\2\2db\3\2\2\2ef\7\20\2\2fg\5\60\31"+
		"\2gh\5H%\2hj\3\2\2\2iY\3\2\2\2jm\3\2\2\2ki\3\2\2\2kl\3\2\2\2l\3\3\2\2"+
		"\2mk\3\2\2\2no\5,\27\2op\7\26\2\2pq\7\13\2\2q\5\3\2\2\2rs\7\13\2\2s\7"+
		"\3\2\2\2tu\7\13\2\2u\t\3\2\2\2v|\5\f\7\2w|\5\16\b\2x|\5\20\t\2y|\5\22"+
		"\n\2z|\5\26\f\2{v\3\2\2\2{w\3\2\2\2{x\3\2\2\2{y\3\2\2\2{z\3\2\2\2|\13"+
		"\3\2\2\2}~\7\b\2\2~\177\7#\2\2\177\u0080\7\33\2\2\u0080\u0085\5\32\16"+
		"\2\u0081\u0082\7\"\2\2\u0082\u0084\5\32\16\2\u0083\u0081\3\2\2\2\u0084"+
		"\u0087\3\2\2\2\u0085\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0088\3\2"+
		"\2\2\u0087\u0085\3\2\2\2\u0088\u0089\7\34\2\2\u0089\r\3\2\2\2\u008a\u008b"+
		"\7\4\2\2\u008b\u008c\7#\2\2\u008c\u008d\7\33\2\2\u008d\u0092\5\34\17\2"+
		"\u008e\u008f\7\"\2\2\u008f\u0091\5\34\17\2\u0090\u008e\3\2\2\2\u0091\u0094"+
		"\3\2\2\2\u0092\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0095\3\2\2\2\u0094"+
		"\u0092\3\2\2\2\u0095\u0096\7\34\2\2\u0096\17\3\2\2\2\u0097\u0098\7\t\2"+
		"\2\u0098\u0099\7#\2\2\u0099\u009a\7\33\2\2\u009a\u009f\5\36\20\2\u009b"+
		"\u009c\7\"\2\2\u009c\u009e\5\36\20\2\u009d\u009b\3\2\2\2\u009e\u00a1\3"+
		"\2\2\2\u009f\u009d\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a2\3\2\2\2\u00a1"+
		"\u009f\3\2\2\2\u00a2\u00a3\7\34\2\2\u00a3\21\3\2\2\2\u00a4\u00a5\7\3\2"+
		"\2\u00a5\u00a6\7#\2\2\u00a6\u00a7\7\33\2\2\u00a7\u00ac\5\24\13\2\u00a8"+
		"\u00a9\7\"\2\2\u00a9\u00ab\5\24\13\2\u00aa\u00a8\3\2\2\2\u00ab\u00ae\3"+
		"\2\2\2\u00ac\u00aa\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00af\3\2\2\2\u00ae"+
		"\u00ac\3\2\2\2\u00af\u00b0\7\34\2\2\u00b0\23\3\2\2\2\u00b1\u00b2\5 \21"+
		"\2\u00b2\u00b3\7$\2\2\u00b3\u00b4\7\33\2\2\u00b4\u00b9\5\"\22\2\u00b5"+
		"\u00b6\7\"\2\2\u00b6\u00b8\5\"\22\2\u00b7\u00b5\3\2\2\2\u00b8\u00bb\3"+
		"\2\2\2\u00b9\u00b7\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bc\3\2\2\2\u00bb"+
		"\u00b9\3\2\2\2\u00bc\u00bd\7\34\2\2\u00bd\25\3\2\2\2\u00be\u00bf\7\6\2"+
		"\2\u00bf\u00c0\7#\2\2\u00c0\u00c1\7\33\2\2\u00c1\u00c6\5\30\r\2\u00c2"+
		"\u00c3\7\"\2\2\u00c3\u00c5\5\30\r\2\u00c4\u00c2\3\2\2\2\u00c5\u00c8\3"+
		"\2\2\2\u00c6\u00c4\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c9\3\2\2\2\u00c8"+
		"\u00c6\3\2\2\2\u00c9\u00ca\7\34\2\2\u00ca\27\3\2\2\2\u00cb\u00cc\5$\23"+
		"\2\u00cc\u00cd\7#\2\2\u00cd\u00ce\7\33\2\2\u00ce\u00d3\5&\24\2\u00cf\u00d0"+
		"\7\"\2\2\u00d0\u00d2\5&\24\2\u00d1\u00cf\3\2\2\2\u00d2\u00d5\3\2\2\2\u00d3"+
		"\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00d6\3\2\2\2\u00d5\u00d3\3\2"+
		"\2\2\u00d6\u00d7\7\34\2\2\u00d7\31\3\2\2\2\u00d8\u00d9\7\13\2\2\u00d9"+
		"\33\3\2\2\2\u00da\u00db\7\13\2\2\u00db\35\3\2\2\2\u00dc\u00dd\7\13\2\2"+
		"\u00dd\37\3\2\2\2\u00de\u00df\7\13\2\2\u00df!\3\2\2\2\u00e0\u00e1\7\13"+
		"\2\2\u00e1#\3\2\2\2\u00e2\u00e3\7\13\2\2\u00e3%\3\2\2\2\u00e4\u00e5\7"+
		"\13\2\2\u00e5\'\3\2\2\2\u00e6\u00e7\7\13\2\2\u00e7)\3\2\2\2\u00e8\u00e9"+
		"\7\13\2\2\u00e9+\3\2\2\2\u00ea\u00eb\7\13\2\2\u00eb-\3\2\2\2\u00ec\u00ed"+
		"\5\62\32\2\u00ed/\3\2\2\2\u00ee\u00ef\5\62\32\2\u00ef\61\3\2\2\2\u00f0"+
		"\u00f1\5\64\33\2\u00f1\63\3\2\2\2\u00f2\u00f9\5\66\34\2\u00f3\u00f9\5"+
		"B\"\2\u00f4\u00f9\5<\37\2\u00f5\u00f9\5> \2\u00f6\u00f9\5@!\2\u00f7\u00f9"+
		"\5:\36\2\u00f8\u00f2\3\2\2\2\u00f8\u00f3\3\2\2\2\u00f8\u00f4\3\2\2\2\u00f8"+
		"\u00f5\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f8\u00f7\3\2\2\2\u00f9\65\3\2\2"+
		"\2\u00fa\u00fb\58\35\2\u00fb\u00fc\7\31\2\2\u00fc\u00fd\7\13\2\2\u00fd"+
		"\u00fe\7\32\2\2\u00fe\67\3\2\2\2\u00ff\u0100\7+\2\2\u01009\3\2\2\2\u0101"+
		"\u0102\7\13\2\2\u0102;\3\2\2\2\u0103\u0104\t\2\2\2\u0104=\3\2\2\2\u0105"+
		"\u0106\7&\2\2\u0106\u0107\5\64\33\2\u0107?\3\2\2\2\u0108\u0109\7\13\2"+
		"\2\u0109\u010a\7\"\2\2\u010a\u010b\t\3\2\2\u010b\u010c\7\"\2\2\u010c\u0117"+
		"\7\f\2\2\u010d\u010e\7\13\2\2\u010e\u010f\7\"\2\2\u010f\u0110\7\27\2\2"+
		"\u0110\u0111\5F$\2\u0111\u0112\7\"\2\2\u0112\u0113\t\3\2\2\u0113\u0114"+
		"\7\"\2\2\u0114\u0115\7\f\2\2\u0115\u0117\3\2\2\2\u0116\u0108\3\2\2\2\u0116"+
		"\u010d\3\2\2\2\u0117A\3\2\2\2\u0118\u0119\7\13\2\2\u0119\u011a\7\"\2\2"+
		"\u011a\u011b\7\30\2\2\u011b\u011c\7\"\2\2\u011c\u011d\7\33\2\2\u011d\u011e"+
		"\5D#\2\u011e\u011f\7\34\2\2\u011fC\3\2\2\2\u0120\u0121\7\n\2\2\u0121E"+
		"\3\2\2\2\u0122\u0123\7\13\2\2\u0123G\3\2\2\2\u0124\u0125\7\7\2\2\u0125"+
		"I\3\2\2\2\17Sbk{\u0085\u0092\u009f\u00ac\u00b9\u00c6\u00d3\u00f8\u0116";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}