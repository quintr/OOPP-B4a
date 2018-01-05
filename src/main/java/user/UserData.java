package user;

import main.KeyValuePair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Key;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UserData {
    String name;
    String filename;
    ArrayList<UserDateScore> scores;
    Map<String, UserPreferenceValue> preferences;

    public UserData() {
        scores = new ArrayList<>();
        preferences = new HashMap<>();
    }

    public int getIntPreference(String key) {
        return preferences.get(key).valueAsInt();
    }

    public String getPreference(String key) {
        return preferences.get(key).value;
    }

    public Boolean getBoolPreference(String key) {
        return preferences.get(key).valueAsBoolean();
    }

    public static UserData parse(String file) {
        UserData data = new UserData();
        data.filename = file;
        String line;
        // 0 = init values
        // 1 = scores
        // 2 = preferences
        int state = 0;
        KeyValuePair pair = null;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            // for each line in the file
            while ((line = br.readLine()) != null) {
                pair = KeyValuePair.splitLine(line);
                int newState = state;
                if (line.equals("")) {
                    continue;
                }

                switch (state) {
                    case 0:
                        if (line.equals("scores")) {
                            newState = 1;
                            break;
                        }

                        if (pair.key.equals("name")) {
                            data.name = pair.value;
                        }

                        break;
                    case 1:
                        if (line.equals("preferences")) {
                            newState = 2;
                            break;
                        }

                        if (pair == null) {
                            break;
                        }

                        UserDateScore newScore = new UserDateScore();
                        newScore.date = pair.key;
                        newScore.score = pair.valueAsInt();
                        data.scores.add(newScore);
                        break;
                    case 2:
                        if (pair == null) {
                            break;
                        }

                        UserPreferenceValue pref = new UserPreferenceValue();
                        pref.value = pair.value;
                        data.preferences.put(pair.key, pref);
                        break;
                }

                state = newState;
            }
        } catch (IOException e) {
            System.out.println("Something went wrong reading the file");
        }

        return data;
    }

    public void save() {
        try {
            PrintWriter writer = new PrintWriter(filename, "UTF-8");
            writer.println("name: " + name);
            writer.println("scores");

            for (UserDateScore score : scores) {
                writer.println(score.date + ":" + score.score);
            }

            writer.println("preferences");
            Iterator it = preferences.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                UserPreferenceValue value = (UserPreferenceValue)pair.getValue();
                writer.println(pair.getKey() + ":" + value.value);
                it.remove(); // avoids a ConcurrentModificationException
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Something went wrong reading the file");
        }
    }

}