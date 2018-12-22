package com;

import UI_main.WinGUI;

public class Main
{
    public static void main(String[] args)
    {
        //ErrLog.restart(true);
        WinGUI wg = new WinGUI();
        wg.sa.theParser.run();
        wg.sa.run();
        wg.setVisible(true);
        //ErrLog.restart(false);
    }
}
