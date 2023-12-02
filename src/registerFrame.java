import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class registerFrame extends JFrame {
    private JPanel panel;
    private JLabel idLabel, pwLabel, nameLabel, emailLabel, bdLabel, genderLabel, phoneLabel, locationLabel;
    private JTextField idField, pwField, nameField, emailField, bdField, genderField, phoneField, locationField;
    private JButton registerButton;

    public registerFrame() {
        super("인스타그램 회원가입");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

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

        registerButton = new JButton("가입");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new welcomeWindow();
                setVisible(false); //프레임이 메모리에 계속 남아있음, 여러 창 사이를 전환하게 할 때 유용
                //dispose(); 이것은 창을 닫는 메모리에서 제거 프레임을 완전히 닫음
            }
        });

        JLabel[] labels = {idLabel, pwLabel, nameLabel, emailLabel, bdLabel, genderLabel, phoneLabel, locationLabel};
        JTextField[] textFields = {idField, pwField, nameField, emailField, bdField, genderField, phoneField, locationField};

        for (int i = 0; i < labels.length; i++) {
            constraints.gridy = i;

            constraints.gridx = 0;
            panel.add(labels[i], constraints);

            constraints.gridx = 1;
            panel.add(textFields[i], constraints);
        }

        constraints.gridy = labels.length;
        constraints.gridx = 1;
        panel.add(registerButton, constraints);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new registerFrame();
    }
}
