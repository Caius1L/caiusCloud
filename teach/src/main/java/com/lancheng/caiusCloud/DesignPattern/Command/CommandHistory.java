package com.lancheng.caiusCloud.DesignPattern.Command;

import java.util.Stack;

public class CommandHistory {

    //全局命令历史记录就是一个堆栈
    private Stack<Command> history = new Stack<>();

    public void push(Command c) {
        history.push(c);
    }

    public Command pop() {
        return history.pop();
    }

    public boolean isEmpty() { return history.isEmpty(); }
}
