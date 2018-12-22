package UI_main;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.border.LineBorder;

import Parser.Parser;
import Semantics.SemanticAnalyzer;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class WinGUI extends JFrame
{
    private static final long serialVersionUID = 5291359850874977375L;
    private JPanel contentPane;
    private JPanel panel;
    private JButton btnRe;
    private TColor co = new TColor(0,0,0);
    public SemanticAnalyzer sa = new SemanticAnalyzer(this);



    private class collection  //存入ArrayList的类
    {
        double x; double y;
        collection(double x2,double y2,TColor color)
        {
            x = x2; y = y2;
        }
    }

    private ArrayList<collection> drawInfo = new ArrayList<collection>();
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JRadioButton radioButton;
    private JRadioButton radioButton_1;
    private JRadioButton radioButton_2;
    private JRadioButton radioButton_3;

    private void setText()  //该函数实现将文件里的数据一行行输入GUI中
    {
        try {
            FileReader fr = new FileReader("src\\ScannerTest.txt");
            java.util.Scanner input=new java.util.Scanner(fr);
            String informationInFile;
            while(input.hasNext() == true)
            {
                informationInFile = input.nextLine();
                textArea.append(informationInFile + "\r\n");
            }
            input.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,"获取源文件信息失败！");}
    }

    //给外界提供的两个接口
    /////////////////////
    // @func: DrawPixel
    // @param: double x, double y, t_color color
    //
    /////////////////////
    public void DrawPixel(double x,double y,TColor color)  //在图形里绘制一个点,实际上仅仅是将三个值存入我的容器里
    {

        drawInfo.add(new collection(x,y,color));
    }
    public void ShowMessage(int flag,String msg)  // 常规类信息 =0 ， 错误类信息 =1。很简单的一个Panel
    {
        JOptionPane.showMessageDialog(null,"msg");
    }



    public WinGUI()
    {
        initCopmonents();
        createEvents();
    }




    void initCopmonents()
    {
        setTitle("函数绘图语言GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 486, 589);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        radioButton = new JRadioButton("\u9ED1");
        buttonGroup.add(radioButton);
        radioButton.setSelected(true);
        radioButton.setBounds(416, 155, 53, 23);
        contentPane.add(radioButton);

        panel = new JPanel() //这里有初始的默认文件里存有的数据构成的巴拉巴拉。
        {
            private static final long serialVersionUID = 1L;
            @Override
            public void paint(Graphics g)
            {
                super.paint(g);  //调用父类的paint方法
                collection c = null;
                for(int i = 0 ; i < drawInfo.size() ;i++)//
                {
                    c = drawInfo.get(i);
                    g.setColor(new Color(co.red,co.green,co.blue));
                    g.drawRect((int)c.x,(int)(400 - c.y),1,1);// *由于Semantic分析传参为Double, 只能将此处进行一次强制转换
                }
            }
        };

        panel.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel.setBounds(10, 134, 400, 400);
        contentPane.add(panel);

        btnRe = new JButton("Go");
        btnRe.setFont(new Font("宋体", Font.PLAIN, 17));
        btnRe.setBounds(420, 504, 52, 48);
        contentPane.add(btnRe);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 452, 114);
        contentPane.add(scrollPane);

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        setText();
        scrollPane.setViewportView(textArea);

        radioButton_1 = new JRadioButton("\u7EA2");
        buttonGroup.add(radioButton_1);
        radioButton_1.setBounds(416, 197, 53, 23);
        contentPane.add(radioButton_1);

        radioButton_2 = new JRadioButton("\u7EFF");
        buttonGroup.add(radioButton_2);
        radioButton_2.setBounds(416, 242, 53, 23);
        contentPane.add(radioButton_2);

        radioButton_3 = new JRadioButton("\u84DD");
        buttonGroup.add(radioButton_3);
        radioButton_3.setBounds(416, 283, 53, 23);
        contentPane.add(radioButton_3);

//        JLabel lblNewLabel = new JLabel();
//        lblNewLabel.setIcon(new ImageIcon(WinGUI.class.getResource("src\\back.jpg")));
//        lblNewLabel.setBounds(0, 0, 487, 589);
//        contentPane.add(lblNewLabel);
    }



    void createEvents()
    {
        btnRe.addActionListener(new ActionListener() {    //点击Go之后的操作
            public void actionPerformed(ActionEvent e)
            {
                //首先将文件存回Scanner.txt中
                try {
                    File file = new File("src\\ScannerTest.txt");
                    Writer out = new FileWriter(file);
                    String info = textArea.getText();
                    out.write(info);
                    out.close();
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null,"信息存入源文件失败！");
                }
                //后续操作就是调用语法分析器，得到一个ArrayList的集合，然后绘制点就完事了
                drawInfo.clear();	//清除所有的点准备下次绘图
                sa.theScanner.initScanner("src\\ScannerTest.txt");
                sa.theParser = new Parser(sa.theScanner);
                sa.theParser.run();
                sa.run();
                panel.repaint();	//根据可能被更改的文件重新绘制

            }
        });

        radioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                co = new TColor(0,0,0);
            }
        });

        radioButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                co = new TColor(255,0,0);
            }
        });

        radioButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                co = new TColor(0,255,0);
            }
        });

        radioButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                co = new TColor(0,0,255);
            }
        });

    }
}
