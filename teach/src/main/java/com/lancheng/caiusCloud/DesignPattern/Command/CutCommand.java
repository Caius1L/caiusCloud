package com.lancheng.caiusCloud.DesignPattern.Command;

public class CutCommand extends Command{

    public CutCommand(Editor editor) {
        super(editor);
    }

    //剪切命令改变了编辑器的状态，因此它必须被保存到历史记录中，只要方法返回true，他就会被保存
    @Override
    public boolean execute() {
        if (editor.textField.getSelectedText().isEmpty()) return false;

        backup();
        String source = editor.textField.getText();
        editor.clipboard = editor.textField.getSelectedText();
        editor.textField.setText(cutString(source));
        return true;
    }

    private String cutString(String source) {
        String start = source.substring(0, editor.textField.getSelectionStart());
        String end = source.substring(editor.textField.getSelectionEnd());
        return start + end;
    }
}
