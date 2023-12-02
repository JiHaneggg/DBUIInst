import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class welcomeWindow extends JFrame {
    public welcomeWindow() {
        JFrame frame = new JFrame("회원가입 완료!");

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Instagram에 오신 것을 환영합니다!", SwingConstants.CENTER);
        label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 20));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label2 = new JLabel("사용자를 팔로우하고 일상의 기록들을 공유해보세요", SwingConstants.CENTER);
        label2.setFont(new Font(label2.getFont().getName(), Font.PLAIN, 12));
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label3 = new JLabel("프로필은 언제든지 변경할 수 있습니다", SwingConstants.CENTER);
        label3.setFont(new Font(label3.getFont().getName(), Font.PLAIN, 12));
        label3.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton nextButton = new JButton("다음");
        nextButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        Dimension newButtonSize = new Dimension(200, nextButton.getPreferredSize().height);
        nextButton.setBackground(new Color(0, 127, 255));  // 파란색 (#0000FF)
        nextButton.setForeground(Color.WHITE);
        nextButton.setFocusPainted(false);
        nextButton.setPreferredSize(newButtonSize); // 버튼 가로 크기 조절
        nextButton.setMinimumSize(newButtonSize); // 버튼 최소 크기 설정
        nextButton.setMaximumSize(newButtonSize);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new login();
                frame.dispose();
            }
        });

        panel.add(Box.createVerticalGlue());
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(label2);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(label3);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(nextButton);
        panel.add(Box.createVerticalGlue());
        nextButton.setPreferredSize(new Dimension(400, nextButton.getPreferredSize().height));

        frame.add(panel);
        frame.setSize(400, 600);
        frame.setLocationRelativeTo(null); // 화면 중앙
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new welcomeWindow();
    }
}