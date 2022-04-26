package eapli.base.app.backoffice.console.presentation.product;

import eapli.base.app.backoffice.console.presentation.category.ListCategoryUI;
import eapli.base.categorymanagement.application.ListCategoryController;
import eapli.base.categorymanagement.domain.Category;
import eapli.base.productmanagement.application.SpecifyNewProductController;
import eapli.base.usermanagement.application.AddCostumerController;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class SpecifyNewProductUI extends AbstractUI {

    private final SpecifyNewProductController theController = new SpecifyNewProductController();
    private final ListCategoryController theCController = new ListCategoryController();
    private boolean invalidData, invalidCategory;
    private String setOfPhotos = "base.core/src/main/resources/";
    private Category category;

    @Override
    protected boolean doShow() {

        do{

            ListCategoryUI listCategoryUI = new ListCategoryUI();
            listCategoryUI.show();

            do {
                try {
                    final String categoryCode = Console.readLine("Please select one of the categories (Enter the code)");
                    category = theCController.findByCode(categoryCode);
                    invalidCategory = false;
                } catch (Exception e) {
                    System.out.println("Invalid code. Category does not exist!");
                        invalidCategory = true;
                }
            }while (invalidCategory);

            invalidData = false;
            boolean op = false;
            // FIXME avoid duplication with SignUpUI. reuse UserDataWidget from
            setOfPhotos = setOfPhotos +  Console.readLine("Photo");
            final String shortDescription = Console.readLine("Short Description (Max 30 chars)");
            final String extendedDescription = Console.readLine("Extended Description (Between 20 and 100 chars)");
            final String technicalDescription = Console.readLine("Technical Description");
            final String brand = Console.readLine("Brand (Max 50 chars)");
            final String reference = Console.readLine("Reference (Alphanumeric code with at maximum 23 chars)");
            String productionCode = null;
            if (Console.readLine("Do you want to add the production code? (Y/N)").equals("Y")){
                productionCode = Console.readLine("Production Code Production code must respect the following pattern: " +
                        "1 letter followed by a dot ('.') and ending with 3 digits (Max 23 chars).");
                op = true;
            }
            final String internalCode = Console.readLine("Internal Code (Please respect the following pattern: " +
                    "4 letters followed by a dot ('.') and ending with 5 digits (Max 23 chars)");
            final double price = Double.parseDouble(Console.readLine("Price (Euros)"));
            final String barcode = Console.readLine("Barcode (Must have 13 chars)");
            System.out.println("\nDIMENSIONS\n");
            final double height = Double.parseDouble(Console.readLine("Height (Milimeters)"));
            final double length = Double.parseDouble(Console.readLine("Length (Milimeters)"));
            final double width = Double.parseDouble(Console.readLine("Width (Milimeters)"));
            final double weight = Double.parseDouble(Console.readLine("Weight (Grams)"));

                try {
                    if (op)
                        this.theController.addProduct(category, setOfPhotos, shortDescription, extendedDescription, technicalDescription, brand, reference, productionCode, internalCode, price, barcode, height, length, width, weight);
                    else
                        this.theController.addProduct(category, setOfPhotos, shortDescription, extendedDescription, technicalDescription, brand, reference, internalCode, price, barcode, height, length, width, weight);
                } catch (final IntegrityViolationException | ConcurrencyException e) {
                    System.out.println("That code is already associated.");
                } catch (IllegalArgumentException e) {
                    System.out.println("\n"+ e.getMessage());
                    if (Console.readLine("Do you want to try again? (Y/N)").equals("Y")){
                        System.out.println();
                        invalidData = true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }while(invalidData);


        return false;
    }

    @Override
    public String headline() {
        return "Specify product";
    }
}
