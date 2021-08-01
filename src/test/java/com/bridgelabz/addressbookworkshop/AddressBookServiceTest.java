package com.bridgelabz.addressbookworkshop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
public class AddressBookServiceTest {
    @Test
    public void givenAddressBookContactsInDB_WhenRetrieved_ShouldMatchContactsCount() throws AddressBookException {
        AddressBookJDBC addressBookJDBC = new AddressBookJDBC();
        List<Person> addressBookData = addressBookJDBC.readAddressBookData(AddressBookJDBC.IOService.DB_IO);
        Assertions.assertEquals(4, addressBookData.size());
    }
    @Test
    public void givenAddressBook_WhenUpdate_ShouldSyncWithDB() throws AddressBookException {
        AddressBookJDBC addressBookJDBC = new AddressBookJDBC();
        addressBookJDBC.updateRecord("Nithin", "GKW Lauout");
        boolean result = addressBookJDBC.checkUpdatedRecordSyncWithDatabase("Nithin");
        Assertions.assertTrue(result);
    }
    @Test
    public void givenAddressBook_WhenRetrieved_ShouldMatchCountInGivenRange() throws AddressBookException {
        AddressBookJDBC addressBookJDBC = new AddressBookJDBC();
        List<Person> addressBookData = addressBookJDBC.readAddressBookData(AddressBookJDBC.IOService.DB_IO, "2018-02-14", "2020-06-02");
        Assertions.assertEquals(3, addressBookData.size());
    }
    @Test
    public void givenAddressBook_WhenRetrieved_ShouldReturnCountOfCity() throws AddressBookException {
        AddressBookJDBC addressBookJDBC = new AddressBookJDBC();
        Assertions.assertEquals(2, addressBookJDBC.readAddressBookData("count", "Tirumala"));
    }
}
