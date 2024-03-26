package org.example;

import java.util.Map;

public class ReverseGeocodingResponse {
    private int place_id;
    private String licence;
    private String osm_type;
    private long osm_id;
    private String lat;
    private String lon;
    private String class_;
    private String type;
    private int place_rank;
    private double importance;
    private String addresstype;
    private String name;
    private String display_name;
    private Address address;
    private Map<String, String> extratags;
    private String[] boundingbox;

    public String getAddresstype() {
        return addresstype;
    }

    public static class Address {

        private String park;
        private String house_number;
        private String road;
        private String city;
        private String county;
        private String state;
        private String ISO3166_2_lvl4;
        private String postcode;
        private String country;
        private String country_code;
        public String getHouse_number() {
            return house_number;
        }

        public String getRoad() {
            return road;
        }

        public String getCity() {
            return city;
        }

        public String getCounty() {
            return county;
        }

        public String getState() {
            return state;
        }

        public String getISO3166_2_lvl4() {
            return ISO3166_2_lvl4;
        }

        public String getPostcode() {
            return postcode;
        }

        public String getCountry() {
            return country;
        }

        public String getCountry_code() {
            return country_code;
        }

        public String getPark() {
            return park;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Append house number and road
        if (address != null) {
            if (address.house_number != null && !address.house_number.isEmpty()) {
                sb.append(address.house_number).append(" ");
            }
            if (address.road != null && !address.road.isEmpty()) {
                sb.append(address.road).append(", ");
            }
        }

        // Append city, county, state, postcode, and country
        if (address != null) {
            if (address.city != null && !address.city.isEmpty()) {
                sb.append(address.city).append(", ");
            }
            if (address.county != null && !address.county.isEmpty()) {
                sb.append(address.county).append(", ");
            }
            if (address.state != null && !address.state.isEmpty()) {
                sb.append(address.state).append(", ");
            }
            if (address.postcode != null && !address.postcode.isEmpty()) {
                sb.append(address.postcode).append(", ");
            }
            if (address.country != null && !address.country.isEmpty()) {
                sb.append(address.country);
            }
        }

        return sb.toString();
    }

    // Getters and setters (You can generate these using your IDE or write them manually)
    public int getPlace_id() {
        return place_id;
    }

    public void setPlace_id(int place_id) {
        this.place_id = place_id;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getOsm_type() {
        return osm_type;
    }

    public void setOsm_type(String osm_type) {
        this.osm_type = osm_type;
    }

    public long getOsm_id() {
        return osm_id;
    }

    public void setOsm_id(long osm_id) {
        this.osm_id = osm_id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getClass_() {
        return class_;
    }

    public void setClass_(String class_) {
        this.class_ = class_;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPlace_rank() {
        return place_rank;
    }

    public void setPlace_rank(int place_rank) {
        this.place_rank = place_rank;
    }

    public Address getAddress() {
        return address;
    }

    public double getImportance() {
        return importance;
    }

    public void setImportance(double importance) {
        this.importance = importance;
    }
}
