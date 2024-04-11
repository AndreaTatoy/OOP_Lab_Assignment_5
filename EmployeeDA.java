import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class EmployeeDA{
    private HashMap<String, Employee> employeeMap;

    public HashMap<String, Employee> getEmployeeMap(){
        return employeeMap;
    }

    public EmployeeDA(){
        employeeMap = new HashMap<>();
        readEmployeeFile();
    }

    private void readEmployeeFile(){
        try {
            Scanner employeeFile = new Scanner(new FileReader("/workspaces/OOP_Lab_Assignment_5/emp.csv"));
            employeeFile.nextLine();

            while (employeeFile.hasNext()){
                String employeeData = employeeFile.nextLine();
                String[] employeeDataSpecific = employeeData.split(",");

                if (employeeDataSpecific.length >= 4){
                    String empNo = employeeDataSpecific[0].trim();
                    String lastName = employeeDataSpecific[1].trim();
                    String firstName = employeeDataSpecific[2].trim();

                    Employee emp = new Employee();
                    emp.setEmpNo(empNo);
                    emp.setLastName(lastName);
                    emp.setFirstName(firstName);
                    employeeMap.put(empNo, emp);
                }
            }

            employeeFile.close();
            readDepEmp();
        }

        catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    private void readDepEmp(){
        try{
            Scanner depEmpFile = new Scanner(new FileReader("/workspaces/OOP_Lab_Assignment_5/deptemp.csv"));
            depEmpFile.nextLine();

            while (depEmpFile.hasNext()){
                String depEmpData = depEmpFile.nextLine();
                String[] depEmpSpecific = depEmpData.split(",");

                String empNo = depEmpSpecific[1].trim();
                double salary = Double.parseDouble(depEmpSpecific[2].trim());

                Employee emp = employeeMap.get(empNo);
                if (emp != null){
                    emp.setSalary(salary);
                }
            }
            depEmpFile.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}