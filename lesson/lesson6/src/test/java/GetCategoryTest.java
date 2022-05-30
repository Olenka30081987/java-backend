
import db.dao.CategoriesMapper;
import db.model.Categories;
import db.model.CategoriesExample;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GetCategoryTest extends AbstractTest{

    @Test
    void getCategoryByIdPositiveTest() {
        try {
            CategoriesMapper categoriesMapper = sqlSession.getMapper(CategoriesMapper.class);
            CategoriesExample example = new CategoriesExample();
            example.createCriteria().andIdEqualTo(1L);
            List<Categories> list = categoriesMapper.selectByExample(example);
            Assertions.assertNotNull(list);

        } finally {
            sqlSession.close();
        }

    }

}