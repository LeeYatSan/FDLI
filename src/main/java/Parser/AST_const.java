package Parser;

import Scanner.Token;

public class AST_const extends AST_leaf
{
    public AST_const(Token t) {super(t);}
    public double value(){return mToken.getValue();}
}