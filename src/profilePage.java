import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class profilePage extends JFrame {
    private JFrame thisFrame;
    public profilePage() {
        thisFrame = new JFrame("프로필");  // 프레임 생성을 thisFrame 변수에 할당
        thisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thisFrame.setSize(400, 600);
        thisFrame.setBackground(Color.WHITE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        //username 과 게시물 추가 버튼
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        // Username
        JLabel usernameLabel = new JLabel("Username");
        topPanel.add(usernameLabel, BorderLayout.NORTH);

        //게시물 추가 버튼
        JButton addPostButton = new JButton("게시물 추가");
        addPostButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new posting();
                thisFrame.setVisible(false);
            }
        });
        topPanel.add(addPostButton, BorderLayout.EAST);

        // topPanel을 NORTH 위치에 추가
        panel.add(topPanel, BorderLayout.NORTH);

        // 나머지 컴포넌트들을 추가할 패널을 생성
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);

        // 프로필 정보를 담을 패널을 생성
        JPanel profilePanel = new JPanel(new BorderLayout());
        profilePanel.setBackground(Color.WHITE);

        JPanel profileInfoPanel = new JPanel(); // 새로운 패널을 추가
        profileInfoPanel.setLayout(new BoxLayout(profileInfoPanel, BoxLayout.Y_AXIS)); // 세로로 컴포넌트를 배치하기 위해 BoxLayout을 설정

        // Profile image
        ImageIcon profileImageIcon = new ImageIcon("src//image//JI_Haeng.jpeg"); // 프로필 이미지 경로
        Image profileImage = profileImageIcon.getImage(); // ImageIcon에서 Image를 가져옴
        Image profileNewimg = profileImage.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // 이미지 크기를 조절
        profileImageIcon = new ImageIcon(profileNewimg);  // ImageIcon에 조절된 이미지를 다시 설정
        JLabel profileImageLabel = new JLabel();
        profileImageLabel.setIcon(profileImageIcon);
        profileInfoPanel.add(profileImageLabel); // 프로필 이미지를 profileInfoPanel에 추가

        // 팔로워 수, 팔로잉 수, 게시물 수를 관리할 변수
        int followerCount = 0;
        int followingCount = 0;
        int postCount = 0;

        // 팔로워 수, 팔로잉 수, 게시물 수를 표시할 JLabel
        JLabel followerCountLabel = new JLabel("" + follow.followerCount);
        JLabel followingCountLabel = new JLabel("" + follow.followingCount);
        JLabel postCountLabel = new JLabel("" + postCount);

        // 팔로워, 팔로잉 버튼
        JButton postButton = new JButton("게시물");
        JButton followButton = new JButton("팔로워");
        JButton followingButton = new JButton("팔로잉");

        // 패널에 위의 레이블과 버튼 추가
        JPanel statsPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;

        // 게시물 수 레이블 및 버튼
        JPanel postPanel = new JPanel(new BorderLayout());
        postPanel.add(postCountLabel, BorderLayout.NORTH);
        postPanel.add(postButton, BorderLayout.SOUTH);


        // 팔로워 수 레이블 및 버튼
        JPanel followerPanel = new JPanel(new BorderLayout());
        followerPanel.add(followerCountLabel, BorderLayout.NORTH);
        followerPanel.add(followButton, BorderLayout.SOUTH);


        // 팔로잉 수 레이블 및 버튼
        JPanel followingPanel = new JPanel(new BorderLayout());
        followingPanel.add(followingCountLabel, BorderLayout.NORTH);
        followingPanel.add(followingButton, BorderLayout.SOUTH);

        gbc.gridx = 0;
        gbc.gridy = 0;
        statsPanel.add(postPanel, gbc); // postPanel을 statsPanel에 추가

        gbc.gridx = 1;
        gbc.gridy = 0;
        statsPanel.add(followerPanel, gbc); // followerPanel을 statsPanel에 추가

        gbc.gridx = 2;
        gbc.gridy = 0;
        statsPanel.add(followingPanel, gbc); // followingPanel을 statsPanel에 추가

        // 팔로워 버튼
        followButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new follow("JI_Haeng", thisFrame).createAndShowGUI("JI_Haeng");
                    }
                });
                thisFrame.setVisible(false);
            }
        });
        followerPanel.add(followButton, BorderLayout.SOUTH);

        // 팔로잉 버튼
        followingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new follow("JI_Haeng", thisFrame).createAndShowGUI("JI_Haeng");
                    }
                });
                thisFrame.setVisible(false);
            }
        });
        followingPanel.add(followingButton, BorderLayout.SOUTH);


        // 프로필 수정 버튼 추가
        JButton editProfileButton = new JButton("프로필 수정");
        editProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new modifyProfile(); // 프로필 수정 창을 새로 열어줌
                thisFrame.setVisible(false);
            }
        });
        profileInfoPanel.add(editProfileButton); // editProfileButton을 profileInfoPanel에 추가

        profilePanel.add(profileInfoPanel, BorderLayout.WEST); // profileInfoPanel을 profilePanel의 WEST 위치에 추가

        profilePanel.add(statsPanel, BorderLayout.CENTER);
        centerPanel.add(profilePanel, BorderLayout.NORTH);


        //게시물들의 그리드
        JPanel gridPanel = new JPanel(new GridLayout(0, 3)); // row의 수를 0으로 설정하면, 자동으로 필요한 만큼의 행이 생성
        for (int i = 1; i <= 20; i++) { // 이미지 개수
            final int index = i; // 새로운 final 변수를 생성하고 i의 값을 할당
            ImageIcon postImageIcon = new ImageIcon("src//image//post" + index + ".jpeg");
            Image image = postImageIcon.getImage(); // ImageIcon에서 Image를 가져옴
            Image newimg = image.getScaledInstance(122, 122, java.awt.Image.SCALE_SMOOTH); // 이미지 크기를 조절합니다.
            postImageIcon = new ImageIcon(newimg);  // ImageIcon에 조절된 이미지를 다시 설정
            JLabel postImageLabel = new JLabel();
            postImageLabel.setIcon(postImageIcon);

            // 마우스 클릭 이벤트를 추가
            postImageLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    String imagePath = "src//image//post" + index + ".jpeg";
                    post postWindow = new post(imagePath, null); // imagePath를 post에 전달
                    postWindow.setVisible(true);
                    thisFrame.setVisible(false);
                }
            });
            gridPanel.add(postImageLabel);

            // 게시물 수를 증가
            postCount++;
        }

        // 게시물 수를 업데이트합니다.
        postCountLabel.setText("" + postCount);


        JScrollPane scrollPane = new JScrollPane(gridPanel); // 스크롤 가능한 패널을 생성
        centerPanel.add(scrollPane, BorderLayout.CENTER); // 스크롤 패널을 centerPanel에 추가

        // centerPanel을 CENTER 위치에 추가
        panel.add(centerPanel, BorderLayout.CENTER);

        // 하단 버튼 패널 생성
        JPanel bottomPanel = new JPanel();
        JButton homeButton = new JButton("홈");
        JButton searchButton = new JButton("검색");
        JButton profileButton = new JButton("프로필");

        bottomPanel.add(homeButton);
        bottomPanel.add(searchButton);
        bottomPanel.add(profileButton);

        thisFrame.add(bottomPanel, BorderLayout.SOUTH);

        thisFrame.add(panel, BorderLayout.CENTER);
        thisFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new profilePage();
    }
}
