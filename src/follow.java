import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;


public class follow {
    private JFrame frame;
    private JLabel userIdLabel;
    private JList<String> userList;
    private JFrame previousFrame;
    private userProfileInformation user1;
    private userProfileInformation user2;
    private userProfileInformation user3;
    private userProfileInformation user4;

    public static int followerCount = 4;  // 팔로워 수 데이터 불러오면 이 변수 이용!
    public static int followingCount = 4; // 팔로잉 수

    // 팔로워와 팔로잉 사용자의 이름 배열
    String[] followerNames = {"JI_Haeng", "Kim_Gun", "Kim_Dain", "Kim_Min_Gyeom"};
    String[] followingNames = {"Kim_Gun", "Kim_Dain", "Kim_Min_Gyeom", "JI_Haeng"};

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new follow(null,null).createAndShowGUI(null);
            }
        });
    }

    // 생성자 추가
    public follow(String username, JFrame previousFrame) {
        this.previousFrame = previousFrame;
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(username);
            }
        });
    }

    public void createAndShowGUI(String username) {
        frame = new JFrame("Instagram 팔로워 목록");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //유저 정보
        ArrayList<String> JI_Haeng_followers = new ArrayList<>(Arrays.asList("Kim_Gun", "Kim_Dain", "Kim_Min_Gyeom"));
        ArrayList<String> JI_Haeng_followings = new ArrayList<>(Arrays.asList("Kim_Gun", "Kim_Dain", "Kim_Min_Gyeom"));

        ArrayList<String> Kim_Gun_followers = new ArrayList<>(Arrays.asList("JI_Haeng", "Kim_Dain","Kim_Min_Gyeom"));
        ArrayList<String> Kim_Gun_followings = new ArrayList<>(Arrays.asList("JI_Haeng", "Kim_Dain","Kim_Min_Gyeom"));

        ArrayList<String> Kim_Dain_followers = new ArrayList<>(Arrays.asList("JI_Haeng", "Kim_Gun", "Kim_Min_Gyeom"));
        ArrayList<String> Kim_Dain_followings = new ArrayList<>(Arrays.asList("JI_Haeng", "Kim_Gun", "Kim_Min_Gyeom"));

        ArrayList<String> Kim_Min_Gyeom_followers = new ArrayList<>(Arrays.asList("JI_Haeng", "Kim_Gun", "Kim_Dain"));
        ArrayList<String> Kim_Min_Gyeom_followings = new ArrayList<>(Arrays.asList("JI_Haeng", "Kim_Gun", "Kim_Dain"));

        user1 = new userProfileInformation("JI_Haeng", "src//image//JI_Haeng.jpeg", 3, 3, 3, JI_Haeng_followers, JI_Haeng_followings);
        user2 = new userProfileInformation("Kim_Gun", "src//image//Kim_Gun.jpeg", 3, 3, 3, Kim_Gun_followers, Kim_Gun_followings);
        user3 = new userProfileInformation("Kim_Dain", "src//image//Kim_Dain.jpeg", 3, 3, 3, Kim_Dain_followers, Kim_Dain_followings);
        user4 = new userProfileInformation("Kim_Min_Gyeom", "src//image//Kim_Min_Gyeom.jpeg", 3, 3, 35, Kim_Min_Gyeom_followers, Kim_Min_Gyeom_followings);

        // 상단 패널
        JPanel topPanel = new JPanel(new BorderLayout());
        JButton backButton = new JButton("<");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (previousFrame != null) {
                    previousFrame.setVisible(true);
                }
                frame.setVisible(false); // 현재 창을 닫습니다.
            }
        });


        topPanel.add(backButton, BorderLayout.WEST);
        backButton.setBackground(Color.WHITE);

        String userId = username; // 수정된 부분
        userIdLabel = new JLabel(userId);
        userIdLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(userIdLabel, BorderLayout.CENTER);

        frame.getContentPane().add(topPanel, BorderLayout.NORTH);

        // 패널 크기 설정
        Dimension preferredSize = new Dimension(400, 40);
        topPanel.setPreferredSize(preferredSize);

        // 중단 패널
        JPanel middlePanel = new JPanel(new GridLayout(1, 2));
        middlePanel.setBackground(Color.WHITE);

        JButton followerButton = new JButton("팔로워");
        followerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] followerData = generateUserList(userIdLabel.getText(), "follower");
                updateList(followerData);
            }
        });
        followerButton.setBackground(Color.WHITE);

        JButton followingButton = new JButton("팔로잉");
        followingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] followingData = generateUserList(userIdLabel.getText(), "following");
                updateList(followingData);
            }
        });
        followingButton.setBackground(Color.WHITE);

        middlePanel.add(followerButton);
        middlePanel.add(followingButton);
        frame.getContentPane().add(middlePanel, BorderLayout.CENTER);

        // 패널 크기 설정
        Dimension preferredSize2 = new Dimension(400, 30);
        middlePanel.setPreferredSize(preferredSize2);

        // 하단 패널
        JPanel bottomPanel = new JPanel(new BorderLayout());
        userList = new JList<>();
        userList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 1) {
                    int index = list.locationToIndex(evt.getPoint());
                    new anotherPersonProfilePage(userList.getModel().getElementAt(index).toString(), frame); // 여기를 수정하였습니다.
                    frame.setVisible(false);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(userList);
        bottomPanel.add(scrollPane, BorderLayout.CENTER);
        userList.setCellRenderer(new ProfileListCellRenderer());

        frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);


        // 패널 크기 설정
        Dimension preferredSize3 = new Dimension(400, 480);
        bottomPanel.setPreferredSize(preferredSize3);

        // 프레임 사이즈 설정
        frame.setSize(400, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void updateList(String[] data) {
        userList.setListData(data);
    }

    private String[] generateUserList(String username, String type) {
        userProfileInformation user;
        switch (username) {
            case "JI_Haeng":
                user = user1;
                break;
            case "Kim_Gun":
                user = user2;
                break;
            case "Kim_Dain":
                user = user3;
                break;
            case "Kim_Min_Gyeom":
                user = user4;
                break;
            default:
                return new String[]{}; // 유저 이름이 일치하는 사용자가 없는 경우
        }

        ArrayList<String> userNames = type.equals("following") ? user.getFollowingNames() : user.getFollowerNames();
        String[] userList = new String[userNames.size()];
        for (int i = 0; i < userNames.size(); i++) {
            userList[i] = userNames.get(i);
        }

        return userList;
    }



    // JList를 위한 사용자 정의 셀 렌더러
    public static class ProfileListCellRenderer extends JPanel implements ListCellRenderer {
        private JLabel nameLabel;
        private JLabel pictureLabel;

        public ProfileListCellRenderer() {
            setLayout(new BorderLayout());

            nameLabel = new JLabel();
            add(nameLabel, BorderLayout.CENTER);

            pictureLabel = new JLabel();
            pictureLabel.setHorizontalAlignment(SwingConstants.CENTER);
            pictureLabel.setVerticalAlignment(SwingConstants.CENTER);
            pictureLabel.setPreferredSize(new Dimension(50, 50)); // 필요에 따라 크기 조절
            add(pictureLabel, BorderLayout.WEST);

        }

        public Component getListCellRendererComponent(JList list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            String userName = (String) value;

            nameLabel.setText(userName);

            String imagePath = "src//image//" + userName + ".jpeg"; //사진 데이터 저장할 때 사진 이름을 그 사람 이름으로 해야할 듯

            ImageIcon originalIcon = new ImageIcon(imagePath);
            Image originalImage = originalIcon.getImage();
            // 이미지 크기 조절
            Image resizedImage = originalImage.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(resizedImage);
            pictureLabel.setIcon(icon);

            setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            setEnabled(list.isEnabled());
            setFont(list.getFont());

            return this;
        }
    }
}
