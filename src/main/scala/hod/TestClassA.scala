package hod

import scala.concurrent.{ExecutionContext, Future}

class TestClassA {
  def function1(aa: Int): Future[String] = Future.successful("oiergienr")

  def functionImplicitExecutionContext(aaa: String)(implicit ec: ExecutionContext) = Future.successful(1)
}
