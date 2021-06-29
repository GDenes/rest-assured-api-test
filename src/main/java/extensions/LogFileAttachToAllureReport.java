package extensions;

import io.qameta.allure.Allure;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.LifecycleMethodExecutionExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogFileAttachToAllureReport
        implements AfterTestExecutionCallback, LifecycleMethodExecutionExceptionHandler {

    protected static final Logger logger =
            LoggerFactory.getLogger(LogFileAttachToAllureReport.class);

    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    private static final String LOGS_PATH_START = "target/logs/";
    private static final String LOG_FILE_EXTENSION = ".log";

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        attachLogFileToReport(context);
    }


    // if test failure in @BeforeEach
    @Override
    public void handleBeforeEachMethodExecutionException(ExtensionContext context, Throwable ex)
            throws Throwable {
        attachLogFileToReport(context);
    }

    private void attachLogFileToReport(ExtensionContext context) {
        final String fullyQualifiedTestName = context.getTestClass().get().getSimpleName()
                + "-" + context.getTestMethod().get().getName()
                + "-" + context.getDisplayName();
        try {
            logger.info("Reading created logfile");
            FileInputStream logFile = new FileInputStream(LOGS_PATH_START + now.format(formatter)
                    + File.separator + fullyQualifiedTestName + LOG_FILE_EXTENSION);
            logger.info("Attaching log file to report");
            Allure.addAttachment(context.getDisplayName() + " - logfile", logFile);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
