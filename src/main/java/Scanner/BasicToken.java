/**
 *@author
 *LI_Yichen
 *ID: 16130120145
 *e-mail: niuqiao2010@163.com
 */

package Scanner;

//基本记号类
public class BasicToken {

    TokenType type;//记号类型
    String lexeme;//记号文本
    double value;//记号值
    Func func;//记号函数引用

    public BasicToken(){
        //默认构造函数
        this.type = null;
        this.lexeme = null;
        this.value = 0;
        this.func = null;
    }
    public BasicToken(TokenType type, String lexeme, double value, Func func){
        //构造函数
        this.type = type;
        this.lexeme = lexeme;
        this.value = value;
        this.func = func;
    }
    public void clear() {
        //初始化清空
        type = TokenType.NONTOKEN;
        lexeme = "";
        value = 0;
        func = null;
    }
}
//定义函数名
enum Func{sin, cos, tan, sqrt, exp, log}
//定义记号类型
enum TokenType{
    COMMENT,//用于处理行注释
    ID,//用于进一步区分各保留字、常量名、函数名
    ORIGIN, SCALE, ROT, IS, TO, STEP, DRAW, FOR, FROM, COLOR,//保留字
    T,//参数
    SEMICO, L_BRACKET, R_BRACKET, COMMA,//分隔符号
    PLUS, MINUS, MUL, DIV, POWER,//运算符
    FUNC,//函数
    CONST_ID,//常数
    NONTOKEN,//空记号
    ERRTOKEN//出错记号
}
