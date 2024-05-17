import javax.swing.*;
import java.util.ArrayList;

public class CharacterSelectionView extends JPanel {

    private int index;
    ArrayList<ImageIcon> images = new ArrayList<>();

    public CharacterSelectionView(int x,int y,int w,int h, ArrayList<ImageIcon> images) {
        super();
        this.index = 0;
        this.setLayout(null);
        this.setBounds(x,y,w,h);

        JLabel selectedImage = new JLabel();
        selectedImage.setIcon(images.get(0));
        selectedImage.setBounds((int) (0.2*w), 0, (int) (0.8*w), h);
        this.add(selectedImage);

        JButton leftButton = new JButton("<");
        leftButton.setBounds(0, (int) (0.25*h), (int) (0.2*w), (int) (0.75*h));
        leftButton.addActionListener(e -> {
            this.index--;
            if (this.index < 0) {
                this.index = images.size()-1;
            }
            selectedImage.setIcon(images.get(index));
            repaint();
            revalidate();
        });
        this.add(leftButton);

        JButton rightButton = new JButton(">");
        rightButton.setBounds((int) (0.8*w), (int) (0.25*h), (int) (0.2*w), (int) (0.75*h));
        rightButton.addActionListener(e -> {
            this.index++;
            if (this.index == images.size()) {
                this.index = 0;
            }
            selectedImage.setIcon(images.get(index));
            repaint();
            revalidate();
        });
        this.add(rightButton);


    }
}