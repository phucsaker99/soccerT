package com.example.soccert.utils

object ApiConfig {
    const val BASE_URL_SOCCER = "https://apiv2.apifootball.com/"
    const val BASE_URL_SEARCH_NEWS = "https://newsapi.org/v2/top-headlines/"
    const val API_SOCCER_KEY = "APIkey"
    const val API_SEARCH_NEWS_KEY = "apiKey"
}

object ApiEndPoint {
    const val GET_COUNTRIES = "?action=get_countries"
    const val GET_LEAGUES = "?action=get_leagues"
    const val GET_TEAMS = "?action=get_teams"
    const val GET_PLAYERS = "?action=get_players"
    const val GET_STANDINGS = "?action=get_standings"
    const val GET_EVENTS = "?action=get_events"

    const val GET_SEARCH_SPORTS = "?category=sports"

    const val PARAMS_COUNTRY_ID = "country_id"
    const val PARAMS_LEAGUE_ID = "league_id"
    const val PARAMS_TEAM_ID = "team_id"
    const val PARAMS_MATCH_ID = "match_id"
    const val PARAMS_PLAYER_NAME = "player_name"
    const val PARAMS_FROM = "from"
    const val PARAMS_TO = "to"
}

object KoinConfig {
    const val SOCCER_CLIENT_NAME = "soccerClient"
    const val SEARCH_CLIENT_NAME = "searchClient"
    const val SOCCER_RETROFIT_NAME = "soccerClient"
    const val SEARCH_RETROFIT_NAME = "searchClient"
    const val SOCCER_SERVICE = "soccerService"
    const val SEARCH_SERVICE = "searchService"
}

object SharePreferencesConst {
    const val PREFS_NAME = "SOCCERT_PREFS"

    const val PREFS_LANGUAGE_KEY = "language_key"
    const val LANGUAGE_VIETNAMESE = "vi"
    const val LANGUAGE_ENGLISH = "en"
    const val PREFS_COMPETITION_KEY = "competition_key"
}
