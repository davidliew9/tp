package seedu.address.logic.commands.main;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpBudgetCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Here are the list of available command:\n"
            + "1. help:\n"
            + "Shows a list of available commands with descriptions in NUSave.\n"
            + "2. open INDEX:\n"
            + "Opens a budget with the given INDEX in NUSave.\n"
            + "3. create /nNAME:\n"
            + "Creates a new budget with the given NAME in NUSave.\n"
            + "4. delete INDEX:\n"
            + "Deletes the specific budget at the given INDEX from NUSave.\n"
            + "5. list:\n"
            + "Shows a list of all budgets in NUSave.\n"
            + "6. find KEYWORD:\n"
            + "Finds budgets whose names contain the given KEYWORD.\n"
            + "7. sort:\n"
            + "Sort all budgets in NUSave by name.\n"
            + "8. exit:\n"
            + "Exits the program.\n";

    /**
     * Executes the help command.
     * @param model {@code Model} which the command should operate on.
     * @return the commmand result along with a success message
     */
    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, false, false);
    }
}