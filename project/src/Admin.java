import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Admin {
	private JPanel adminPanel;
	private JPanel reservationPanel;
	private JButton[] seatButtons;
	private boolean[] seatStatus;

	public Admin() {
		adminPanel = new JPanel();
		adminPanel.setLayout(new GridLayout(4, 1));

		// 예약 상황 버튼
		JButton reservationButton = new JButton("예약 상황");
		reservationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showReservationStatus();
			}
		});
		adminPanel.add(reservationButton);

		// 프로 상태 버튼
		JButton proStatusButton = new JButton("프로 상태");
		proStatusButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showProStatus();
			}
		});
		adminPanel.add(proStatusButton);

		// 회원 정보 버튼
		JButton memberInfoButton = new JButton("회원 정보");
		memberInfoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showMemberInfo();
			}
		});
		adminPanel.add(memberInfoButton);
		//회원등록 버튼
		JButton memberadd = new JButton("회원 추가");
		memberadd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addMember();
			}
		});
		adminPanel.add(memberadd);
		//로그아웃 버튼
		JButton logoutButton = new JButton("로그아웃");
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		adminPanel.add(logoutButton);
		// 예약 타석 패널 초기화
		seatButtons = new JButton[9];
		seatStatus = new boolean[9];
		reservationPanel = new JPanel();
		reservationPanel.setLayout(new GridLayout(3, 3));

		for (int i = 0; i < 9; i++) {
			seatButtons[i] = new JButton(String.valueOf(i + 1));
			seatButtons[i].addActionListener(new SeatButtonListener(i));
			reservationPanel.add(seatButtons[i]);
			seatStatus[i] = false;
		}
	}

	public JPanel getAdminPanel() {
		return adminPanel;
	}

	// 예약 상황 화면 보여주기
	private void showReservationStatus() {
		// 예약 타석 패널을 보여줌
		JOptionPane.showMessageDialog(null, reservationPanel);
	}

	// 프로 상태 화면 보여주기
	private void showProStatus() {
		int pros = Pro.getpros();
		if(pros == 0) {
			JOptionPane.showMessageDialog(null, "프로는 강의중 입니다.");
		}else {
			JOptionPane.showMessageDialog(null, "프로는 자리비움 입니다.");
		}
	}

	// 회원 정보 화면 보여주기
	private void showMemberInfo() {
		JOptionPane.showMessageDialog(null, "회원 정보 화면입니다.");
	}

	// 타석 버튼 리스너
	private class SeatButtonListener implements ActionListener {
		private int seatIndex;

		public SeatButtonListener(int index) {
			seatIndex = index;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (seatStatus[seatIndex]) {
				// 타석이 사용중인 경우
				seatButtons[seatIndex].setBackground(Color.BLUE);
				seatStatus[seatIndex] = false;
			} else {
				// 타석이 사용중이 아닌 경우
				seatButtons[seatIndex].setBackground(Color.GRAY);
				seatStatus[seatIndex] = true;
			}
		}
	}
	private void addMember() {
		JFrame frame = new JFrame("회원 추가");
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

		JLabel idLabel = new JLabel("아이디:");
		JTextField idField = new JTextField(20);
		panel.add(idLabel);
		panel.add(idField);

		JLabel passwordLabel = new JLabel("비밀번호:");
		JPasswordField passwordField = new JPasswordField(20);
		panel.add(passwordLabel);
		panel.add(passwordField);

		JLabel confirmPasswordLabel = new JLabel("비밀번호 확인:");
		JPasswordField confirmPasswordField = new JPasswordField(20);
		panel.add(confirmPasswordLabel);
		panel.add(confirmPasswordField);

		JLabel nameLabel = new JLabel("이름:");
		JTextField nameField = new JTextField(20);
		panel.add(nameLabel);
		panel.add(nameField);

		JLabel phoneNumberLabel = new JLabel("전화번호:");
		JTextField phoneNumberField = new JTextField(20);
		panel.add(phoneNumberLabel);
		panel.add(phoneNumberField);

		JLabel addressLabel = new JLabel("거주지:");
		JTextField addressField = new JTextField(20);
		panel.add(addressLabel);
		panel.add(addressField);

		JButton addButton = new JButton("추가");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = idField.getText();
				String password = new String(passwordField.getPassword());
				String confirmPassword = new String(confirmPasswordField.getPassword());
				String name = nameField.getText();
				String phoneNumber = phoneNumberField.getText();
				String address = addressField.getText();

				if (!password.equals(confirmPassword)) {
					JOptionPane.showMessageDialog(frame, "비밀번호와 비밀번호 확인이 일치하지 않습니다.");
				} else if (id.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()
						|| name.isEmpty() || phoneNumber.isEmpty() || address.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "모든 필드를 입력해주세요.");
				} else {
					saveMember(id, password, name, phoneNumber, address);
					JOptionPane.showMessageDialog(frame, "회원 추가가 완료되었습니다.");
					frame.dispose();
				}
			}
		});
		panel.add(addButton);

		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

	private void saveMember(String id, String password, String name, String phoneNumber, String address) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("login.txt", true))) {
			writer.write(id + "," + password + "," +"회원");
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}