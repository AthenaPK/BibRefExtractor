import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class CDSbib { 
 
 public static void main (String[] args) throws IOException {
  
  Scanner sc = new Scanner(System.in);
  String [] urlArray = new String[2];
  for (int i = 0; i< urlArray.length; i++){
   urlArray[i] = sc.nextLine();
   
  }
  //System.out.println(urlArray[2]);
  String bibtex = "/export/hx";
  for (int j = 0; j < urlArray.length; j++){
   urlArray[j] = urlArray[j].substring(0, urlArray[j].length()-6);
	  String finalUrl = urlArray[j] + bibtex;
   Document doc = Jsoup.connect(finalUrl).get();
      Elements bibref = doc.select("pre");
      String bibreftext = bibref.text();
      System.out.println(bibreftext);
  }
  sc.close();
 }

}