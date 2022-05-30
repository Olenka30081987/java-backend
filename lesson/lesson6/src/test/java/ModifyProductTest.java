
import db.model.Products;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ModifyProductTest {

    static SqlSession sqlSession = null;

    @BeforeAll
    static void setUp() {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);

            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void modifyProductInFoodCategoryTest() {
        try {
            db.dao.ProductsMapper productsMapper = sqlSession.getMapper(db.dao.ProductsMapper.class);
            db.model.ProductsExample example = new db.model.ProductsExample();
            example.createCriteria().andCategory_idEqualTo(1L);
            List<Products> list = productsMapper.selectByExample(example);
            Products product = list.get(0);
            product.setPrice(200);
            productsMapper.updateByPrimaryKey(product);
            sqlSession.commit();

            Products modifyProduct = productsMapper.selectByPrimaryKey(product.getId());
            Assertions.assertEquals(200, modifyProduct.getPrice());

        } finally {
            sqlSession.close();
    }
    }

}

