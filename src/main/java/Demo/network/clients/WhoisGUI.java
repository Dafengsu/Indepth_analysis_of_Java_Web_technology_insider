package Demo.network.clients;

import Demo.network.clients.Whois.SearchIn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;

import static Demo.network.clients.Whois.SearchFor.*;
import static Demo.network.clients.Whois.SearchIn.*;

/**
 * @description:
 * @author: su
 * @date: 2020/2/5
 */
public class WhoisGUI extends JFrame{
    private JTextField searchString = new JTextField(30);
    private JTextArea names = new JTextArea(15, 80);
    private JButton findButton = new JButton("Find");;
    private ButtonGroup searchIn = new ButtonGroup();
    private ButtonGroup searchFor = new ButtonGroup();
    private JCheckBox exactMatch = new JCheckBox("Exact Match", true);
    private JTextField chosenServer = new JTextField();
    private Whois server;
    public WhoisGUI(Whois whois) {
        super("Whois");
        this.server = whois;
        Container pane = this.getContentPane();

        Font f = new Font("Monospaced", Font.PLAIN, 12);
        names.setFont(f);
        names.setEditable(false);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1, 1, 10, 10));
        JScrollPane jsp = new JScrollPane(names);
        centerPanel.add(jsp);
        pane.add("Center", centerPanel);

        JPanel northPanel = new JPanel();
        JPanel northPanelTop = new JPanel();
        northPanelTop.setLayout(new FlowLayout(FlowLayout.LEFT));
        northPanelTop.add(new JLabel("Whois: "));
        northPanelTop.add("North", searchString);
        northPanelTop.add(exactMatch);
        northPanelTop.add(findButton);
        northPanel.setLayout(new BorderLayout(2,1));
        northPanel.add("North", northPanelTop);
        JPanel northPanelBottom = new JPanel();
        northPanelBottom.setLayout(new GridLayout(1,3,5,5));
        northPanelBottom.add(initRecordType());
        northPanelBottom.add(initSearchFields());
        northPanelBottom.add(initServerChoice());
        northPanel.add("Center", northPanelBottom);

        pane.add("North", northPanel);
        ActionListener al = new LookupNames();
        findButton.addActionListener(al);
        searchString.addActionListener(al);
    }

    private JPanel initRecordType() {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(6, 2, 5, 2));
        p.add(new JLabel("Search for:"));
        p.add(new JLabel(""));
        JRadioButton any = new JRadioButton("Any", true);
        any.setActionCommand("Any");
        searchFor.add(any);
        p.add(any);
        p.add(makeRadioButton("Network"));
        p.add(makeRadioButton("Person"));
        p.add(makeRadioButton("Host"));
        p.add(makeRadioButton("Domain"));
        p.add(makeRadioButton("Organization"));
        p.add(makeRadioButton("Group"));
        p.add(makeRadioButton("Gateway"));
        p.add(makeRadioButton("ASN"));
        return p;
    }

    private JRadioButton makeRadioButton(String label) {
        JRadioButton button = new JRadioButton(label, false);
        button.setActionCommand(label);
        searchFor.add(button);
        return button;
    }
    private JRadioButton makeSearchInRadioButton(String label) {
        JRadioButton button = new JRadioButton(label, false);
        button.setActionCommand(label);
        searchIn.add(button);
        return button;
    }

    private JPanel initSearchFields() {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(6, 1, 5, 2));
        p.add(new JLabel("Search In: "));
        JRadioButton all = new JRadioButton("All", true);
        all.setActionCommand("All");
        searchIn.add(all);
        p.add(all);
        p.add(makeSearchInRadioButton("Name"));
        p.add(makeSearchInRadioButton("Mailbox"));
        p.add(makeSearchInRadioButton("Handle"));

        return p;
    }

    private JPanel initServerChoice() {
        final JPanel p = new JPanel();
        p.setLayout(new GridLayout(6, 1, 5, 2));
        p.add(new JLabel("Search At: "));
        chosenServer.setText(server.getHost().getHostName());
        p.add(chosenServer);
        chosenServer.addActionListener(event -> {
            try {
                server = new Whois(chosenServer.getText());
            } catch (UnknownHostException ex) {
                JOptionPane.showMessageDialog(p,
                        ex.getMessage(), "Alert", JOptionPane.ERROR_MESSAGE);
            }
        });
        return p;
    }

    private class LookupNames implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            names.setText("");
            SwingWorker<String, Object> worker = new Lookup();
            worker.execute();
        }
    }

    private class Lookup extends SwingWorker<String, Object> {

        @Override
        protected String doInBackground() throws Exception {
            SearchIn group = ALL;
            Whois.SearchFor category = ANY;
            String searchForLabel = searchFor.getSelection().getActionCommand();
            String searchInLabel = searchIn.getSelection().getActionCommand();
            switch (searchInLabel) {
                case "Name":
                    group = NAME;
                    break;
                case "Mailbox":
                    group = MAILBOX;
                    break;
                case "Handle":
                    group = HANDLE;
                    break;
            }
            switch (searchForLabel) {
                case "Network":
                    category = NETWORK;
                    break;
                case "Person":
                    category = PERSON;
                    break;
                case "Host":
                    category = HOST;
                    break;
                case "Domain":
                    category = DOMAIN;
                    break;
                case "Organization":
                    category = ORGANIZATION;
                    break;
                case "Group":
                    category = GROUP;
                    break;
                case "Gateway":
                    category = GATEWAY;
                    break;
                case "ASN":
                    category = ASN;
                    break;
            }
            server.setHost(chosenServer.getText());
            return server.lookUpNames(searchString.getText(),
                    category, group, exactMatch.isSelected());
        }

        @Override
        protected void done() {
            try {
                names.setText(get());
                System.out.println("ceshi");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(WhoisGUI.this,
                        ex.getMessage(), "Lookup Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public static void main(String[] args) {
        try {
            Whois server = new Whois();
            WhoisGUI a = new WhoisGUI(server);
            a.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            a.pack();
            EventQueue.invokeLater(new FrameShower(a));
        } catch (UnknownHostException ex) {
            JOptionPane.showMessageDialog(null, "Could not locate default host "
                    + Whois.DEFAULT_HOST, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static class FrameShower implements Runnable {
        private final Frame frame;

        public FrameShower(Frame frame) {
            this.frame = frame;
        }

        @Override
        public void run() {
            frame.setVisible(true);
        }
    }
}
