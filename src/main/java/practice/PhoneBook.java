package practice;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBook {
    private final Map<String, Set<String>> map = new TreeMap<>();
    private final String regexPhone = "\\d{11}";
    private final String regexName = "[a-zA-Zа-яА-Я]+";
    private final Pattern patternPhone = Pattern.compile(regexPhone);
    private final Pattern patternName = Pattern.compile(regexName);


    public boolean isPhone(String phone) {
        Matcher matcherPhone = patternPhone.matcher(phone);
        return matcherPhone.matches();
    }

    public boolean isName(String name) {
        Matcher matcherName = patternName.matcher(name);
        return matcherName.matches();
    }

    public void addContact(String phone, String name) {
        if (!isName(name) || !isPhone(phone)) return;
        for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            Set<String> value = entry.getValue();
            if (value.contains(phone)) {
                if (key.equals(name))
                    return;
                value.remove(phone);
                if (value.isEmpty()) {
                    map.remove(key);
                }
                break;
            }
        }
        Set<String> phones;
        if (map.containsKey(name)) {
            phones = map.get(name);
        } else {
            phones = new TreeSet<>();
        }
        phones.add(phone);
        map.put(name, phones);
    }


    public String getContactByPhone(String phone) {
        if (phone != null) {
            for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
                String result = entry.getKey() + " - " + entry.getValue();
                return result.replaceAll("[\\[\\]]", "");
            }
        }
        return "";
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
            String result = entry.getKey() + " - " + entry.getValue();
            keysAndValues.add(result.replaceAll("[\\[\\]]", ""));
        }
        return keysAndValues;
    }
}


