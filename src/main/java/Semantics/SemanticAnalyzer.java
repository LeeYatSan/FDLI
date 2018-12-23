package Semantics;

/**
 * 语义分析模块
 * @author WangJiaxiang 16130120131 752097910@qq.com
 *
 */

import Parser.*;	// 使用AST语法树结构及其公开操作接口
import Scanner.BasicToken;
import Scanner.Scanner;
import Scanner.Token;
import Scanner.TokenType;
import UI_main.WinGUI;		// 调用UI绘图
import UI_main.*;		// 颜色


class DoubleRefer {
    public double douRef;
    DoubleRefer(double x){
        douRef = x;
    }
}
// 语义分析器
// 以语法分析产生的 语法树 为输入，对其遍历
// 遍历的过程中计算、绘图
public class SemanticAnalyzer
{

    private AST_stmt_list theProgram;
    private TColor Color = new TColor(255, 0 , 0);
    private double Origin_x, Origin_y;
    private double Scale_x, Scale_y;
    private double rot_angle;
    private double myT_storage = 0;
    public WinGUI wg = null;
    public Scanner theScanner = null;
    public Parser theParser = null;	// 参数来指定一个词法分析器
    int coo = 0;
    // 计算被绘制点的坐标
    private void CalcCoord(AST x_tree, AST y_tree, DoubleRefer ptr_x_value, DoubleRefer ptr_y_value)
    {
        double x_val, x_temp, y_val;

        // 计算表达式的值，得到点的原始坐标
        x_val = (x_tree).value();
        y_val = (y_tree).value();

        // 比例变换
        x_val *=  Scale_x;
        y_val *=  Scale_y;

        // 旋转变换
        x_temp = x_val*Math.cos(rot_angle) + y_val*Math.sin(rot_angle);
        y_val = y_val*Math.cos(rot_angle) - x_val*Math.sin(rot_angle);
        x_val = x_temp;

        // 平移变换
        x_val += Origin_x;
        y_val += (400 - Origin_y);

        // 返回变化后点的坐标
        // 包装类Double可以判断null
        if((Double)ptr_x_value.douRef != null)
            ptr_x_value.douRef = x_val;
        if((Double)ptr_y_value.douRef != null)
            ptr_y_value.douRef = y_val;
//		System.out.println("cal x:" + x_val + " y:" + y_val);

    }

    // 循环绘制点的坐标
    private void Drawloop(
            AST start_tree, AST end_tree,
            AST step_tree, AST x_tree, AST y_tree)
    {

        DoubleRefer x_val = new DoubleRefer(0);
        DoubleRefer y_val = new DoubleRefer(0);
        double start_val, end_val, step_val;	// 绘图起点、终点、步长
        Tclass p_T_value = theParser.getTmemory();	// parser manager it
        start_val = start_tree.value();	// 计算起点表达式的值
        end_val = end_tree.value();	// 计算终点表达式的值
        step_val = step_tree.value();	// 计算步长表达式的值

        for(p_T_value.parameter = start_val; p_T_value.parameter <= end_val; p_T_value.parameter += step_val)
        {
            coo++;
            CalcCoord(x_tree, y_tree, x_val, y_val);
            System.out.println("----------------------------");
            System.out.println(coo+" x: "+x_val.douRef+" y: "+y_val.douRef);

            wg.DrawPixel(x_val.douRef, y_val.douRef, Color);
        }
    }

    public SemanticAnalyzer(WinGUI wg)
    {
        this.wg = wg;
        myT_storage = 0;
        theScanner = new Scanner();
        theScanner.initScanner("src\\ScannerTest.txt");
        theParser = new Parser(theScanner);
        theProgram = theParser.getStmts();
        theParser.setTmemory(myT_storage);


        Color.setValue(255, 0, 0);	// default color is RED
        Origin_x = 0; Origin_y = 0;
        Scale_x = 1; Scale_y = 1;
        rot_angle = 0;
    }

