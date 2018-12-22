package Parser;

import ErrorLog.ErrorLog;
import Scanner.Scanner;

public class ParserMain {

    void doParse(String file_name) //测试代码
    {
        ErrorLog.restart(true);
        Scanner theScanner = new Scanner();
        if (0 == theScanner.initScanner(file_name))
            return;
        Parser theParser = new Parser(theScanner);
        try
        {
            AST_stmt_list stmts = theParser.run();
            if (stmts != null) stmts = null;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        ErrorLog.restart(false);
    }

    public static void main(String[] args)
    {
        ParserMain test = new ParserMain();
        test.doParse("ScannerTest.txt");
    }

}
