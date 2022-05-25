import api.ProductService;
import org.junit.jupiter.api.BeforeAll;
import utils.RetrofitUtils;

public class AbstractTest {

    static ProductService productService;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }
}
