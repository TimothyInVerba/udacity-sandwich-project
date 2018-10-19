package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.DetailActivity;
import com.udacity.sandwichclub.MainActivity;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {


    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich;

        JSONObject jsondwich;
        JSONObject jsonName;

        if (json != null && !json.equals("")) {
            try {
                jsondwich = new JSONObject(json);
                sandwich = new Sandwich();

                jsonName = jsondwich.getJSONObject("name");

                sandwich.setMainName(jsonName.getString("mainName"));

                ArrayList<String> aka = new ArrayList<>();
                JSONArray akaJsonArray = jsonName.getJSONArray("alsoKnownAs");
                if (akaJsonArray == null || akaJsonArray.length() == 0) {
                    sandwich.setAlsoKnownAs(null);
                } else {
                    for (int i = 0; i < akaJsonArray.length(); i++) {
                        aka.add(akaJsonArray.getString(i));
                    }
                    sandwich.setAlsoKnownAs(aka);
                }
                if (jsondwich.isNull("placeOfOrigin")) {
                    sandwich.setPlaceOfOrigin(null);
                } else {
                    sandwich.setPlaceOfOrigin(jsondwich.getString("placeOfOrigin"));
                }

                sandwich.setDescription(jsondwich.getString("description"));
                sandwich.setImage(jsondwich.getString("image"));

                ArrayList<String> ingredients = new ArrayList<>();

                if (jsondwich.isNull("ingredients")) {
                    sandwich.setIngredients(null);
                } else {
                    JSONArray ingredientsJsonArray = jsondwich.getJSONArray("ingredients");
                    for (int i = 0; i < ingredientsJsonArray.length(); i++) {
                        ingredients.add(ingredientsJsonArray.getString(i));
                    }
                    sandwich.setIngredients(ingredients);
                }


                return sandwich;

            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;

        }

    }
}

