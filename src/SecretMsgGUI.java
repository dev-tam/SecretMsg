import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SecretMsgGUI extends JPanel {
	private JTextField txtKey;
	private JTextArea txtOut;
	private JTextArea txtIn;

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
		
		txtIn = new JTextArea();
		txtIn.setBounds(12, 18, 426, 96);
		add(txtIn);
		
		JPanel panel = new JPanel();
		panel.setBounds(186, 132, 252, 35);
		add(panel);
		
		JLabel lblKey = new JLabel("Key:");
		panel.add(lblKey);
		lblKey.setHorizontalAlignment(SwingConstants.RIGHT);
		
		txtKey = new JTextField();
		panel.add(txtKey);
		txtKey.setColumns(3);
		
		JButton btnEncodedecode = new JButton("ENCODE/DECODE");
		btnEncodedecode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get the message from txtIn
				String message = txtIn.getText();

				// get the key amount from txtkey
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
		txtOut.setBounds(12, 185, 426, 96);
		add(txtOut);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
