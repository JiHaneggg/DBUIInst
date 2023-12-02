import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class modifyProfile extends JFrame {
    private JPanel panel, buttonPanel;
    private JButton modifyButton, prevButton, completeButton;
    private JLabel idLabel, pwLabel, nameLabel, emailLabel, bdLabel, genderLabel, phoneLabel, locationLabel;
    private JLabel profilePicture;
    private JTextField idField, pwField, nameField, emailField, bdField, genderField, phoneField, locationField;

    public modifyProfile() {
        super("프로필 수정");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBackground(Color.WHITE); // 버튼 패널 배경색을 흰색으로 설정
        prevButton = new JButton("이전");
        completeButton = new JButton("완료");

        prevButton = new JButton("이전");
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new profilePage();
                setVisible(false);
            }
        });

        completeButton = new JButton("완료");
        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new profilePage();
                setVisible(false);
            }
        });

        buttonPanel.add(prevButton);
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(completeButton);


        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        ImageIcon icon = new ImageIcon("src//image//profile4.jpeg"); // 사진 경로
        Image image = icon.getImage(); // ImageIcon에서 Image를 추출
        Image newimg = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH); // 이미지 크기 조절
        icon = new ImageIcon(newimg);  // 크기를 조정한 후 ImageIcon에 다시 설정
        profilePicture = new JLabel(icon); // 프로필 사진
        profilePicture.setAlignmentX(Component.CENTER_ALIGNMENT); // 라벨 가운데 정렬

        modifyButton = new JButton("프로필 사진 변경"); // 수정 버튼 초기화
        modifyButton.setAlignmentX(Component.CENTER_ALIGNMENT); // 버튼 가운데 정렬

        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(modifyProfile.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath()); // 사진 경로
                    Image image = icon.getImage(); // ImageIcon에서 Image를 추출
                    Image newimg = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH); // 이미지 크기 조절
                    icon = new ImageIcon(newimg);  // 크기를 조정한 후 ImageIcon에 다시 설정
                    profilePicture.setIcon(icon); // 프로필 사진 변경
                }
            }
        });

        panel.add(profilePicture);
        panel.add(modifyButton);


        idLabel = new JLabel("ID");
        idField = new JTextField(20);

        pwLabel = new JLabel("Password");
        pwField = new JTextField(20);

        nameLabel = new JLabel("User name");
        nameField = new JTextField(20);

        emailLabel = new JLabel("E-mail");
        emailField = new JTextField(20);

        bdLabel = new JLabel("Birthday");
        bdField = new JTextField(20);

        genderLabel = new JLabel("Gender");
        genderField = new JTextField(20);

        phoneLabel = new JLabel("Phone number");
        phoneField = new JTextField(20);

        locationLabel = new JLabel("Location");
        locationField = new JTextField(20);

        JLabel[] labels = {idLabel, pwLabel, nameLabel, emailLabel, bdLabel, genderLabel, phoneLabel, locationLabel};
        JTextField[] textFields = {idField, pwField, nameField, emailField, bdField, genderField, phoneField, locationField};

        for (int i = 0; i < labels.length; i++) {
            JPanel subPanel = new JPanel();
            subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));

            JPanel labelPanel = new JPanel();
            labelPanel.setLayout(null);  // 레이아웃 관리자를 사용하지 않음
            labels[i].setBounds(50, 0, 100, 35);  // 레이블의 위치와 크기를 지정
            labelPanel.add(labels[i]);
            subPanel.add(labelPanel);

            JPanel textPanel = new JPanel();
            textPanel.setLayout(null);  // 레이아웃 관리자를 사용하지 않음
            textFields[i].setBounds(0, 0, 150, 35);  // 텍스트 필드의 위치와 크기를 지정
            textPanel.add(textFields[i]);

            subPanel.add(textPanel);
            panel.add(subPanel);
        }




        add(buttonPanel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        new modifyProfile();
    }
}
