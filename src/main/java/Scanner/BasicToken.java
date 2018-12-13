/**
 *@author
 *LI_Yichen
 *ID: 16130120145
 *e-mail: niuqiao2010@163.com
 */

package Scanner;

//基本记号类
public class BasicToken {

    Scanner.ScannerConstant.TokenType type;//记号类型
    String lexeme;//记号文本
    double value;//记号值
    Scanner.ScannerConstant.Func func;//记号函数引用

    public BasicToken(Scanner.ScannerConstant.TokenType type,
                      String lexeme, double value, Scanner.ScannerConstant.Func func){
        //默认构造函数
        this.type = type;
        this.lexeme = lexeme;
        this.value = value;
        this.func = func;
    }
    public void clear() {
        //初始化清空
        type = ScannerConstant.TokenType.ERRTOKEN;
        lexeme = "";
        value = 0;
        func = null;
    }
}
