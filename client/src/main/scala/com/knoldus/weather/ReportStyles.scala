package com.knoldus.weather

import scalacss.Defaults._

object ReportStyles extends StyleSheet.Inline {

  import dsl._

  val mainDiv = style(
    addClassName("col-md-12"),
    borderBottom := "1px solid #eee",
    backgroundColor(Color("#3D4048"))
  )

  val heading = style(
    marginBottom(5 px),
    color.rgb(220, 208, 192),
    textAlign.center
  )

  val firstSpan = style(
    marginLeft(20 px),
    textTransform.uppercase,
    textShadow := "2px 2px 4px #000"
  )

  val secondSpan = style(
    textShadow := "1px 1px 1px #000"
  )

  val imageCommon = mixin(
    height(60 px),
    width(60 px)
  )

  val firstImg = style(imageCommon)

  val secondImg = style(
    imageCommon,
    marginLeft(20 px)
  )

  val secondDiv = style(
    addClassName("col-md-12"),
    marginTop(10 %%)
  )
  val search = style(
    width(60 %%),
    height(35 px),
    margin := "0% 0px 0px 16%",
    borderRadius(0 px),
    paddingLeft(5 px)
  )

  val bootstrapButton = style(
    addClassName("btn btn-info"),
    height(35 px),
    margin := "-1px 0px 0px 0%",
    borderRadius(0 px)
  )

  val mainContainer = style(
    addClassName("col-md-12 maincontainer"),
    marginTop(30 px),
    borderTop := "2px solid #ccc",
    paddingTop(30 px),
    borderBottom := "2px solid #ccc",
    paddingBottom(30 px),
    display.none
  )

  val innerDiv = style(
    width(530 px),
    height(400 px),
    marginLeft(60 px)
  )

  val city = style(
    fontSize(28 px),
    color := "#67890a",
    fontWeight.bold
  )

  val table = style(
    addClassName("table-bordered table-striped"),
    width(540 px),
    textAlign.center,
    marginTop(10 px)
  )

  val commonTD = mixin(
    padding(0 px),
    fontWeight.bold
  )

  val td = style(
    padding(20 px)
  )

  val firstTd = style(
    commonTD,
    fontSize(22 px)
  )

  val secondTd = style(commonTD)

  val mapCanvas = style(
    height(430 px),
    width(512 px)
  )
}