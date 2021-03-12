package followthewhiterabbit;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    /*
        We have a message for you. But we hid it.
        Unless you know the secret phrase, it will remain hidden.

        Can you write the algorithm to find it?

        Here is a couple of important hints to help you out:
        - An anagram of the phrase is: "poultry outwits ants"
        - There are three levels of difficulty to try your skills with
        - The MD5 hash of the easiest secret phrase is "e4820b45d2277f3844eac66c903e84be"
        - The MD5 hash of the more difficult secret phrase is "23170acc097c24edb98fc5488ab033fe"
        - The MD5 hash of the hard secret phrase is "665e5bcb0c20062fe8abaaf4628bb154"
        Here is a list of english words, it should help you out.
    */

    private static final String poultry = "poultry";
    private static final String outwits = "outwits";
    private static final String ants = "ants";
    public static final String RESOURCES_WORDLIST_TXT = "./src/main/resources/wordlist.txt";
    private static final String easiest = "e4820b45d2277f3844eac66c903e84be";
    private static final String difficult = "23170acc097c24edb98fc5488ab033fe";
    private static final String hardsecret = "665e5bcb0c20062fe8abaaf4628bb154";

    private static final List<String> hashes = Arrays.asList(easiest, difficult, hardsecret);

    public static void main(String[] args) {
        System.out.println("Follow the rabbit: ");

        List<String> poultryGram = new ArrayList<>();
        List<String> outwitsGram = new ArrayList<>();
        List<String> antsGram = new ArrayList<>();

        System.out.println();

        Anagram poultryTest = new Anagram(poultry);
        Anagram outwitsTest = new Anagram(outwits);
        Anagram antsTest = new Anagram(ants);

        try (BufferedReader br = new BufferedReader(new FileReader(RESOURCES_WORDLIST_TXT))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (poultryTest.test(line)) {
                    poultryGram.add(line);
                }
                if (outwitsTest.test(line)) {
                    outwitsGram.add(line);
                }
                if (antsTest.test(line)) {
                    antsGram.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println(poultryGram.toString());
        System.out.println(outwitsGram.toString());
        System.out.println(antsGram.toString());


        for (String p : poultryGram) {
            for (String o : outwitsGram) {
                for (String a : antsGram) {
                    for (String hash : hashes) {
                        check(p, o, a, hash);
                        check(p, a, o, hash);

                        check(o, p, a, hash);
                        check(o, a, p, hash);

                        check(a, o, p, hash);
                        check(a, p, o, hash);
                    }
                }
            }
        }

        //System.out.println("\t* MD5 * \t" + cryto.getMD5Hash("poultry outwits tans"));
        System.out.println("END");
    }

    private static void check(String one, String two, String third, String hash) {
        Crypto cryto = new Crypto();

        System.out.println(String.format("%s %s %s \t Hash: %s", one, two, third, hash));

        if (cryto.getMD5Hash(one + two + third).equals(hash)) {
            System.out.println("founded!! " + one + two + third);
        }

        if (cryto.getMD5Hash(one.toUpperCase() + two.toUpperCase() + third.toUpperCase()).equals(hash)) {
            System.out.println("founded: " + one + two + third);
        }
    }
}
