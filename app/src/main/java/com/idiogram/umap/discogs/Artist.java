import java.util.ArrayList;
import java.util.List;

public class Artist {
    private String albumArtist;
    private Personnel personnel;
    private List<Personnel> allPersonnel;

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
