package edu.youzg.util;

import javax.swing.*;

/**
 * 用于 显示 信息、警告、严重错误、选择 框<br/>
 * 信息内容 需要自定义
 * @author Youzg
 */
public class PromptBoxTool {
    public static final String DEFAULT_TITLE = "右转哥温馨提示";

    private static String title;

    public PromptBoxTool() {
        title = DEFAULT_TITLE;
    }

    public PromptBoxTool(String title) {
        title = title;
    }

    public void setTitle(String title) {
        title = title;
    }

    /**
     * 显示 信息 模态框
     * @param jfame 父frame
     * @param message 模态框内 要显示的信息
     */
    public static void showMessage(JFrame jfame, String message) {
        JOptionPane.showMessageDialog(jfame, message, title, -1);
    }

    /**
     * 显示 警告 模态框
     * @param jfame 父frame
     * @param message 模态框内 要显示的信息
     */
    public static void showWarnning(JFrame jfame, String message) {
        JOptionPane.showMessageDialog(jfame, message, title, 2);
    }

    /**
     * 显示 严重错误 模态框
     * @param jfame 父frame
     * @param message 模态框内 要显示的信息
     */
    public static void showError(JFrame jfame, String message) {
        JOptionPane.showMessageDialog(jfame, message, title, 0);
    }

    /**
     * 显示 选择 模态框
     * @param jfame 父frame
     * @param message 模态框内 要显示的信息
     * @param optionType 选择值
     * @return 选择的值
     */
    public static int getChoice(JFrame jfame, String message, int optionType) {
        return JOptionPane.showConfirmDialog(jfame, message, title, optionType);
    }

}
