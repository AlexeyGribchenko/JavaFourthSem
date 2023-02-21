package task7.proxy;

public class ProductDatabaseProxy implements DatabaseInterface {

    private ProductDatabase productDatabase = new ProductDatabase();
    private String[] cachedProductTable = null;

    @Override
    public String[] getProductTable() {
        if (cachedProductTable == null) {
            cachedProductTable = productDatabase.getProductTable();
        }
        return cachedProductTable;
    }

    @Override
    public String getProduct(int productId) {
        if (cachedProductTable == null) {
            cachedProductTable = productDatabase.getProductTable();
        }
        if (productId < 0 || productId >= cachedProductTable.length) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            return cachedProductTable[productId];
        }
    }


}
