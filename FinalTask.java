import java.util.*;

public class FinalTask {
    public static void main(String[] args) {
        Map<String, Map<String, Set<String>>> phoneBook = new HashMap<>();

        List<Contact> contacts = Arrays.asList(
            new Contact("Денис", "Рабочий1", "+7 (900) 317-18-35"),
            new Contact("Денис", "Домашний", "+7 (958) 555-82-01"),
            new Contact("Тимофей", "Домашний", "+7 (932) 923-77-66"),
            new Contact("Тимофей", "Рабочий1", "+7 (902) 952-82-63"),
            new Contact("Шаров", "Домашний", "+7 (900) 379-59-19"),
            new Contact("Денис", "Рабочий2", "+7 (711) 391-14-33")
        );

        for (Contact contact : contacts) {
            if (!phoneBook.containsKey(contact.name)) {
                phoneBook.put(contact.name, new HashMap<>());
            }
            if (!phoneBook.get(contact.name).containsKey(contact.type)) {
                phoneBook.get(contact.name).put(contact.type, new HashSet<>());
            }
            phoneBook.get(contact.name).get(contact.type).add(contact.phone);
        }

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("Выберите действие:");
            System.out.println("1. Просмотреть контакт");
            System.out.println("2. Изменить номер телефона");
            System.out.println("3. Добавить новый контакт");
            System.out.println("4. Выйти");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Введите имя человека: ");
                    String name = scanner.nextLine();

                    if (!phoneBook.containsKey(name)) {
                        System.out.println("Контакт с именем " + name + " не найден.");
                        break;
                    }

                    System.out.println(name + ": ");
                    Map<String, Set<String>> phones = phoneBook.get(name);
                    phones.entrySet().stream()
                            .sorted((e1, e2) -> Integer.compare(e2.getValue().size(), e1.getValue().size()))
                            .forEach(entry -> {
                                System.out.println("  " + entry.getKey() + ": " + String.join(", ", entry.getValue()));
                            });
                    break;
                case 2:
                    System.out.print("Введите имя человека: ");
                    name = scanner.nextLine();

                    if (!phoneBook.containsKey(name)) {
                        System.out.println("Контакт с именем " + name + " не найден.");
                        break;
                    }

                    System.out.println(name + ": ");
                    phones = phoneBook.get(name);
                    phones.entrySet().stream()
                            .sorted((e1, e2) -> Integer.compare(e2.getValue().size(), e1.getValue().size()))
                            .forEach(entry -> {
                                System.out.println("  " + entry.getKey() + ": " + String.join(", ", entry.getValue()));
                            });

                    System.out.print("Введите тип номера (домашний, рабочий1, рабочий2): ");
                    String type = scanner.nextLine();
                    System.out.print("Введите новый номер телефона: ");
                    String newPhone = scanner.nextLine();

                    if (!phones.containsKey(type)) {
                        phones.put(type, new HashSet<>());
                    }
                    phones.get(type).clear();
                    phones.get(type).add(newPhone);

                    System.out.println("Номер телефона успешно изменен.");
                    break;
                case 3:
                    System.out.print("Введите имя человека: ");
                    name = scanner.nextLine();
                    System.out.print("Введите тип номера (домашний, рабочий1, рабочий2): ");
                    type = scanner.nextLine();
                    System.out.print("Введите номер телефона: ");
                    String phone = scanner.nextLine();

                    if (!phoneBook.containsKey(name)) {
                        phoneBook.put(name, new HashMap<>());
                    }
                    if (!phoneBook.get(name).containsKey(type)) {
                        phoneBook.get(name).put(type, new HashSet<>());
                    }
                    phoneBook.get(name).get(type).add(phone);

                    System.out.println("Контакт успешно добавлен.");
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Некорректный выбор.");
                    break;
            }
        }
    }

    static class Contact {
        String name;
        String type;
        String phone;

        Contact(String name, String type, String phone) {
            this.name = name;
            this.type = type;
            this.phone = phone;
        }
    }
}
