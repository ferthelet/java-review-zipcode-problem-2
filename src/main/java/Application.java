import com.kenzie.app.http.HttpUtil;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.app.data.zipcode.ZipCodeDTO;


// github https://github.com/ferthelet/java-review-zipcode-problem-2

public class Application {
    public static void main(String[] args) {



        // sample url https://api.zippopotam.us/us/ma/belmont for testing
        try {
            String url = "https://api.zippopotam.us/us/ca/los%20angeles";
            String response = HttpUtil.get(url);
            System.out.println(response.toString());

            // json object mapping
            // 1. object mapper
            ObjectMapper mapper = new ObjectMapper();
            // 2. dto class + read value
            ZipCodeDTO zipCode = mapper.readValue(response, ZipCodeDTO.class);
            // print
            System.out.println();
            System.out.println(zipCode.getState());
            System.out.println(zipCode.getPlaces().get(0).getPlace_name());
            System.out.println(zipCode.getPlaces().get(0).getPost_code());

            System.out.println("In case of more than one record");
            if (zipCode.getPlaces().size() == 1) {
                System.out.println(zipCode.getState());
                System.out.println(zipCode.getPlaces().get(0).getPlace_name());
                System.out.println(zipCode.getPlaces().get(0).getPost_code());
            } else {
                for (int i = 0; i < zipCode.getPlaces().size(); i++) {
                    System.out.println("Zone " + i);
                    System.out.println(zipCode.getState());
                    System.out.println(zipCode.getPlaces().get(i).getPlace_name());
                    System.out.println(zipCode.getPlaces().get(i).getPost_code());
                }
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
