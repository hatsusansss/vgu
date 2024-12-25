package ru.vsu.cs.solution;

import ru.vsu.cs.solution.lists.Logic;
import ru.vsu.cs.util.ArrayUtils;
import ru.vsu.cs.util.JTableUtils;
import ru.vsu.cs.util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FrameMain extends JFrame {
    private JPanel mainPanel;
    private JTable tableInput;
    private JPanel bottomPanel;
    private JButton buttonDownloadFromFile;
    private JButton buttonLogic;
    private JButton buttonSafeIntoFile;
    private JTable tableOutput;
    private JScrollPane input;
    private JScrollPane output;
    private JButton buttonRandomInput;
    private JFileChooser fileChooserOpen;
    private JFileChooser fileChooserSave;
    private JMenuBar menuBarMain;
    private JMenu menuLookAndFeel;

    public FrameMain() {
        this.setTitle("Task 2 - Amazing it works\n");
        this.setContentPane(mainPanel);
        this.setPreferredSize(new Dimension(750, 475));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

        JTableUtils.initJTableForArray(tableInput, 40, true, true,
                false, true);
        JTableUtils.initJTableForArray(tableOutput, 40, true, true,
                false, true);
        tableOutput.setEnabled(false);
        tableInput.setRowHeight(25);
        tableOutput.setRowHeight(25);

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        menuBarMain = new JMenuBar();
        setJMenuBar(menuBarMain);

        menuLookAndFeel = new JMenu();
        menuLookAndFeel.setText("Вид");
        menuBarMain.add(menuLookAndFeel);
        SwingUtils.initLookAndFeelMenu(menuLookAndFeel);

        int[] arr = {7, 10, 0, -78};

        JTableUtils.writeArrayToJTable(tableInput, arr);

        this.pack();

        buttonDownloadFromFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserOpen.showOpenDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {
                        int[] arr = ArrayUtils.readIntArrayFromFile(fileChooserOpen.getSelectedFile().getPath());
                        JTableUtils.writeArrayToJTable(tableInput, arr);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
        buttonRandomInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int[] matrix = ArrayUtils.createRandomIntArray(
                            tableInput.getColumnCount(), tableInput.getRowCount(), 100);
                    JTableUtils.writeArrayToJTable(tableInput, matrix);
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
        buttonLogic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int[] arr = JTableUtils.readIntArrayFromJTable(tableInput);
                    arr = Logic.logic(arr);

                    JTableUtils.writeArrayToJTable(tableOutput, arr);
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        buttonSafeIntoFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserSave.showSaveDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {
                        int[][] matrix = JTableUtils.readIntMatrixFromJTable(tableOutput);
                        String file = fileChooserSave.getSelectedFile().getPath();
                        if (!file.toLowerCase().endsWith(".txt")) {
                            file += ".txt";
                        }
                        ArrayUtils.writeArrayToFile(file, matrix);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
    }
}
