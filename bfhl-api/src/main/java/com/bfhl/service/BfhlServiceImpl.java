package com.bfhl.service;

import com.bfhl.dto.BfhlRequest;
import com.bfhl.dto.BfhlResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    @Value("${app.user.full-name}")
    private String fullName;

    @Value("${app.user.dob}")
    private String dob;

    @Value("${app.user.email}")
    private String email;

    @Value("${app.user.roll-number}")
    private String rollNumber;

    @Override
    public BfhlResponse processData(BfhlRequest request) {
        List<String> data = request.getData();

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        long numericSum = 0;
        StringBuilder allAlphabets = new StringBuilder();

        for (String item : data) {
            if (isNumeric(item)) {
                long number = Long.parseLong(item);
                numericSum += number;
                if (number % 2 == 0) {
                    evenNumbers.add(item);
                } else {
                    oddNumbers.add(item);
                }
            } else if (isAlphabetic(item)) {
                alphabets.add(item.toUpperCase());
                // Collect individual alphabetical characters for concat
                for (char c : item.toCharArray()) {
                    if (Character.isLetter(c)) {
                        allAlphabets.append(c);
                    }
                }
            } else {
                // Special characters (includes mixed strings with non-alpha, non-digit)
                specialCharacters.add(item);
            }
        }

        // Build concat string: reverse all alphabetical chars, then alternate caps
        String concatString = buildConcatString(allAlphabets.toString());

        BfhlResponse response = new BfhlResponse();
        response.setSuccess(true);
        response.setUserId(buildUserId());
        response.setEmail(email);
        response.setRollNumber(rollNumber);
        response.setOddNumbers(oddNumbers);
        response.setEvenNumbers(evenNumbers);
        response.setAlphabets(alphabets);
        response.setSpecialCharacters(specialCharacters);
        response.setSum(String.valueOf(numericSum));
        response.setConcatString(concatString);

        return response;
    }

    /**
     * Checks if a string is purely numeric (handles negative numbers too)
     */
    private boolean isNumeric(String s) {
        if (s == null || s.isEmpty()) return false;
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if a string contains only alphabetical characters
     */
    private boolean isAlphabetic(String s) {
        if (s == null || s.isEmpty()) return false;
        for (char c : s.toCharArray()) {
            if (!Character.isLetter(c)) return false;
        }
        return true;
    }

    /**
     * Reverses the full alphabetical string, then applies alternating caps
     * starting with UPPERCASE for the first character.
     *
     * E.g., "ayb" reversed = "bya" -> alternating caps = "ByA"
     */
    private String buildConcatString(String alphabetStr) {
        if (alphabetStr == null || alphabetStr.isEmpty()) return "";

        // Reverse the string
        String reversed = new StringBuilder(alphabetStr).reverse().toString();

        // Apply alternating caps: index 0 = uppercase, index 1 = lowercase, ...
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);
            if (i % 2 == 0) {
                result.append(Character.toUpperCase(c));
            } else {
                result.append(Character.toLowerCase(c));
            }
        }
        return result.toString();
    }

    /**
     * Builds the user_id in the format: fullname_ddmmyyyy
     */
    private String buildUserId() {
        return fullName.toLowerCase().replace(" ", "_") + "_" + dob;
    }
}
