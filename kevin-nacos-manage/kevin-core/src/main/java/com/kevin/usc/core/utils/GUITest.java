package com.kevin.usc.core.utils;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.swing.*;

/**
 * @author Ljob
 * @title: GUITest
 * @projectName Ecare
 * @description: TODO
 * @date 2019/12/2311:06
 */
public class GUITest {

    
    private boolean isLoop = true;

    
    JFrame rFrame = new JFrame("search LuckyDog");

    
    List<String> stuName = getStuName();

    
    JLabel name = new JLabel();

    
    JButton startBtn = new JButton("start");

    JButton endBtn = new JButton("end");

    
    Random rd = new Random();

    public void init() {
        
        JLabel jt = new JLabel("who is luck boy");
        
        jt.setHorizontalAlignment(SwingConstants.CENTER);
        
        jt.setFont(new java.awt.Font("who is luck boy", 1, 30));
        
        name.setHorizontalAlignment(SwingConstants.CENTER);
        
        rFrame.pack();
        
        rFrame.setSize(500, 300);

        
        rFrame.setVisible(true);
        
        setJFrameLcotionCenter(rFrame);

        
        Container p = this.rFrame.getContentPane();
        
        p.setLayout(null);
        jt.setBounds(125, 10, 250, 40);
        name.setBounds(125, 80, 250, 80);
        startBtn.setBounds(75, 200, 100, 25);
        endBtn.setBounds(325, 200, 100, 25);
        
        p.add(jt);
        
        p.add(name);
        
        p.add(endBtn);
        p.add(startBtn);

        
        rFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
        
        startBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    public void run() {
                        isLoop = true;
                        
                        while (isLoop) {
                            String luckyDog = getRandomName();
                            
                            name.setText(luckyDog);
                            
                            name.setFont(new java.awt.Font(luckyDog, 1, 50));
                            
                            name.setForeground(Color.red);
                            try {
                                
                                Thread.sleep(100);
                            }
                            catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            }).start();
            }
        });
        
        endBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                isLoop = false;
                
                String luckyDog = getRandomName();
                
                name.setText(luckyDog);
                
                name.setFont(new java.awt.Font(luckyDog, 1, 50));
                
                name.setForeground(Color.red);
            }

        });
    }

    public void setJFrameLcotionCenter(JFrame jf) {
        int jfw = jf.getWidth();
        int jfh = jf.getHeight();
        Toolkit t1 = Toolkit.getDefaultToolkit();
        Dimension screen = t1.getScreenSize();
        int winw = screen.width;
        int winh = screen.height;
        jf.setLocation((winw - jfw) / 2, (winh - jfh) / 2);
    }

    
    public String getRandomName() {
        int a = 0;
        
        a = rd.nextInt(stuName.size());
        
        

        return stuName.get(a);
    }

    public static void main(String[] args) {
        GUITest rn = new GUITest();
        rn.init();
    }

    public ArrayList<String> getStuName() {
        try {
            Set<String> stuName = new HashSet<>();
            BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(new File("C:/Users/Ljob/Desktop/something/studentName.txt")), "UTF-8"));
            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {
                stuName.add(lineTxt);
            }
            br.close();
            return new ArrayList<String>(stuName);
        }
        catch (Exception e) {
            System.err.println("read errors :" + e);
        }
        return null;
    }
}
