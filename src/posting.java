import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class posting extends JFrame {

    private JLabel label;
    private JButton nextButton;
    private JButton prevButton;
    private JPanel panel;
    private JFileChooser fileChooser;

    public posting() {
        super("게시물에 사진을 추가");

        label = new JLabel("사진을 선택하세요");
        nextButton = new JButton("다음");
        prevButton = new JButton("이전");
        panel = new JPanel(new GridLayout(1, 0));
        fileChooser = new JFileChooser();

        panel.add(prevButton);
        panel.add(label);
        panel.add(nextButton);

        getContentPane().add(panel);

        // 파일 선택기에 필터를 추가하여 이미지 파일만 선택하도록 합니다.
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "png", "gif", "bmp"));

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fileChooser.showOpenDialog(posting.this);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    // 선택된 파일의 경로를 가져옵니다.
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                    label.setText("선택된 사진: " + filePath);
                }
            }
        });

        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
            }
        });

        setSize(400, 600);
        setVisible(true);
    }

    public static void main(String[] args){
        new posting();
    }
}
