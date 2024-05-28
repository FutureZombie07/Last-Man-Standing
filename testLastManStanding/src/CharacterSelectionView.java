import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CharacterSelectionView extends JPanel {

    private JTextField nameField;
    private int index;
    ArrayList<ImageIcon> images = new ArrayList<>();

    public CharacterSelectionView(int x,int y,int w,int h, ArrayList<ImageIcon> images) {
        super();
        this.index = 0;
        this.images =images;
        this.setLayout(null);
        this.setBounds(x,y,w,h);
        this.setOpaque(false);

        JLabel selectedImage = new JLabel();
        selectedImage.setIcon(new ImageIcon(images.get(0).getImage().getScaledInstance((int) (GUI.characterViewWidth*0.8 + 1), GUI.characterViewHeight, Image.SCALE_DEFAULT)));
        selectedImage.setBounds((int) (0.1*w), 0, (int) (0.8*w), (int) (0.8*h));
        this.add(selectedImage);

        JButton leftButton = new JButton("<");
        leftButton.setBounds(0, (int) (0.3*h), (int) (0.1*w), (int) (0.3*h));
        leftButton.setBackground(new Color(0,0,0));
        leftButton.setForeground(new Color(255,255,255));
        leftButton.setFocusPainted(false);
        leftButton.setFont(GUI.bestFont);
        leftButton.setMargin(new Insets(0,0,0,0));
        leftButton.addActionListener(e -> {
            this.index--;
            if (this.index < 0) {
                this.index = images.size()-1;
            }
            selectedImage.setIcon(new ImageIcon(images.get(index).getImage().getScaledInstance((int) (GUI.characterViewWidth*0.8 + 1), GUI.characterViewHeight, Image.SCALE_DEFAULT)));
            repaint();
            revalidate();
        });
        this.add(leftButton);

        JButton rightButton = new JButton(">");
        rightButton.setBounds((int) (0.9*w), (int) (0.3*h), (int) (0.1*w), (int) (0.3*h));
        rightButton.setBackground(new Color(0,0,0));
        rightButton.setForeground(new Color(255,255,255));
        rightButton.setFocusPainted(false);
        rightButton.setFont(GUI.bestFont);
        rightButton.setMargin(new Insets(0,0,0,0));
        rightButton.addActionListener(e -> {
            this.index++;
            if (this.index == images.size()) {
                this.index = 0;
            }
            selectedImage.setIcon(new ImageIcon(images.get(index).getImage().getScaledInstance((int) (GUI.characterViewWidth*0.8 + 1), GUI.characterViewHeight, Image.SCALE_DEFAULT)));
            repaint();
            revalidate();
        });
        this.add(rightButton);

        JTextField nameField = new JTextField("Enter Name...");
        nameField.setBounds(0, (int) (0.8*h), w, (int) (0.2*h));
        nameField.setBackground(new Color(0,0,0));
        nameField.setForeground(new Color(255,255,255));
        nameField.setFont(GUI.bestFont);
        this.add(nameField);
        this.nameField =nameField;


    }
    public ImageIcon getCurrentImage() {
        return new ImageIcon(this.images.get(index).getImage().getScaledInstance(250,250, Image.SCALE_DEFAULT));
    }

    public String getName() {
        return nameField.getText();
    }
}
