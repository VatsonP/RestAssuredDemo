package utilities;

public class APIConstant {

    public static String BaseURLstr = "http://localhost:3000/";

    public static class ApiMethods {
        public static final String POST = "POST";
        public static final String GET = "GET";
        public static final String DELETE = "DELETE";
   }

   public static class StrUtils {

       public static final String addSegToPath(String path, String segment) {

           String newPath, newSegment;
           int startCharNum = 0;

           if (segment == null) {
               newSegment = "";
           } else if (segment.charAt(startCharNum) == '/') {
               newSegment = removeChar(segment, startCharNum);
           } else
               newSegment = segment;

           if (path == null) {
               newPath = "";
           } else if (path.charAt(path.length() - 1) == '/') {
               newPath = removeChar(path, (path.length() - 1));
           } else
               newPath = path;

           return newPath + "/" + newSegment;
       }

       public static final String removeChar(String str, Integer n) {
           String front = str.substring(0, n);
           String back = str.substring(n + 1, str.length());
           return front + back;
       }
   }
}