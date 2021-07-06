package api.tests.httpbin.images;

import api.httpbin.ApiHttpBin;
import api.tests.base.ApiTestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ImageTests extends ApiTestBase {

    private static final String DOWNLOAD_PATH = "src/main/resources/test_data";
    private static final String FILE_NAME = "testImage.jpeg";

    @Test
    @Story("Testing image response")
    @DisplayName("Successfully image download")
    @Description("In this case, test image in response")
    public void getImageTest() throws IOException {
        File outputImageFile = new File(DOWNLOAD_PATH, FILE_NAME);
        final byte[] image = ApiHttpBin.getImageResponse().asByteArray();
        OutputStream outputStream = new FileOutputStream(outputImageFile);
        outputStream.write(image);
        outputStream.close();
    }
}
