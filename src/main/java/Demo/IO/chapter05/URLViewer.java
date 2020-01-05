package Demo.IO.chapter05;

import Demo.IO.chapter02.JStreamedTextArea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/5 20:13
 */
public class URLViewer extends JFrame implements ActionListener {
    private JTextField theURL = new JTextField();
    private JButton loadButton = new JButton("Load");
    private JStreamedTextArea thisDisplay = new JStreamedTextArea(60,72);

    public static void main(String[] args) {
        final URLViewer me = new URLViewer();
        SwingUtilities.invokeLater(me::show);
    }
    public URLViewer() {
        super("URL Viewer");
        Container contentPane = getContentPane();
        contentPane.add(BorderLayout.NORTH, theURL);
        JScrollPane pane = new JScrollPane(thisDisplay);
        contentPane.add(BorderLayout.CENTER, pane);
        Panel south = new Panel();
        south.add(loadButton);
        contentPane.add(BorderLayout.SOUTH, south);
        theURL.addActionListener(this);
        loadButton.addActionListener(this);
        setLocation(50, 50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            URL u = new URL(theURL.getText());
            InputStream in = u.openStream();
            OutputStream out = thisDisplay.getOutputStream();
            thisDisplay.setText("");
            for (int c = in.read(); c != -1; c = in.read()) {
                out.write(c);
            }
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
