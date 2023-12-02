import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;

public class search extends JFrame {

    private JPanel postPanel;
    private String[] data = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    public search() {
        // JFrame 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);

        // 메인 패널 생성
        JPanel mainPanel = new JPanel(new BorderLayout());

        // 상단 패널 생성
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBackground(Color.WHITE);

        // 검색 텍스트 필드
        final JTextField searchField = new JTextField(20);
        topPanel.add(searchField);

        // 검색 버튼a
        JButton searchButton = new JButton("Search");
        topPanel.add(searchButton);

        // 검색 버튼 이벤트 처리
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 검색 기능 구현
                String searchTerm = searchField.getText();

                // 검색 결과를 중앙 패널에 표시
                displaySearchResults(searchTerm);
            }
        });

        // 상단 패널을 메인 패널에 추가
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // 하단 패널
        JPanel bottomPanel = new JPanel(new GridLayout(1, 3));
        bottomPanel.setBackground(Color.WHITE);

        // Home 버튼 (로고로 대체)
        JButton homeButton = createButton("https://cdn-icons-png.flaticon.com/512/6490/6490332.png", "Home");
        bottomPanel.add(homeButton);

        // Search 버튼 (로고로 대체)
        JButton searchButton2 = createButton("https://w7.pngwing.com/pngs/599/899/png-transparent-button-search-button-internet-search-engine-search-button-thumbnail.png", "Search");
        bottomPanel.add(searchButton2);

        // Profile 버튼 (로고로 대체)
        JButton profileButton = createButton("https://cdn-icons-png.flaticon.com/512/456/456283.png", "Profile");
        bottomPanel.add(profileButton);

        // 버튼을 이벤트 처리
        homeButton.addMouseListener(new NavigationMouseListener("Home"));
        searchButton2.addMouseListener(new NavigationMouseListener("Search"));
        profileButton.addMouseListener(new NavigationMouseListener("Profile"));

        // 하단 패널을 메인 패널에 추가
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // 가운데 패널(게시물) 생성 및 추가
        postPanel = createPostPanel();
        JScrollPane centerPanel = new JScrollPane(postPanel);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // 메인 패널을 프레임에 추가
        add(mainPanel);

        // 프레임을 화면 중앙에 표시
        setLocationRelativeTo(null);
    }

    private JButton createButton(String iconUrl, String command) {
        JButton button = null;
        try {
            URL url = new URL(iconUrl);
            ImageIcon icon = new ImageIcon(url);
            Image scaledImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            icon = new ImageIcon(scaledImage);
            button = new JButton(icon);
            button.setBackground(Color.WHITE);
            button.setActionCommand(command);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return button;
    }

    private JPanel createPostPanel() {
        return new JPanel();
    }

    private void displaySearchResults(String searchTerm) {
        // 이전 검색 결과 지우기
        postPanel.removeAll();
        postPanel.revalidate();
        postPanel.repaint();

        // 검색어와 일치하는 경우에만 데이터 추가
        for (String item : data) {
            if (item.equals(searchTerm)) {
                JLabel resultLabel = new JLabel(item);
                resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
                resultLabel.setHorizontalAlignment(SwingConstants.CENTER);

                postPanel.add(resultLabel);
            }
        }

        // 패널을 다시 그리기
        postPanel.revalidate();
        postPanel.repaint();
    }

    private class NavigationMouseListener extends MouseAdapter {
        private String destination;

        public NavigationMouseListener(String destination) {
            this.destination = destination;
        }

        public void mouseClicked(MouseEvent e) {
            JOptionPane.showMessageDialog(null, destination + " page");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new search().setVisible(true);
            }
        });
    }
}