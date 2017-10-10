package hod

import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import org.specs2.specification.Scope
import org.specs2.specification.core.{Env, OwnExecutionEnv}

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future}

class TestClassesTest extends Specification with Mockito with OwnExecutionEnv {
  override val env: Env = Env()

  trait BaseTest extends Scope {
    val testClassA = mock[TestClassA]


    testClassA.function1(anyInt) returns Future.successful("verifyme")
    testClassA.functionImplicitExecutionContext(anyString)(any[ExecutionContext]) returns Future.successful(5)

    val testClassB = mock[TestClassB]
    testClassB.function1(anyInt) returns Future.successful("verifyme")
    testClassB.functionImplicitExecutionContext(anyString)(anyObject[ExecutionContext]) returns Future.successful(5)
  }

  "TestClass" should {

    "do stuff 1" in new BaseTest {
      testClassA.function1(1)

      there was after (FiniteDuration(300, MILLISECONDS)).one(testClassA).function1(1)
    }
    "do execution context stuff" in new BaseTest {
      testClassA.functionImplicitExecutionContext("wergw")

      there was after (FiniteDuration(300, MILLISECONDS)).one(testClassA).functionImplicitExecutionContext("wergw")
    }

    "do execution context stuff inside an actual future" in new BaseTest {

      Future {
        Thread.sleep(100)
        testClassA.functionImplicitExecutionContext("wergw")
      }

      there was after (FiniteDuration(300, MILLISECONDS)).one(testClassA).functionImplicitExecutionContext("wergw")
    }

    "override execution context stuff" in new BaseTest {
      testClassA.functionImplicitExecutionContext(anyString)(any[ExecutionContext]) returns Future.successful(99)
      testClassA.functionImplicitExecutionContext("wergw")

      there was after (FiniteDuration(300, MILLISECONDS)).one(testClassA).functionImplicitExecutionContext("wergw")
    }
    "do stuff 2" in new BaseTest {
      testClassA.function1(1)

      there was after (FiniteDuration(300, MILLISECONDS)).one(testClassA).function1(1)
    }

    "override mock" in new BaseTest {
      testClassA.function1(anyInt) returns Future.successful("verify you!")
      testClassA.function1(1)

      there was after (FiniteDuration(300, MILLISECONDS)).one(testClassA).function1(1)
    }

    "do stuff 3" in new BaseTest {
      testClassA.function1(1)

      there was after (FiniteDuration(300, MILLISECONDS)).one(testClassA).function1(1)
    }
    "calling two mocks but verifying just one" in new BaseTest {
      testClassA.function1(1)
      testClassA.functionImplicitExecutionContext("wergw")

      there was after (FiniteDuration(300, MILLISECONDS)).one(testClassA).function1(1)
    }
    "calling two mocks but verifying just the other one" in new BaseTest {
      testClassA.function1(1)
      testClassA.functionImplicitExecutionContext("wergw")

      there was after (FiniteDuration(300, MILLISECONDS)).one(testClassA).functionImplicitExecutionContext("wergw")
    }

    "calling two mocks in a future verifying just the other one" in new BaseTest {
      Future {

        testClassA.functionImplicitExecutionContext("wergw")
        Thread.sleep(500)
        testClassA.function1(1)
      }

      there was after (FiniteDuration(300, MILLISECONDS)).one(testClassA).functionImplicitExecutionContext("wergw")
    }
    "do stuff 4" in new BaseTest {
      testClassA.function1(1)

      there was after (FiniteDuration(300, MILLISECONDS)).one(testClassA).function1(1)
    }

    "not verify always" in new BaseTest {
      testClassA.function1(1) must beEqualTo("verifyme").await
    }

    "do stuff 5" in new BaseTest {
      testClassA.function1(1)

      there was after (FiniteDuration(300, MILLISECONDS)).one(testClassA).function1(1)
    }
    "do stuff 6" in new BaseTest {
      testClassA.function1(1)

      there was after (FiniteDuration(300, MILLISECONDS)).one(testClassA).function1(1)
    }

    "verify after await" in new BaseTest {
      testClassA.function1(1) must beEqualTo("verifyme").await
      there was after (FiniteDuration(300, MILLISECONDS)).one(testClassA).function1(1)
    }

    "do stuff 7" in new BaseTest {
      testClassA.function1(1)

      there was after (FiniteDuration(300, MILLISECONDS)).one(testClassA).function1(1)
    }
    "do stuff 8" in new BaseTest {
      testClassA.function1(1)

      there was after (FiniteDuration(300, MILLISECONDS)).one(testClassA).function1(1)
    }
    "do stuff 1" in new BaseTest {
      testClassB.function1(1)

      there was after (FiniteDuration(300, MILLISECONDS)).one(testClassB).function1(1)
    }
    "do execution context stuff" in new BaseTest {
      testClassB.functionImplicitExecutionContext("wergw")

      there was after (FiniteDuration(300, MILLISECONDS)).one(testClassB).functionImplicitExecutionContext("wergw")
    }

    "do execution context stuff inside an actual future" in new BaseTest {

      Future {
        Thread.sleep(100)
        testClassB.functionImplicitExecutionContext("wergw")
      }

      there was after (FiniteDuration(300, MILLISECONDS)).one(testClassB).functionImplicitExecutionContext("wergw")
    }

    "override execution context stuff" in new BaseTest {
      testClassB.functionImplicitExecutionContext(anyString)(any[ExecutionContext]) returns Future.successful(99)
      testClassB.functionImplicitExecutionContext("wergw")

      there was after (FiniteDuration(300, MILLISECONDS)).one(testClassB).functionImplicitExecutionContext("wergw")
    }
    "do stuff 2" in new BaseTest {
      testClassB.function1(1)

      there was after (FiniteDuration(300, MILLISECONDS)).one(testClassB).function1(1)
    }

    "override mock" in new BaseTest {
      testClassB.function1(anyInt) returns Future.successful("verify you!")
      testClassB.function1(1)

      there was after (FiniteDuration(300, MILLISECONDS)).one(testClassB).function1(1)
    }

    "do stuff 3" in new BaseTest {
      testClassB.function1(1)

      there was after (FiniteDuration(300, MILLISECONDS)).one(testClassB).function1(1)
    }
    "calling two mocks but verifying just one" in new BaseTest {
      testClassB.function1(1)
      testClassB.functionImplicitExecutionContext("wergw")

      there was after (FiniteDuration(300, MILLISECONDS)).one(testClassB).function1(1)
    }
    "calling two mocks but verifying just the other one" in new BaseTest {
      testClassB.function1(1)
      testClassB.functionImplicitExecutionContext("wergw")

      there was after (FiniteDuration(300, MILLISECONDS)).one(testClassB).functionImplicitExecutionContext("wergw")
    }

    "calling two mocks in a future verifying just the other one" in new BaseTest {
      Future {

        testClassB.functionImplicitExecutionContext("wergw")
        Thread.sleep(500)
        testClassB.function1(1)
      }

      there was after (FiniteDuration(300, MILLISECONDS)).one(testClassB).functionImplicitExecutionContext("wergw")
    }
    "do stuff 4" in new BaseTest {
      testClassB.function1(1)

      there was after (FiniteDuration(300, MILLISECONDS)).one(testClassB).function1(1)
    }

    "not verify always" in new BaseTest {
      testClassB.function1(1) must beEqualTo("verifyme").await
    }

    "do stuff 5" in new BaseTest {
      testClassB.function1(1)

      there was after (FiniteDuration(300, MILLISECONDS)).one(testClassB).function1(1)
    }
    "do stuff 6" in new BaseTest {
      testClassB.function1(1)

      there was after (FiniteDuration(300, MILLISECONDS)).one(testClassB).function1(1)
    }

    "verify after await" in new BaseTest {
      testClassB.function1(1) must beEqualTo("verifyme").await
      there was after (FiniteDuration(300, MILLISECONDS)).one(testClassB).function1(1)
    }

    "do stuff 7" in new BaseTest {
      testClassB.function1(1)

      there was after (FiniteDuration(300, MILLISECONDS)).one(testClassB).function1(1)
    }
    "do stuff 8" in new BaseTest {
      testClassB.function1(1)

      there was after (FiniteDuration(300, MILLISECONDS)).one(testClassB).function1(1)
    }

    "using two mocks and verify both" in new BaseTest {
      testClassB.function1(1)
      testClassA.function1(1)

      there was after (FiniteDuration(300, MILLISECONDS)).one(testClassB).function1(1)
      there was after (FiniteDuration(300, MILLISECONDS)).one(testClassA).function1(1)
    }
    "using two mocks and verify one" in new BaseTest {
      testClassB.function1(1)
      testClassA.function1(1)

      there was after (FiniteDuration(300, MILLISECONDS)).one(testClassB).function1(1)
    }

    "using two mocks and verify other one" in new BaseTest {
      testClassB.function1(1)
      testClassA.function1(1)

      there was after (FiniteDuration(300, MILLISECONDS)).one(testClassA).function1(1)
    }

    "using two mocks with execution context and verify one" in new BaseTest {
      Future {
        testClassB.functionImplicitExecutionContext("srgerge")
        testClassA.functionImplicitExecutionContext("ssrresr")
      }
      
      there was after (FiniteDuration(300, MILLISECONDS)).one(testClassB).functionImplicitExecutionContext("srgerge")
    }
  }
}
