package practice;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBook {
    private Map<String, Set<String>> map = new TreeMap<>();
    private String regexPhone = "\\d{11}";
    private String regexName = "^[a-zA-Zа-яА-Я]+$";
    private Pattern patternPhone = Pattern.compile(regexPhone);
    private Pattern patternName = Pattern.compile(regexName);

    public boolean isPhone(String phone) {
        Matcher matcherPhone = patternPhone.matcher(phone);
        return matcherPhone.matches();
    }

    public boolean isName(String name) {
        Matcher matcherName = patternName.matcher(name);
        return matcherName.matches();
    }

    public void addContact(String phone, String name) {
        if (isPhone(phone) && isName(name)) {
            if (map.containsKey(name)) {
                map.replace(phone, map.get(name), Collections.singleton(name));

            } else {
                map.put(phone, Collections.singleton(name));
            }
        }
    }

    public String getContactByPhone(String phone) {
        Set<String> name = map.get(phone);
        if (name != null) {
            String s = name + " - " + phone;
            return s.replaceAll("[\\[\\]]", "");
        } else {
            return "";
        }
    }

    public Set<String> getContactByName(String name) {
        Set<String> nameContact = new TreeSet<>();
        if (map.containsKey(name)) {
            Set<String> strings = map.get(name);
            String contact = name + " - " + strings;
            nameContact.add(contact.replaceAll("[\\[\\]]", ""));
        }
        return nameContact;
    }

    public Set<String> getAllContacts() {
        Set<String> keysAndValues = new TreeSet<>();
        for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
            String result = entry.getValue() + " - " + entry.getKey();
            keysAndValues.add(result.replaceAll("[\\[\\]]", ""));
        }
        return keysAndValues;
    }
}