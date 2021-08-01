package com.bridgelabz.addressbookworkshop;

import java.util.List;

public class AddressBookJDBC {
    public enum IOService {
        DB_IO
    }

    private List<Person> addressBookList;
    private static Service addressBookDBService;

    public AddressBookJDBC() {
        addressBookDBService = Service.getInstance();
    }

    public AddressBookJDBC(List<Person> addresBookList) {
        this();
        this.addressBookList = addressBookList;
    }

    public List<Person> readAddressBookData(IOService ioservice) throws AddressBookException {
        if (ioservice.equals(IOService.DB_IO))
            this.addressBookList = addressBookDBService.readData();
        return this.addressBookList;
    }
    public void updateRecord(String firstname, String address) throws AddressBookException {
        int result = addressBookDBService.updateAddressBookData(firstname, address);
        if (result == 0)
            return;
        Person addressBookData = this.getAddressBookData(firstname);
        if (addressBookData != null)
            addressBookData.setAddress(address);
    }

    public boolean checkUpdatedRecordSyncWithDatabase(String firstname) throws AddressBookException {
        try {
            List<Person> addressBookData = addressBookDBService.getAddressBookData(firstname);
            return addressBookData.get(0).equals(getAddressBookData(firstname));
        } catch (AddressBookException e) {
            throw new AddressBookException(e.getMessage(), AddressBookException.ExceptionType.DATABASE_EXCEPTION);
        }
    }

    private Person getAddressBookData(String firstname) {
        for (Person addressBookItem : addressBookList) {
            if (addressBookItem.getFirstName().equals(firstname)) {
                return addressBookItem;
            }
        }
        return null;
    }
}
