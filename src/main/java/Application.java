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
        street = AddressFormatUtil.replaceAbbreviation(cleanString(scanner.nextLine(), StringType.STR_NUM_SPC));
        System.out.println("Enter city: ");
        city = cleanString(scanner.nextLine(), StringType.STR_SPC);
        System.out.println("Enter state: ");
        state = cleanString(scanner.nextLine(), StringType.STR_SPC).substring(0, 2);
        // System.out.println("Enter zip code: ");
        //zipCode = cleanString(scanner.nextLine(), StringType.NUMBER).substring(0, 5);

        System.out.println("Partial address: ");
        System.out.println(recipientName);
        System.out.println(street);
        System.out.println(city);
        System.out.println(state);
        // System.out.println(zipCode);

        // replace spaces
        String tempCity = city.replaceAll(" ", "%20");

        //format user input
        //String formattedAddress = AddressFormatUtil.formatAddress(recipientName, street, city, state, zipCode);
        String finalURL = BASE_URL + state + "/" + tempCity;
        System.out.println(finalURL);

        // call httpUtil to get json string
        // sample url https://api.zippopotam.us/us/ma/belmont for testing
        try {
            String response = HttpUtil.get(finalURL);
            if (response.contains("GET request failed")) {
                System.out.println("Please check the information entered");
            } else {
                // json object mapping
                // 1. object mapper
                ObjectMapper mapper = new ObjectMapper();
                // 2. dto class + read value
                ZipCodeDTO zipCodeDTO = mapper.readValue(response, ZipCodeDTO.class);
                // process it
                if (zipCodeDTO.getPlaces().size() == 1) {
                    zipCode = zipCodeDTO.getPlaces().get(0).getPostCode();
//                    System.out.println(zipCodeDTO.getState());
//                    System.out.println(zipCodeDTO.getPlaces().get(0).getPlaceName());
//                    System.out.println(zipCodeDTO.getPlaces().get(0).getPostCode());
                } else {
                    System.out.println("Please select zip code");
                    String userInput;
                    for (int i = 0; i < zipCodeDTO.getPlaces().size(); i++) {
                        System.out.println("(" + (i + 1) + ") " + zipCodeDTO.getPlaces().get(i).getPlaceName() +
                                " " + zipCodeDTO.getPlaces().get(i).getPostCode());
//                        System.out.println(zipCodeDTO.getState());
//                        System.out.println(zipCodeDTO.getPlaces().get(i).getPlaceName());
//                        System.out.println(zipCodeDTO.getPlaces().get(i).getPostCode());
                    }
                    System.out.println("Enter zip code selection: ");
                    userInput = scanner.nextLine();
                    System.out.println(userInput);
                    System.out.println(zipCodeDTO.getPlaces().get(Integer.parseInt(userInput) - 1).getPostCode());
                    zipCode = zipCodeDTO.getPlaces().get(Integer.parseInt(userInput) - 1).getPostCode();
                }
                System.out.println("Final address: ");
                System.out.println(recipientName);
                System.out.println(street);
                System.out.println(city + ", " + state + " " + zipCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // convert json string to ZipCodeDTO object

        // print out the state and place name


    }
}
