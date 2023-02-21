package task7.proxy;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductDatabase implements DatabaseInterface {

    @Override
    public String[] getProductTable() {
        ArrayList<String> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new FileReader("src\\task7\\proxy\\product_database.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                list.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error:  " + e);
        }
        return list.toArray(new String[0]);
    }

    @Override
    public String getProduct(int productId) {
        String[] productTable = getProductTable();
        if (productId < 0 || productId >= productTable.length) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            return productTable[productId];
        }
    }
}
