package com.crio.shorturl;

import java.util.*;

public class XUrlImpl implements XUrl {

private Map<String, String> urlMap = new HashMap<>();
private Map<String, Integer> count = new HashMap<>();
private Map<String,String> reverseMap = new HashMap<>();

@Override
public String registerNewUrl(String longUrl) {
Random rand = new Random();
//create map for storing longurl and it's associated shorturl
//if longurl is already exist
if(urlMap.containsKey(longUrl)) 
return urlMap.get(longUrl);
else{
//if long url is not existed then register and return it
String shortUrlPrefix = "http://short.url/";
StringBuilder sb = new StringBuilder("");
for(int i=0;i<9;i++){
int alphaNumeric = rand.nextInt (3);
if(alphaNumeric == 0){
char ch = (char) (rand.nextInt(10)+48);
sb.append(ch);
} 
else if(alphaNumeric == 1){
char ch= (char) (rand.nextInt(26)+65);
sb.append(ch);
}
else{
  char ch = (char) (rand.nextInt(26)+97);
  sb.append(ch);
  }
  }
  
  String shortUrlSuffix = sb.toString();
  String shortUrl = shortUrlPrefix + shortUrlSuffix;
   urlMap.put(longUrl, shortUrl); 
   reverseMap.put(shortUrl, longUrl);
    return urlMap.get(longUrl);
  
  }
  
  }
  
  @Override
  public String registerNewUrl(String longUrl, String shortUrl) {
  if(reverseMap.containsKey(shortUrl)){
  return null;
  } 
  else{
  urlMap.put(longUrl, shortUrl); 
  reverseMap.put (shortUrl,longUrl);
  return shortUrl;
  }
  
  }
  
  @Override
  public String getUrl(String shortUrl) {
  String ans = "";
  if(reverseMap.containsKey(shortUrl)) {
    ans = reverseMap.get(shortUrl);
     if(count.containsKey(ans)) {
      int c = count.get((ans));
      c += 1;
      count.put(ans, c);
      }else{
      count.put(ans, 1);
      }
      return ans;
      }
      else
      return null;
      }
      
      @Override
      public Integer getHitCount(String longUrl) {
      if(count.containsKey(longUrl)) 
      return count.get(longUrl);
      else{
      return 0;
      }
      }
      @Override
      public String delete(String longUrl) {
      //string short = urlMap.get(longurl); 
      reverseMap.remove(urlMap.get(longUrl));
      urlMap.remove(longUrl);
      return null;
      }
}