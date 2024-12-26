package mvc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeListener;

public class ActionBlockUI extends JPanel {

	private static final int ACTION_BLOCK_WIDTH = 300;
	private static final int ACTION_BLOCK_HEIGHT = 50;
	private static final Dimension ACTION_BLOCK_SPINNER_SIZE = new Dimension(50, 30);
	
	SpinnerUI lineNumberField;
	SpinnerUI endPointField;
	SpinnerUI startPointField;
	SpinnerUI jumpLineField;
	
	ButtonUI removeBlockButton;
	
	LabelUI actionIcon;
	
	LabelUI lineText;
	LabelUI typeText;
	LabelUI actionText;
	LabelUI jumpText;
	LabelUI immediatelyText;
	LabelUI afterwardsText;
	LabelUI minusText;
	LabelUI counterText;
	
	public ActionBlockUI(int lineNumber, ActionTypeEnum type, int endPoint, int startPoint, int jumpLine) {
		lineNumberField = new SpinnerUI(ACTION_BLOCK_SPINNER_SIZE, null, lineNumber, -1, 999, -1);
		endPointField = new SpinnerUI(ACTION_BLOCK_SPINNER_SIZE, null, endPoint, -999, 999, -1);
		startPointField = new SpinnerUI(ACTION_BLOCK_SPINNER_SIZE, null, startPoint, -999, 999, -1);
		jumpLineField = new SpinnerUI(ACTION_BLOCK_SPINNER_SIZE, null, jumpLine, -1, 999, -1);
		
		removeBlockButton = new ButtonUI(true, new Dimension(50, 50), null, IconUI.removeBlockButtonIcon);
		
		actionIcon = new LabelUI(IconUI.ICON_BUTTON_SIZE, null, IconUI.startBlockIcon);
		
		lineText = new LabelUI(new Dimension(50, 30), null, "line");
		typeText = new LabelUI(new Dimension(100, 30), null, "action type");
		actionText = new LabelUI(new Dimension(100, 50), null, type.toString());
		jumpText = new LabelUI(new Dimension(100, 50), null, "jump to line =");
		immediatelyText = new LabelUI(new Dimension(100, 50), null, "immediately");
		afterwardsText = new LabelUI(new Dimension(100, 50), null, "afterwards");
		minusText = new LabelUI(new Dimension(50, 50), null, "minus");
		counterText = new LabelUI(new Dimension(150, 50), null, "");
		
		if (type == ActionTypeEnum.LOOP) {
			setSize(new Dimension(ACTION_BLOCK_WIDTH, ACTION_BLOCK_HEIGHT * 3));
		} else if (type == ActionTypeEnum.GOTO) {
			setSize(new Dimension(ACTION_BLOCK_WIDTH, ACTION_BLOCK_HEIGHT * 2));
		} else if (type != ActionTypeEnum.UNKNOWN) {
			setSize(new Dimension(ACTION_BLOCK_WIDTH, ACTION_BLOCK_HEIGHT * 1));
		} else {
			setSize(new Dimension(ACTION_BLOCK_WIDTH, ACTION_BLOCK_HEIGHT * 0 + 30)); // stage ID bar
		}
		
		setVisible(true);
		setLayout(null);
		setPreferredSize(getSize());
		setMinimumSize(getSize());
		setMaximumSize(getSize());
		
		switch (type) {
		case UNKNOWN:
			actionIcon.setIcon(IconUI.startBlockIcon);
			break;
		case START:
			actionIcon.setIcon(IconUI.startBlockIcon);
			break;
		case END:
			actionIcon.setIcon(IconUI.endBlockIcon);
			break;
		case FORWARD:
			actionIcon.setIcon(IconUI.forwardBlockIcon);
			break;
		case BACK:
			actionIcon.setIcon(IconUI.backBlockIcon);
			break;
		case LEFT:
			actionIcon.setIcon(IconUI.leftBlockIcon);
			break;
		case RIGHT:
			actionIcon.setIcon(IconUI.rightBlockIcon);
			break;
		case GOTO:
			actionIcon.setIcon(IconUI.gotoBlockIcon);
			break;
		case LOOP:
			actionIcon.setIcon(IconUI.loopBlockIcon);
			break;
		default:
			actionIcon.setIcon(IconUI.startBlockIcon);
			break;
		}
		
		if (type == ActionTypeEnum.UNKNOWN) {
			// Header of action chain
			
			lineText.setLocation(new Point(20, 0));
			add(lineText);
			typeText.setLocation(new Point(150, 0));
			add(typeText);
		} else {
			// Typical action blocks
			
			if (type != ActionTypeEnum.START && type != ActionTypeEnum.END) {
				// START or END cannot be removed
				removeBlockButton.setLocation(new Point(230, 0));
				add(removeBlockButton);
			}
			
			actionIcon.setLocation(new Point(60, 0));
			add(actionIcon);
			lineNumberField.setLocation(new Point(10, 10));
			add(lineNumberField);
			actionText.setLocation(new Point(110, 0));
			add(actionText);
		}
		
		if (type == ActionTypeEnum.GOTO) {
			// GOTO action block
			
			jumpText.setLocation(new Point(10, 50));
			add(jumpText);
			jumpLineField.setLocation(new Point(110, 60));
			add(jumpLineField);
			immediatelyText.setLocation(new Point(165, 50));
			add(immediatelyText);
		}
		
		if (type == ActionTypeEnum.LOOP) {
			// LOOP action block
			
			endPointField.setLocation(new Point(10, 60));
			add(endPointField);
			startPointField.setLocation(new Point(110, 60));
			add(startPointField);
			jumpText.setLocation(new Point(10, 100));
			add(jumpText);
			jumpLineField.setLocation(new Point(110, 110));
			add(jumpLineField);
			afterwardsText.setLocation(new Point(165, 100));
			add(afterwardsText);
			minusText.setLocation(new Point(65, 50));
			add(minusText);
			counterText.setLocation(new Point(165, 50));
			add(counterText);
			setCounterText(endPoint - startPoint);
		}
		
		if (type != ActionTypeEnum.UNKNOWN) {
			setStatus(0);
		} else {
			setStatus(-1);
		}
	}
	
