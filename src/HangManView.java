import javax.imageio.ImageIO;
import javax.swing.*;

import component.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HangManView extends JFrame {
    private static final long serialVersionUID = -6149601216998660790L;
    private HButton submitBtn;
    private JTextField inputField;
    private HLabel hiddenText;
    private JPanel hangman;
    private GridBagLayout gBag;
    private HLabel successCountLabel;
    private HLabel failCountLabel;
    private HLabel wrongCountLabel;

    HangManView() {
        setTitle("HangMan");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initHiddenText();
        initHangManImg();
        initScoreBoard();
        initInputField();
        setVisible(true);
    }

    private void initHiddenText(){
        hiddenText = new HLabel("question");
        hiddenText.setHeader();
        getContentPane().add(hiddenText, BorderLayout.NORTH);
    }

    private void initHangManImg(){
        hangman = new JPanel();
        getContentPane().add(hangman, BorderLayout.CENTER);
    }
    private void initScoreBoard(){
        JPanel side = new JPanel();
        side.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gBag = new GridBagLayout();
        side.setBackground(Color.white);
        side.setLayout(gBag);
        HLabel success = new HLabel("success");
        success.setAnnounce();
        side.add(success);
        successCountLabel = new HLabel("");
        successCountLabel.setNum();
        HLabel fail = new HLabel("fail");
        fail.setAnnounce();
        side.add(fail);
        failCountLabel = new HLabel("");
        failCountLabel.setNum();
        HLabel wrong = new HLabel("wrong");
        wrong.setAnnounce();
        side.add(wrong);
        wrongCountLabel = new HLabel("");
        wrongCountLabel.setNum();
        gbinsert(success, 0, 1, 1, 1);
        gbinsert(fail, 0, 3, 1, 1);
        gbinsert(wrong, 0, 5, 1, 1);
        gbinsert(successCountLabel, 0, 2, 1, 1);
        gbinsert(failCountLabel, 0, 4, 1, 1);
        gbinsert(wrongCountLabel, 0, 6, 1, 1);
        side.add(successCountLabel);
        side.add(failCountLabel);
        side.add(wrongCountLabel);
        getContentPane().add(side, BorderLayout.EAST);
    }

    private void initInputField(){
        JPanel south = new JPanel();
        gBag = new GridBagLayout();
        south.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        south.setLayout(gBag);
        south.setBackground(new Color(255, 224, 178, 100));
        HLabel ans = new HLabel("정답");
        ans.setTextFieldLabel();
        inputField = new HTextField("", 10);
        this.submitBtn = new HButton("submit");
        gbinsert(ans, 0, 1, 1, 1);
        gbinsert(inputField, 1, 1, 1, 1);
        gbinsert(this.submitBtn, 2, 1, 1, 1);
        south.add(ans);
        south.add(inputField);
        south.add(this.submitBtn);
        getContentPane().add(south, BorderLayout.SOUTH);
    }

    public JButton getSubmitBtn() {
        return submitBtn;
    }

    public void setHiddenText(String str) {
        hiddenText.setText(str);
    }

    public JTextField getInputField() {
        return inputField;
    }

    /*
     * 카운트 현재 상태로 설정
     */
    public void setCount(CountDto dto) {
        successCountLabel.setText(String.valueOf(dto.getSuccessCount()));
        failCountLabel.setText(String.valueOf(dto.getFailCount()));
        wrongCountLabel.setText(String.valueOf(dto.getWrongCount()));
        setHangStep(dto.getWrongCount());
    }

    /*
     * 행맨 이미지 재설정
     */
    public void setHangStep(int num) {
        try {
            this.hangman.removeAll();
            BufferedImage objectImage;
            String fileName = "./resource/hang" + String.valueOf(num) + ".jpg";
            objectImage = ImageIO.read(new File(fileName));
            ImageLabel img = new ImageLabel();
            img.setIcon(new ImageIcon(objectImage));
            this.hangman.add(img);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
     * input 초기화
     */
    public void clearInput() {
        this.inputField.setText("");
    }

    /*
     * gbag 위치 설정
     */
    private void gbinsert(Component c, int x, int y, int w, int h) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gBag.setConstraints(c, gbc);
    }
}
