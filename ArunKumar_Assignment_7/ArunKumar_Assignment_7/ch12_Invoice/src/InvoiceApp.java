import java.text.DateFormat;
import java.util.Scanner;
import java.text.NumberFormat;

/********************************************************
 Programmer - ArunKumar
 ********************************************************/

public class InvoiceApp
{
    public static GenericQueue<Invoice> invoices
        = new GenericQueue<Invoice>();

    public static Invoice invoice;

    static Scanner sc;

    public static void main(String args[])
    {
        // start Eric Berkowitz's interactive console
        new eric.Console();

        System.out.println("Welcome to the invoice application.\n");
        getInvoices();
        displayInvoices();
    }

    public static void getInvoices()
    {
        sc = new Scanner(System.in);
        int invoiceNumber = 1;
        String anotherInvoice = "y";
        while (anotherInvoice.equalsIgnoreCase("y"))
        {
            invoice = new Invoice();
            System.out.println("\nEnter line items for invoice " + invoiceNumber);
            getLineItems();
            invoices.push(invoice);

            // see if the user wants to continue
            anotherInvoice = Validator.getString(sc, "Another invoice? (y/n): ");
            System.out.println();
            invoiceNumber++;
        }
    }

    public static void getLineItems()
    {

        String anotherItem = "y";
        while (anotherItem.equalsIgnoreCase("y"))
        {
            // get the input from the user
            String productCode = Validator.getString(sc,
                "Enter product code: ");
            int quantity = Validator.getInt(sc,
                "Enter quantity:     ", 0, 1000);

            Product product = ProductDB.getProduct(productCode);
            invoice.addItem(new LineItem(product, quantity));

            // see if the user wants to continue
            anotherItem = Validator.getString(sc, "Another line item? (y/n): ");
            System.out.println();
        }
    }

    public static void displayInvoices()
    {
        System.out.println("You entered the following invoices:\n");
        System.out.println("Number\t Total\t Invoice Date\t Due On");
        System.out.println("------\t-------\t ------------\t--------");
        double batchTotal = 0;
        int invoiceNumber = 1;
        while (invoices.size() > 0)
        {
            Invoice invoice = invoices.pull();
            System.out.println(invoiceNumber + "\t" 
                             + invoice.getFormattedTotal() +"\t   " 
                             + invoice.getFormattedDate() + "\t" 
                             + invoice.getFormattedDueDate());
            
            invoiceNumber++;
            batchTotal += invoice.getInvoiceTotal();
        }
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        System.out.println("Total for all invoices: " + currency.format(batchTotal));
    }
}
