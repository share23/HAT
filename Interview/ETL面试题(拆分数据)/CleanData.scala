package com.share

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 数据清洗,筛选出需要的数据
  */
object CleanData {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
    conf.setAppName("ParseLog2")
    conf.setMaster("local[6]")
    val sc = new SparkContext(conf)

    //1. 加载文件
    val rdd1 = sc.textFile("file:///F:/download/baiduyun/raw_data.txt")

    //2. 处理数据
    val rdd2: RDD[String] = rdd1.map(line => {
      var str1 = "aaaaa"
      if (line.startsWith("com")) {

        val arr = line.split(",")

        if (arr(0).contains("TVBasicBehavior") && line.split(",").length == 3) {
          val packageName = arr(0)
          val startTime = arr(1).substring(3)
          val endTime = arr(2).split("=")(1).replace("}", "")
          val TV = "TV" + "&" + packageName + "&" + startTime + "&" + endTime
          str1 = TV
        }

        if (arr(0).contains("thirdAppPlayBehavior") && line.split(",").length == 6) {
          val packageName = arr(0)
          val origin = arr(1).substring(3)
          val programName = arr(3).substring(2)
          val totalDuration = arr(4).substring(2)
          val duration = arr(5).substring(2).replace("}", "")
          val thirdBehavior = "TB" + "&" + packageName + "&" + origin + "&" + programName + "&" + totalDuration + "&" + duration
          str1 = thirdBehavior
        }

        if (!line.contains("Behavior") && (!line.contains("mediaplayer")) && line.split(",").length == 4) {
          val packageName = arr(0)
          val startTime = arr(1).substring(3)
          val endTime = arr(2).substring(2)
          val viewHistory = "VH" + "&" + packageName + "&" + startTime + "&" + endTime
          str1 = viewHistory
        }
      }

      if (line.startsWith("2019") && line.substring(line.lastIndexOf("&data=")).length > 6) {
        if (line.contains("&clienttype=") && line.contains("&macline=") && line.contains("&version=")) {
          if (line.substring(line.lastIndexOf("&clienttype=")).split("&")(1).length > 0) {
            if (line.substring(line.lastIndexOf("&version=")).split("&")(1).length > 0) {

              val arr = line.split("&")
              // date
              val date = arr(0).split(" ")(0)
              // clienttype
              val clientType = line.substring(line.lastIndexOf("&clienttype=")).split("&")(1).substring(11)
              // macline
              val macLine = line.substring(line.lastIndexOf("&macline=")).split("&")(1).substring(8)
              // version
              val version = line.substring(line.lastIndexOf("&version=")).split("&")(1).substring(8)

              val common = "Common" + "&" + date + "&" + clientType + "&" + macLine + "&" + version

              str1 = common
            }
          }
        }
      }

      str1
    })
    //3. 过滤空数据
    val rdd3: RDD[String] = rdd2.filter(line => {
      !line.equals("aaaaa")
    })

    //4. 保存数据到本地
    rdd3.repartition(1).saveAsTextFile("file:///F:/cleanData")

  }
}
