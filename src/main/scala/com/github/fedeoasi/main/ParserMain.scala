package com.github.fedeoasi.main

import com.github.fedeoasi.Constants._
import com.github.fedeoasi.model.DailyRideCount
import com.github.fedeoasi.parsing.{StationParser, DailyRidesParser}

object ParserMain {
  def main(args: Array[String]) {

    println("Parsing stations file...")
    val stationParser = new StationParser
    val stations = stationParser.parse(ctaLStationsFile)
    println(s"Found ${stations.size} stations")
    println(stations)

    println("Parsing daily rides file...")
    val RidesParser = new DailyRidesParser()
    val dailyRideCounts = RidesParser.parse(ctaDailyRidesFile)

    println("Computing Top dailyRideCount...")
    val maxRides = dailyRideCounts.max(Ordering.by[DailyRideCount, Int](_.rides))
    println(maxRides)

    println("Computing Distinct Stations...")
    val distinctStations = dailyRideCounts.map(_.station).toSet
    println(distinctStations.size)
    println(distinctStations)
  }
}
