package practice;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBook {
    private Map<String,Set<String>> map = new TreeMap<>();
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
                map.replace(name,map.get(name),Collections.singleton(phone));
            } else {
                map.put(name, Collections.singleton(phone));
            }
        }
    }

    public String getContactByPhone(String phone) {
        if (map.containsKey(phone)) {
            return map.get(phone ) + " - " +  phone;
        } else {
            return "";
        }
    }

    public Set<String> getContactByName(String name) {
        Set<String> contactName = new TreeSet<>();
        return new TreeSet<>();
    }

    public Set<String> getAllContacts() {
        Set<String> keysAndValues = new TreeSet<>();
        for (Map.Entry<String,Set<String>> entry : map.entrySet()) {
            keysAndValues.add(entry.getKey() + " - " + entry.getValue());
        }
        return keysAndValues;
    }
}