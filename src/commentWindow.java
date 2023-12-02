import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class commentWindow extends JFrame implements KeyListener {
    private JTextField usernameField;
    private JTextArea commentArea;
    private JButton commentButton;
    private JButton backButton;
    private JPanel commentPanel;
    private JScrollPane commentScrollPane;

    private post postWindow; // post 창 객체를 저장할 변수

    // 사용자 아이디와 프로필 사진 경로를 매핑하는 맵 생성
    private Map<String, String> userProfiles = new HashMap<>();

    public commentWindow(post postWindow) {
        super("Comment");

        this.postWindow = postWindow; // post 창 객체를 저장

        // 사용자 아이디와 프로필 사진 경로를 매핑
        userProfiles.put("김민겸", "src//image//profile2.jpeg");
        userProfiles.put("김건", "src//image//profile1.jpeg");
        userProfiles.put("허지행", "src//image//profile4.jpeg");
        userProfiles.put("김다인", "src//image//profile5.jpg");
        // ...

        commentPanel = new JPanel();
        commentPanel.setLayout(new BoxLayout(commentPanel, BoxLayout.Y_AXIS));

        usernameField = new JTextField(10);
        commentArea = new JTextArea(3, 20);
        commentArea.addKeyListener(this); // 키 리스너를 commentArea에 추가

        commentButton = new JButton("댓글");
        backButton = new JButton("이전");

        commentScrollPane = new JScrollPane(commentPanel);
        commentScrollPane.setPreferredSize(new Dimension(400, 300));

        commentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String comment = commentArea.getText();
                if (!username.isEmpty() && !comment.isEmpty()) {
                    JPanel commentPane = new JPanel(new BorderLayout());

                    // 프로필 사진 레이블 생성
                    JLabel profileLabel = new JLabel();
                    String profilePath = userProfiles.getOrDefault(username, "src//image//profile3.jpeg"); //기본 프로필
                    ImageIcon originalIcon = new ImageIcon(profilePath);
                    ImageIcon profileIcon = new ImageIcon(originalIcon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
                    profileLabel.setIcon(profileIcon);
                    commentPane.add(profileLabel, BorderLayout.WEST);


                    // 댓글 레이블 생성
                    JLabel commentLabel = new JLabel(username + ": " + comment);
                    commentPane.add(commentLabel, BorderLayout.CENTER);

                    commentPanel.add(commentPane);
                    commentPanel.revalidate();
                    commentArea.setText("");

                    // 댓글을 작성한 후 post 창의 댓글 수를 증가시킴
                    postWindow.increaseCommentCount();
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                postWindow.setVisible(true); // post 창을 다시 보이게 함
                setVisible(false); // 현재 창을 숨김
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(usernameField, BorderLayout.WEST);
        inputPanel.add(commentArea, BorderLayout.CENTER);
        inputPanel.add(commentButton, BorderLayout.EAST);

        setLayout(new BorderLayout());
        add(backButton, BorderLayout.NORTH);
        add(commentScrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            String username = usernameField.getText();
            String comment = commentArea.getText();
            if (!username.isEmpty() && !comment.isEmpty()) {
                JPanel commentPane = new JPanel(new BorderLayout());

                // 프로필 사진 레이블 생성
                JLabel profileLabel = new JLabel();
                String profilePath = userProfiles.getOrDefault(username, "src//image//profile3.jpeg"); //기본 프로필
                ImageIcon originalIcon = new ImageIcon(profilePath);
                ImageIcon profileIcon = new ImageIcon(originalIcon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
                profileLabel.setIcon(profileIcon);
                commentPane.add(profileLabel, BorderLayout.WEST);


                // 댓글 레이블 생성
                JLabel commentLabel = new JLabel(username + ": " + comment);
                commentPane.add(commentLabel, BorderLayout.CENTER);

                commentPanel.add(commentPane);
                commentPanel.revalidate();
                commentArea.setText("");

                // 댓글을 작성한 후 post 창의 댓글 수를 증가시킴
                postWindow.increaseCommentCount();
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new commentWindow(new post("src//image//test1.jpeg", null)); // 테스트를 위해 post 객체를 생성하여 전달
            }
        });
    }
}
