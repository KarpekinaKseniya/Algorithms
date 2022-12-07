package org.example.url;

import java.util.HashMap;
import java.util.Map;

/*
    Encode and Decode TinyURL

    There is no restriction on how your encode/decode algorithm should work.
    You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded
    to the original URL.

    Implement the Solution class:
        Solution() Initializes the object of the system.
        String encode(String longUrl) Returns a tiny URL for the given longUrl.
        String decode(String shortUrl) Returns the original long URL for the given shortUrl.
            It is guaranteed that the given shortUrl was encoded by the same object.
*/
public class Codec {

    private static final String MAIN_TINY_URL = "https://tinyurl.com/";
    private static final String EMPTY_STRING = "";

    private final Map<Integer, String> map = new HashMap<>();

    public String encode(String longUrl) {
        final int urlHashCode = longUrl.hashCode();
        map.put(urlHashCode, longUrl);
        return MAIN_TINY_URL + urlHashCode;
    }

    public String decode(String shortUrl) {
        return map.get(Integer.parseInt(shortUrl.replace(MAIN_TINY_URL, EMPTY_STRING)));
    }
}
