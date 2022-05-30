
import db.dao.ProductsMapper;
import db.model.Products;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateProductTest extends AbstractTest{

        @Test
        public void createProductInFoodCategoryTest(){
        try {
           ProductsMapper productsMapper = sqlSession.getMapper(ProductsMapper.class);
            Products products = new Products();
            products.setTitle("loaf");
            products.setPrice(58);
            products.setCategory_id(1L);
            productsMapper.insert(products);
            sqlSession.commit();
            Products createdProducts = productsMapper.selectByPrimaryKey(products.getId());
            Assertions.assertNotNull(createdProducts);
            Assertions.assertEquals(products.getId(), createdProducts.getId());
            Assertions.assertEquals(products.getTitle(), createdProducts.getTitle());
            Assertions.assertEquals(products.getPrice(), createdProducts.getPrice());
            Assertions.assertEquals(products.getCategory_id(), createdProducts.getCategory_id());

            productsMapper.deleteByPrimaryKey(products.getId());
            sqlSession.commit();
            Products deleteProducts = productsMapper.selectByPrimaryKey(products.getId());
            Assertions.assertNull(deleteProducts);
        } finally {
            sqlSession.close();
        }

    }
}