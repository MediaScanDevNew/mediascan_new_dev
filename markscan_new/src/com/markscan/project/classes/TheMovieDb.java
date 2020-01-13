package com.markscan.project.classes;

import java.util.HashMap;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbSearch;
import info.movito.themoviedbapi.TmdbTV;
import info.movito.themoviedbapi.TmdbTvSeasons;
import info.movito.themoviedbapi.TvResultsPage;
import info.movito.themoviedbapi.model.tv.TvEpisode;
import info.movito.themoviedbapi.model.tv.TvSeason;
import info.movito.themoviedbapi.model.tv.TvSeries;

public class TheMovieDb {
	
	
	public HashMap<String, HashMap<String, HashMap<String, String>>> getEnglishTvShowDtails(String project_nm){
		HashMap<String, HashMap<String, HashMap<String, String>>> tvContentDetails = new HashMap<String, HashMap<String, HashMap<String, String>>>();
		try {
			int NO_OF_SEASONS = 0;
			String API_KEY = "7c619719f398bf4e91191231112c0236";
			String SEASON_NAME = project_nm.trim();
			TmdbApi tmdbApi = new TmdbApi(API_KEY);
			HashMap<String, String> tvSeasons 	= searchSeasons(tmdbApi, SEASON_NAME);
			NO_OF_SEASONS = Integer.valueOf(tvSeasons.get("totalSeasons"));
			
			
			if(tvSeasons.isEmpty()) {
	        	System.err.println("No TV Content Found !");
	        	
	        } else {
	        	//HashMap<String, HashMap<String, HashMap<String, String>>> tvContentDetails = getSeasonsDetails(tmdbApi, tvSeasons, NO_OF_SEASONS);
	        	tvContentDetails = getSeasonsDetails(tmdbApi, tvSeasons, NO_OF_SEASONS);
	        	//System.out.println("Season Info5555555555555555 : "+ tvContentDetails);
	        }    
			
		}catch(Exception ex) {
    		//System.out.println("OOPS !! Something Went Wrong !! : "+ ex);
			ex.printStackTrace();
    	}
		return tvContentDetails;
	}
	
	
	
	
	public HashMap<String,String> searchSeasons(TmdbApi tmdbApi, String seriesName) {
    	HashMap<String,String> tvSeriesResults	= new HashMap<String, String>();
    	String language = "en-US";
    	/* We can Search Any Web Series As well As Any Movies Using this TmdbSeach  */
    	TmdbSearch tmdbSearch 	= tmdbApi.getSearch();
    	TvResultsPage tvResults = tmdbSearch.searchTv(seriesName, language, 1);
    	if(!tvResults.getResults().isEmpty()) {
    	
	    	for(TvSeries res : tvResults.getResults())
	    		tvSeriesResults.put("seriesId", String.valueOf(res.getId()));
	    	
	    	/* Get Complete Details About Seasons Using TmdbTV */
	    	TmdbTV searchTvSeries	= tmdbApi.getTvSeries();
	    	TvSeries seasonInfo		= searchTvSeries.getSeries(Integer.parseInt(tvSeriesResults.get("seriesId")), language, TmdbTV.TvMethod.values());
	    	
	    	tvSeriesResults.put("originalName", seasonInfo.getOriginalName());
	    	tvSeriesResults.put("firstAirDate", seasonInfo.getFirstAirDate());
	    	tvSeriesResults.put("lastAirDate", seasonInfo.getLastAirDate());
	    	tvSeriesResults.put("totalSeasons", String.valueOf(seasonInfo.getNumberOfSeasons()));
	    	tvSeriesResults.put("totalEpisodes", String.valueOf(seasonInfo.getNumberOfEpisodes()));
	    	tvSeriesResults.put("homePage", seasonInfo.getHomepage());
	    	tvSeriesResults.put("overview", seasonInfo.getOverview());
	    	tvSeriesResults.put("posterPath", seasonInfo.getPosterPath());
	    	tvSeriesResults.put("status", seasonInfo.getStatus());
	    	tvSeriesResults.put("popularity", String.valueOf(seasonInfo.getPopularity()));
	    	tvSeriesResults.put("userRating", String.valueOf(seasonInfo.getUserRating()));
	    	tvSeriesResults.put("averageVote", String.valueOf(seasonInfo.getVoteAverage()));
	    	tvSeriesResults.put("voteCount", String.valueOf(seasonInfo.getVoteCount()));
	    	tvSeriesResults.put("credits", seasonInfo.getCredits().getAll().toString());
	    	tvSeriesResults.put("seasonContent", seasonInfo.getSeasons().toString());
	    	
	    	if(!seasonInfo.getEpisodeRuntime().isEmpty())
	    		tvSeriesResults.put("episodeRuntime", String.valueOf(seasonInfo.getEpisodeRuntime().get(0).intValue()));
	    	
	    	if(!seasonInfo.getContentRatings().isEmpty())
	    		tvSeriesResults.put("contentRating", String.valueOf(seasonInfo.getContentRatings().get(0).getRating()));
	    	
    	}
    	
    	return tvSeriesResults;

    }
	
	public HashMap<String, HashMap<String, HashMap<String, String>>> getSeasonsDetails(TmdbApi tmdbApi, HashMap<String, String> tvSeries, int noOfSeasons) {
	
		HashMap<String, HashMap<String,String>> episodeDetails 	= null;
    	HashMap<String,String> contentList = null;
    	HashMap<String, HashMap<String, HashMap<String, String>>> seasonInfo = new HashMap<String, HashMap<String, HashMap<String, String>>>();
    	String language = "en-US";
    	TmdbTvSeasons tmdbTvSeasons = tmdbApi.getTvSeasons();
    	
    	for(int seasonNumber = 1; seasonNumber <= noOfSeasons; seasonNumber++) {
    		TvSeason tvSeason 	=  tmdbTvSeasons.getSeason(Integer.parseInt(tvSeries.get("seriesId")), seasonNumber, language, TmdbTvSeasons.SeasonMethod.values());
    		episodeDetails		= new HashMap<String, HashMap<String,String>>();
    		
		    for(TvEpisode episode : tvSeason.getEpisodes()) {
		    	//System.out.print("Episode details -------------->"+episode.get);
		    	contentList 	= new HashMap<String,String>();
		    	
		    	contentList.put("seasonName", tvSeries.get("originalName"));
		    	contentList.put("SeasonNumber", String.valueOf(episode.getSeasonNumber()));
		    	contentList.put("episodeId", String.valueOf(episode.getId()));
		    	contentList.put("episodeNo", String.valueOf(episode.getEpisodeNumber()));
		    	contentList.put("episodeName", episode.getName());
		    	contentList.put("episodeReleaseDate", episode.getAirDate());
		    	contentList.put("averageVote", String.valueOf(episode.getVoteAverage()));
		    	contentList.put("voteCount", String.valueOf(episode.getVoteCount()));
		    	episodeDetails.put(episode.getName(), contentList);
		    	seasonInfo.put("Season "+seasonNumber, episodeDetails);
		    }
		    
    	}
//    	System.out.println("Season Info : "+ seasonInfo);
	    
	    return seasonInfo;
    }
	
}	
