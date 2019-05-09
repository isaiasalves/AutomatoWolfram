import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.Timer;

public class WolframCA extends JPanel {

    //final int[] ruleSet = {30, 45, 50, 57, 62, 70, 73, 75, 86, 89, 90, 99,
     //   101, 105, 109, 110, 124, 129, 133, 135, 137, 139, 141, 164, 170, 232, 54};
    byte[][] cells;
    int rule = 0;

    public WolframCA(int cod) {
        Dimension dim = new Dimension(900, 450);
        setPreferredSize(dim);
        setBackground(Color.white);
        setFont(new Font("SansSerif", Font.BOLD, 28));
        rule = cod;

        cells = new byte[dim.height][dim.width];
        cells[0][dim.width / 2] = 1;

    }

    private byte rules(int lhs, int mid, int rhs) {
        //int idx = (lhs << 2 | mid << 1 | rhs);
        ///return (byte) (ruleSet[rule] >> idx & 1);
        boolean next, actual, prev;
        int ret = 0;

        if (lhs == 1) {
            prev = true;
        } else {
            prev = false;
        }

        if (mid == 1) {
            actual = true;
        } else {
            actual = false;
        }

        if (rhs == 1) {
            next = true;
        } else {
            next = false;
        }

        switch (rule) {
            case 30:
                if (prev && actual && next) {           //111
                    ret = 0;
                } else if (prev && actual && !next) {   //110
                    ret = 0;
                } else if (prev && !actual && next) {   //101
                    ret = 1;
                } else if (prev && !actual && !next) {  //100
                    ret = 1;
                } else if (!prev && actual && next) {   //011
                    ret = 0;
                } else if (!prev && actual && !next) {  //010
                    ret = 1;
                } else if (!prev && !actual && next) {  //001
                    ret = 1;
                } else if (!prev && !actual && !next) { //000
                    ret = 0;
                }
                break;
            case 90:
               if (prev && actual && next) {           //111
                    ret = 0;
                } else if (prev && actual && !next) {   //110
                    ret = 1;
                } else if (prev && !actual && next) {   //101
                    ret = 0;
                } else if (prev && !actual && !next) {  //100
                    ret = 1;
                } else if (!prev && actual && next) {   //011
                    ret = 1;
                } else if (!prev && actual && !next) {  //010
                    ret = 0;
                } else if (!prev && !actual && next) {  //001
                    ret = 1;
                } else if (!prev && !actual && !next) { //000
                    ret = 0;
                }
                break;
            case 110:
                if (prev && actual && next) {           //111
                    ret = 0;
                } else if (prev && actual && !next) {   //110
                    ret = 1;
                } else if (prev && !actual && next) {   //101
                    ret = 1;
                } else if (prev && !actual && !next) {  //100
                    ret = 0;
                } else if (!prev && actual && next) {   //011
                    ret = 1;
                } else if (!prev && actual && !next) {  //010
                    ret = 1;
                } else if (!prev && !actual && next) {  //001
                    ret = 1;
                } else if (!prev && !actual && !next) { //000
                    ret = 0;
                }
                break;
        }
       
        return (byte) ret;
    }

    void drawCa(Graphics2D g) {
        g.setColor(Color.black);
        for (int r = 0; r < cells.length - 1; r++) { //quantidade de iterações
            for (int c = 1; c < cells[r].length - 1; c++) {
                byte lhs = cells[r][c - 1];
                byte mid = cells[r][c];
                byte rhs = cells[r][c + 1];
                cells[r + 1][c] = rules(lhs, mid, rhs); // next generation
                if (cells[r][c] == 1) {
                    g.fillRect(c, r, 1, 1);
                }
            }
        }
    }

   
    void drawLegend(Graphics2D g) {
        String s = String.valueOf(rule);
        int sw = g.getFontMetrics().stringWidth(s);

        g.setColor(Color.white);
        g.fillRect(16, 5, 55, 30);

        g.setColor(Color.darkGray);
        g.drawString(s, 16 + (55 - sw) / 2, 30);
    }
    

    @Override
    public void paintComponent(Graphics gg) {
        super.paintComponent(gg);
        Graphics2D g = (Graphics2D) gg;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawCa(g);
        drawLegend(g);
    }

}
