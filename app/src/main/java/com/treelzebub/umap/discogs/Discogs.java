package com.treelzebub.umap.discogs;

import java.util.ArrayList;
import java.util.List;

import retrofit.client.Response;

/**
 * Created by Tre Murillo 1/27/2015
 */

public class Discogs {
    private String albumArtist;
    private Personnel personnel;
    private List<Personnel> allPersonnel;

    protected static int Discogs(Response response) {
        //TODO stuff

        return response.getStatus();
    }


    public String getAlbumArtist() {
        return albumArtist;
    }

    public void setAlbumArtist(String albumArtist) {
        this.albumArtist = albumArtist;
    }

    public List<Personnel> getPersonnel() {
        return allPersonnel;
    }

    public void addPersonnel(Personnel personnel) {
        if (allPersonnel.isEmpty()) {
            allPersonnel = new ArrayList<Personnel>();
        }

        allPersonnel.add(personnel);
    }

    public List<Personnel> getAllPersonnel() {
        if (allPersonnel.isEmpty()) {
            Personnel soloArtist = new Personnel(albumArtist, "Artist");

            return allPersonnel;
        }
        return allPersonnel;
    }

    private class Personnel {
        String name, job;

        Personnel(String name, String job) {
            this.name = name;
            this.job = job;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }
    }

}
