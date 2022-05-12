package practice;

import java.util.Scanner;


public class Main {
    private static String regexList = "List";
    private static String regexPhone = "\\d{11}";
    private static String regexName = "[a-zA-Zа-яА-Я]+";
    private static PhoneBook phoneBook = new PhoneBook();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("Введите номер, имя или команду: ");
            String input = scanner.nextLine();
            if (input.matches(regexList)) {
                System.out.println(phoneBook.getAllContacts());
            } else if (input.matches(regexName)) {
                if (phoneBook.getContactByName(input).isEmpty()) {
                    System.out.println("Такого имени в телефонной книге нет.");
                    System.out.println("Введите номер телефона для абонента " + input);
                    String input1 = scanner.nextLine();
                    if (input1.matches(regexPhone)) {
                        phoneBook.addContact(input1, input);
                        System.out.println("Контакт сохранен");
                    } else {
                        System.out.println("Неверный формат ввода номера");
                    }
                } else {
                    System.out.println(phoneBook.getAllContacts());
                }
            } else if (input.matches(regexPhone)) {
                if (phoneBook.getContactByPhone(input).contains(phoneBook.getContactByPhone(input))) {
                    System.out.println("Такого номера нет в телефонной книге.");
                    System.out.println("Введите имя абонента для номера " + input);
                    String input2 = scanner.nextLine();
                    if (input2.matches(regexName)) {
                        phoneBook.addContact(input, input2);
                        System.out.println("Контакт сохранен");
                    } else {
                        System.out.println("Неверный формат ввода имени");
                    }
                } else {
                    System.out.println(phoneBook.getAllContacts());
                }
            } else if (!input.matches(regexName) || !input.matches(regexPhone)) {
                System.out.println("Имя или номер не корректны");
            }
        }
    }
}


