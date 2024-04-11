public class Employee{
    private String empNo;
    private String lastName;
    private String firstName;
    private double salary;

    public String getEmpNo(){
        return empNo;
    }

    public String getLastName(){
        return lastName;
    }

    public String getFirstName(){
        return firstName;
    }

    public double getSalary(){
        return salary;
    }

    public void setEmpNo(String empNo){
        this.empNo = empNo;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setSalary(double salary){
        this.salary = salary;
    }
}