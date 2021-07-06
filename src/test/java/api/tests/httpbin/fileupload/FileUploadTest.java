package api.tests.httpbin.fileupload;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import api.httpbin.ApiHttpBin;
import api.tests.base.ApiTestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import java.io.File;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("HttpBin Api tests")
@Feature("File upload tests")
public class FileUploadTest extends ApiTestBase {

    private static final String FILE_UPLOAD_PATH = "src/main/resources/test_data/sampleFile.jpeg";

    private static final String USER_AGENT_TEST_DATA = "Apache-HttpClient/4.5.13 (Java/1.8.0_151)";
    private static final String FILE_TEST_DATA = "data:application/octet-stream;base64,/9j/4AAQSkZJRgABAQAAAQABAAD";

    @Test
    @Story("Testing success file upload functionality")
    @DisplayName("Successfully file upload test")
    @Description("In this case, test file upload")
    public void fileUploadTest() {
        String jsonString = ApiHttpBin.getFileUploadResponse(new File(FILE_UPLOAD_PATH)).asString();
        final JSONObject respJsonObj = new JSONObject(jsonString);

        final String userAgent = respJsonObj.getJSONObject("headers").get("User-Agent").toString();
        final String file = respJsonObj.getJSONObject("files").get("file").toString();

        assertEquals(USER_AGENT_TEST_DATA, userAgent, "Wrong user agent value");
        assertTrue(file.contains(FILE_TEST_DATA));

        logger.info(jsonString);
    }
}
