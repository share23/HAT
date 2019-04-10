package com.share

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
  * 拆分数据
  * 最终格式为 client_type,mac_line,package_name,start_time,end_time,program_name,duration,origin,version,total_duration,dt
  */
object ParseData {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
    conf.setAppName("ParseDataApp")
    conf.setMaster("local[3]")
    val sc = new SparkContext(conf)

    // 定义list存放公共部分字段，|date|clienttype|macline|version|
    var commonList: List[String] = Nil

    // 自定义函数，判断是否是公共部分，如果是返回|date|clienttype|macline|version|
    def isCommon(string: String): List[String] = {
      if (string.startsWith("Common")) {
        val arr = string.split("&")
        // date
        val date = arr(1)
        // clienttype
        val clientType = arr(2)
        // macline
        val macLine = arr(3)
        // version
        val version = arr(4)
        commonList = date :: clientType :: version :: macLine :: commonList
      } else {
        commonList
      }
      return commonList
    }

    //1. 加载文件
    val rdd1 = sc.textFile("file:///F:/cleanData/part-00000")


    //2. 对数据进行拼接处理
    val rdd2: RDD[String] = rdd1.map(line => {

      var cleanData = ""
      // 判断当前行是否是公共数据
      val List = isCommon(line)
      if (List != Nil) {

        val data = line.split("&")

        // 数包名为com.tcl.TVBasicBehavior
        if (data(0).equals("TV")) {
          val packageName = data(1)
          val startTime = data(2)
          val endTime = data(3)

          val date = commonList(0)
          val clientType = commonList(1)
          val version = commonList(2)
          val macLine = commonList(3)

          // 合并
          cleanData = (clientType + "," + macLine + "," + packageName + "," + startTime + "," + endTime + "," + "," + "," + "," + version + "," + "," + date)
        }

        // 数包名为com.tcl.thirdAppPlayBehavior
        if (data(0).equals("TB")) {
          val packageName = data(1)
          val origin = data(2)
          val programName = data(3)
          val totalDuration = data(4)
          val duration = data(5)

          val date = commonList(0)
          val clientType = commonList(1)
          val version = commonList(2)
          val macLine = commonList(3)

          // 合并
          cleanData = (clientType + "," + macLine + "," + packageName + "," + "," + "," + programName + "," + duration + "," + origin + "," + version + "," + totalDuration + "," + date)
        }

        // 数包名为类似于com.tcl.tv形式,不包含Behavior
        if (data(0).equals("VH")) {
          val packageName = data(1)
          val startTime = data(2)
          val endTime = data(3)

          val date = commonList(0)
          val clientType = commonList(1)
          val version = commonList(2)
          val macLine = commonList(3)

          // 合并
          cleanData = (clientType + "," + macLine + "," + packageName + "," + startTime + "," + endTime + "," + "," + "," + "," + version + "," + "," + date)
        }

      }

      cleanData
    })

    //3. 过滤空数据
    val rdd3: RDD[String] = rdd2.filter(line => {
      !line.isEmpty
    })

    //4. 保存结果集到本地
    rdd3.repartition(1).saveAsTextFile("file:///F:/cleanData2")

  }

}
