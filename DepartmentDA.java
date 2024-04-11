import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DepartmentDA{
    private HashMap<String, Department> departmentMap = new HashMap<>();
    private HashMap<String, Employee> employeeMap = new HashMap<>();
    private HashMap<String, List<String>> depEmpMap = new HashMap<>();

    public DepartmentDA(){
        readDepartment();
        readDepEmp();
        readEmployee();
        calculateTotalSalary();
        printReport();
    }

    private void readDepartment(){
        try{
            Scanner departmentFile = new Scanner(new FileReader("/workspaces/OOP_Lab_Assignment_5/dep.csv"));
            departmentFile.nextLine();

            while (departmentFile.hasNext()){
                String depData = departmentFile.nextLine();
                String[] depFileSpecific = depData.split(",");

                Department dep = new Department();
                dep.setDepCode(depFileSpecific[0].trim());
                dep.setDepName(depFileSpecific[1].trim());

                departmentMap.put(dep.getDepCode(), dep);
            }

            departmentFile.close();
        }

        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    private void readEmployee(){
        EmployeeDA empDA = new EmployeeDA();
        employeeMap = empDA.getEmployeeMap();
    }

    private void readDepEmp(){
        try {
            Scanner depEmpFile = new Scanner (new FileReader("/workspaces/OOP_Lab_Assignment_5/deptemp.csv"));
            depEmpFile.nextLine();

            while (depEmpFile.hasNext()){
                String depEmpData = depEmpFile.nextLine();
                String[] depEmpSpecific = depEmpData.split(",");
                
                String depCode = depEmpSpecific[0].trim();
                String empNo = depEmpSpecific[1].trim();

                if (!depEmpMap.containsKey(depCode)){
                    depEmpMap.put(depCode, new ArrayList<>());
                }
                depEmpMap.get(depCode).add(empNo);
            }

            depEmpFile.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    private void calculateTotalSalary(){
        for (String depCode : depEmpMap.keySet()){
            double depTotalSalary = 0;
            for (String empNo : depEmpMap.get(depCode)){
                Employee emp = employeeMap.get(empNo);
                if (emp != null){
                    depTotalSalary += emp.getSalary();
                }
            }

            Department department = departmentMap.get(depCode);
            if (department != null){
                department.setDepTotalSalary(depTotalSalary);
            }
        }
    }

    private void printReport(){
        for (Department dep : departmentMap.values()){
            System.out.print("\nDepartment Code: " + dep.getDepCode() +
                            "\nDepartment Name: " + dep.getDepName() +
                            "\nDepartment Total Salary: " + String.format("%.2f", dep.getDepTotalSalary()) +
                            "\n------------| Details |------------" +
                            "\nEmpNo\t Employee Name \t\tSalary");

            List<String> employee = depEmpMap.get(dep.getDepCode());
            if (employee != null){
                for (String empNo : employee) {
                    Employee emp = employeeMap.get(empNo);
                    if (emp != null){
                        System.out.print("\n" + emp.getEmpNo() + 
                                    "\t" + emp.getLastName() + ", " + emp.getFirstName() + 
                                    "\t\t" + String.format("%.2f", emp.getSalary()));
                    }
                }
            }
            System.out.println();
        }
    }
}