import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main{

       private static JottoModel model;

    public static void main(String[] args){
        JFrame frame = new JFrame("Jotto");

        model = new JottoModel();
        GameView gameView = new GameView(model);
        TableView tableView = new TableView(model);
        HintsView wordView = new HintsView(model);
        model.addView(gameView);
        model.addView(tableView);
        model.addView(wordView);
        model.init();


        // create the window
        JPanel p = new JPanel(new BorderLayout());
        frame.getContentPane().add(p);
        p.add(gameView, BorderLayout.PAGE_START);
        p.add(tableView, BorderLayout.PAGE_END);
        p.add(wordView, BorderLayout.CENTER);
//        p.add(letterHints, BorderLayout.CENTER);

//      Set up menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem menuItem = new JMenuItem("New Game");
        menuItem.setMnemonic(KeyEvent.VK_N);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));


        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.init();
            }
        });
        JMenuItem setWord = new JMenuItem("Set Target Word");
        setWord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String word = JOptionPane.showInputDialog(null, "Enter the target word");
                model.setTargetString(word);

            }
        });


        JMenu menuDiff = new JMenu("Difficulty");
        final JCheckBoxMenuItem menuItemDiffEasy = new  JCheckBoxMenuItem("Easy", true);
        final JCheckBoxMenuItem menuItemDiffMed = new  JCheckBoxMenuItem("Medium", false);
        final JCheckBoxMenuItem menuItemDiffHard = new  JCheckBoxMenuItem("Hard", false);
        final JCheckBoxMenuItem menuItemDiffAny = new  JCheckBoxMenuItem("Any", false);


        menuItemDiffEasy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setDifficulty(0);
                menuItemDiffMed.setState(false);
                menuItemDiffHard.setState(false);
                menuItemDiffAny.setState(false);
            }
        });
        menuItemDiffMed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setDifficulty(1);
                menuItemDiffEasy.setState(false);
                menuItemDiffHard.setState(false);
                menuItemDiffAny.setState(false);
            }
        });
        menuItemDiffHard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setDifficulty(2);
                menuItemDiffMed.setState(false);
                menuItemDiffEasy.setState(false);
                menuItemDiffAny.setState(false);
            }
        });
        menuItemDiffAny.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setDifficulty(3);
                menuItemDiffMed.setState(false);
                menuItemDiffHard.setState(false);
                menuItemDiffEasy.setState(false);
            }
        });

        menu.add(menuItem);
        menu.add(setWord);

        menuDiff.add(menuItemDiffEasy);
        menuDiff.add(menuItemDiffMed);
        menuDiff.add(menuItemDiffHard);
        menuDiff.add(menuItemDiffAny);

        menuBar.add(menu);
        menuBar.add(menuDiff);
        frame.setJMenuBar(menuBar);


        p.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                Component comp = (Component) e.getSource();
                model.setDimensions(comp.getSize());
            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });

        frame.setPreferredSize(new Dimension(625,600));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(625, 600));
        frame.setVisible(true);


    }


}