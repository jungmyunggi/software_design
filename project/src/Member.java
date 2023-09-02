import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Member {
	private JPanel memberPanel;
	public static int reservation =0;
	public Member() {
		memberPanel = new JPanel();
		memberPanel.setLayout(new GridLayout(4, 1));

		// 예약 상황 버튼
		JButton reservationButton = new JButton("타석 예약");
		reservationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showReservationStatus();
			}
		});
		memberPanel.add(reservationButton);

		// 프로 상태 버튼
		JButton proStatusButton = new JButton("프로 상태");
		proStatusButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showProStatus();
			}
		});
		memberPanel.add(proStatusButton);

		
		JButton checkButton = new JButton("예약 확인");
		checkButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showcheck();
			}
		});
		memberPanel.add(checkButton);
		//로그아웃 버튼
		JButton logoutButton = new JButton("로그아웃");
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		memberPanel.add(logoutButton);

	}

	public JPanel getMemberPanel() {
		return memberPanel;
	}

	// 타석 예약 화면 보여주기
	private void showReservationStatus() {
		reservation =1;
		JOptionPane.showMessageDialog(null, "예약이 완료되었습니다.");
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

	// 예약 상태 화면 보여주기
	private void showcheck() {
		if(reservation == 1) {
			JOptionPane.showMessageDialog(null, "예약 완료.");
		}else {JOptionPane.showMessageDialog(null, "예약 미완료.");}
	}





}

