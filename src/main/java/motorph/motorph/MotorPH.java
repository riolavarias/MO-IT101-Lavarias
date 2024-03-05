package motorph.motorph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class MotorPH {
    public static void main(String[] args) {
        String employeeDataFile = "C:\\Users\\Rio\\Documents\\NetBeansProjects\\MotorPH\\CSVFiles\\MotorPH Employee Data - Employee Details.csv";
        String timesheetFile = "C:\\Users\\Rio\\Documents\\NetBeansProjects\\MotorPH\\CSVFiles\\MotorPH Employee Data - Attendance Record.csv";
        String csvSplitBy = ",";
        
        // Constants for SSS share Calculations
                final double grossPay1 = 20249.99;
                final double SSSAmount1 = 225.00;
                
                final double grossPay2 = 21249.99;
                final double SSSAmount2 = 236.25;
                
                final double grossPay3 = 21749.99;
                final double SSSAmount3 = 241.88;
                
                final double grossPay4 = 22249.99;
                final double SSSAmount4 = 247.50;
                
                final double grossPay5 = 22749.99;
                final double SSSAmount5 = 253.13;
                
                final double grossPay6 = 23249.99;
                final double SSSAmount6 = 258.75;
                
                final double grossPay7 = 23749.99;
                final double SSSAmount7 = 264.38;
                
                final double grossPay8 = 24249.99;
                final double SSSAmount8 = 270.00;
                
                final double grossPay9 = 24749.99;
                final double SSSAmount9 = 275.50;
                
                final double grossPay10 = 25249.99;
                final double SSSAmount10 = 281.25;
                
                final double grossPay11 = 25749.99;
                final double SSSAmount11 = 286.88;
                
                final double grossPay12 = 26249.99;
                final double SSSAmount12 = 292.50;
                
                final double grossPay13 = 26749.99;
                final double SSSAmount13 = 298.13;
                
                final double grossPay14 = 27249.99;
                final double SSSAmount14 = 303.75;
                
                final double grossPay15 = 27749.99;
                final double SSSAmount15 = 309.38;
                
                final double grossPay16 = 28249.99;
                final double SSSAmount16 = 315.00;
                
                final double grossPay17 = 28749.99;
                final double SSSAmount17 = 320.63;
                
                final double grossPay18 = 29249.99;
                final double SSSAmount18 = 326.25;
                
                final double grossPay19 = 29749.99;
                final double SSSAmount19 = 331.88;
                
                final double grossPay20 = 29750;
                final double SSSAmount20 = 337.50;
                      
        try (BufferedReader employeeDataReader = new BufferedReader(new FileReader(employeeDataFile));
             BufferedReader timesheetReader = new BufferedReader(new FileReader(timesheetFile))) {
            Scanner scanner = new Scanner(System.in);
            
            System.out.print("Enter Employee ID: ");
            String inputEmployeeId = scanner.nextLine();
            System.out.print("Enter Start Date (DD-MMM-YY): ");
            String startDateStr = scanner.nextLine();
            System.out.print("Enter End Date (DD-MMM-YY): ");
            String endDateStr = scanner.nextLine();
            
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MMM-yy", Locale.ENGLISH);
            LocalDate startDate = LocalDate.parse(startDateStr, dateFormatter);
            LocalDate endDate = LocalDate.parse(endDateStr, dateFormatter);
            
            String line;
            while ((line = employeeDataReader.readLine()) != null) {
                String[] employeeData = line.split(csvSplitBy);
                String employeeId = employeeData[0];
                if (!employeeId.equals(inputEmployeeId)) {
                    continue; // Skip to the next employee if ID does not match
                }
                
                // Display basic employee information
                String firstName = employeeData[2];
                String lastName = employeeData[1];
                String Birthday = employeeData[3];
                String Address = employeeData[4];
                String PhoneNumber = employeeData[5];
                String SSS = employeeData[6];
                String PHIC = employeeData[7];
                String TIN = employeeData[8];
                String HDMF = employeeData[9];
                String Status = employeeData[10];
                String Position = employeeData[11];
                String Supervisor = employeeData[12];
                String BasicSalary = employeeData[13];
                String RiceSubsidy = employeeData[14];
                String PhoneAllowance = employeeData[15];
                String ClothingAllowance = employeeData[16];
                String HourlyRate = employeeData[18];
                System.out.println("\nBasic Information:");
                System.out.println("Employee Name: " + firstName + " " + lastName);
                System.out.println("Birthday: " + Birthday);
                System.out.println("Address: " + Address);
                System.out.println("Phone Number: " + PhoneNumber);
                System.out.println("\nEmployee Details:");
                System.out.println("Status: " + Status);
                System.out.println("Position: " + Position);
                System.out.println("Immediate Supervisor: " + Supervisor);
                System.out.println("\nIdentification:");
                System.out.println("SSS: " + SSS);
                System.out.println("Philhealth: " + PHIC);
                System.out.println("Pagibig: " + HDMF);
                System.out.println("TIN: " + TIN);
                System.out.println("\nPayroll Information:");
                System.out.println("Basic Salary: " + BasicSalary + " " + "Hourly Rate: " + HourlyRate);
                System.out.println("Rice Subsidy: " + RiceSubsidy);
                System.out.println("Phone Allowance: " + PhoneAllowance);
                System.out.println("Clothing Allowance: " + ClothingAllowance);
                System.out.println("=============================================");
                
                // Calculate and display total hours worked within the specified date range
                double totalHours = calculateTotalHours(timesheetReader, inputEmployeeId, startDate, endDate, csvSplitBy);
                System.out.println("\nWeekly Pay Computation:");
                System.out.println("Total Hours Worked: " + totalHours);
                
                // Calculate total pay
                double hourlyRate = Double.parseDouble(HourlyRate);
                double grossPay = totalHours * hourlyRate;
                grossPay = Math.round(grossPay * 100.0)/100.0; // Round off to 2 decimal places
                System.out.println("Gross Pay: " + grossPay);
                
                // Calculate SSS share
                double sssShare = 0.0;
                if (grossPay * 4 >= grossPay1) {
                sssShare = SSSAmount1;
                } else if (grossPay * 4 >= grossPay2) {
                sssShare = SSSAmount2;
                } else if (grossPay * 4 >= grossPay3) {
                sssShare = SSSAmount3;
                } else if (grossPay * 4 >= grossPay4) {
                sssShare = SSSAmount4;
                } else if (grossPay * 4 >= grossPay5) {
                sssShare = SSSAmount5;
                } else if (grossPay * 4 >= grossPay6) {
                sssShare = SSSAmount6;
                } else if (grossPay * 4 >= grossPay7) {
                sssShare = SSSAmount7;
                } else if (grossPay * 4 >= grossPay8) {
                sssShare = SSSAmount8;
                } else if (grossPay * 4 >= grossPay9) {
                sssShare = SSSAmount9;
                } else if (grossPay * 4 >= grossPay10) {
                sssShare = SSSAmount10;
                } else if (grossPay * 4 >= grossPay11) {
                sssShare = SSSAmount11;
                } else if (grossPay * 4 >= grossPay12) {
                sssShare = SSSAmount12;
                } else if (grossPay * 4 >= grossPay13) {
                sssShare = SSSAmount13;
                } else if (grossPay * 4 >= grossPay14) {
                sssShare = SSSAmount14;
                } else if (grossPay * 4 >= grossPay16) {
                sssShare = SSSAmount16;
                } else if (grossPay * 4 >= grossPay17) {
                sssShare = SSSAmount17;
                } else if (grossPay * 4 >= grossPay18) {
                sssShare = SSSAmount18;
                } else if (grossPay * 4 >= grossPay19) {
                sssShare = SSSAmount19;
                } else if (grossPay * 4 <= grossPay20) {
                sssShare = SSSAmount20;
                }
               
                System.out.println("\nDeductions:");
                System.out.println("SSS Share: " + sssShare);
               
                // Calculate PHIC share
                double phicShare = (grossPay * .025)/4;
                phicShare = Math.round(phicShare * 100.0)/100.0;
                System.out.println("PHIC Share: " + phicShare);
                
                // Add HDMF Share
                double hdmfShare = 50.00;
                System.out.println("HDMF Share: " + hdmfShare);
                
                // Total Deduction
                double totalDeduction = sssShare + phicShare + hdmfShare;
                totalDeduction = Math.round(totalDeduction * 100.0)/100.0;
                System.out.println("Total Deduction: " + totalDeduction);
                
                // To get the taxable income
                double taxableIncome = grossPay - totalDeduction;
                taxableIncome = Math.round(taxableIncome * 100.0)/100.0;
                System.out.println("\nTaxable Income: " + taxableIncome);
                
                // Calculate tax
                double tax = calculateTax(taxableIncome);
                tax = Math.round(tax * 100.0)/100.0;
                System.out.println("Less Tax: " + tax);
                
                // Calculate income after Tax
                double incomeAfterTax = taxableIncome - tax;
                incomeAfterTax = Math.round(incomeAfterTax * 100.0)/100.0;
                System.out.println("Income After Tax: " + incomeAfterTax);
                
                // Calculate weekly phone allowance
                double weeklyPhoneAllowance = Double.parseDouble(PhoneAllowance) / 4.0;
                System.out.println("\nAdd Allowances:");
                System.out.println("Weekly Phone Allowance: " + weeklyPhoneAllowance);

                // Calculate weekly rice subsidy
                double weeklyRiceSubsidy = Double.parseDouble(RiceSubsidy) / 4.0;
                System.out.println("Weekly Rice Subsidy: " + weeklyRiceSubsidy);

                // Calculate weekly clothing allowance
                double weeklyClothingAllowance = Double.parseDouble(ClothingAllowance) / 4.0;
                System.out.println("Weekly Clothing Allowance: " + weeklyClothingAllowance);
                
                // Total Allowances
                double totalAllowances = weeklyPhoneAllowance + weeklyRiceSubsidy + weeklyClothingAllowance;
                System.out.println("Total Weekly Allowance: " + totalAllowances);
                
                // Calculate Net Pay
                double netPay = incomeAfterTax + totalAllowances;
                System.out.println("\nNet Pay: " + netPay);
                netPay = Math.round(netPay * 100.0)/100.0;                
                
                break; // Stop searching after finding the employee
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double calculateTotalHours(BufferedReader timesheetReader, String employeeId,
                                              LocalDate startDate, LocalDate endDate, String csvSplitBy) throws IOException {
        String line;
        double totalHours = 0.0;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MMM-yy", Locale.ENGLISH);

        while ((line = timesheetReader.readLine()) != null) {
            String[] timesheetData = line.split(csvSplitBy);
            if (timesheetData.length < 2) {
                continue; // Skip invalid lines
            }
            if (!timesheetData[0].equals(employeeId)) {
                continue; // Skip entries for other employees
            }
            LocalDate entryDate = LocalDate.parse(timesheetData[1], dateFormatter);
            if (entryDate.isBefore(startDate) || entryDate.isAfter(endDate)) {
                continue; // Skip entries outside the specified date range
            }
            double hoursWorked = Double.parseDouble(timesheetData[4]);
            totalHours += hoursWorked;
        }
        return totalHours;
    }
    private static double calculateTax(double taxableIncome) {
        double tax = 0.0;
        if ((taxableIncome *52) <= 250000) {
            tax = 0;
        } else if ((taxableIncome *52) <= 400000) {
            tax = ((taxableIncome * 52) - 250000) * 0.15;
        } else if ((taxableIncome *52) <= 800000) {
            tax = (((taxableIncome * 52) - 400000) * 0.20)+22500;
        } else if ((taxableIncome *52) <= 2000000) {
            tax = (((taxableIncome * 52) - 800000) * 0.25)+102500;
        } else if ((taxableIncome *52) <= 8000000) {
            tax = (((taxableIncome * 52) - 2000000) * 0.30)+402500;
        } 
        return tax/52;
    }
}
