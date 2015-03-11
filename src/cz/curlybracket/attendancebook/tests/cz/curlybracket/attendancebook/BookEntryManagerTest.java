package cz.curlybracket.attendancebook;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.*;

import java.awt.print.Book;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.matchers.JUnitMatchers.hasItem;

@RunWith(Parameterized.class)
public class BookEntryManagerTest {

    private Class bookEntryManagerImpl;
    private BookEntryManager bookEntryManager;

    @Parameters
    public static Collection<Class> getParameters() {
        return Arrays.asList(new Class[]{
                        BookEntryManagerImpl.class
                }
        );
    }

    public BookEntryManagerTest (Class bookEntryManagerImpl) {
        this.bookEntryManagerImpl = bookEntryManagerImpl;
    }


    @Before
    public void setUp() throws Exception {
        this.bookEntryManager = (BookEntryManager) this.bookEntryManagerImpl.newInstance();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCreateCarWithNull() throws Exception {
        try {
            bookEntryManager.createNewBookEntry(null);
            fail("Did not throw a NullPointerException for empty input");
        } catch (NullPointerException ex) {
        }
    }

    @Test
    public void testCreateNewBookEntryWithId() throws Exception {
        BookEntry bookEntry = new BookEntry();
        BookEntry newBookEntry = bookEntryManager.createNewBookEntry(bookEntry);
        assertThat(newBookEntry.getId(), is(not(equalTo(null))));
    }

    @Test
    public void testUpdateBookEntry() throws Exception {

    }

    @Test
    public void testDeleteBookEntry() throws Exception {

    }

    @Test
    public void testCreateBookEntryCanBeRetrieved() throws Exception {
        BookEntry entry = new BookEntry();
        entry.setType(EntryType.Holiday);
        entry.setStartDate(new Date());
        BookEntry newEntry = bookEntryManager.createNewBookEntry(entry);
        assertThat(bookEntryManager.findAllBookEntries(), hasItem(newEntry));
    }
}