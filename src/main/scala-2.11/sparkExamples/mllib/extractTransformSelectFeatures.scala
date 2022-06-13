package sparkExamples.mllib

import org.apache.spark.ml.feature.{HashingTF, IDF, OneHotEncoder, Tokenizer}
import org.apache.spark.sql.SparkSession



object extractTransformSelectFeatures {

  def main(args:Array[String]):Unit= {
    val spark: SparkSession = SparkSession.builder.master("local").getOrCreate
    val sc = spark.sparkContext

    // feature-extractors

    val sentenceData = spark.createDataFrame(Seq(
      (0.0, "Hi I heard about Spark"),
      (0.0, "I wish Java could use case classes"),
      (1.0, "Logistic regression models are neat")
    )).toDF("label", "sentence")

    val tokenizer = new Tokenizer().setInputCol("sentence").setOutputCol("words")

    val wordsData = tokenizer.transform(sentenceData)

    val hashingTf = new HashingTF().setInputCol("words").setOutputCol("rawFeatures").setNumFeatures(20)

    val featurizedData = hashingTf.transform(wordsData)

    val idf = new IDF().setInputCol("rawFeatures").setOutputCol("features")

    val idfModel = idf.fit(featurizedData)

    val rescaledData = idfModel.transform(featurizedData)
    rescaledData.select("label", "features").show()

    val testSentenceData = spark.createDataFrame(Seq(
      (0.0, "Hi I heard about Spark"),
      (0.0, "I wish Java could use case classes"),
      (1.0, "Logistic regression models are neat")
    )).toDF("label", "sentence")

    idfModel.transform(hashingTf.transform(tokenizer.transform(testSentenceData)))
      .select("label","rawFeatures")
      .collect()
      .foreach {println}

    // One-Hot encoding
    val df = spark.createDataFrame(Seq(
      (0.0, 1.0),
      (1.0, 0.0),
      (2.0, 1.0),
      (0.0, 2.0),
      (0.0, 1.0),
      (2.0, 0.0)
    )).toDF("categoryIndex1", "categoryIndex2")

    val encoder = new OneHotEncoder()
      .setInputCol("categoryIndex1")
      .setOutputCol("categoryVec1")

    // Logistic regression
  }
}
