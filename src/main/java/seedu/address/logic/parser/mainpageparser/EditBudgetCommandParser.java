package seedu.address.logic.parser.mainpageparser;

import seedu.address.logic.commands.main.EditBudgetCommand;
import seedu.address.logic.commands.main.EditBudgetCommand.EditBudgetDescriptor;
import seedu.address.logic.parser.*;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.budget.Budget;
import seedu.address.state.budgetindex.BudgetIndex;

import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;

public class EditBudgetCommandParser implements Parser<EditBudgetCommand> {
    @Override
    public EditBudgetCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PRICE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                && !arePrefixesPresent(argMultimap, PREFIX_PRICE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditBudgetCommand.MESSAGE_USAGE));
        }

        BudgetIndex budgetIndex;
        try {
            budgetIndex = ParserUtil.parseBudgetIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,EditBudgetCommand.MESSAGE_USAGE));
        }

        EditBudgetDescriptor editBudgetDescriptor = new EditBudgetDescriptor();
        if(argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editBudgetDescriptor.setName(ParserUtil.parseBudgetName((argMultimap.getValue(PREFIX_NAME).get())));
        }
        if(argMultimap.getValue(PREFIX_PRICE).isPresent()) {
            editBudgetDescriptor.setThreshold(ParserUtil.parseBudgetThreshold(argMultimap.getValue(PREFIX_PRICE).get()));
        }

        return new EditBudgetCommand(budgetIndex, editBudgetDescriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
