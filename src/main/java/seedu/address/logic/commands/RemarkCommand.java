package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

public class RemarkCommand extends Command {

    public static final String COMMAND_WORD = "remark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a remark for the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_REMARK + "Birthday on 15th Feb";

    public static final String MESSAGE_ADD_REMARK_SUCCESS = "Added remark for Person: %1$s";

    private final Index targetIndex;
    private Remark remark;

    public RemarkCommand(Index targetIndex, Remark remark) {
        this.targetIndex = targetIndex;
        this.remark = remark;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToAddRemark = lastShownList.get(targetIndex.getZeroBased());
        Person personWithAddedRemark = createPersonWithRemark(personToAddRemark, remark);
        model.setPerson(personToAddRemark, personWithAddedRemark);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_ADD_REMARK_SUCCESS, personWithAddedRemark));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemarkCommand)) {
            return false;
        }

        // state check
        RemarkCommand e = (RemarkCommand) other;
        return targetIndex.equals(e.targetIndex)
                && remark.equals(e.remark);
    }

    private static Person createPersonWithRemark(Person personToAddRemark, Remark remark) {
        assert personToAddRemark != null;
        Person personWithAddedRemark = new Person(
                personToAddRemark.getName(),
                personToAddRemark.getPhone(),
                personToAddRemark.getEmail(),
                personToAddRemark.getAddress(),
                personToAddRemark.getTags()
        );

        personWithAddedRemark.setRemark(remark);
        return personWithAddedRemark;

    }

}