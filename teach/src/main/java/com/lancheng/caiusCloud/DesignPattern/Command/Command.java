package com.lancheng.caiusCloud.DesignPattern.Command;

/**
 * 命令基类会为所有具体命令定义通用接口
 */
public abstract class Command {

    public Editor editor;
    private String backup;

    Command(Editor editor) {
        this.editor = editor;
    }

    //备份编辑器状态
    void backup() {
        backup = editor.textField.getText();
    }

    //恢复编辑器状态
    public void undo() {
        editor.textField.setText(backup);
    }

    //执行方法被申明为抽象以强制所有的具体命令提供自己的实现，该方法必须根据命令是否更改编辑器的状态返回true或者false
    public abstract boolean execute();
}
