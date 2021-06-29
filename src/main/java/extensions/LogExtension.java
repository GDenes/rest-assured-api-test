package extensions;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.MDC;

public class LogExtension implements BeforeEachCallback, AfterEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        final String fullyQualifiedTestName = context.getTestClass().get().getSimpleName()
                + "-" + context.getTestMethod().get().getName()
                + "-" + context.getDisplayName();

        MDC.put("methodName", fullyQualifiedTestName);
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        MDC.remove("methodName");
    }

}
