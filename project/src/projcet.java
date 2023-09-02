import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class projcet extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public projcet() {
        super("골프 연습장 관리 프로그램");

        // 로그인 체크를 위한 텍스트 파일 경로
        String loginFilePath = "login.txt";

        // 로그인 패널
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());

        // 아이디 입력 필드
        JLabel usernameLabel = new JLabel("아이디:");
        usernameField = new JTextField(20);
        loginPanel.add(usernameLabel, createGridBagConstraints(0, 0));
        loginPanel.add(usernameField, createGridBagConstraints(1, 0));

        // 비밀번호 입력 필드
        JLabel passwordLabel = new JLabel("비밀번호:");
        passwordField = new JPasswordField(20);
        loginPanel.add(passwordLabel, createGridBagConstraints(0, 1));
        loginPanel.add(passwordField, createGridBagConstraints(1, 1));

        // 로그인 버튼
        JButton loginButton = new JButton("로그인");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // 로그인 검증
                String role = verifyLogin(loginFilePath, username, password);
                if (role != null) {
                    JOptionPane.showMessageDialog(null, "로그인 성공! 권한: " + role);
                    // TODO: 다음 화면으로 이동하는 코드 작성
                    switch (role) {
                        case "관리자":
                            showAdminScreen();
                            break;
                        case "프로":
                            showProScreen();
                            break;
                        case "회원":
                            showMemberScreen();
                            break;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "로그인 실패. 다시 확인해주세요!");
                }
            }
        });
        loginPanel.add(loginButton, createGridBagConstraints(1, 2));

        // 프레임에 로그인 패널 추가
       getContentPane().add(loginPanel);

        // 프레임 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null); // 화면 중앙에 표시
        setVisible(true);
    }

    // GridBagConstraints 생성 메소드
    private GridBagConstraints createGridBagConstraints(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(5, 5, 5, 5);
        return gbc;
    }

    // 로그인 검증 함수
    public String verifyLogin(String filePath, String username, String password) {
        File file = new File(filePath);
        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] loginInfo = line.split(",");

                if (loginInfo.length == 3 && loginInfo[0].equals(username) && loginInfo[1].equals(password)) {
                    fileScanner.close();
                    return loginInfo[2];  // 권한 반환
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;  // 일치하는 아이디와 비밀번호가 없거나 권한 정보가 없을 경우 null 반환
    }

    // 관리자 화면 표시 함수
    private void showAdminScreen() {
        getContentPane().removeAll();
        getContentPane().setLayout(new FlowLayout());

        // AdminScreen 클래스의 인스턴스 생성
        Admin adminScreen = new Admin();

        // 프레임에 AdminScreen 패널 추가
       getContentPane().add(adminScreen.getAdminPanel());

        // 프레임 새로고침
        revalidate();
        repaint();
    }

    // 프로 화면 표시 함수
    private void showProScreen() {
    
    	getContentPane().removeAll();
        getContentPane().setLayout(new FlowLayout());

        // AdminScreen 클래스의 인스턴스 생성
        Pro ProScreen = new Pro();

        // 프레임에 AdminScreen 패널 추가
       getContentPane().add(ProScreen.getProPanel());

        // 프레임 새로고침
        revalidate();
        repaint();
    }

    // 회원 화면 표시 함수
    private void showMemberScreen() {
    	getContentPane().removeAll();
        getContentPane().setLayout(new FlowLayout());

        // AdminScreen 클래스의 인스턴스 생성
        Member MemberScreen = new Member();

        // 프레임에 AdminScreen 패널 추가
       getContentPane().add(MemberScreen.getMemberPanel());

        // 프레임 새로고침
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
     new projcet();
    }
}

