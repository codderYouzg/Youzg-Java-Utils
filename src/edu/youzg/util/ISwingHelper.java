package edu.youzg.util;

import edu.youzg.util.exceptions.FrameIsNullException;

import javax.swing.*;
import java.awt.*;

/**
 * 用于 快速、便捷 搭建Swing页面的小工具
 * @author Youzg
 */
public interface ISwingHelper {
    Font topicFont = new Font("微软雅黑", Font.BOLD, 32);
    Font normalFont = new Font("宋体", Font.PLAIN, 16);
    Font smallFont = new Font("宋体", Font.PLAIN, 14);
    Font tinyFont = new Font("宋体", Font.PLAIN, 12);

    int topicFontSize = topicFont.getSize();
    int normalFontSize = normalFont.getSize();

    Color topicColor = new Color(3, 31, 203);

    int PADDING = 5;

    Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);

    default void initView() {
        init();
        dealEvent();
    }

    void init();

    void reinit();

    void dealEvent();

    RootPaneContainer getFrame();

    default void show() {
        try {
            showView();
        } catch (FrameIsNullException e) {
            e.printStackTrace();
        }
    }

    default void showView() throws FrameIsNullException {
        RootPaneContainer frame = getFrame();
        if (frame == null) {
            throw new FrameIsNullException("窗口为NULL！");
        }
        if (frame instanceof JFrame) {
            ((JFrame) frame).setVisible(true);
        } else if (frame instanceof JDialog) {
            ((JDialog) frame).setVisible(true);
        } else if (frame instanceof JInternalFrame) {
            ((JInternalFrame) frame).setVisible(true);
        }
        reinit();
    }

    default void exit() {
        try {
            exitView();
        } catch (FrameIsNullException e) {
            e.printStackTrace();
        }
    }

    default void exitView() throws FrameIsNullException {
        RootPaneContainer frame = getFrame();
        if (frame == null) {
            throw new FrameIsNullException("窗口为NULL！");
        }
        if (frame instanceof JFrame) {
            ((JFrame) frame).dispose();
        } else if (frame instanceof JDialog) {
            ((JDialog) frame).dispose();
        } else if (frame instanceof JInternalFrame) {
            ((JInternalFrame) frame).dispose();
        }
    }

}
