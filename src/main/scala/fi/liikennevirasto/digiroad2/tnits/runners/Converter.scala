package fi.liikennevirasto.digiroad2.tnits.runners

import java.net.URLEncoder
import java.time.Instant
import java.time.temporal.{ChronoUnit, TemporalAmount, TemporalUnit}

import dispatch.Http
import fi.liikennevirasto.digiroad2.tnits.aineistot.RemoteDatasets
import fi.liikennevirasto.digiroad2.tnits.oth.OTHClient
import fi.liikennevirasto.digiroad2.tnits.rosatte.RosatteConverter

object Converter {
  def main(args: Array[String]) {
    try {
      val start = RemoteDatasets.getLatestEndTime.getOrElse(Instant.now.minus(1, ChronoUnit.DAYS))
      val end = Instant.now.minus(1, ChronoUnit.MINUTES)
      val speedLimits = OTHClient.readSpeedLimitChanges(start, end)
      val dataSet = RosatteConverter.convert(speedLimits, start, end)
      println(dataSet.updates)
      val filename = s"${URLEncoder.encode(dataSet.id, "UTF-8")}.xml"
      RemoteDatasets.put(filename, dataSet.updates)
    } finally {
      Http.shutdown()
    }
  }
}
