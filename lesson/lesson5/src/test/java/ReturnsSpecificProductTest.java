
import dto.Product;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ReturnsSpecificProductTest extends AbstractTest{

    @Test
    void returnProductInFoodCategoryTest() throws IOException {
        product = new Product()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random() * 10000));

        Response<Product> response = productService.createProduct(product)
                .execute();
        id =  response.body().getId();

        Response<Product> response1 = productService.getProductById(id)
                .execute();
        assertThat(response1.isSuccessful(), CoreMatchers.is(true));
        assertThat(response1.code(), equalTo(200));
        assertThat(response1.body().getId(), equalTo(id));

        Response<ResponseBody> response2 = productService.deleteProduct(id).execute();
        assertThat(response2.isSuccessful(), CoreMatchers.is(true));
    }

    @Test
    void returnProductNegativeIdTest() throws IOException {

        Response<Product> response = productService.getProductById(12563445)
                .execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(false));
        assertThat(response.code(), equalTo(404));
    }

}
