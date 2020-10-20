package seedu.address.logic.commands.main;


import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.MainPageCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Creates a new SortBudgetCommand.
 */
public class SortBudgetCommand extends MainPageCommand {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": sort budget by name.";

    public static final String MESSAGE_SUCCESS = "Sorted budget by name.";

    /**
     * Executes the create budget command.
     * @param model {@code Model} which the command should operate on.
     * @return the commmand result along with a success message
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.sortAllBudgetByName();
        model.repopulateObservableList();
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }
}