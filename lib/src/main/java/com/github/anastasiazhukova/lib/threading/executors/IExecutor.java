package com.github.anastasiazhukova.lib.threading.executors;

import com.github.anastasiazhukova.lib.threading.IExecutedCallback;
import com.github.anastasiazhukova.lib.threading.command.ICommand;

import java.util.List;

public interface IExecutor {

    <Result> void execute(ICommand<Result> pCommand);

    void execute(List<? extends ICommand> pCommands);

    void execute(List<? extends ICommand> pCommands, IExecutedCallback pExecutedCallback);

}
