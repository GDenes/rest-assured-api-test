package api.tests.base;

import extensions.LogExtension;
import extensions.LogFileAttachToAllureReport;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(LogExtension.class)
@ExtendWith(LogFileAttachToAllureReport.class)
public class ApiTestBase {

    protected static final Logger logger = LoggerFactory.getLogger(ApiTestBase.class);

}
