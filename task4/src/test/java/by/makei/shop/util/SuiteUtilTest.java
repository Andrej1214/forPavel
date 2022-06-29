package by.makei.shop.util;

import by.makei.shop.model.validator.impl.AttributeValidatorImplTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        PasswordEncoderTest.class,
        CodeGeneratorTest.class,
        AttributeValidatorImplTest.class
})
public class SuiteUtilTest {
}