    public int run()
    {
        // 分析的文件名
        if((theProgram = theParser.getStmts()) == null)
            return -1;

        AST_statement stmt = null;
        //Zorro();//假核心???
        int n_stmt = theProgram.children_count();
        for(int i = 0; i < n_stmt; ++i)
        {
            stmt = theProgram.getChild(i);
            switch(stmt.kind())
            {
                // childValue 参数类型 int
                case ORIGIN:
                    System.out.println("origin.");
                    Origin_x = stmt.childValue(0);
                    Origin_y = stmt.childValue(1);
                    break;
                case SCALE:
                    System.out.println("scale.");
                    Scale_x = stmt.childValue(0);
                    Scale_y = stmt.childValue(1);
                    break;
                case ROT:
                    System.out.println("rot.");
                    rot_angle = stmt.childValue(0);
                    break;
                case FOR:
                    System.out.println("for.");
                    Drawloop(stmt.getChild(0), stmt.getChild(1), stmt.getChild(2), stmt.getChild(3), stmt.getChild(4));
                    break;
                case COLOR:
                    System.out.println("color.");
                    Color.setValue(
                            (char)stmt.childValue(0),
                            (char)stmt.childValue(1),
                            (char)stmt.childValue(2));
                    break;
                default:
                    break;

            }

        }
        return 0;
    }

    // 获取文件名
    public String getFilename()
    {
        if(theParser == null)
            return null;
        else
            return theParser.getFilename();
    }

    // ???假核心???
   /* private void Zorro()
    {
        AST_stmt_list stmts = theProgram;
        if(null == stmts)
            return;

        int x = 0, y = 1;
        int sz = 12;
        BasicToken tv = new BasicToken(TokenType.FOR, "FOR", 0, null);
        Token tk = new Token(tv);
        AST_statement stmt = new AST_statement(tk);
        tv.type = TokenType.CONST_ID;	tv.value = x;
        tk.setBasic(tv); 	stmt.addExpression(new AST_const(tk));
        tk.setValue(x+sz); 	stmt.addExpression(new AST_const(tk));
        tk.setValue(1); 	stmt.addExpression(new AST_const(tk));
        Tclass referTemp = new Tclass(myT_storage);		// 引用
        tk.setType(TokenType.T);	stmt.addExpression(new AST_T(tk, referTemp));
        tk.setType(TokenType.CONST_ID); 	stmt.addExpression(new AST_const(tk));
        stmts.addStmt(stmt, 0);

        tk.setType(TokenType.FOR); 	stmt = new AST_statement(tk);
        tv.type = TokenType.CONST_ID; 	tv.value = x+sz/4;
        tk.setBasic(tv); 	stmt.addExpression(new AST_const(tk));
        tk.setValue(x+sz-sz/4); 	stmt.addExpression(new AST_const(tk));
        tk.setValue(1); 	stmt.addExpression(new AST_const(tk));
        tk.setType(TokenType.T); 	stmt.addExpression(new AST_T(tk, referTemp));
        tk.setType(TokenType.CONST_ID); 	tk.setValue(y+sz/2); 	stmt.addExpression(new AST_const(tk));
        stmts.addStmt(stmt, 0);

        tk.setType(TokenType.FOR);  	stmt = new AST_statement(tk);
        tv.type = TokenType.CONST_ID; 	tv.value = x;
        tk.setBasic(tv); 	stmt.addExpression(new AST_const(tk));
        tk.setValue(x+sz); 	stmt.addExpression(new AST_const(tk));
        tk.setValue(1); 	stmt.addExpression(new AST_const(tk));
        tk.setType(TokenType.T); 	stmt.addExpression(new AST_T(tk, referTemp));
        tk.setType(TokenType.CONST_ID); 	tk.setValue(y+sz); 		stmt.addExpression(new AST_const(tk));
        stmts.addStmt(stmt, 0);

        tk.setType(TokenType.FOR); 	stmt = new AST_statement(tk);
        tv.type = TokenType.CONST_ID; 	tv.value = x;
        tk.setBasic(tv); 	stmt.addExpression(new AST_const(tk));
        tk.setValue(x+sz); 	stmt.addExpression(new AST_const(tk));
        tk.setValue(1); 	stmt.addExpression(new AST_const(tk));
        tk.setType(TokenType.T);	stmt.addExpression(new AST_T(tk, referTemp));
        tk.setType(TokenType.CONST_ID);		tk.setValue(y+sz);
        AST l = new AST_const(tk);
        tk.setType(TokenType.T);
        AST r = new AST_T(tk, referTemp);
        tk.setType(TokenType.MINUS); 	stmt.addExpression(new AST_minus(tk, l, r));
        stmts.addStmt(stmt, 0);
    }*/
}


