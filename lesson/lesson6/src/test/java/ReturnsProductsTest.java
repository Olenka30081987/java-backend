
import db.dao.ProductsMapper;
import db.model.Products;
import db.model.ProductsExample;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

public class ReturnsProductsTest extends AbstractTest {

    @Test
    void getProductsPositiveTest() {
        try {
            ProductsMapper productsMapper = sqlSession.getMapper(ProductsMapper.class);
            ProductsExample example = new ProductsExample();
            example.createCriteria().andTitleLike("Food");
            List<Products> list = productsMapper.selectByExample(example);
            Assertions.assertNotNull(list);

        } finally {
            sqlSession.close();
        }
    }
}

