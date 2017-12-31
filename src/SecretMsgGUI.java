import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import java.awt.FlowLayout;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class SecretMsgGUI extends JPanel {
	private JTextField txtKey;
	private JTextArea txtOut;
	private JTextArea txtIn;
	private JSlider slider;

	public String encode(String message, int k){
		String out = "";
		char key = (char)k;
		
		for(int x=0; x < message.length(); x++) {
			char in = message.charAt(x);
			if (in >= 'A' && in <= 'Z') {
				in += key;
				if (in > 'Z') 
					in -= 26;
					if (in < 'A') 
						in += 26;	
			}else if (in >= 'a' && in <= 'z') {
				in += key;
				if (in > 'z') 
					in -= 26;
				if (in < 'a') 
					in += 26;				
			}
			
			out += in;
		}
		return out;
	}

	public SecretMsgGUI() {
		setLayout(null);
		
		txtKey = new JTextField();
		txtKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtKey.setText("0");
		
		txtKey.setColumns(3);
		
		txtIn = new JTextArea();
		txtIn.setBounds(12, 11, 426, 96);
		add(txtIn);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 118, 426, 63);
		add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.add(txtKey);
		
		slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				txtKey.setText("" + slider.getValue());
			}
		});
		slider.setMajorTickSpacing(13);
		slider.setMinorTickSpacing(1);
		slider.setMinimum(-13);
		slider.setMaximum(13);
		slider.setValue(0);
		panel.add(slider);
		
		JLabel lblKey = new JLabel("Key:");
		panel.add(lblKey);
		lblKey.setHorizontalAlignment(SwingConstants.RIGHT);
		
		
		
		JButton btnEncodedecode = new JButton("ENCODE/DECODE");
		btnEncodedecode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get the message from txtIn
				String message = txtIn.getText();

				// get the key amount from txtKey
				int key = Integer.parseInt(txtKey.getText());
				
				// encode that message with that key
				String output = encode(message, key);
				
				// show the message in txtOut
				txtOut.setText(output);
			}
		});
		panel.add(btnEncodedecode);
		
		txtOut = new JTextArea();
		txtOut.setColumns(10);
		txtOut.setBounds(12, 192, 426, 96);
		add(txtOut);
		setPreferredSize(new Dimension(450, 300));
	}

	public static void main(String[] args) {
		// set up a window JFrame for the app
		JFrame frame = new JFrame("jTams Secret Msg");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// add the encoder panel to the frame
		frame.getContentPane().add(new SecretMsgGUI());
		
		// prepare and show the frame
		frame.pack();
		frame.setVisible(true);

	}
}
