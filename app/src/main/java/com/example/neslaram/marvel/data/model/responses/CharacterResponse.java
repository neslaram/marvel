package com.example.neslaram.marvel.data.model.responses;

import com.example.neslaram.marvel.data.model.Character;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by neslaram on 04/09/16.
 */
public class CharacterResponse {
    @SerializedName("data")
    private Data data;
    @SerializedName("code")
    private int code;
    @SerializedName("etag")
    private String etag;
    @SerializedName("status")
    private String status;

    public Data getData() {
        return data;
    }


    public class Data {
        @SerializedName("results")
        private List<Character> results;
        @SerializedName("count")
        private int count;
        @SerializedName("limit")
        private int limit;
        @SerializedName("offset")
        private int offset;
        @SerializedName("total")
        private int total;

        public List<Character> getResults() {
            return results;
        }

        public int getCount() {
            return count;
        }

        public int getLimit() {
            return limit;
        }

        public int getOffset() {
            return offset;
        }

        public int getTotal() {
            return total;
        }
    }
}
