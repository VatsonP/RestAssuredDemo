package utilities;

public class APIConstant {

    public static class ApiMethods {
        public static final String POST = "POST";
        public static final String GET = "GET";
        public static final String DELETE = "DELETE";
   }

    public static String BaseURIstr = "http://localhost:3000/";

    public static String addSegToPath(String path, String segment) {
        if (path == null || path.isEmpty()) {
            return "/" + segment;
        }

        if (path.charAt(path.length() - 1) == '/') {
            return path + segment;
        }

        return path + "/" + segment;
    }
}