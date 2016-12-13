package main;

import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;
import com.google.api.services.youtube.model.Video;
import model.MainModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

/**
 * Created by tld on 12/12/2016.
 */
public class MainTerminal {

    private static final long NUMBER_OF_VIDEOS_RETURNED = 5;
    static MainModel model = new MainModel();


    private static String getInputQuery() throws IOException {

        String inputQuery = "";

        //System.out.print("Please enter a search term: ");
        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
        inputQuery = bReader.readLine();

        if (inputQuery.length() < 1) {
            System.out.print("Please enter a search term \n");
        }
        return inputQuery;
    }

    private static void prettyPrint(Iterator<SearchResult> iteratorSearchResults, String query) {

        System.out.println("\n=============================================================");
        System.out.println(
                "   First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + query + "\".");
        System.out.println("=============================================================\n");

        if (!iteratorSearchResults.hasNext()) {
            System.out.println(" There aren't any results for your query.");
        }

        while (iteratorSearchResults.hasNext()) {

            SearchResult singleVideo = iteratorSearchResults.next();
            ResourceId rId = singleVideo.getId();

            // Confirm that the result represents a video. Otherwise, the
            // item will not contain a video ID.
            if (rId.getKind().equals("youtube#video")) {
                Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();

                System.out.println(" Video Id" + rId.getVideoId());
                System.out.println(" Title: " + singleVideo.getSnippet().getTitle());
                System.out.println(" Thumbnail: " + thumbnail.getUrl());
                System.out.println("\n-------------------------------------------------------------\n");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String menuQuery="0";
        String searchQuery;
        String selectVideo;

        System.out.println("\n-------------------------------------------------------------\n");
        System.out.println("\n----------Welcome to Youtube Terminal Monkey Boy-------------\n");
        System.out.println("\n-------------------------------------------------------------\n");
        while (Integer.parseInt(menuQuery)<1 || Integer.parseInt(menuQuery)>1) {
            System.out.println("What do you want to do ? \n");
            System.out.println("1-Search\n");
            System.out.println("2-Sign in\n");
            System.out.println("3-Quit\n");
            menuQuery = getInputQuery();
            switch (Integer.parseInt(menuQuery)){
                case 1:
                    System.out.print("Please enter a search term: ");
                    searchQuery = getInputQuery();

                    List<SearchResult> searchResultList = model.search(NUMBER_OF_VIDEOS_RETURNED,searchQuery);
                    prettyPrint(searchResultList.iterator(),searchQuery);

                    System.out.print("Please select a video (0-4) : ");
                    selectVideo = getInputQuery();

                    //model.initialize(searchQuery);
                    System.out.println("    Video title : " + model.getVideoTitle(searchResultList.get(Integer.parseInt(selectVideo)))+"\n");
                    System.out.println("    Video id : " + model.getVideoID(searchResultList.get(Integer.parseInt(selectVideo)))+"\n");
                    System.out.println("    Video thumbnail URL : " + model.getVideoThumbnail(searchResultList.get(Integer.parseInt(selectVideo)))+"\n");
                    menuQuery="0";
                    break;
                case 2:
                   model.signIn();
                    break;
                case 3:
                    return;

                default:
                    System.out.println("Invalid entry\n");


            }
        }

    }

}
