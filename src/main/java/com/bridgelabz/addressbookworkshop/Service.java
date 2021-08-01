package com.bridgelabz.addressbookworkshop;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Service {
    static Scanner scanner = new Scanner(System.in);
    ArrayList<Person> personList = new ArrayList<>();
    private Map<String, ArrayList<Person>> addressBooks = new HashMap<>();
    public static final String FILE_PATH="C:\\Users\\nithinkrishna\\Desktop";
    public static String addressBookFile = "AddressBookFile.txt";
    public static final String CSV_FILE="/addressBook.csv";
    public void addNewContact() {
        Person person = new Person();
        System.out.println("Enter First name:");
        person.setFirstName(scanner.next());

        System.out.println("Enter Last Name:");
        person.setLastName(scanner.next());

        System.out.println("Enter Address:");
        person.setAddress(scanner.next());

        System.out.println("Enter City:");
        person.setCity(scanner.next());

        System.out.println("Enter State:");
        person.setState(scanner.next());

        System.out.println("Enter Zip:");
        person.setZip(scanner.next());

        System.out.println("Enter Email:");
        person.setEmail(scanner.next());

        System.out.println("Enter Phone:");
        person.setPhoneNumber(scanner.next());
        System.out.println("Enter Book name to which you have to add contact");
        String bookName = scanner.next();
        if (addressBooks.containsKey(bookName))
        {
            personList.stream().filter(value -> value.getFirstName().equals(person.getFirstName())).forEach(value ->
            {
                System.out.println("duplicate contact");
                addNewContact();
            });
            personList.add(person);
            addressBooks.put(bookName, personList);
            System.out.println("New Contact Added Successfully");
        }
        else
        {
            personList.add(person);
            addressBooks.put(bookName, personList);
            System.out.println("New book created and added Contact Added Successfully");
        }
    }
    public void writeToFile()
    {
        StringBuffer addressBuffer = new StringBuffer();
        personList.forEach(address -> { String addressDataString = address.toString().concat("\n");addressBuffer.append(addressDataString);});
        try
        {
            Files.write(Paths.get(addressBookFile),addressBuffer.toString().getBytes(StandardCharsets.UTF_8));
            System.out.println("Data successfully written to file.");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void readDataFromFile()
    {
        try
        {
            System.out.println("Reading Data From File :");
            Files.lines(new File(addressBookFile).toPath()).map(line -> line.trim()).forEach(line -> System.out.println(line));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void editContact(String bookName) {
        if (addressBooks.containsKey(bookName)) {
            String enteredFirstName;
            System.out.println("Enter First name of contact to edit it ");
            enteredFirstName = scanner.next();
            for (int i = 0; i < personList.size(); i++) {
                if (personList.get(i).getFirstName().equals(enteredFirstName)) {
                    System.out.println("Enter the field to edit:\n1.First Name\n2.Last Name\n3.Address\n4.city\n5.State\n6.Zip\n7.Phone\n8.Email");
                    int userInput = scanner.nextInt();
                    switch (userInput) {
                        case 1:
                            System.out.println("Enter new first name");
                            personList.get(i).setFirstName(scanner.next());
                            break;
                        case 2:
                            System.out.println("Enter new last name");
                            personList.get(i).setLastName(scanner.next());
                            break;
                        case 3:
                            System.out.println("Enter new Address");
                            personList.get(i).setAddress(scanner.next());
                            break;
                        case 4:
                            System.out.println("Enter new city");
                            personList.get(i).setCity(scanner.next());
                            break;
                        case 5:
                            System.out.println("Enter new state");
                            personList.get(i).setState(scanner.next());
                            break;
                        case 6:
                            System.out.println("Enter new zip");
                            personList.get(i).setZip(scanner.next());
                            break;
                        case 7:
                            System.out.println("Enter new phone number");
                            personList.get(i).setPhoneNumber(scanner.next());
                            break;
                        case 8:
                            System.out.println("Enter new email");
                            personList.get(i).setEmail(scanner.next());
                            break;
                        default:
                            System.out.println("Invalid Entry");
                    }
                }
            }
        }
    }

    /**
     * deleting a person using person's name
     *
     * @param name
     */
    public void deleteContact(String name, String bookName) {
        if (addressBooks.containsKey(bookName)) {
            for (int i = 0; i < personList.size(); i++) {
                if (personList.get(i).getFirstName().equals(name)) {
                    Person person = personList.get(i);
                    personList.remove(person);
                }
            }
        }
    }
    public void searchPersonInACity (String city)
    {
        System.out.println("following are the persons who belongs to :" + city);
        for(String bookName : addressBooks.keySet())
        {
            addressBooks.get(bookName);
            personList.stream().filter(value -> value.getCity().equals(city)).map(Person::getFirstName).forEach(System.out::println);
        }
    }
    public void viewPersonInACity (String city)
    {
        for(String bookName : addressBooks.keySet())
        {
            int countPerson = 0;
            addressBooks.get(bookName);
            personList.stream().filter(value -> value.getCity().equals(city)).map(Person::getFirstName).forEach(System.out::println);
            countPerson++;
            System.out.println("total persons:"+countPerson);
        }
    }
    public void sortByName() {
        addressBooks.keySet().forEach((String name) -> {
            addressBooks.get(name).stream().sorted(Comparator.comparing(Person::getFirstName))
                    .collect(Collectors.toList()).forEach(person -> System.out.println(person.toString()));
        });
    }
    public void sortByCity() {
        addressBooks.keySet().forEach((String key) -> {
            addressBooks.get(key).stream()
                    .sorted(Comparator.comparing(Person::getCity))
                    .collect(Collectors.toList())
                    .forEach(person -> System.out.println(person.toString()));
        });
    }
    public void displayList() {
        for (Person iterator : personList) System.out.println(iterator);
    }
    public void writeToCsv()
    {
        try
        {
            Writer writer = Files.newBufferedWriter(Paths.get(FILE_PATH+CSV_FILE));
            StatefulBeanToCsv<Person> beanToCsv = new StatefulBeanToCsvBuilder<Person>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();
            List<Person> ContactList = new ArrayList<>();
            addressBooks.entrySet().stream()
                    .map(books->books.getKey())
                    .map(bookNames->{
                        return addressBooks.get(bookNames);
                    }).forEach(contacts ->{
                ContactList.addAll(contacts);
            });
            beanToCsv.write(ContactList);
            writer.close();
        }
        catch (CsvDataTypeMismatchException e)
        {
            e.printStackTrace();
        }
        catch (CsvRequiredFieldEmptyException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void readFromCsvFile()
    {
        Reader reader;
        try {
            reader = Files.newBufferedReader(Paths.get(FILE_PATH+CSV_FILE));
            CsvToBean<Person> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Person.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<Person> contacts = csvToBean.parse();

            for(Person contact: contacts) {
                System.out.println("Name : " + contact.getFirstName()+" "+contact.getLastName());
                System.out.println("Email : " + contact.getEmail());
                System.out.println("PhoneNo : " + contact.getPhoneNumber());
                System.out.println("Address : " + contact.getAddress());
                System.out.println("State : " + contact.getState());
                System.out.println("City : " + contact.getCity());
                System.out.println("Zip : " + contact.getZip());
                System.out.println("==========================");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
