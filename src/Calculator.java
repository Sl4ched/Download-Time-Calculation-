import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends WindowAdapter implements ActionListener {

    final JFrame f;
    final JPanel mainPanel;
    final JLabel label, label2, labelOfAnswer;
    final JTextField tx1, tx2;
    final JButton bt, btArrow1, btArrow2;
    final JPopupMenu popupmenu1, popupmenu2;
    final JMenuItem gb, mb, kb, mbps, mb2;
    int x = -45;
    int y = -40;
    int whichTop, whichBelow = -1;
    float speed, size,hours, minute, second;
    String secondS, minuteS, hoursS;

    Calculator() {

        ImageIcon img = new ImageIcon("1.png");

        Font font1 = new Font("Courier", Font.PLAIN, 16);
        Font font2 = new Font("Courier", Font.PLAIN, 20);
        Font font3 = new Font("Courier", Font.PLAIN, 14);
        Font font4 = new Font("Courier", Font.PLAIN, 9);
        Font font5 = new Font("Courier", Font.PLAIN, 30);

        gb = new JMenuItem("GB");
        gb.addActionListener(this);
        mb = new JMenuItem("MB");
        mb.addActionListener(this);
        kb = new JMenuItem("KB");
        kb.addActionListener(this);

        mbps = new JMenuItem("MBPS");
        mbps.addActionListener(this);
        mb2 = new JMenuItem("MB");
        mb2.addActionListener(this);

        popupmenu1 = new JPopupMenu("Edit");
        popupmenu1.add(mbps);
        popupmenu1.add(mb2);

        popupmenu2 = new JPopupMenu("Edit");
        popupmenu2.add(gb);
        popupmenu2.add(mb);
        popupmenu2.add(kb);

        bt = new JButton("Click To Calculate Time");
        bt.setBounds(110+x, 220+y, 220, 40);
        bt.setBackground(Color.BLACK);
        bt.setFont(font3);
        bt.setForeground(Color.WHITE);
        bt.setFocusable(false);
        bt.addActionListener(this);

        labelOfAnswer = new JLabel("");
        labelOfAnswer.setFont(font5);
        labelOfAnswer.setBounds(160+x, 285+y, 300, 30);
        labelOfAnswer.setForeground(Color.GREEN);

        //

        label = new JLabel("Type Current Speed :");
        label.setFont(font1);
        label.setBounds(55+x, 80+y, 300, 30);
        label.setForeground(Color.BLACK);

        tx1 = new JTextField();
        tx1.setBounds(220+x, 80+y, 100, 30);

        btArrow1 = new JButton("...");
        btArrow1.setBounds(320+x, 80+y, 60, 30);
        btArrow1.setBackground(Color.BLACK);
        btArrow1.setForeground(Color.WHITE);
        btArrow1.setFont(font4);
        btArrow1.setFocusable(false);
        btArrow1.addActionListener(this);

        //

        label2 = new JLabel("Type File Size :");
        label2.setFont(font2);
        label2.setBounds(55+x, 160+y, 300, 30);
        label2.setForeground(Color.BLACK);

        tx2 = new JTextField();
        tx2.setBounds(220+x, 160+y, 100, 30);

        btArrow2 = new JButton("...");
        btArrow2.setBounds(320+x, 160+y, 60, 30);
        btArrow2.setBackground(Color.BLACK);
        btArrow2.setForeground(Color.WHITE);
        btArrow2.setFocusable(false);
        btArrow2.setFont(font4);
        btArrow2.addActionListener(this);

        mainPanel = new JPanel();
        mainPanel.setVisible(true);
        mainPanel.setSize(370, 360);
        mainPanel.setBackground(Color.gray);
        mainPanel.setLayout(null);
        mainPanel.add(label);
        mainPanel.add(tx1);
        mainPanel.add(label2);
        mainPanel.add(tx2);
        mainPanel.add(bt);
        mainPanel.add(btArrow1);
        mainPanel.add(btArrow2);
        mainPanel.add(labelOfAnswer);

        f = new JFrame("Speed Time Calculator");

        f.setIconImage(img.getImage());
        f.setVisible(true);
        f.setSize(370, 360);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.addWindowListener(this);
        f.add(mainPanel);
        f.getRootPane().setDefaultButton(bt);

    }

    @Override
    public void windowClosing(WindowEvent e) {

        int a = JOptionPane.showConfirmDialog(null, "Are You Sure");

        if (a == 0) f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        else f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == bt) {

            if (isNumberAllDigits(tx1.getText(), tx2.getText()) && !tx1.getText().equals("") && !tx2.getText().equals("") && whichTop != -1 && whichBelow != -1) {

                switch (whichTop) {

                    case 1://mbps

                        speed = Float.parseFloat(tx1.getText()) / (float) 8.3892;

                        switch (whichBelow) {
                            case 1://gb
                                size = Float.parseFloat(tx2.getText()) * 1024;
                                break;
                            case 2://mb
                                size = Float.parseFloat(tx2.getText());
                                break;
                            case 3://kb
                                size = Float.parseFloat(tx2.getText()) / 1024;
                                break;
                        }
                        break;
                    case 2://mb

                        speed = Float.parseFloat(tx1.getText());

                        switch (whichBelow) {
                            case 1://gb
                                size = Float.parseFloat(tx2.getText()) * 1024;
                                break;
                            case 2://mb
                                size = Float.parseFloat(tx2.getText());
                                break;
                            case 3://kb
                                size = Float.parseFloat(tx2.getText()) / 1024;
                                break;
                        }
                        break;
                    default:
                        break;
                }

                float totalSecond;

                totalSecond = size / speed;

                while(totalSecond >= 60) {
                minute++;
                totalSecond -= 60;
                }

                second = totalSecond ;

                while(minute >= 60) {
                    hours++;
                    minute -= 60;
                }

                hoursS = Integer.toString((int)hours);
                minuteS = Integer.toString((int)minute);
                secondS = Integer.toString((int)second);

                if (hoursS.length() <= 1) hoursS = 0 + hoursS;
                if (minuteS.length() <= 1) minuteS = 0 + minuteS;
                if (secondS.length() <= 1) secondS = 0 + secondS;

                String lastTime = hoursS + "." + minuteS + "." + secondS;
                minute = 0;
                second = 0;
                hours = 0;
                labelOfAnswer.setText(lastTime);

            }

        } else if (e.getSource() == btArrow1) {

            popupmenu1.show(mainPanel, btArrow1.getX(), btArrow1.getY());

        } else if (e.getSource() == btArrow2) {

            popupmenu2.show(mainPanel, btArrow2.getX(), btArrow2.getY());

        } else if (e.getSource() == gb) {
            btArrow2.setText("GB");
            whichBelow = 1;
        }
        else if (e.getSource() == mb) {
            btArrow2.setText("MB");
            whichBelow = 2;
        }
        else if (e.getSource() == kb) {
            btArrow2.setText("KB");
            whichBelow = 3;
        }
        else if (e.getSource() == mbps) {
            btArrow1.setText("MBPS");
            whichTop = 1;
        }
        else if (e.getSource() == mb2) {
            btArrow1.setText("MB");
            whichTop = 2;
        }

    }

    boolean isNumberAllDigits(String a, String b) {

        return num(a) + num(b) == a.length() + b.length();

    }

    int num(String aAndB) {

        int number = 0;

        for (int i = 0; i < aAndB.length(); i++) {

            for (int j = 0; j < 10; j++) {

                String temp1 = Integer.toString(j);
                char temp2 = temp1.charAt(0);

                if (aAndB.charAt(i) == temp2) { //this checks is there number or not
                    number++;
                    break;
                } else if (aAndB.charAt(i) == '.') {
                    number++;
                    break;
                }

            }

        }

        return number;
    }

}
