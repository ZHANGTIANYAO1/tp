package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEWTIME;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.InterviewTime;
import seedu.address.model.person.InterviewTimeContainsKeywordsPredicate;


/**
 * Filters and lists all contacts in address book whose InterviewTime is within the interview time range provided.
 */
public class FilterInterviewTimeCommand extends FilterCommand {
    public static final String COMMAND_WORD = FilterCommand.COMMAND_WORD + " " + PREFIX_INTERVIEWTIME;
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all companies whose "
            + "range of interview times contain any of "
            + "the specified salary and displays them as a list with index numbers.\n"
            + "Parameters: INTERVIEW_TIME_RANGE [MORE_INTERVIEW_TIME_RANGE]...\n"
            + "Example:\n"
            + "- " + COMMAND_WORD + "before/020220241100\n"
            + "- " + COMMAND_WORD + "after/020220241100\n"
            + "- " + COMMAND_WORD + "from/020220241100-020520241100";
    public static final String WRONG_INTERVIEW_TIME_RANGE_MESSAGE = "INTERVIEW_TIME_RANGE should be in the form of:\n"
            + "before/INTERVIEW_TIME\nor\n"
            + "after/INTERVIEW_TIME\nor\n"
            + "from/INTERVIEW_TIME-INTERVIEW_TIME\n"
            + "where INTERVIEW_TIME is in the form: " + InterviewTime.INTERVIEW_TIME_FORMAT
            + "\n Example: from/" + InterviewTime.EXAMPLE_INTERVIEW_TIME_FORMAT_1 + "-"
            + InterviewTime.EXAMPLE_INTERVIEW_TIME_FORMAT_2;

    private final InterviewTimeContainsKeywordsPredicate predicate;

    public FilterInterviewTimeCommand(InterviewTimeContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterInterviewTimeCommand)) {
            return false;
        }

        FilterInterviewTimeCommand otherFilterInterviewTimeCommand = (FilterInterviewTimeCommand) other;
        return predicate.equals(otherFilterInterviewTimeCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("interview time", predicate)
                .toString();
    }
}
