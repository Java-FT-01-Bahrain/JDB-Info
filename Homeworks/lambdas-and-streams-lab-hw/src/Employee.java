import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class Employee {
    static AtomicInteger IDGenerator = new AtomicInteger(0);
    private int id = IDGenerator.incrementAndGet();
    private String name;
    private LocalDate hireDate;
    private Double salary;
    private final String gender;

    public Employee(String name, LocalDate hireDate, Double salary, String gender) {
        this.name = name;
        this.hireDate = hireDate;
        this.salary = salary;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public Double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hireDate=" + hireDate +
                ", salary=" + salary +
                ", gender=" + gender +
                '}';
    }
}