package com.mkodo.training.stevenw.movieapp.api

interface DiscoverMoviesParamsBuilder {

    var sortBy: String?
    var certificationCountry: String?
    var certification: String?
    var certificationLessThan: String?
    var certificationMoreThan: String?
    var includeAdult: Boolean?
    var includeVideo: Boolean?
    var page: Int?
    var primaryReleaseYear: Int?
    var primaryReleaseDateGreaterThan: String?
    var releaseDateGreaterThan: String?
    var releaseDateLessThan: String?
    var withReleaseType: Int?
    var year: Int?
    var voteCountGreaterThan: Int?
    var voteCountLesserThan: Int?


}