	public int getLineNumberField() {
		return (int) lineNumberField.getValue();
	}
	
	public int getEndPointField() {
		return (int) endPointField.getValue();
	}
	
	public int getStartPointField() {
		return (int) startPointField.getValue();
	}
	
	public int getJumpLineField() {
		return (int) jumpLineField.getValue();
	}
	
	public void setLineNumberField(int value) {
		lineNumberField.setValue(value);
		repaint();
		revalidate();
	}
	
	public void setEndPointField(int value) {
		endPointField.setValue(value);
		repaint();
		revalidate();
	}
	
	public void setStartPointField(int value) {
		startPointField.setValue(value);
		repaint();
		revalidate();
	}
	
	public void setJumpLineField(int value) {
		jumpLineField.setValue(value);
		repaint();
		revalidate();
	}
	
	public void setCounterText(int value) {
		counterText.setText("= (" + value + ") times");
		repaint();
		revalidate();
	}
	
	/**
	 * Mutate the status of the action block.
	 * @param status 0 if inactive, 1 if current, 2 if next.
	 */
	public void setStatus(int status) {
		if (status == 2) {
			setBackground(IconUI.actionBlockNext);
		} else if (status == 1) {
			setBackground(IconUI.actionBlockCurrent);
		} else if (status == 0) {
			setBackground(IconUI.actionBlockIdle);
		} else {
			setBackground(IconUI.actionBlockHeader);
		}
		
		setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		
		repaint();
		revalidate();
	}
	
	// ActionListeners
	
	public void removeBlockButton(ActionListener actionListener) {
		removeBlockButton.addActionListener(actionListener);
	}
	
	// ChangeListeners
	
	public void lineNumberField(ChangeListener changeListener) {
		lineNumberField.addChangeListener(changeListener);
	}
	
	public void endPointField(ChangeListener changeListener) {
		endPointField.addChangeListener(changeListener);
	}
	
	public void startPointField(ChangeListener changeListener) {
		startPointField.addChangeListener(changeListener);
	}
	
	public void jumpLineField(ChangeListener changeListener) {
		jumpLineField.addChangeListener(changeListener);
	}
	
}
