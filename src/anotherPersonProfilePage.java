import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class anotherPersonProfilePage extends JFrame {
    private JFrame previousFrame;
    private static HashMap<String, userProfileInformation> userProfiles = new HashMap<>();
    public anotherPersonProfilePage(String username, JFrame previousFrame) {
        this.previousFrame = previousFrame;
        initProfiles();
        userProfileInformation userProfile = userProfiles.get(username);

        JFrame frame = new JFrame(userProfile.getUsername() + "의 프로필");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setBackground(Color.WHITE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        //username 과 게시물 추가 버튼
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        JPanel usernameAndBackButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // 새로운 패널을 만들고, 레이아웃을 FlowLayout으로 설정

        JButton backButton = new JButton("이전");
        backButton.addActionListener(e -> {
            frame.setVisible(false); // 현재 프레임을 닫습니다.
            if (previousFrame != null) {  // 이전 창이 null이 아니라면
                previousFrame.setVisible(true);  // 이전 창을 다시 보이게 합니다.
            }
        });
        usernameAndBackButtonPanel.add(backButton); // backButton을 usernameAndBackButtonPanel에 추가

        // Username
        JLabel usernameLabel = new JLabel(userProfile.getUsername());
        usernameAndBackButtonPanel.add(usernameLabel); // usernameLabel을 usernameAndBackButtonPanel에 추가

        topPanel.add(usernameAndBackButtonPanel, BorderLayout.NORTH); // usernameAndBackButtonPanel을 topPanel의 NORTH 위치에 추가


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
        ImageIcon profileImageIcon = new ImageIcon(userProfile.getProfileImagePath()); // UserProfile 객체에서 프로필 이미지 경로를 가져옴
        Image profileImage = profileImageIcon.getImage(); // ImageIcon에서 Image를 가져옴
        Image profileNewimg = profileImage.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // 이미지 크기를 조절
        profileImageIcon = new ImageIcon(profileNewimg);  // ImageIcon에 조절된 이미지를 다시 설정
        JLabel profileImageLabel = new JLabel();
        profileImageLabel.setIcon(profileImageIcon);
        profileInfoPanel.add(profileImageLabel); // 프로필 이미지를 profileInfoPanel에 추가

        // 팔로워 수, 팔로잉 수, 게시물 수를 표시할 JLabel
        // UserProfile 객체에서 팔로워 수, 팔로잉 수, 게시물 수를 가져옴
        JLabel followerCountLabel = new JLabel("" + userProfile.getFollowerCount());
        JLabel followingCountLabel = new JLabel("" + userProfile.getFollowingCount());
        JLabel postCountLabel = new JLabel("" + userProfile.getPostCount());

        // 게시물, 팔로워, 팔로잉 버튼
        JButton postButton = new JButton("게시물");
        JButton followButton = new JButton("팔로워");
        JButton followingButton = new JButton("팔로잉");

        // 패널에 위의 레이블과 버튼 추가
        JPanel statsPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;

        //게시물
        JPanel postPanel = new JPanel(new BorderLayout());
        postPanel.add(postCountLabel, BorderLayout.NORTH);
        postPanel.add(postButton, BorderLayout.SOUTH);

        // 팔로워 수 레이블 및 버튼
        JPanel followerPanel = new JPanel(new BorderLayout());
        followerPanel.add(followerCountLabel, BorderLayout.NORTH);
        followButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                follow followWindow = new follow(userProfile.getUsername(), frame); // follow 창을 생성
                followWindow.notifyAll(); // follow 창을 보이게 합니다.
                frame.setVisible(false); // 현재 창을 숨깁니다.
            }
        });
        followerPanel.add(followButton, BorderLayout.SOUTH);

        // 팔로잉 수 레이블 및 버튼
        JPanel followingPanel = new JPanel(new BorderLayout());
        followingPanel.add(followingCountLabel, BorderLayout.NORTH);
        followingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                follow followWindow = new follow(userProfile.getUsername(), frame); // follow 창을 생성
                followWindow.notifyAll(); // follow 창을 보이게 합니다.
                frame.setVisible(false); // 현재 창을 숨깁니다.
            }
        });
        followingPanel.add(followingButton, BorderLayout.SOUTH);
        gbc.gridx = 0;
        gbc.gridy = 0;
        statsPanel.add(postPanel, gbc); //게시물

        gbc.gridx = 1;
        gbc.gridy = 0;
        statsPanel.add(followerPanel, gbc); // followerPanel을 statsPanel에 추가

        gbc.gridx = 2;
        gbc.gridy = 0;
        statsPanel.add(followingPanel, gbc); // followingPanel을 statsPanel에 추가

        profilePanel.add(profileInfoPanel, BorderLayout.WEST); // profileInfoPanel을 profilePanel의 WEST 위치에 추가
        profilePanel.add(statsPanel, BorderLayout.CENTER);

        centerPanel.add(profilePanel, BorderLayout.NORTH);

        // 팔로우/팔로잉 버튼 생성
        JButton followerFollowingButton = new JButton("팔로우");
        followerFollowingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 버튼의 현재 텍스트에 따라 텍스트를 변경합니다.
                if (followerFollowingButton.getText().equals("팔로우")) {
                    followerFollowingButton.setText("팔로잉");

                    // 팔로워 수를 증가시키는 코드를 추가합니다.
                    //userProfile.setFollowerCount(userProfile.getFollowerCount() + 1);

                    // 팔로워 목록에 현재 사용자를 추가합니다.
                    //userProfile.getFollowerNames().add(currentUsername);
                } else {
                    followerFollowingButton.setText("팔로우");

                    // 팔로워 수를 감소시키는 코드를 추가합니다.
                    //userProfile.setFollowerCount(userProfile.getFollowerCount() - 1);

                    // 팔로워 목록에서 현재 사용자를 제거합니다.
                    //userProfile.getFollowerNames().remove(currentUsername);
                }
            }
        });

        profilePanel.add(followerFollowingButton, BorderLayout.SOUTH);



        // 게시물들의 그리드
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
                    post postWindow = new post(imagePath, frame); // 여기를 수정하였습니다.
                    postWindow.setVisible(true);
                    frame.setVisible(false);
                }
            });

            gridPanel.add(postImageLabel);
        }

        JScrollPane scrollPane = new JScrollPane(gridPanel); // 스크롤 가능한 패널을 생성
        centerPanel.add(scrollPane, BorderLayout.CENTER); // 스크롤 패널을 centerPanel에 추가

        // centerPanel을 CENTER 위치에 추가
        panel.add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 3));
        JButton homeButton = new JButton("홈");
        bottomPanel.add(homeButton);
        JButton searchButton = new JButton("검색");
        bottomPanel.add(searchButton);
        JButton profileButton = new JButton("프로필");
        bottomPanel.add(profileButton);

        panel.add(bottomPanel, BorderLayout.SOUTH);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void initProfiles() {
        ArrayList<String> JI_Haeng_followers = new ArrayList<>(Arrays.asList("Kim_Dain", "Kim_Gun", "Kim_Min_Gyeom"));
        ArrayList<String> JI_Haeng_followings = new ArrayList<>(Arrays.asList("Kim_Dain", "Kim_Gun", "Kim_Min_Gyeom"));

        ArrayList<String> Kim_Gun_followers = new ArrayList<>(Arrays.asList("JI_Haeng", "Kim_Dain", "Kim_Min_Gyeom"));
        ArrayList<String> Kim_Gun_followings = new ArrayList<>(Arrays.asList("JI_Haeng", "Kim_Dain", "Kim_Min_Gyeom"));

        ArrayList<String> Kim_Dain_followers = new ArrayList<>(Arrays.asList("JI_Haeng", "Kim_Gun", "Kim_Min_Gyeom"));
        ArrayList<String> Kim_Dain_followings = new ArrayList<>(Arrays.asList("JI_Haeng", "Kim_Gun", "Kim_Min_Gyeom"));

        ArrayList<String> Kim_Min_Gyeom_followers = new ArrayList<>(Arrays.asList("JI_Haeng", "Kim_Gun", "Kim_Dain"));
        ArrayList<String> Kim_Min_Gyeom_followings = new ArrayList<>(Arrays.asList("JI_Haeng", "Kim_Gun", "Kim_Dain"));

        userProfileInformation JI_Haeng = new userProfileInformation("JI_Haeng", "src//image//JI_Haeng.jpeg", 3, 3, 3, JI_Haeng_followers, JI_Haeng_followings);
        userProfileInformation Kim_Gun = new userProfileInformation("Kim_Gun", "src//image//Kim_Gun.jpeg", 3, 3, 3, Kim_Gun_followers, Kim_Gun_followings);
        userProfileInformation Kim_Dain = new userProfileInformation("Kim_Dain", "src//image//Kim_Dain.jpeg", 3, 3, 3, Kim_Dain_followers, Kim_Dain_followings);
        userProfileInformation Kim_Min_Gyeom = new userProfileInformation("Kim_Min_Gyeom", "src//image//Kim_Min_Gyeom.jpeg", 3, 3, 35, Kim_Min_Gyeom_followers, Kim_Min_Gyeom_followings);

        userProfiles.put(JI_Haeng.getUsername(), JI_Haeng);
        userProfiles.put(Kim_Gun.getUsername(), Kim_Gun);
        userProfiles.put(Kim_Dain.getUsername(), Kim_Dain);
        userProfiles.put(Kim_Min_Gyeom.getUsername(), Kim_Min_Gyeom);
    }

    public static void main(String[] args) {
        new anotherPersonProfilePage("JI_Haeng", null);
    }
}
