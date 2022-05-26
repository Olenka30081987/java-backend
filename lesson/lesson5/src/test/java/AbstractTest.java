import api.ProductService;
import com.github.javafaker.Faker;
import dto.Product;
import org.junit.jupiter.api.BeforeAll;
import utils.RetrofitUtils;

public class AbstractTest {
    Product product = null;
    Faker faker = new Faker();
    int id;
    static ProductService productService;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }
}
