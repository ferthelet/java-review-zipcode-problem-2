import com.kenzie.app.format.AddressFormatUtil;
import com.kenzie.app.http.HttpUtil;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.app.data.zipcode.ZipCodeDTO;

import java.util.Scanner;


// github https://github.com/ferthelet/java-review-zipcode-problem-2

public class Application {

    enum StringType {
        NUMBER, STR_SPC, STR_NUM_SPC, STR, STR_NUM
    }

    public static String cleanString(String string, StringType cleanType) {
        String cleanString = string.replaceAll("\\s+", " ").trim().toUpperCase();

        switch (cleanType) {
            case NUMBER:
                cleanString = cleanString.replaceAll("[^0-9]", "");
                break;
            case STR_SPC:
                cleanString = cleanString.replaceAll("[^a-zA-Z ]", "");
                break;
            case STR_NUM_SPC:
                cleanString = cleanString.replaceAll("[^a-zA-Z0-9 ]", "");
                break;
            case STR:
                cleanString = cleanString.replaceAll("[^a-zA-Z]", "");
                break;
            case STR_NUM:
                cleanString = cleanString.replaceAll("[^a-zA-Z0-9]", "");
                break;
        }
        return cleanString;
    }

    public static void main(String[] args) {
        // declare vars
        String BASE_URL = "https://api.zippopotam.us/us/";
        Scanner scanner = new Scanner(System.in);
        String recipientName;
        String street;
        String city;
        String state;
        String zipCode;

        // read user input
        System.out.println("Enter recipient name: ");
        recipientName = cleanString(scanner.nextLine(), StringType.STR_SPC);
        System.out.println("Enter street: ");
        street = cleanString(scanner.nextLine(), StringType.STR_NUM_SPC);
        System.out.println("Enter city: ");
        city = cleanString(scanner.nextLine(), StringType.STR_SPC);
        System.out.println("Enter state: ");
        state = cleanString(scanner.nextLine(), StringType.STR_SPC).substring(0, 2);
        System.out.println("Enter zip code: ");
        zipCode = cleanString(scanner.nextLine(), StringType.NUMBER).substring(0, 5);
        System.out.println(zipCode);

        System.out.println(recipientName);
        System.out.println(street);
        System.out.println(city);
        System.out.println(state);
        System.out.println(zipCode);

        // clean spaces
        String tempCity = city.replaceAll(" ", "%20");

        //format user input
        //String formattedAddress = AddressFormatUtil.formatAddress(recipientName, street, city, state, zipCode);
        String finalURL = BASE_URL + state + "/" + zipCode;
        System.out.println(finalURL);

        // call httpUtil to get json string

        // convert json string to ZipCodeDTO object

        // print out the state and place name


    }

    public static void main_backup(String[] args) {



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

            // calling format from app main
            String testStr = "123 Main St.";
            AddressFormatUtil.initCodeTable();
            System.out.println(AddressFormatUtil.replaceAbbreviation(testStr));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
