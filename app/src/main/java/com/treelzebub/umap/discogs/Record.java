package com.treelzebub.umap.discogs;

import com.treelzebub.umap.gemm.Medium;

import org.joda.time.DateTime;

import java.util.List;

public class Record {
    private String recordTitle, year, country, catalogNumber;
    private Medium medium;
    private String[] genre, style;
    private Artist artist;
    private List<Track> tracklist;
    private DateTime releaseDate, pressingDate;


}
