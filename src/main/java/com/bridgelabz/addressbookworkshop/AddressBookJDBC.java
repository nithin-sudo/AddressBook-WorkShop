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
}
