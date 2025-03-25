import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizFrame extends JFrame implements ActionListener {
    private JLabel questionLabel;
    private JRadioButton[] options;
    private JButton nextButton;
    private ButtonGroup group;
    private int currentQuestion = 0, score = 0;

    private Question[] questions = {
        new Question("What is the capital of France?", "Paris", "London", "Berlin", "Rome", "A"),
        new Question("Which programming language is used in Android?", "Python", "Java", "C++", "Swift", "B"),
        new Question("What is 2 + 2?", "3", "4", "5", "6", "B")
    };

    public QuizFrame() {
        setTitle("Quiz Application");
        setSize(500, 300);
        setLayout(new GridLayout(6, 1));

        questionLabel = new JLabel();
        add(questionLabel);

        options = new JRadioButton[4];
        group = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            group.add(options[i]);
            add(options[i]);
        }

        nextButton = new JButton("Next");
        nextButton.addActionListener(this);
        add(nextButton);

        loadQuestion();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void loadQuestion() {
        if (currentQuestion < questions.length) {
            Question q = questions[currentQuestion];
            questionLabel.setText(q.getQuestion());
            options[0].setText(q.getOptionA());
            options[1].setText(q.getOptionB());
            options[2].setText(q.getOptionC());
            options[3].setText(q.getOptionD());
        } else {
            JOptionPane.showMessageDialog(this, "Quiz Completed! Your Score: " + score);
            System.exit(0);
        }
    }

    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected()) {
                if (questions[currentQuestion].getCorrectOption().equals(Character.toString((char) ('A' + i)))) {
                    score++;
                }
                currentQuestion++;
                group.clearSelection();
                loadQuestion();
                break;
            }
        }
    }
}
