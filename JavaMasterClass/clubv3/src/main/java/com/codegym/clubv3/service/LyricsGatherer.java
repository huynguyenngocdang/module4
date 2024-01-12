package com.codegym.clubv3.service;

import java.io.IOException;
import java.util.List;

public interface LyricsGatherer {
    List<String> getSongLyrics(String band, String songTitle) throws IOException;
}
