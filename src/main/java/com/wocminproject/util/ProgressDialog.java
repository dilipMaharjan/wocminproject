/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wocminproject.util;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

/**
 *
 * @author dmaharjan
 */
public class ProgressDialog {

    private JProgressBar progressBar;

    public ProgressDialog(JFrame jFrame) {
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        jFrame.add(progressBar);
    }

}
