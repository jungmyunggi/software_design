import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Pro {
	private JPanel proPanel;
	private JPanel statusPanel;
	private String status;
	public static int pros = 0;
	public Pro() {
		proPanel = new JPanel();
		proPanel.setLayout(new GridLayout(4, 1));

		// 상태변경 버튼
		JButton changeButton = new JButton("프로상태 변경");
		changeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeProStatus ();
			}
		});
		proPanel.add(changeButton);

		// 비거리 확인 버튼
		JButton distanceButton = new JButton("회원 비거리 확인");
		distanceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showdistance();
			}
		});
		proPanel.add(distanceButton);


		//로그아웃 버튼
		JButton logoutButton = new JButton("로그아웃");
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		proPanel.add(logoutButton);
		
		
		
	}

	public JPanel getProPanel() {
		return proPanel;
	}
	public static int getpros() {
		return pros;
	}
	// 상태 변경 화면 보여주기
	private void changeProStatus() {
		statusPanel = new JPanel();
		JLabel statusLabel = new JLabel("상태:");
		JTextField statusField = new JTextField(20);
		statusPanel.add(statusLabel);
		statusPanel.add(statusField);
		JOptionPane.showMessageDialog(null, statusPanel);
		
		status = statusField.getText();
		if(status.equals("강의중")) {	
			JOptionPane.showMessageDialog(null, "상태가 강의중으로 변경되었습니다");
			
			pros = 0;
		}else {
			JOptionPane.showMessageDialog(null, "상태가 자리비움으로 변경되었습니다");
			
			pros = 1;
		}
	}

	// 비거리 화인 화면 보여주기
	private void showdistance() {
		JOptionPane.showMessageDialog(null, "회원 비거리 확인 화면입니다.");
	}




}
