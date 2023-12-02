import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class login extends JFrame {
    public login() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel ID = new JLabel("ID ");
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 0, 5, 0);  // 수직 간격 추가
        panel.add(ID, gbc);

        JTextField txtID= new JTextField(20);
        gbc.gridx = 1;
        panel.add(txtID, gbc);

        JLabel passWord = new JLabel("PassWord ");
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 0, 30, 0);  // 수직 간격 추가
        panel.add(passWord, gbc);

        JPasswordField txtPass = new JPasswordField(20);
        gbc.gridx = 1;
        panel.add(txtPass, gbc);

        JButton logBtn = new JButton("로그인");
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);  // 수직 간격 추가
        logBtn.setBackground(new Color(0, 127, 255));  // 파란색 (#0000FF)
        logBtn.setForeground(Color.WHITE);
        logBtn.setFocusPainted(false);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(logBtn, gbc);

        // 가로 크기 설정
        logBtn.setPreferredSize(new Dimension(250, 30));

        logBtn.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = "hi";
                String pass = "1234";

                if(id.equals(txtID.getText()) &&  pass.equals(txtPass.getText())) {
                    JOptionPane.showMessageDialog( null, "you have logged in successfully" );
                } else {
                    JOptionPane.showMessageDialog( null , " you failed to log in ");
                }
            }
        } );

        JButton signUpBtn = new JButton("회원가입");
        gbc.gridy = 3;
        signUpBtn.setBackground(new Color(0, 127, 255));  // 파란색 (#0000FF)
        signUpBtn.setForeground(Color.WHITE);
        signUpBtn.setFocusPainted(false);
        panel.add(signUpBtn, gbc);

        // 가로 크기 설정
        signUpBtn.setPreferredSize(new Dimension(250, 30));


        //회원가입 버튼
        signUpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new registerFrame();
                setVisible(false);
                //dispose(); 창을 닫음
            }
        });

        add(panel);
        setVisible(true);
        setSize( 400 , 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new login();
    }
}