package Week_5.Exercise_2;

public class Main {
    public static void main(String[] args) {

        SalariedEmployee otieno = new SalariedEmployee("Otieno Money", 3500000);
        HourlyEmployee fidel = new HourlyEmployee("fidel Jones", 15000, 160);

        Employee[] employees = { otieno, fidel };

        for (Employee e : employees) {
            e.clockIn();
            System.out.println(e.name + "'s pay: UGX " + e.calculateMonthlyPay());
        }


        Taxable[] taxableEntities = { otieno, fidel };
        for (Taxable t : taxableEntities) {

            System.out.println(" Taxes for each employee displayed respectively " + t.calculateTax());
        }
    }
}

