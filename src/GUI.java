import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Font;
import java.awt.Insets;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.util.LinkedList;
import java.util.Queue;

public class GUI extends JPanel implements Runnable {
    volatile Queue<String> input_messages;
    volatile JTextArea messages_field;
    JTextField input_field;
    String user;

    GUI(String user) {
        this.user = user;

        this.setLayout(new GridBagLayout());
        this.input_messages = new LinkedList<String>();
    }

    public void display_message(String msg, String user) {
        LOG.debug("Message added to messages_field, message = " + msg);
        this.messages_field.append(user + ": " + msg + "\n");
    }

    public String get_message() {
        if (this.input_messages.isEmpty()) return null;
        return this.input_messages.remove();
    }

    public void run() {
        Font font = new Font(Config.FONT_NAME, Font.PLAIN, Config.FONT_SIZE);

        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;

        this.messages_field = new JTextArea(25, 55);
        this.messages_field.setWrapStyleWord(true);
        this.messages_field.setEditable(false);
        this.messages_field.setFont(font);

        JScrollPane scroll = new JScrollPane(this.messages_field);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scroll, c);
        
        c.gridwidth = 1;
        c.gridy = 1;

        this.input_field = new JTextField(50);
        this.input_field.setFont(font);
        this.input_field.setMargin(new Insets(0, 20, 0, 0));
        this.input_field.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ev) {
                if (ev.getKeyCode() == KeyEvent.VK_ENTER)
                    send_message();
            }
        });
        this.add(this.input_field, c);

        c.gridx = 1;

        JButton submit_button = new JButton("Enviar");
        submit_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                send_message();
            }
        });
        this.add(submit_button, c);

        JFrame f = new JFrame("Chat");
        f.setContentPane(this);
        f.pack();

        f.setVisible(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.input_field.requestFocus();
    }

    private void send_message() {
        String message = this.input_field.getText();
        if (message.length() > 0) {
            this.display_message(Translator.clean(message), this.user);

            this.input_messages.add(message);
            LOG.debug("Message ready to be sent, message = " + message);

            this.input_field.setText("");
        }
    }
}
