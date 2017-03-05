package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ApplicationTest.class,
        ApplicationUITest.class,
        TaxiLicenceTest.class
})

public class TestSuite {
}