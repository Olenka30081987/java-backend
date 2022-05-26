
import dto.Product;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DeleteProductTest extends AbstractTest{


    @SneakyThrows
    @Test
    void deleteProductNegativeTest() {
        Response<ResponseBody> response = productService.deleteProduct(12354).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(false));
        assertThat(response.code(), equalTo(500));
    }

    @SneakyThrows
    @Test
    void deleteDoubleProductNegativeTest() {
            product = new Product()
                    .withTitle(faker.food().ingredient())
                    .withCategoryTitle("Food")
                    .withPrice((int) (Math.random() * 10000));

        Response<Product> response = productService.createProduct(product)
                .execute();
        System.out.println(product);
        id =  response.body().getId();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));

        Response<ResponseBody> response1 = productService.deleteProduct(id).execute();
        assertThat(response1.isSuccessful(), CoreMatchers.is(true));

        Response<ResponseBody> response2 = productService.deleteProduct(id).execute();
        assertThat(response2.isSuccessful(), CoreMatchers.is(false));
        assertThat(response2.code(), equalTo(500));
    }
}
