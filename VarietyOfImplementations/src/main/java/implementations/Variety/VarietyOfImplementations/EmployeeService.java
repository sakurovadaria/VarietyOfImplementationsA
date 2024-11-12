package implementations.Variety.VarietyOfImplementations;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeService {
    private final Map<String, Employee> employees = new HashMap<String, Employee>(); // Хранилище сотрудников

    // Метод для добавления сотрудника
    public void addEmployee(String firstName, String lastName) {
        String key = generateKey(firstName, lastName);

        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException("Сотрудник с именем " + firstName + " и фамилией " + lastName + " уже добавлен.");
        }

        employees.put(key, new Employee(firstName, lastName)); // Добавляем нового сотрудника
    }

    // Метод для удаления сотрудника
    public void removeEmployee(String firstName, String lastName) {
        String key = generateKey(firstName, lastName);

        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException("Сотрудник с именем " + firstName + " и фамилией " + lastName + " не найден.");
        }

        employees.remove(key); // Удаляем сотрудника
    }

    // Метод для поиска сотрудника
    public Employee findEmployee(String firstName, String lastName) {
        String key = generateKey(firstName, lastName);

        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException("Сотрудник с именем " + firstName + " и фамилией " + lastName + " не найден.");
        }

        return employees.get(key); // Возвращаем найденного сотрудника
    }

    // Метод для генерации ключа на основе ФИО
    private String generateKey(String firstName, String lastName) {
        return firstName.toLowerCase() + "_" + lastName.toLowerCase(); // Пример ключа: "ivan_ivanov"
    }

    // Метод для получения всех сотрудников (если нужно)
    public Map<String, Employee> getAllEmployees() {
        return new HashMap<>(employees); // Возвращаем копию карты сотрудников
    }
}