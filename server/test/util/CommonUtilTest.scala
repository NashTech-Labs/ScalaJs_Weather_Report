package util

import org.scalatest.FunSuite
import scala.xml.XML._

class CommonUtilTest extends FunSuite {

  val commonUtil = CommonUtil
  val projectPath = CommonUtil.conf.getString("user.dir") + "/conf/resources/CodeSquadTest"
  val version = "scala-2.11"
  val elem = loadFile(projectPath + "/target/" + version + "/scapegoat-report/scapegoat.xml")

  test("One should be able to get correct number of warnings") {
    val result = commonUtil.findScalaStyleWarnings(projectPath)
    assert(result === "0")
  }

  test("One should be able to get 'File not found' exception, if file doesn't exist") {
    val result = commonUtil.findScalaStyleWarnings("/home/knoldus/WebPag")
    assert("N/A" === result)
  }

  test("One should be able to get paths from config") {
    val result = commonUtil.findProjectPathsFromConfig
    assert(result.size == 1)
  }

  test("One should be able to get time from config") {

    val result = util.CommonUtil.findRefreshTimeFromConfig
    assert(result === 20)
  }

  test("One should be able to get Scapegoat warning") {
    val result = commonUtil.findScapegoatWarnings(elem)
    assert(result === "9")
  }

  test("One should be able to get Scapegoat errors") {
    val result = commonUtil.findScapegoatError(elem)
    assert(result === "6")
  }

  test("One should be able to get Scapegoat infos") {
    val result = commonUtil.findScapegoatInfos(elem)
    assert(result === "71")
  }

  test("One should be able to get coverage report of project") {
    val result = commonUtil.findScoverageReport(version, projectPath)
    assert(result === "81.95")
  }

  test("One should be able to get Copy Paste Detector") {
    val result = commonUtil.findCopyPasteDetectorReport(version, projectPath)
    assert("17" === result)
  }
}
