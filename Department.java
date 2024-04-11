import java.util.HashMap;

public class Department{
    private String depCode;
    private String depName;
    private double depTotalSalary;
    private HashMap<String, Employee> employee;

    public String getDepCode(){
        return depCode;
    }

   public String getDepName(){
        return depName;
    }

    public double getDepTotalSalary(){
        return depTotalSalary;
    }

    public HashMap<String, Employee> getEmployee(){
        return employee;
    }

    public void setDepCode(String depCode){
        this.depCode = depCode;
    }

    public void setDepName(String depName){
        this.depName = depName;
    }

    public void setEmployee(HashMap<String, Employee> employee){
        this.employee = new HashMap<>();
    }

    public void setDepTotalSalary(double depTotalSalary){
        this.depTotalSalary = depTotalSalary;
    }

    public void addEmployee(Employee employee){
        this.employee.put(employee.getEmpNo(), employee);
    }
}
