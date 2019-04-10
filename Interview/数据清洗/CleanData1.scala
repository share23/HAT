package com.share.etl2

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 提取出原始数据中的性别、地区、年龄、职业为标签，并统计数量和设置自增id
  */
object CleanData1 {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
    conf.setAppName("CleanData1")
    conf.setMaster("local[6]")
    val sc = new SparkContext(conf)

    // 定义方法判断年龄
    def matchAge(age: Int): String = {
      var str = "年龄"
      if (age > 60) {
        str = "老年"
      } else if (age > 40) {
        str = "中年"
      } else if (age > 20) {
        str = "青年"
      } else {
        str = "少年"
      }
      str
    }


    //1. 加载文件
    val rdd1 = sc.textFile("file:///F:/download/baiduyun/data.txt")

    //2. 处理数据
    val rdd2: RDD[String] = rdd1.map(line => {
      val arr = line.split(",")
      var str = "aaa"
      if (arr.length == 7) {
        str = arr(2) + "," + arr(3) + "," + matchAge(arr(5).toInt) + "," + arr(6)
      }
      str
    })

    //3. 过滤空数据
    val rdd3: RDD[String] = rdd2.filter(line => {
      !line.equals("aaa")
    })

    // 4. 压扁
    val rdd4 = rdd3.flatMap(_.split(","))

    // 5. 标1成对
    val rdd5 = rdd4.map((_, 1))

    // 6. 聚合
    val rdd6 = rdd5.reduceByKey(_ + _)

    var i = 1
    // 7. 加id
    val rdd7: RDD[String] = rdd6.map(line => {
      val str = i + "," + line._1.toString + "," + line._2.toString
      i += 1
      str
    })

    // 8. 保存结果集到本地
    rdd7.repartition(1).saveAsTextFile("file:///F:/download/baiduyun/cleanData")

  }

}
