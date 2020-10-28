package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBudgets.getTypicalNusave;
import static seedu.address.testutil.TypicalExpenditures.getTypicalExpenditures;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.model.expenditure.Expenditure;
import seedu.address.state.budgetindex.BudgetIndexManager;

class NusaveTest {

    private final Nusave nusave = new Nusave();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), nusave.getBudgetList());
    }

    @Test
    void getPageName() {
        nusave.resetData(getTypicalNusave());
        assertEquals(nusave.getPageName(new BudgetIndexManager(0)), "McDonalds");
    }

    @Test
    void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nusave.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        Nusave newData = getTypicalNusave();
        nusave.resetData(newData);
        assertEquals(nusave.getBudgetList(), newData.getBudgetList());
    }

    @Test
    void addExpenditure() {
        nusave.resetData(getTypicalNusave());
        List<Expenditure> list = getTypicalExpenditures();
        nusave.addExpenditure(list.get(0), Optional.of(0));
        assertEquals(nusave.getInternalList().get(0), list.get(0));
    }

    @Test
    void editExpenditure() {
        nusave.resetData(getTypicalNusave());
        List<Expenditure> list = getTypicalExpenditures();
        nusave.addExpenditure(list.get(0), Optional.of(0));
        nusave.editExpenditure(list.get(0), list.get(1), Optional.of(0));
        assertEquals(nusave.getInternalList().get(0), list.get(1));
    }


    @Test
    void getTotalExpenditureValue_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nusave.getTotalExpenditureValue(null));
    }


    @Test
    void repopulateObservableList() {
    }

    @Test
    void getBudgetList() {
    }

    @Test
    void getInternalList() {
    }

}