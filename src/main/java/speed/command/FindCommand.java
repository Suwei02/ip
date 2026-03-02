package speed.command;

import speed.exception.SpeedException;
import speed.storage.Storage;
import speed.task.Task;
import speed.task.TaskList;
import speed.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpeedException {
        ArrayList<Task> matchedTasks = tasks.find(keyword);
        ui.showFindList(matchedTasks);
    }
}
