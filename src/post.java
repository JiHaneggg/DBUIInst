import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class post extends JFrame {
    private JLabel photoLabel;
    private JLabel profilePhotoLabel;
    private JLabel usernameLabel;
    private JLabel locationLabel;
    private JLabel likesLabel;
    private JLabel commentsLabel;
    private JFrame previousFrame;
    private int commentCount = 0;  // 댓글 수를 저장할 변수

    public post(String imagePath, JFrame previousFrame) {
        super("게시물 페이지");

        this.previousFrame = previousFrame;
        // 창 크기 설정
        setSize(400, 600);

        // 레이아웃 설정
        setLayout(new BorderLayout());

        // 중앙 패널 생성
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(null);

        //이전 버튼생성
        JButton backButton = new JButton("이전");
        backButton.setBounds(20, 10, 70, 20);
        centerPanel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 이전 버튼 클릭 시 창을 닫습니다.
                if (previousFrame != null) {  // 이전 창이 null이 아니라면
                    previousFrame.setVisible(true);  // 이전 창을 다시 보이게 합니다.
                }
                setVisible(false);
            }
        });


        // 사진 레이블 생성
        photoLabel = new JLabel();
        photoLabel.setBounds(20, 100, 340, 300);
        ImageIcon originalIcon = new ImageIcon(imagePath); // 매개변수로 받은 이미지 경로를 사용
        ImageIcon imageIcon = new ImageIcon(originalIcon.getImage().getScaledInstance(450, 500, Image.SCALE_DEFAULT));
        photoLabel.setIcon(imageIcon);
        centerPanel.add(photoLabel);

        // 프로필 사진 레이블 생성
        profilePhotoLabel = new JLabel();
        profilePhotoLabel.setBounds(20, 30, 50, 50);
        ImageIcon imageIcon1 = new ImageIcon("src//image//profile4.jpeg");
        profilePhotoLabel.setIcon(imageIcon1);
        centerPanel.add(profilePhotoLabel);

        // 사용자 ID 레이블 생성
        usernameLabel = new JLabel("JI_Haeng");
        usernameLabel.setBounds(80, 30, 100, 20); // 위치와 크기를 적절히 조정
        centerPanel.add(usernameLabel);

        // 위치 레이블 생성
        locationLabel = new JLabel("Seoul");
        locationLabel.setBounds(80, 50, 100, 20);
        centerPanel.add(locationLabel);

        // 좋아요 수 레이블 생성
        likesLabel = new JLabel("0");
        likesLabel.setBounds(10, 430, 100, 20);
        centerPanel.add(likesLabel);

        // 좋아요 버튼 생성
        JButton likeButton = new JButton("좋아요");
        likeButton.setBounds(30, 430, 80, 25);
        centerPanel.add(likeButton);

        likeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 좋아요 수 레이블의 값을 1 증가
                likesLabel.setText(Integer.parseInt(likesLabel.getText()) + 1 + "");
            }
        });

        // 댓글 버튼 생성
        JButton commentButton = new JButton("댓글");
        commentButton.setBounds(140, 430, 80, 25);
        centerPanel.add(commentButton);

        // 댓글 수 레이블 생성
        commentsLabel = new JLabel("" + commentCount);
        commentsLabel.setBounds(120, 430, 100, 20);
        centerPanel.add(commentsLabel);

        // 댓글 버튼 이벤트 처리
        commentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new commentWindow(post.this); // commentWindow에 현재 post 객체를 전달함
                setVisible(false);
            }
        });

        // 하단 버튼 패널 생성
        JPanel bottomPanel = new JPanel();
        JButton homeButton = new JButton("홈");
        JButton searchButton = new JButton("검색");
        JButton profileButton = new JButton("프로필");

        bottomPanel.add(homeButton);
        bottomPanel.add(searchButton);
        bottomPanel.add(profileButton);

        // 패널 추가
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // 창 표시
        setVisible(true);
    }

    // 댓글 수를 증가시키는 메서드를 추가
    public void increaseCommentCount() {
        commentCount++;
        commentsLabel.setText("" + commentCount);
    }

    public static void main(String[] args) {
        new post("src//image//test1.jpeg", null);
    }
}
