import com.kenzie.app.http.HttpUtil;

public class Application {
    public static void main(String[] args) {



        // sample url https://api.zippopotam.us/us/ma/belmont for testing
        try {
            String url = "https://api.zippopotam.us/us/ma/belmont";
            String response = HttpUtil.get(url);
            System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
