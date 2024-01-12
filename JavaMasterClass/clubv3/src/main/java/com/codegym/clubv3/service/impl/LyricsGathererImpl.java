package com.codegym.clubv3.service.impl;

import com.codegym.clubv3.service.LyricsGatherer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LyricsGathererImpl implements LyricsGatherer {

    @Override
    public List<String> getSongLyrics(String band, String songTitle) throws IOException {
        String songLyricsURL = "http://www.songlyrics.com";
        List<String> lyrics= new ArrayList<String>();

        Document doc = Jsoup.connect(songLyricsURL+ "/"+band.replace(" ", "-").toLowerCase()+"/"+songTitle.replace(" ", "-").toLowerCase()+"-lyrics/").get();
        String title = doc.title();
        System.out.println(title);
        Element p = doc.select("p.songLyricsV14").get(0);
        for (Node e: p.childNodes()) {
            if (e instanceof TextNode) {
                lyrics.add(((TextNode)e).getWholeText());
            }
        }
        return lyrics;
    }

}