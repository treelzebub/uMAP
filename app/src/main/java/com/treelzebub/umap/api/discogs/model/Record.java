package com.treelzebub.umap.api.discogs.model;

import com.treelzebub.umap.api.gemm.model.Medium;

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
