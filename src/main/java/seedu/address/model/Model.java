package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.budget.Budget;
import seedu.address.model.expenditure.Expenditure;
import seedu.address.state.Page;
import seedu.address.state.budgetindex.BudgetIndex;
import seedu.address.state.expenditureindex.ExpenditureIndex;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Renderable> PREDICATE_SHOW_ALL_RENDERABLES = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getNusaveFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setNusavePath(Path nusaveFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setNusave(ReadOnlyNusave nusave);

    ReadOnlyNusave getNusave();

    void openBudget(BudgetIndex budgetIndex);

    void closeBudget();

    void addBudget(Budget budget);

    void editBudget(Budget oldBudget, Budget editedBudget);

    void deleteBudget(BudgetIndex budget) throws CommandException;

    void deleteAllBudgets();

    void sortBudgetsByName();

    void sortBudgetsByCreatedDate() throws CommandException;

    void deleteExpenditure(ExpenditureIndex expenditure) throws CommandException;

    void addExpenditure(Expenditure expenditure) throws CommandException;

    void sortExpendituresByName();

    String calculateExpenditureValue(BudgetIndex budgetIndex);

    void editExpenditure(Expenditure oldExpenditure, Expenditure editedExpenditure);

    Page getPage();

    String getPageName(BudgetIndex index);

    String getPageTitle();

    String getTotalExpenditureValue();

    String getThresholdValue();

    boolean isBudgetPage();

    BooleanProperty getBudgetPageProp();

    StringProperty getTotalExpenditureStringProp();

    void setPage(Page page);

    void setTotalExpenditure(String expenditure);

    void setBudgetIndex(BudgetIndex index);

    void setPageName(String page);

    boolean isMain();

    boolean isBudget();

    boolean isWithinRange(BudgetIndex budgetIndex);

    boolean isWithinRange(ExpenditureIndex expenditureIndex);

    /** Returns an unmodifiable view of the filtered renderable list */
    ObservableList<Renderable> getFilteredRenderableList();

    void updateFilteredRenderableList(Predicate<Renderable> predicate);

    void repopulateObservableList() throws CommandException;
}
