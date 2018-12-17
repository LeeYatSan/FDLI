# FDLI
Function Drawing Language Interpreter （FDLI）

## UML CLASS DIAGRAM
[LINK](https://www.lucidchart.com/invitations/accept/c7075955-0e29-45f5-a0da-cc2f675324a1)
![image](https://github.com/LeeYatSan/FDLI/blob/master/Function%20Drawing%20Language%20Interpreter%20%EF%BC%88FDLI%EF%BC%89.svg)

## Lexer(Scanner) Test
### Input File
```
--------------- 函数f(t)=t的图形
origin is (100, 300);	-- 设置原点的偏移量
rot is 0;-- 设置旋转角度(不旋转)
scale is (1, 1);-- 设置横坐标和纵坐标的比例
for T from 0 to 200 step 1 draw (t, 0);-- 横坐标的轨迹（纵坐标为0）
for T from 0 to 150 step 1 draw (0, -t);-- 纵坐标的轨迹（横坐标为0）
for T from 0 to 120 step 1 draw (t, -t);-- 函数f(t)=t的轨迹 
```
### Result
```
TokenType    Lexeme        Value       Func           Location
_________________________________________________________________

ID           ORIGIN       0.00000      null         (2    ,6    )
ID           IS           0.00000      null         (2    ,9    )
L_BRACKET    (            0.00000      null         (2    ,11   )
CONST_ID     100          100.00000    null         (2    ,14   )
COMMA        ,            0.00000      null         (2    ,15   )
CONST_ID     300          300.00000    null         (2    ,19   )
R_BRACKET    )            0.00000      null         (2    ,20   )
SEMICO       ;            0.00000      null         (2    ,21   )
ID           ROT          0.00000      null         (3    ,3    )
ID           IS           0.00000      null         (3    ,6    )
CONST_ID     0            0.00000      null         (3    ,8    )
SEMICO       ;            0.00000      null         (3    ,9    )
ID           SCALE        0.00000      null         (4    ,5    )
ID           IS           0.00000      null         (4    ,8    )
L_BRACKET    (            0.00000      null         (4    ,10   )
CONST_ID     1            1.00000      null         (4    ,11   )
COMMA        ,            0.00000      null         (4    ,12   )
CONST_ID     1            1.00000      null         (4    ,14   )
R_BRACKET    )            0.00000      null         (4    ,15   )
SEMICO       ;            0.00000      null         (4    ,16   )
ID           FOR          0.00000      null         (5    ,3    )
ID           T            0.00000      null         (5    ,5    )
ID           FROM         0.00000      null         (5    ,10   )
CONST_ID     0            0.00000      null         (5    ,12   )
ID           TO           0.00000      null         (5    ,15   )
CONST_ID     200          200.00000    null         (5    ,19   )
ID           STEP         0.00000      null         (5    ,24   )
CONST_ID     1            1.00000      null         (5    ,26   )
ID           DRAW         0.00000      null         (5    ,31   )
L_BRACKET    (            0.00000      null         (5    ,33   )
ID           T            0.00000      null         (5    ,34   )
COMMA        ,            0.00000      null         (5    ,35   )
CONST_ID     0            0.00000      null         (5    ,37   )
R_BRACKET    )            0.00000      null         (5    ,38   )
SEMICO       ;            0.00000      null         (5    ,39   )
ID           FOR          0.00000      null         (6    ,3    )
ID           T            0.00000      null         (6    ,5    )
ID           FROM         0.00000      null         (6    ,10   )
CONST_ID     0            0.00000      null         (6    ,12   )
ID           TO           0.00000      null         (6    ,15   )
CONST_ID     150          150.00000    null         (6    ,19   )
ID           STEP         0.00000      null         (6    ,24   )
CONST_ID     1            1.00000      null         (6    ,26   )
ID           DRAW         0.00000      null         (6    ,31   )
L_BRACKET    (            0.00000      null         (6    ,33   )
CONST_ID     0            0.00000      null         (6    ,34   )
COMMA        ,            0.00000      null         (6    ,35   )
MINUS        -            0.00000      null         (6    ,37   )
ID           T            0.00000      null         (6    ,38   )
R_BRACKET    )            0.00000      null         (6    ,39   )
SEMICO       ;            0.00000      null         (6    ,40   )
ID           FOR          0.00000      null         (7    ,3    )
ID           T            0.00000      null         (7    ,5    )
ID           FROM         0.00000      null         (7    ,10   )
CONST_ID     0            0.00000      null         (7    ,12   )
ID           TO           0.00000      null         (7    ,15   )
CONST_ID     120          120.00000    null         (7    ,19   )
ID           STEP         0.00000      null         (7    ,24   )
CONST_ID     1            1.00000      null         (7    ,26   )
ID           DRAW         0.00000      null         (7    ,31   )
L_BRACKET    (            0.00000      null         (7    ,33   )
ID           T            0.00000      null         (7    ,34   )
COMMA        ,            0.00000      null         (7    ,35   )
MINUS        -            0.00000      null         (7    ,37   )
ID           T            0.00000      null         (7    ,38   )
R_BRACKET    )            0.00000      null         (7    ,39   )
SEMICO       ;            0.00000      null         (7    ,40   )
_________________________________________________________________
```
